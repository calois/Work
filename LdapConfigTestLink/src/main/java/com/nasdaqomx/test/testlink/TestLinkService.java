package com.nasdaqomx.test.testlink;

import java.net.URL;

import javax.annotation.PostConstruct;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;

import com.nasdaqomx.selenium.test.base.TestResult;
import com.nasdaqomx.selenium.test.base.TestResultStatus;

public class TestLinkService {

	private static final ExecutionType AUTOMATED = ExecutionType.AUTOMATED;
	private static final boolean GETSTEPINFO = true;
	private static final boolean OVERWRITE_TEST_RESULT = true;
	private TestLinkAPI api;

	private URL testLinkUrl;
	private String devKey;
	private String automationKeyFieldName;
	private String inputDataFieldName;
	private String outputDataFieldName;

	private boolean debug = false;

	@PostConstruct
	public void init() {
		if (!debug) {
			api = new TestLinkAPI(testLinkUrl, devKey);
		}
	}

	public void setUrl(URL url) {
		this.testLinkUrl = url;
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

	public AutomationTestCase[] getTestCasesForSuite(Integer testSuiteId,
			Integer testProjectId) {
		TestCase[] testCases = api.getTestCasesForTestSuite(testSuiteId, null,
				null);
		AutomationTestCase[] t = new AutomationTestCase[testCases.length];
		for (int i = 0; i < testCases.length; i++) {
			TestCase testCase = getTestCaseById(testCases[i].getId());
			t[i] = convertTestCase(testCase, testProjectId);
		}
		return t;
	}

	public AutomationTestCase[] getTestCasesForPlan(Integer testPlanId,
			Integer testProjectId) {
		TestCase[] testCases = api.getTestCasesForTestPlan(testPlanId, null,
				null, null, null, null, null, null, AUTOMATED, GETSTEPINFO,
				null);
		AutomationTestCase[] t = new AutomationTestCase[testCases.length];
		for (int i = 0; i < testCases.length; i++) {
			t[i] = convertTestCase(testCases[i], testProjectId);
		}
		return t;
	}

	private AutomationTestCase convertTestCase(TestCase testCase,
			Integer testProjectId) {
		AutomationTestCase t = new AutomationTestCase(testCase);
		t.setAutomationKey(getAutomationKey(testCase, testProjectId));
		t.setInputData(getInputData(testCase, testProjectId));
		t.setOutputData(getOutputData(testCase, testProjectId));
		return t;
	}

	public AutomationTestCase getTestCase(Integer testCaseId,
			Integer testProjectId) {
		return convertTestCase(getTestCaseById(testCaseId), testProjectId);
	}

	public String getAutomationKey(TestCase testCase, Integer testProjectId) {
		return getCustomFieldValue(testCase, automationKeyFieldName,
				testProjectId);
	}

	public String getInputData(TestCase testCase, Integer testProjectId) {
		return getCustomFieldValue(testCase, inputDataFieldName, testProjectId);
	}

	public String getOutputData(TestCase testCase, Integer testProjectId) {
		return getCustomFieldValue(testCase, outputDataFieldName, testProjectId);

	}

	public Integer reportResult(Integer testCaseId, Integer testPlanId,
			String buildName, TestResult result) {
		if (TestResultStatus.INVALID.equals(result.getStatus())) {
			return null;
		} else {
			return api.reportTCResult(testCaseId, null, testPlanId,
					ExecutionStatus.valueOf(result.getStatus().toString()),
					null, buildName, result.getMessage(), false, null, null,
					null, null, OVERWRITE_TEST_RESULT).getExecutionId();
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

	public TestPlan[] getTestPlansForProject(Integer testProjectId) {
		if (debug) {
			TestPlan testPlan = new TestPlan();
			testPlan.setId(1);
			testPlan.setName("asdasd");
			return new TestPlan[] { testPlan };
		}
		return api.getProjectTestPlans(testProjectId);
	}

	public Build[] getBuildsForPlan(Integer testPlanId) {
		if (debug) {
			Build b = new Build();
			b.setId(1);
			b.setName("asd");
			return new Build[] { b };
		}
		return api.getBuildsForTestPlan(testPlanId);
	}

	public TestSuite[] getTestSuitesForPlan(Integer testPlanId) {
		return api.getTestSuitesForTestPlan(testPlanId);
	}

	public TestSuite[] getTestSuitesForSuite(Integer testSuiteId) {
		return api.getTestSuitesForTestSuite(testSuiteId);
	}

	public TestPlan getTestPlanByName(String projectName, String planName) {
		return api.getTestPlanByName(planName, projectName);
	}

	public TestProject getTestProjectByName(String projectName) {
		return api.getTestProjectByName(projectName);
	}

	public Build createBuild(Integer testPlanId, String buildName) {
		return api.createBuild(testPlanId, buildName, null);
	}

	public String getCustomFieldValue(TestCase testCase, String fieldName,
			Integer testProjectId) {
		return api.getTestCaseCustomFieldDesignValue(testCase.getId(), null,
				testCase.getVersion(), testProjectId, fieldName, null)
				.getValue();
	}

	private TestCase getTestCaseById(Integer id) {
		return api.getTestCase(id, null, null);
	}
}
