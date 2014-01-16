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
		setAvailable(testJob.getTestConfig().getDriverType(), false);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Start Running");
				TestService testService = new TestService();
				TestResult result = testService.run(testJob.getTestConfig(),
						testJob.getAutomationKey(), testJob.getTestData());
				testJob.getCallback().finish(testJob, result);
				setAvailable(testJob.getTestConfig().getDriverType(), true);
				System.out.println("Complete");
			}
		});
		thread.start();
	}
}
