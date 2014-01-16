package com.nasdaqomx.test.selenium.base.job;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.nasdaqomx.test.selenium.base.TestConfig;
import com.nasdaqomx.test.selenium.base.TestData;
import com.nasdaqomx.test.selenium.base.TestResult;

@Service
public class TestJobManager {
	private ConcurrentLinkedQueue<TestJob> queue = new ConcurrentLinkedQueue<>();

	private TestJobRunner[] testJobRunnerList = new TestJobRunner[] { new SingleInstanceTestJobRunner(
			"1") };

	private List<TestJob> runningList = new LinkedList<TestJob>();

	public void push(String id, TestConfig testConfig, String automationKey,
			TestData testData, final TestJobCallback callback) {
		TestJob testJob = new TestJob();
		testJob.setId(id);
		testJob.setAutomationKey(automationKey);
		testJob.setCallback(new TestJobInnerCallback() {
			@Override
			public void finish(TestJob testJob, TestResult result) {
				runningList.remove(testJob);
				callback.finish(result);
			}
		});
		testJob.setTestConfig(testConfig);
		testJob.setTestData(testData);
		queue.add(testJob);
	}

	private boolean shutdown = false;

	public void stop() {
		this.shutdown = true;
	}

	public TestStatus getStatus(String id) {
		for (TestJob testJob : queue) {
			if (testJob.getId().equals(id)) {
				return TestStatus.WAITING;
			}
		}
		for (TestJob testJob : runningList) {
			if (testJob.getId().equals(id)) {
				return TestStatus.RUNNING;
			}
		}
		return TestStatus.NONE;
	}

	@PostConstruct
	public void start() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				shutdown = false;
				while (!shutdown) {
					if (!queue.isEmpty()) {
						TestJob testJob = queue.peek();
						for (TestJobRunner testJobRunner : testJobRunnerList) {
							if (testJobRunner.isAvailable(testJob
									.getTestConfig().getDriverType())) {
								testJobRunner.setTestJob(testJob);
								testJobRunner.run();
								queue.poll();
								runningList.add(testJob);
								break;
							}
						}

					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}
}
