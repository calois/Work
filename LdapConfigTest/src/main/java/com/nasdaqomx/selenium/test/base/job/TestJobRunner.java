package com.nasdaqomx.selenium.test.base.job;

import com.nasdaqomx.selenium.test.base.DriverType;

public interface TestJobRunner {
	public void setTestJob(TestJob testJob);

	public void run();

	public boolean isAvailable(DriverType driverType);

	public String getName();

	public String getId();
}
