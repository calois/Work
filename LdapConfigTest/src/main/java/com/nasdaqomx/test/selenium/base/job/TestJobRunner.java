package com.nasdaqomx.test.selenium.base.job;

import com.nasdaqomx.test.selenium.base.DriverType;

public interface TestJobRunner {
	public void setTestJob(TestJob testJob);

	public void run();

	public boolean isAvailable(DriverType driverType);

	public String getName();

	public String getId();
}
