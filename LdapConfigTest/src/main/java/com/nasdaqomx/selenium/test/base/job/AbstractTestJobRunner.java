package com.nasdaqomx.selenium.test.base.job;

import java.util.HashMap;
import java.util.Map;

import com.nasdaqomx.selenium.test.base.DriverType;

public abstract class AbstractTestJobRunner implements TestJobRunner {

	private TestJob testJob;

	private Map<DriverType, Boolean> availableMap = new HashMap<>();

	private String id;

	public AbstractTestJobRunner(String id) {
		this.id = id;
	}

	protected synchronized void setAvailable(DriverType type, boolean available) {
		availableMap.put(type, available);
	}

	@Override
	public synchronized void setTestJob(TestJob testJob) {
		this.testJob = testJob;
	}

	protected synchronized TestJob getTestJob() {
		return testJob;
	}

	@Override
	public synchronized boolean isAvailable(DriverType type) {
		return availableMap.containsKey(type) ? availableMap.get(type) : true;
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
