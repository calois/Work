package com.nasdaqomx.test.selenium.base.job;

public abstract class AbstractTestJobRunner implements TestJobRunner {

	private TestJob testJob;

	private String id;

	public AbstractTestJobRunner(String id) {
		this.id = id;
	}

	@Override
	public synchronized void setTestJob(TestJob testJob) {
		this.testJob = testJob;
	}

	public synchronized TestJob getTestJob() {
		return testJob;
	}

	@Override
	public String getName() {
		return "Single Instance";
	}

	@Override
	public String getId() {
		return id;
	}
}
