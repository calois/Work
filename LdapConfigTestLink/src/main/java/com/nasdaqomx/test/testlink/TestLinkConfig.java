package com.nasdaqomx.test.testlink;

public class TestLinkConfig {

	private static final String XMLRPC_URL = "lib/api/xmlrpc/v1/xmlrpc.php";

	private String baseUrl;
	private String devKey;
	private String automationKeyFieldName = "Automation Key";
	private String inputDataFieldName = "Input Data";
	private String outputDataFieldName = "Output Data";
	private Integer projectId;
	private String projectName;
	private Integer planId;
	private Integer suiteId;
	private String planName;
	private String build;
	private String platform;

	public String getAutomationKeyFieldName() {
		return automationKeyFieldName;
	}

	public void setAutomationKeyFieldName(String automationKeyFieldName) {
		this.automationKeyFieldName = automationKeyFieldName;
	}

	public String getInputDataFieldName() {
		return inputDataFieldName;
	}

	public void setInputDataFieldName(String inputDataFieldName) {
		this.inputDataFieldName = inputDataFieldName;
	}

	public String getOutputDataFieldName() {
		return outputDataFieldName;
	}

	public void setOutputDataFieldName(String outputDataFieldName) {
		this.outputDataFieldName = outputDataFieldName;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(Integer suiteId) {
		this.suiteId = suiteId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getBuild() {
		return build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getUrl() {
		return baseUrl.endsWith("/") ? baseUrl.concat(XMLRPC_URL) : baseUrl
				.concat("/").concat(XMLRPC_URL);
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getDevKey() {
		return devKey;
	}

	public void setDevKey(String devKey) {
		this.devKey = devKey;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
