package com.nasdaqomx.ldapconfig.test.testlink;

import java.net.URL;

import javax.annotation.PostConstruct;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseDetails;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;

import com.nasdaqomx.ldapconfig.test.base.TestResult;

public class TestLinkService {
	private URL url;
	private String devKey;
	private TestLinkAPI api;
	private boolean debug = true;

	@PostConstruct
	public void init() {
		if (debug) {
			return;
		}
		api = new TestLinkAPI(url, devKey);
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public void setDevKey(String devKey) {
		this.devKey = devKey;
	}

	public TestPlan getTestPlanByName(String projectName, String planName) {
		if (debug) {
			TestPlan t = new TestPlan();
			t.setId(1);
			t.setName("testPlan");
			t.setActive(true);
			t.setNotes("notes");
			t.setProjectName("project");
			t.setPublic(true);
			return t;
		}
		return api.getTestPlanByName(planName, projectName);
	}

	public NasdaqTestCase[] getTestCasesForTestPlan(Integer testPlanId) {
		if (debug) {
			TestCase t = new TestCase();
			t.setId(1);
			t.setName("testCase1");
			return new NasdaqTestCase[] { convertTestCase(t) };
		}

		TestCase[] testCases = api.getTestCasesForTestPlan(testPlanId, null,
				null, null, null, null, null, null, ExecutionType.AUTOMATED,
				true, TestCaseDetails.FULL);
		NasdaqTestCase[] t = new NasdaqTestCase[testCases.length];
		for (int i = 0; i < testCases.length; i++) {
			t[i] = convertTestCase(testCases[i]);
		}
		return t;
	}

	private NasdaqTestCase convertTestCase(TestCase testCase) {
		NasdaqTestCase t = new NasdaqTestCase(testCase);
		t.setTestKey(getTestKey(testCase));
		t.setData(getTestData(testCase));
		t.setExpect(getExpectData(testCase));
		return t;
	}

	public NasdaqTestCase getTestCase(Integer testCaseId) {
		if (debug) {
			TestCase t = new TestCase();
			t.setId(1);
			t.setName("testCase1");
			return convertTestCase(t);
		}
		return convertTestCase(api.getTestCase(testCaseId, null, null));
	}

	public Build[] getBuildsForTestPlan(Integer testPlanId) {
		if (debug) {
			Build[] b = new Build[1];
			b[0].setId(1);
			b[0].setName("Build");
			b[0].setNotes("Notes");
			return b;
		}
		return api.getBuildsForTestPlan(testPlanId);
	}

	public Build createBuild(Integer testPlanId, String buildName) {
		if (debug) {
			Build b = new Build();
			b.setId(1);
			b.setName(buildName);
			b.setNotes("Notes");
			return b;
		}
		return api.createBuild(testPlanId, buildName, null);
	}

	public String getCustomFieldValue(TestCase testCase, String fieldName) {
		if (debug) {
			return "data";
		}
		return api.getTestCaseCustomFieldDesignValue(testCase.getId(),
				Integer.valueOf(testCase.getFullExternalId()),
				testCase.getVersion(), testCase.getTestProjectId(), fieldName,
				null).getValue();
	}

	public String getTestData(TestCase testCase) {
		if (debug) {
			return "test1=123123\n\rtest2=34567";
		}
		return getCustomFieldValue(testCase, "testData");
	}

	public String getTestKey(TestCase testCase) {
		if (debug) {
			return "com.nasdaqomx.ldapconfig.test.cases.LoginTest.testLogin";
		}
		return getCustomFieldValue(testCase, "testKey");
	}

	public String getExpectData(TestCase testCase) {
		if (debug) {
			return "test1=123123\n\rtest2=34567";
		}
		return getCustomFieldValue(testCase, "expectData");

	}

	public void report(Integer testCaseId, Integer testPlanId,
			String buildName, TestResult result) {
		if (debug) {
			return;
		}
		api.reportTCResult(testCaseId, null, testPlanId,
				ExecutionStatus.FAILED, null, buildName, result.getMessage(),
				false, null, null, null, null, true);
	}
}
