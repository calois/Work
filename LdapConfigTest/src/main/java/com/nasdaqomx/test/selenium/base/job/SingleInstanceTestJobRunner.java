package com.nasdaqomx.test.selenium.base.job;

import com.nasdaqomx.test.selenium.base.TestResult;
import com.nasdaqomx.test.selenium.base.TestService;

public class SingleInstanceTestJobRunner extends AbstractTestJobRunner {

	public SingleInstanceTestJobRunner(String id) {
		super(id);
	}

	@Override
	public void run() {
		final TestJob testJob = SingleInstanceTestJobRunner.this.getTestJob();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TestService testService = new TestService();
					TestResult result = testService.run(
							testJob.getTestConfig(),
							testJob.getAutomationKey(), testJob.getTestData());
					testJob.setResult(result);
					testJob.getCallback().finish(testJob);
				} catch (Throwable e) {
					e.printStackTrace();
				} finally {
					clear();
				}
			}
		});
		thread.start();
	}
}
