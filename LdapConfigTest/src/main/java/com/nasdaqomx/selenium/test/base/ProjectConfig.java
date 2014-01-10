package com.nasdaqomx.selenium.test.base;

import java.io.Serializable;

public class ProjectConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	private String baseUrl;

	private Project project;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
