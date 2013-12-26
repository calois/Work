package com.nasdaqomx.ldapconfig.test.testlink;

import java.util.List;
import java.util.Properties;

import br.eti.kinoshita.testlinkjavaapi.constants.ActionOnDuplicate;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestImportance;
import br.eti.kinoshita.testlinkjavaapi.model.CustomField;
import br.eti.kinoshita.testlinkjavaapi.model.Platform;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep;

import com.nasdaqomx.ldapconfig.test.base.TestUtils;

public class NasdaqTestCase {
	private TestCase testCase;

	private String testKey;
	private String data;
	private String expect;

	public String getTestKey() {
		return testKey;
	}

	public void setTestKey(String testKey) {
		this.testKey = testKey;
	}

	public String getData() {
		return data;
	}

	public Properties getDataProperties() {
		return TestUtils.toProperties(data);
	}

	public Properties getExceptProperties() {
		return TestUtils.toProperties(expect);
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	public Integer getParentId() {
		return testCase.getParentId();
	}

	public void setParentId(Integer parentId) {
		testCase.setParentId(parentId);
	}

	public Integer getVersionId() {
		return testCase.getVersionId();
	}

	public void setVersionId(Integer versionId) {
		testCase.setVersionId(versionId);
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

	public Integer getTestProjectId() {
		return testCase.getTestProjectId();
	}

	public void setTestProjectId(Integer testProjectId) {
		testCase.setTestProjectId(testProjectId);
	}

	public String getAuthorLogin() {
		return testCase.getAuthorLogin();
	}

	public void setAuthorLogin(String authorLogin) {
		testCase.setAuthorLogin(authorLogin);
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

	public Integer getExecutionOrder() {
		return testCase.getExecutionOrder();
	}

	public void setExecutionOrder(Integer executionOrder) {
		testCase.setExecutionOrder(executionOrder);
	}

	public ExecutionType getExecutionType() {
		return testCase.getExecutionType();
	}

	public void setExecutionType(ExecutionType executionType) {
		testCase.setExecutionType(executionType);
	}

	public Integer getOrder() {
		return testCase.getOrder();
	}

	public void setOrder(Integer order) {
		testCase.setOrder(order);
	}

	public Integer getInternalId() {
		return testCase.getInternalId();
	}

	public void setInternalId(Integer internalId) {
		testCase.setInternalId(internalId);
	}

	public String getFullExternalId() {
		return testCase.getFullExternalId();
	}

	public void setFullExternalId(String fullExternalId) {
		testCase.setFullExternalId(fullExternalId);
	}

	public Boolean getCheckDuplicatedName() {
		return testCase.getCheckDuplicatedName();
	}

	public void setCheckDuplicatedName(Boolean checkDuplicatedName) {
		testCase.setCheckDuplicatedName(checkDuplicatedName);
	}

	public ActionOnDuplicate getActionOnDuplicatedName() {
		return testCase.getActionOnDuplicatedName();
	}

	public void setActionOnDuplicatedName(
			ActionOnDuplicate actionOnDuplicatedName) {
		testCase.setActionOnDuplicatedName(actionOnDuplicatedName);
	}

	public List<CustomField> getCustomFields() {
		return testCase.getCustomFields();
	}

	public void setCustomFields(List<CustomField> customFields) {
		testCase.setCustomFields(customFields);
	}

	public ExecutionStatus getExecutionStatus() {
		return testCase.getExecutionStatus();
	}

	public void setExecutionStatus(ExecutionStatus executionStatus) {
		testCase.setExecutionStatus(executionStatus);
	}

	public Platform getPlatform() {
		return testCase.getPlatform();
	}

	public void setPlatform(Platform platform) {
		testCase.setPlatform(platform);
	}

	public Integer getFeatureId() {
		return testCase.getFeatureId();
	}

	public void setFeatureId(Integer featureId) {
		testCase.setFeatureId(featureId);
	}

	public NasdaqTestCase(TestCase testCase) {
		this.testCase = testCase;
	}
}
