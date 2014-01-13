package com.nasdaqomx.test.testlink;

import java.net.MalformedURLException;
import java.net.URL;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

import com.nasdaqomx.selenium.test.base.TestResult;
import com.nasdaqomx.selenium.test.base.TestResultStatus;

public class TestLinkService {

	private static final ExecutionType AUTOMATED = ExecutionType.AUTOMATED;
	private static final boolean GETSTEPINFO = true;
	private static final boolean OVERWRITE_TEST_RESULT = true;
	private TestLinkAPI api;
	private TestLinkConfig testLinkConfig;
	private boolean debug = false;

	public void setTestLinkConfig(TestLinkConfig testLinkConfig) {
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

	public AutomationTestCase[] getTestCasesForSuite() {
		TestCase[] testCases = api.getTestCasesForTestSuite(
				testLinkConfig.getSuiteId(), null, null);
		AutomationTestCase[] t = new AutomationTestCase[testCases.length];
		for (int i = 0; i < testCases.length; i++) {
			t[i] = convertTestCase(getTestCaseById(testCases[i].getId()));
		}
		return t;
	}

	public AutomationTestCase[] getTestCasesForPlan() {
		TestCase[] testCases = api.getTestCasesForTestPlan(
				testLinkConfig.getPlanId(), null, null, null, null, null, null,
				null, AUTOMATED, GETSTEPINFO, null);
		AutomationTestCase[] t = new AutomationTestCase[testCases.length];
		for (int i = 0; i < testCases.length; i++) {
			t[i] = convertTestCase(getTestCaseById(testCases[i].getId()));
		}
		return t;
	}

	private AutomationTestCase convertTestCase(TestCase testCase) {
		AutomationTestCase t = new AutomationTestCase(testCase);
		t.setAutomationKey(getAutomationKey(testCase));
		t.setInputData(getInputData(testCase));
		t.setOutputData(getOutputData(testCase));
		return t;
	}

	public AutomationTestCase getTestCase(Integer testCaseId) {
		return convertTestCase(getTestCaseById(testCaseId));
	}

	public Integer reportResult(Integer testCaseId, TestResult result) {
		if (TestResultStatus.INVALID.equals(result.getStatus())) {
			return null;
		} else {
			return api.reportTCResult(testCaseId, null,
					testLinkConfig.getPlanId(),
					ExecutionStatus.valueOf(result.getStatus().toString()),
					null, testLinkConfig.getBuild(), result.getMessage(),
					false, null, null, null, null, OVERWRITE_TEST_RESULT)
					.getExecutionId();
		}
	}

	public Attachment uploadExecutionAttachment(Integer executionId,
			String fileName, String fileContent) {
		return api.uploadExecutionAttachment(executionId, null, null, fileName,
				"image/png", fileContent);
	}

	public TestProject[] getTestProjects() {
		if (debug) {
			TestProject t = new TestProject();
			t.setId(1);
			t.setName("2");
			return new TestProject[] { t };
		}
		return api.getProjects();
	}

	public TestPlan[] getTestPlansForProject() {
		if (debug) {
			TestPlan testPlan = new TestPlan();
			testPlan.setId(1);
			testPlan.setName("asdasd");
			return new TestPlan[] { testPlan };
		}
		return api.getProjectTestPlans(testLinkConfig.getProjectId());
	}

	public Build[] getBuildsForPlan() {
		if (debug) {
			Build b = new Build();
			b.setId(1);
			b.setName("asd");
			return new Build[] { b };
		}
		return api.getBuildsForTestPlan(testLinkConfig.getPlanId());
	}

	public TestSuite[] getTestSuitesForPlan() {
		return api.getTestSuitesForTestPlan(testLinkConfig.getPlanId());
	}

	public TestSuite[] getTestSuitesForSuite() {
		return api.getTestSuitesForTestSuite(testLinkConfig.getSuiteId());
	}

	public TestPlan getTestPlanByName() {
		return api.getTestPlanByName(testLinkConfig.getPlanName(),
				testLinkConfig.getProjectName());
	}

	public TestProject getTestProjectByName() {
		return api.getTestProjectByName(testLinkConfig.getProjectName());
	}

	public Build createBuild() {
		return api.createBuild(testLinkConfig.getPlanId(),
				testLinkConfig.getBuild(), null);
	}

	public boolean checkDevKey(TestLinkAPI api, String devKey) {
		return api.checkDevKey(devKey);
	}

	private String getCustomFieldValue(TestCase testCase, String fieldName) {
		return api.getTestCaseCustomFieldDesignValue(testCase.getId(), null,
				testCase.getVersion(), testLinkConfig.getProjectId(),
				fieldName, null).getValue();
	}

	private String getAutomationKey(TestCase testCase) {
		return getCustomFieldValue(testCase,
				testLinkConfig.getAutomationKeyFieldName());
	}

	private String getInputData(TestCase testCase) {
		return getCustomFieldValue(testCase,
				testLinkConfig.getInputDataFieldName());
	}

	private String getOutputData(TestCase testCase) {
		return getCustomFieldValue(testCase,
				testLinkConfig.getOutputDataFieldName());

	}

	private TestCase getTestCaseById(Integer id) {
		return api.getTestCase(id, null, null);
	}
}
