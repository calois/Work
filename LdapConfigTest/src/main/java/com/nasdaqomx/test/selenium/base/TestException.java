package com.nasdaqomx.test.selenium.base;

public class TestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Project project;

	public Project getProject() {
		return project;
	}

	public TestException(Project project, String message) {
		super(message);
		this.project = project;
	}

	public TestException(Project project, String message, Throwable cause) {
		super(message, cause);
		this.project = project;
	}

	public TestException(Project project, Throwable cause) {
		super(cause);
		this.project = project;
	}
}
