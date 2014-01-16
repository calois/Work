package com.nasdaqomx.test.selenium.base.job;

import com.nasdaqomx.test.selenium.base.TestResult;

interface TestJobInnerCallback {
	public void finish(TestJob testJob, TestResult result);
}
