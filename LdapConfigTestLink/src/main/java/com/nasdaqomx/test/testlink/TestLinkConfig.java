package com.nasdaqomx.test.testlink;

import java.net.URL;

public class TestLinkConfig {
	private URL url;
	private String devKey;
	private String automationKeyFieldName;
	private String inputDataFieldName;
	private String outputDataFieldName;
	private Integer projectId;
	private Integer PlanId;
	private String build;
	
	public void setUrl(URL url) {
		this.url = url;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getPlanId() {
		return PlanId;
	}

	public void setPlanId(Integer planId) {
		PlanId = planId;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public URL getUrl() {
		return url;
	}

	public String getDevKey() {
		return devKey;
	}

	public String getAutomationKeyFieldName() {
		return automationKeyFieldName;
	}

	public String getInputDataFieldName() {
		return inputDataFieldName;
	}

	public String getOutputDataFieldName() {
		return outputDataFieldName;
	}

	public void setDevKey(String devKey) {
		this.devKey = devKey;
	}

	public void setAutomationKeyFieldName(String name) {
		this.automationKeyFieldName = name;
	}

	public void setInputDataFieldName(String name) {
		this.inputDataFieldName = name;
	}

	public void setOutputDataFieldName(String name) {
		this.outputDataFieldName = name;
	}
}
