package com.nasdaqomx.test.testlink;

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
	
	private boolean debug = false;

	public AutomationTestCase[] getTestCasesForSuite(
			TestLinkManager testLinkManager) {
		TestLinkAPI api = testLinkManager.getApi();
		TestCase[] testCases = api.getTestCasesForTestSuite(
				testLinkManager.getSuiteId(), null, null);
		AutomationTestCase[] t = new AutomationTestCase[testCases.length];
		for (int i = 0; i < testCases.length; i++) {
			t[i] = convertTestCase(getTestCaseById(testCases[i].getId(), api),
					testLinkManager);
		}
		return t;
	}

	public AutomationTestCase[] getTestCasesForPlan(
			TestLinkManager testLinkManager) {
		TestLinkAPI api = testLinkManager.getApi();
		TestCase[] testCases = api.getTestCasesForTestPlan(
				testLinkManager.getPlanId(), null, null, null, null, null,
				null, null, AUTOMATED, GETSTEPINFO, null);
		AutomationTestCase[] t = new AutomationTestCase[testCases.length];
		for (int i = 0; i < testCases.length; i++) {
			t[i] = convertTestCase(getTestCaseById(testCases[i].getId(), api),
					testLinkManager);
		}
		return t;
	}

	private AutomationTestCase convertTestCase(TestCase testCase,
			TestLinkManager testLinkManager) {
		AutomationTestCase t = new AutomationTestCase(testCase);
		t.setAutomationKey(getAutomationKey(testCase, testLinkManager));
		t.setInputData(getInputData(testCase, testLinkManager));
		t.setOutputData(getOutputData(testCase, testLinkManager));
		return t;
	}

	public AutomationTestCase getTestCase(Integer testCaseId,
			TestLinkManager testLinkManager) {
		return convertTestCase(
				getTestCaseById(testCaseId, testLinkManager.getApi()),
				testLinkManager);
	}

	public Integer reportResult(Integer testCaseId,
			TestLinkManager testLinkManager, TestResult result) {
		if (TestResultStatus.INVALID.equals(result.getStatus())) {
			return null;
		} else {
			return testLinkManager
					.getApi()
					.reportTCResult(
							testCaseId,
							null,
							testLinkManager.getPlanId(),
							ExecutionStatus.valueOf(result.getStatus()
									.toString()), null,
							testLinkManager.getBuild(), result.getMessage(),
							false, null, null, null, null,
							OVERWRITE_TEST_RESULT).getExecutionId();
		}
	}

	public Attachment uploadExecutionAttachment(Integer executionId,
			String fileName, String fileContent, TestLinkAPI api) {
		return api.uploadExecutionAttachment(executionId, null, null, fileName,
				"image/png", fileContent);
	}

	public TestProject[] getTestProjects(TestLinkAPI api) {
		if (debug) {
			TestProject t = new TestProject();
			t.setId(1);
			t.setName("2");
			return new TestProject[] { t };
		}
		return api.getProjects();
	}

	public TestPlan[] getTestPlansForProject(TestLinkManager testLinkManager) {
		if (debug) {
			TestPlan testPlan = new TestPlan();
			testPlan.setId(1);
			testPlan.setName("asdasd");
			return new TestPlan[] { testPlan };
		}
		return testLinkManager.getApi().getProjectTestPlans(
				testLinkManager.getProjectId());
	}

	public Build[] getBuildsForPlan(TestLinkManager testLinkManager) {
		if (debug) {
			Build b = new Build();
			b.setId(1);
			b.setName("asd");
			return new Build[] { b };
		}
		return testLinkManager.getApi().getBuildsForTestPlan(
				testLinkManager.getPlanId());
	}

	public TestSuite[] getTestSuitesForPlan(TestLinkManager testLinkManager) {
		return testLinkManager.getApi().getTestSuitesForTestPlan(
				testLinkManager.getPlanId());
	}

	public TestSuite[] getTestSuitesForSuite(TestLinkManager testLinkManager) {
		return testLinkManager.getApi().getTestSuitesForTestSuite(
				testLinkManager.getSuiteId());
	}

	public TestPlan getTestPlanByName(TestLinkManager testLinkManager) {
		return testLinkManager.getApi()
				.getTestPlanByName(testLinkManager.getPlanName(),
						testLinkManager.getProjectName());
	}

	public TestProject getTestProjectByName(TestLinkManager testLinkManager) {
		return testLinkManager.getApi().getTestProjectByName(
				testLinkManager.getProjectName());
	}

	public Build createBuild(TestLinkManager testLinkManager) {
		return testLinkManager.getApi().createBuild(
				testLinkManager.getPlanId(), testLinkManager.getBuild(), null);
	}

	public boolean checkDevKey(TestLinkAPI api, String devKey){
		return api.checkDevKey(devKey);
	}
	
	private String getCustomFieldValue(TestCase testCase, String fieldName,
			TestLinkManager testLinkManager) {
		return testLinkManager
				.getApi()
				.getTestCaseCustomFieldDesignValue(testCase.getId(), null,
						testCase.getVersion(), testLinkManager.getProjectId(),
						fieldName, null).getValue();
	}

	private String getAutomationKey(TestCase testCase,
			TestLinkManager testLinkManager) {
		return getCustomFieldValue(testCase,
				testLinkManager.getAutomationKeyFieldName(), testLinkManager);
	}

	private String getInputData(TestCase testCase,
			TestLinkManager testLinkManager) {
		return getCustomFieldValue(testCase,
				testLinkManager.getInputDataFieldName(), testLinkManager);
	}

	private String getOutputData(TestCase testCase,
			TestLinkManager testLinkManager) {
		return getCustomFieldValue(testCase,
				testLinkManager.getOutputDataFieldName(), testLinkManager);

	}

	private TestCase getTestCaseById(Integer id, TestLinkAPI api) {
		return api.getTestCase(id, null, null);
	}
}
