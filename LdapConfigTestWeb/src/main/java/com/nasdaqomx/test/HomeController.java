package com.nasdaqomx.test;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;

import com.nasdaqomx.selenium.test.base.DriverType;
import com.nasdaqomx.selenium.test.base.Project;
import com.nasdaqomx.selenium.test.base.ProjectConfig;
import com.nasdaqomx.selenium.test.base.TestConfig;
import com.nasdaqomx.selenium.test.base.TestData;
import com.nasdaqomx.selenium.test.base.TestResult;
import com.nasdaqomx.selenium.test.base.TestService;
import com.nasdaqomx.test.testlink.AutomationTestCase;
import com.nasdaqomx.test.testlink.TestLinkConfig;
import com.nasdaqomx.test.testlink.TestLinkManager;
import com.nasdaqomx.test.testlink.TestLinkService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({ "testConfig", "testLinkConfig" })
public class HomeController {

	@Autowired
	private TestLinkService testLinkService;

	@Autowired
	private TestService testService;

	@ModelAttribute("testConfig")
	public TestConfig produceTestConfig(HttpServletRequest request) {
		TestConfig config = new TestConfig();
		ProjectConfig projectConfig = new ProjectConfig();
		projectConfig.setProject(Project.LDAP_CONFIG);
		config.getProjectConfigMap().put(projectConfig.getProject(),
				projectConfig);
		config.setChromeDriver(request.getSession().getServletContext()
				.getRealPath("chromedriver.exe"));
		return config;
	}

	@ModelAttribute("testLinkConfig")
	public TestLinkConfig produceTestLinkConfig(HttpServletRequest request) {
		TestLinkConfig testLinkConfig = new TestLinkConfig();
		testLinkConfig
				.setBaseUrl("http://au03smbcqa02.dev.smbc.nasdaqomx.com/testlink/");
		testLinkConfig.setDevKey("6ab4b64c40af9cb23d72a1f25885b9f2");
		return testLinkConfig;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			@ModelAttribute("testConfig") TestConfig testConfig,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig) {
		model.addAttribute("baseUrl", testLinkConfig.getBaseUrl());
		model.addAttribute("devKey", testLinkConfig.getDevKey());
		model.addAttribute("explicitWait", testConfig.getExplicitWait());
		model.addAttribute("implicitWait", testConfig.getImplicitWait());
		return "testConfig";
	}

	@RequestMapping(value = "/testProjects", method = RequestMethod.GET)
	@ResponseBody
	public TestProject[] getTestProjects(@RequestParam String baseUrl,
			@RequestParam String devKey,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig,
			Model model) {
		testLinkConfig.setDevKey(devKey);
		testLinkConfig.setBaseUrl(baseUrl);
		return testLinkService.getTestProjects(new TestLinkManager(
				testLinkConfig).getApi());
	}

	@RequestMapping(value = "/{testProjectId}/testPlan", method = RequestMethod.GET)
	@ResponseBody
	public TestPlan[] getTestPlans(@PathVariable Integer testProjectId,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig,
			Model model) {
		testLinkConfig.setProjectId(testProjectId);
		return testLinkService.getTestPlansForProject(new TestLinkManager(
				testLinkConfig));
	}

	@RequestMapping(value = "/{testPlanId}/build", method = RequestMethod.GET)
	@ResponseBody
	public Build[] getBuilds(@PathVariable Integer testPlanId,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig,
			Model model) {
		testLinkConfig.setPlanId(testPlanId);
		return testLinkService.getBuildsForPlan(new TestLinkManager(
				testLinkConfig));
	}

	@RequestMapping(value = "/runTestCases", method = RequestMethod.POST)
	public String runTestCases(@RequestParam DriverType browserType,
			@RequestParam Long explicitWait, @RequestParam Long implicitWait,
			@RequestParam String url, @RequestParam Integer projectId,
			@RequestParam Integer planId, @RequestParam String build,
			Model model, @ModelAttribute("testConfig") TestConfig testConfig,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig) {
		// TODO: Need to set for different APP
		testConfig.getProjectConfigMap().get(Project.LDAP_CONFIG)
				.setBaseUrl(url);
		testConfig.setDriverType(browserType);
		testConfig.setExplicitWait(explicitWait);
		testConfig.setImplicitWait(implicitWait);
		testLinkConfig.setProjectId(projectId);
		testLinkConfig.setPlanId(planId);
		testLinkConfig.setBuild(build);
		TestLinkManager testLinkManager = new TestLinkManager(testLinkConfig);
		AutomationTestCase[] testCases = testLinkService
				.getTestCasesForPlan(testLinkManager);
		if (null != testCases) {
			createTestLinkBuild(testLinkManager);
			for (int i = 0; i < testCases.length; i++) {
				testCases[i].setTestResult(runTestCase(testConfig,
						testCases[i], testLinkManager));
			}
		}
		model.addAttribute("testCases", testCases);
		return "testList";
	}

	@RequestMapping(value = "/viewTestCases", method = RequestMethod.POST)
	public String viewTestCases(@RequestParam DriverType browserType,
			@RequestParam String url, @RequestParam Integer projectId,
			@RequestParam Integer planId, @RequestParam String build,
			Model model, @ModelAttribute("testConfig") TestConfig testConfig,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig) {
		// TODO: Need to set for different APP
		testConfig.getProjectConfigMap().get(Project.LDAP_CONFIG)
				.setBaseUrl(url);
		testConfig.setDriverType(browserType);
		testLinkConfig.setProjectId(projectId);
		testLinkConfig.setPlanId(planId);
		testLinkConfig.setBuild(build);
		TestLinkManager testLinkManager = new TestLinkManager(testLinkConfig);
		AutomationTestCase[] testCases = testLinkService
				.getTestCasesForPlan(testLinkManager);
		model.addAttribute("testCases", testCases);
		return "testList";
	}

	@RequestMapping(value = "/run/{testCaseId}", method = RequestMethod.POST)
	@ResponseBody
	public AutomationTestCase runTestCase(@PathVariable Integer testCaseId,
			Model model, @ModelAttribute("testConfig") TestConfig testConfig,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig) {
		TestLinkManager testLinkManager = new TestLinkManager(testLinkConfig);
		AutomationTestCase testCase = testLinkService.getTestCase(testCaseId,
				testLinkManager);
		createTestLinkBuild(testLinkManager);
		testCase.setTestResult(runTestCase(testConfig, testCase,
				testLinkManager));
		return testCase;
	}

	@RequestMapping(value = "/runAll", method = RequestMethod.GET)
	public String runTestCases(Model model,
			@ModelAttribute("testConfig") TestConfig testConfig,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig) {
		TestLinkManager testLinkManager = new TestLinkManager(testLinkConfig);
		AutomationTestCase[] testCases = testLinkService
				.getTestCasesForPlan(testLinkManager);
		if (null != testCases) {
			createTestLinkBuild(testLinkManager);
			for (int i = 0; i < testCases.length; i++) {
				testCases[i].setTestResult(runTestCase(testConfig,
						testCases[i], testLinkManager));
			}
		}
		model.addAttribute("testCases", testCases);
		return "testList";
	}

	private void createTestLinkBuild(TestLinkManager testLinkManager) {
		boolean hasBuild = false;
		Build[] builds = testLinkService.getBuildsForPlan(testLinkManager);
		if (null != builds) {
			for (int i = 0; i < builds.length; i++) {
				if (testLinkManager.getBuild().equals(builds[i].getName())) {
					hasBuild = true;
					break;
				}
			}
		}
		if (!hasBuild) {
			testLinkService.createBuild(testLinkManager);
		}
	}

	private TestResult runTestCase(TestConfig testConfig,
			AutomationTestCase tc, TestLinkManager testLinkManager) {
		TestResult result = testService.run(
				testConfig,
				tc.getAutomationKey(),
				new TestData(tc.getInputDataProperties(), tc
						.getOutputProperties()));
		Integer executionId = testLinkService.reportResult(tc.getId(),
				testLinkManager, result);
		if (null != result.getScreenshot()) {
			testLinkService.uploadExecutionAttachment(executionId,
					"Screenshot_" + System.currentTimeMillis(),
					result.getScreenshot(), testLinkManager.getApi());
		}
		return result;
	}
}
