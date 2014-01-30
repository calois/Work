package com.nasdaqomx.test.selenium.base.job;

public interface TestJobRunner {
	public TestJob getTestJob();

	public void run();

	public boolean accept(TestJob testJob);

	public String getName();

	public String getId();
}
