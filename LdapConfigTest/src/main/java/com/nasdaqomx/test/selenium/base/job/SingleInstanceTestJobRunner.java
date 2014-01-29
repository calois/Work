package com.nasdaqomx.test.selenium.base.job;

import com.nasdaqomx.test.selenium.base.DriverType;
import com.nasdaqomx.test.selenium.base.TestResult;
import com.nasdaqomx.test.selenium.base.TestService;

public class SingleInstanceTestJobRunner extends AbstractTestJobRunner {

	private DriverType supportDriverType;

	public SingleInstanceTestJobRunner(DriverType supportDriverType, String id) {
		super(id);
		this.supportDriverType = supportDriverType;
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
					setTestJob(null);
				}
			}
		});
		thread.start();
	}

	@Override
	public boolean accept(TestJob testJob) {
		if (this.getTestJob() != null
				|| supportDriverType != testJob.getTestConfig().getDriverType()) {
			return false;
		}
		return true;
	}
}
