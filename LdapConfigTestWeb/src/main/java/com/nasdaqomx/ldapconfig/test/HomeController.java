package com.nasdaqomx.ldapconfig.test;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;

import com.nasdaqomx.ldapconfig.test.base.DriverType;
import com.nasdaqomx.ldapconfig.test.base.TestData;
import com.nasdaqomx.ldapconfig.test.base.TestResult;
import com.nasdaqomx.ldapconfig.test.base.TestService;
import com.nasdaqomx.ldapconfig.test.testlink.AutomationTestCase;
import com.nasdaqomx.ldapconfig.test.testlink.TestLinkService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private TestLinkService testLinkService;

	@Autowired
	private TestService testService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("testProjects", testLinkService.getTestProjects());
		model.addAttribute("testPlans",
				testLinkService.getTestPlansForProject(2));
		return "home";
	}

	@RequestMapping(value = "/{testProjectId}/testPlan", method = RequestMethod.GET)
	@ResponseBody
	public TestPlan[] getTestPlans(@PathVariable Integer testProjectId,
			Model model) {
		return testLinkService.getTestPlansForProject(testProjectId);
	}

	@RequestMapping(value = "/{testPlanId}/build", method = RequestMethod.GET)
	@ResponseBody
	public Build[] getBuilds(@PathVariable Integer testPlanId, Model model) {
		return testLinkService.getBuildsForPlan(testPlanId);
	}

	@RequestMapping(value = "/executeTestCases", method = RequestMethod.GET)
	public String executeTestCases(@RequestParam String browserType,
			@RequestParam String url, @RequestParam String projectId,
			@RequestParam String planId, @RequestParam String build, Model model) {
		Integer testProjectId = Integer.valueOf(projectId);
		Integer testPlanId = Integer.valueOf(planId);
		testService.getProp().setProperty("baseUrl", url);
		AutomationTestCase[] testCases = testLinkService.getTestCasesForPlan(
				testPlanId, testProjectId);
		if (null != testCases) {
			boolean hasBuild = false;
			Build[] builds = testLinkService.getBuildsForPlan(testPlanId);
			for (int i = 0; i < builds.length; i++) {
				if (build.equals(builds[i].getName())) {
					hasBuild = true;
					break;
				}
			}
			if (!hasBuild) {
				testLinkService.createBuild(testPlanId, build);
			}
			for (int i = 0; i < testCases.length; i++) {
				TestResult result = testService.test(DriverType
						.valueOf(browserType), testCases[i].getAutomationKey(),
						new TestData(testCases[i].getInputDataProperties(),
								testCases[i].getOutputProperties()));
				testCases[i].setTestResult(result);
				Integer executionId = testLinkService.reportResult(
						testCases[i].getId(), Integer.valueOf(planId), build,
						result);
				if (null != result.getScreenshot()) {
					testLinkService.uploadExecutionAttachment(executionId,
							"Screenshot_" + System.currentTimeMillis(),
							result.getScreenshot());
				}
			}
		}
		model.addAttribute("testCases", testCases);
		return "testCase";
	}

	@RequestMapping(value = "/{testProjectId}/testCase/{testCaseId}", method = RequestMethod.GET)
	public String runTestCase(@PathVariable Integer testProjectId,
			@PathVariable Integer testCaseId, Model model) {
		AutomationTestCase testCase = testLinkService.getTestCase(testCaseId,
				testProjectId);
		TestResult result = testService.test(
				DriverType.CHROME,
				testCase.getAutomationKey(),
				new TestData(testCase.getInputDataProperties(), testCase
						.getOutputProperties()));
		model.addAttribute("result", result);
		return "testCaseResult";
	}
}
