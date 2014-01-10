package com.nasdaqomx.test.testlink;

import java.net.MalformedURLException;
import java.net.URL;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

public class TestLinkManager {

	private TestLinkAPI api;
	private TestLinkConfig testLinkConfig;

	private boolean debug = false;

	public TestLinkManager(TestLinkConfig testLinkConfig) {
		this.testLinkConfig = testLinkConfig;
		if (!debug) {
			try {
				this.api = new TestLinkAPI(new URL(testLinkConfig.getUrl()),
						testLinkConfig.getDevKey());
			} catch (TestLinkAPIException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	public TestLinkAPI getApi() {
		return api;
	}

	public String getAutomationKeyFieldName() {
		return testLinkConfig.getAutomationKeyFieldName();
	}

	public void setAutomationKeyFieldName(String automationKeyFieldName) {
		testLinkConfig.setAutomationKeyFieldName(automationKeyFieldName);
	}

	public String getInputDataFieldName() {
		return testLinkConfig.getInputDataFieldName();
	}

	public void setInputDataFieldName(String inputDataFieldName) {
		testLinkConfig.setInputDataFieldName(inputDataFieldName);
	}

	public String getOutputDataFieldName() {
		return testLinkConfig.getOutputDataFieldName();
	}

	public void setOutputDataFieldName(String outputDataFieldName) {
		testLinkConfig.setOutputDataFieldName(outputDataFieldName);
	}

	public Integer getProjectId() {
		return testLinkConfig.getProjectId();
	}

	public void setProjectId(Integer projectId) {
		testLinkConfig.setProjectId(projectId);
	}

	public String getProjectName() {
		return testLinkConfig.getProjectName();
	}

	public void setProjectName(String projectName) {
		testLinkConfig.setProjectName(projectName);
	}

	public Integer getPlanId() {
		return testLinkConfig.getPlanId();
	}

	public void setPlanId(Integer planId) {
		testLinkConfig.setPlanId(planId);
	}

	public Integer getSuiteId() {
		return testLinkConfig.getSuiteId();
	}

	public void setSuiteId(Integer suiteId) {
		testLinkConfig.setSuiteId(suiteId);
	}

	public String getPlanName() {
		return testLinkConfig.getPlanName();
	}

	public void setPlanName(String planName) {
		testLinkConfig.setPlanName(planName);
	}

	public String getBuild() {
		return testLinkConfig.getBuild();
	}

	public void setBuild(String build) {
		testLinkConfig.setBuild(build);
	}

	public String getUrl() {
		return testLinkConfig.getUrl();
	}

	public String getBaseUrl() {
		return testLinkConfig.getBaseUrl();
	}

	public void setBaseUrl(String baseUrl) {
		testLinkConfig.setBaseUrl(baseUrl);
	}

	public String getDevKey() {
		return testLinkConfig.getDevKey();
	}

	public void setDevKey(String devKey) {
		testLinkConfig.setDevKey(devKey);
	}

}
