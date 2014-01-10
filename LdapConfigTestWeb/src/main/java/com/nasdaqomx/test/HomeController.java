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
import com.nasdaqomx.selenium.test.base.Project;
import com.nasdaqomx.selenium.test.base.ProjectConfig;
import com.nasdaqomx.selenium.test.base.TestData;
import com.nasdaqomx.selenium.test.base.TestConfig;
import com.nasdaqomx.selenium.test.base.TestResult;
import com.nasdaqomx.selenium.test.base.TestService;
import com.nasdaqomx.test.testlink.AutomationTestCase;
import com.nasdaqomx.test.testlink.TestLinkService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("testConfig")
public class HomeController {

	@Autowired
	private TestLinkService testLinkService;

	@Autowired
	private TestService testService;

	@ModelAttribute("testConfig")
	public TestConfig produceTestConfig() {
		TestConfig config = new TestConfig();
		ProjectConfig projectConfig = new ProjectConfig();
		projectConfig.setProject(Project.LDAP_CONFIG);
		config.getProjectConfigMap().put(projectConfig.getProject(),
				projectConfig);
		return config;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			@ModelAttribute("testConfig") TestConfig testConfig) {
		model.addAttribute("testProjects", testLinkService.getTestProjects());
		model.addAttribute("testPlans",
				testLinkService.getTestPlansForProject(2));
		model.addAttribute("explicitWait", testConfig.getExplicitWait());
		model.addAttribute("implicitWait", testConfig.getImplicitWait());
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

	@RequestMapping(value = "/runTestCases", method = RequestMethod.POST)
	public String runTestCases(@RequestParam DriverType browserType,
			@RequestParam Long explicitWait, @RequestParam Long implicitWait,
			@RequestParam String url, @RequestParam Integer projectId,
			@RequestParam Integer planId,
			@RequestParam(required = false) String build, Model model,
			@ModelAttribute("testConfig") TestConfig testConfig) {
		// TODO: Need to set for different APP
		testConfig.getProjectConfigMap().get(Project.LDAP_CONFIG)
				.setBaseUrl(url);
		testConfig.setDriverType(browserType);
		testConfig.setExplicitWait(explicitWait);
		testConfig.setImplicitWait(implicitWait);
		AutomationTestCase[] testCases = testLinkService.getTestCasesForPlan(
				planId, projectId);
		if (null != testCases) {
			createTestLinkBuild(build, planId);
			for (int i = 0; i < testCases.length; i++) {
				testCases[i].setTestResult(runTestCase(testConfig,
						testCases[i], planId, build));
			}
		}
		model.addAttribute("testCases", testCases);
		model.addAttribute("projectId", projectId);
		model.addAttribute("planId", planId);
		model.addAttribute("build", build);
		return "testList";
	}

	@RequestMapping(value = "/viewTestCases", method = RequestMethod.POST)
	public String viewTestCases(@RequestParam DriverType browserType,
			@RequestParam String url, @RequestParam Integer projectId,
			@RequestParam Integer planId, @RequestParam String build,
			Model model, @ModelAttribute("testConfig") TestConfig testConfig) {
		// TODO: Need to set for different APP
		testConfig.getProjectConfigMap().get(Project.LDAP_CONFIG)
				.setBaseUrl(url);
		testConfig.setDriverType(browserType);
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
			@ModelAttribute("testConfig") TestConfig testConfig) {
		AutomationTestCase testCase = testLinkService.getTestCase(testCaseId,
				projectId);
		createTestLinkBuild(build, planId);
		testCase.setTestResult(runTestCase(testConfig, testCase, planId, build));
		return testCase;
	}

	@RequestMapping(value = "/{projectId}/{planId}/{build}/testCases", method = RequestMethod.GET)
	public String runTestCases(@PathVariable Integer projectId,
			@PathVariable Integer planId, @PathVariable String build,
			Model model, @ModelAttribute("testConfig") TestConfig testConfig) {
		AutomationTestCase[] testCases = testLinkService.getTestCasesForPlan(
				planId, projectId);
		if (null != testCases) {
			createTestLinkBuild(build, planId);
			for (int i = 0; i < testCases.length; i++) {
				testCases[i].setTestResult(runTestCase(testConfig,
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

	private TestResult runTestCase(TestConfig testConfig,
			AutomationTestCase tc, Integer planId, String build) {
		TestResult result = testService.run(
				testConfig,
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
