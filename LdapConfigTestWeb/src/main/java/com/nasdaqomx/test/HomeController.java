package com.nasdaqomx.test;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;

import com.nasdaqomx.selenium.test.base.DriverType;
import com.nasdaqomx.selenium.test.base.TestApp;
import com.nasdaqomx.selenium.test.base.TestAppObject;
import com.nasdaqomx.selenium.test.base.TestData;
import com.nasdaqomx.selenium.test.base.TestObject;
import com.nasdaqomx.selenium.test.base.TestResult;
import com.nasdaqomx.selenium.test.base.TestService;
import com.nasdaqomx.test.testlink.AutomationTestCase;
import com.nasdaqomx.test.testlink.TestLinkService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("testObject")
public class HomeController {

	@Autowired
	private TestLinkService testLinkService;

	@Autowired
	private TestService testService;

	@ModelAttribute("testObject")
	public TestObject produceTestObject() {
		TestObject testObject = new TestObject();
		TestAppObject appObj = new TestAppObject();
		appObj.setApp(TestApp.LDAP_CONFIG);
		testObject.getAppObj().put(appObj.getApp(), appObj);
		return testObject;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			@ModelAttribute("testObject") TestObject testObject) {
		model.addAttribute("testProjects", testLinkService.getTestProjects());
		model.addAttribute("testPlans",
				testLinkService.getTestPlansForProject(2));
		model.addAttribute("explicitWait", testObject.getExplicitWait());
		model.addAttribute("implicitWait", testObject.getImplicitWait());
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

	@RequestMapping(value = "/runTestCases", method = RequestMethod.GET)
	public String runTestCases(@RequestParam DriverType browserType,
			@RequestParam Long explicitWait, @RequestParam Long implicitWait,
			@RequestParam String url, @RequestParam Integer projectId,
			@RequestParam Integer planId,
			@RequestParam(required = false) String build, Model model,
			@ModelAttribute("testObject") TestObject testObject) {
		//TODO: Need to set for different APP
		testObject.getAppObj().get(TestApp.LDAP_CONFIG).setBaseUrl(url);
		testObject.setDriverType(browserType);
		testObject.setExplicitWait(explicitWait);
		testObject.setImplicitWait(implicitWait);

		AutomationTestCase[] testCases = testLinkService.getTestCasesForPlan(
				planId, projectId);
		if (null != testCases) {
			createTestLinkBuild(build, planId);
			for (int i = 0; i < testCases.length; i++) {
				testCases[i].setTestResult(runTestCase(testObject,
						testCases[i], planId, build));
			}
		}
		model.addAttribute("testCases", testCases);
		model.addAttribute("projectId", projectId);
		model.addAttribute("planId", planId);
		model.addAttribute("build", build);
		return "testList";
	}

	@RequestMapping(value = "/viewTestCases", method = RequestMethod.GET)
	public String viewTestCases(@RequestParam DriverType browserType,
			@RequestParam String url, @RequestParam Integer projectId,
			@RequestParam Integer planId, @RequestParam String build,
			Model model, @ModelAttribute("testObject") TestObject testObject) {
		//TODO: Need to set for different APP
		testObject.getAppObj().get(TestApp.LDAP_CONFIG).setBaseUrl(url);
		testObject.setDriverType(browserType);

		AutomationTestCase[] testCases = testLinkService.getTestCasesForPlan(
				planId, projectId);
		model.addAttribute("testCases", testCases);
		model.addAttribute("projectId", projectId);
		model.addAttribute("planId", planId);
		model.addAttribute("build", build);
		return "testList";
	}

	@RequestMapping(value = "/{projectId}/{planId}/{build}/testCase/{testCaseId}", method = RequestMethod.GET)
	@ResponseBody
	public AutomationTestCase runTestCase(@PathVariable Integer projectId,
			@PathVariable Integer planId, @PathVariable String build,
			@PathVariable Integer testCaseId, Model model,
			@ModelAttribute("testObject") TestObject testObject) {
		AutomationTestCase testCase = testLinkService.getTestCase(testCaseId,
				projectId);
		createTestLinkBuild(build, planId);
		testCase.setTestResult(runTestCase(testObject, testCase, planId, build));
		return testCase;
	}

	@RequestMapping(value = "/{projectId}/{planId}/{build}/testCases", method = RequestMethod.GET)
	public String runTestCases(@PathVariable Integer projectId,
			@PathVariable Integer planId, @PathVariable String build,
			Model model, @ModelAttribute("testObject") TestObject testObject) {
		AutomationTestCase[] testCases = testLinkService.getTestCasesForPlan(
				planId, projectId);
		if (null != testCases) {
			createTestLinkBuild(build, planId);
			for (int i = 0; i < testCases.length; i++) {
				testCases[i].setTestResult(runTestCase(testObject,
						testCases[i], planId, build));
			}
		}
		model.addAttribute("testCases", testCases);
		model.addAttribute("projectId", projectId);
		model.addAttribute("planId", planId);
		model.addAttribute("build", build);
		return "testList";
	}

	private void createTestLinkBuild(String build, Integer testPlanId) {
		boolean hasBuild = false;
		Build[] builds = testLinkService.getBuildsForPlan(testPlanId);
		if (null != builds) {
			for (int i = 0; i < builds.length; i++) {
				if (build.equals(builds[i].getName())) {
					hasBuild = true;
					break;
				}
			}
		}
		if (!hasBuild) {
			testLinkService.createBuild(testPlanId, build);
		}
	}

	private TestResult runTestCase(TestObject testObject,
			AutomationTestCase tc, Integer planId, String build) {
		TestResult result = testService.run(
				testObject,
				tc.getAutomationKey(),
				new TestData(tc.getInputDataProperties(), tc
						.getOutputProperties()));
		Integer executionId = testLinkService.reportResult(tc.getId(),
				Integer.valueOf(planId), build, result);
		if (null != result.getScreenshot()) {
			testLinkService.uploadExecutionAttachment(executionId,
					"Screenshot_" + System.currentTimeMillis(),
					result.getScreenshot());
		}
		return result;
	}
}
