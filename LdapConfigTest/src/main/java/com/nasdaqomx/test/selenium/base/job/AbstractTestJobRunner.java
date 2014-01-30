package com.nasdaqomx.test.selenium.base.job;

public abstract class AbstractTestJobRunner implements TestJobRunner {

	private TestJob testJob;

	private String id;

	public AbstractTestJobRunner(String id) {
		this.id = id;
	}

	protected synchronized void clear() {
		this.testJob = null;
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

	@Override
	public synchronized boolean accept(TestJob testJob) {
		if (this.getTestJob() != null) {
			return false;
		}
		this.testJob = testJob;
		return true;
	}
}
