package com.nasdaqomx.test.selenium.base.job;

public interface TestJobRunner {
	public void setTestJob(TestJob testJob);

	public TestJob getTestJob();

	public void run();

	public boolean accept(TestJob testJob);

	public String getName();

	public String getId();
}
