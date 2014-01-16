package com.nasdaqomx.selenium.test.base.job;

import com.nasdaqomx.selenium.test.base.TestResult;

interface TestJobInnerCallback {
	public void finish(TestJob testJob, TestResult result);
}
