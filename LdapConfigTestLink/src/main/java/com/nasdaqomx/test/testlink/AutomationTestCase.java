package com.nasdaqomx.test.testlink;

import java.util.List;
import java.util.Properties;

import br.eti.kinoshita.testlinkjavaapi.constants.TestImportance;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep;

import com.nasdaqomx.test.selenium.base.TestResult;
import com.nasdaqomx.test.selenium.base.TestUtils;
import com.nasdaqomx.test.selenium.base.job.TestStatus;

public class AutomationTestCase {
	private TestCase testCase;

	private String automationKey;
	private String inputData;
	private String outputData;
	private TestResult result;
	private TestStatus status;

	public TestStatus getStatus() {
		return status;
	}

	public void setStatus(TestStatus status) {
		this.status = status;
	}

	public AutomationTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public String getAutomationKey() {
		return automationKey;
	}

	public void setAutomationKey(String automationKey) {
		this.automationKey = automationKey;
	}

	public String getInputData() {
		return inputData;
	}

	public Properties getInputDataProperties() {
		return TestUtils.toProperties(inputData);
	}

	public void setInputData(String data) {
		this.inputData = data;
	}

	public String getOutputData() {
		return outputData;
	}

	public Properties getOutputProperties() {
		return TestUtils.toProperties(outputData);
	}

	public void setOutputData(String data) {
		this.outputData = data;
	}

	public Integer getVersion() {
		return testCase.getVersion();
	}

	public void setVersion(Integer version) {
		testCase.setVersion(version);
	}

	public Integer getId() {
		return testCase.getId();
	}

	public void setId(Integer id) {
		testCase.setId(id);
	}

	public String getName() {
		return testCase.getName();
	}

	public void setName(String name) {
		testCase.setName(name);
	}

	public Integer getTestSuiteId() {
		return testCase.getTestSuiteId();
	}

	public void setTestSuiteId(Integer testSuiteId) {
		testCase.setTestSuiteId(testSuiteId);
	}

	public String getSummary() {
		return testCase.getSummary();
	}

	public void setSummary(String summary) {
		testCase.setSummary(summary);
	}

	public List<TestCaseStep> getSteps() {
		return testCase.getSteps();
	}

	public void setSteps(List<TestCaseStep> steps) {
		testCase.setSteps(steps);
	}

	public String getPreconditions() {
		return testCase.getPreconditions();
	}

	public void setPreconditions(String preconditions) {
		testCase.setPreconditions(preconditions);
	}

	public TestImportance getTestImportance() {
		return testCase.getTestImportance();
	}

	public void setTestImportance(TestImportance testImportance) {
		testCase.setTestImportance(testImportance);
	}

	public String getExternalId() {
		return testCase.getFullExternalId();
	}

	public void setExternalId(String externalId) {
		testCase.setFullExternalId(externalId);
	}

	public TestResult getTestResult() {
		return result;
	}

	public void setTestResult(TestResult result) {
		this.result = result;
	}
}
