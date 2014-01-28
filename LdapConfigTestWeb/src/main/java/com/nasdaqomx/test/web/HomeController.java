package com.nasdaqomx.test.web;

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

import com.nasdaqomx.test.selenium.base.DriverType;
import com.nasdaqomx.test.selenium.base.Project;
import com.nasdaqomx.test.selenium.base.ProjectConfig;
import com.nasdaqomx.test.selenium.base.TestConfig;
import com.nasdaqomx.test.selenium.base.TestData;
import com.nasdaqomx.test.selenium.base.TestResult;
import com.nasdaqomx.test.selenium.base.job.TestJob;
import com.nasdaqomx.test.selenium.base.job.TestJobCallback;
import com.nasdaqomx.test.selenium.base.job.TestJobManager;
import com.nasdaqomx.test.testlink.AutomationTestCase;
import com.nasdaqomx.test.testlink.TestLinkConfig;
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
	private TestJobManager testJobManager;

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
		return testLinkConfig;
	}

	@RequestMapping(value = "/testJob/{id}", method = RequestMethod.GET)
	@ResponseBody
	public TestJob testJob(@PathVariable String id) {
		return testJobManager.getTestJob(id);
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
		testLinkService.setTestLinkConfig(testLinkConfig);
		return testLinkService.getTestProjects();
	}

	@RequestMapping(value = "/{testProjectId}/testPlan", method = RequestMethod.GET)
	@ResponseBody
	public TestPlan[] getTestPlans(@PathVariable Integer testProjectId,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig,
			Model model) {
		testLinkConfig.setProjectId(testProjectId);
		testLinkService.setTestLinkConfig(testLinkConfig);
		return testLinkService.getTestPlansForProject();
	}

	@RequestMapping(value = "/{testPlanId}/build", method = RequestMethod.GET)
	@ResponseBody
	public Build[] getBuilds(@PathVariable Integer testPlanId,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig,
			Model model) {
		testLinkConfig.setPlanId(testPlanId);
		testLinkService.setTestLinkConfig(testLinkConfig);
		return testLinkService.getBuildsForPlan();
	}

	@RequestMapping(value = "/runTestCases", method = RequestMethod.GET)
	public String runTestCases(@RequestParam DriverType browserType,
			@RequestParam Long explicitWait, @RequestParam Long implicitWait,
			@RequestParam String url, @RequestParam Integer projectId,
			@RequestParam Integer planId, @RequestParam String build,
			Model model, @ModelAttribute("testConfig") TestConfig testConfig,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig) {
		testConfig.getProjectConfigMap().get(Project.LDAP_CONFIG)
				.setBaseUrl(url);
		testConfig.setDriverType(browserType);
		testConfig.setExplicitWait(explicitWait);
		testConfig.setImplicitWait(implicitWait);
		testLinkConfig.setProjectId(projectId);
		testLinkConfig.setPlanId(planId);
		testLinkConfig.setBuild(build);
		TestLinkService testLinkService = new TestLinkService();
		testLinkService.setTestLinkConfig(testLinkConfig);
		AutomationTestCase[] testCases = testLinkService.getTestCasesForPlan();
		model.addAttribute("testCases", testCases);
		model.addAttribute("runAll", true);
		return "testList";
	}

	@RequestMapping(value = "/viewTestCases", method = RequestMethod.GET)
	public String viewTestCases(@RequestParam DriverType browserType,
			@RequestParam String url, @RequestParam Integer projectId,
			@RequestParam Integer planId, @RequestParam String build,
			Model model, @ModelAttribute("testConfig") TestConfig testConfig,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig) {
		testConfig.getProjectConfigMap().get(Project.LDAP_CONFIG)
				.setBaseUrl(url);
		testConfig.setDriverType(browserType);
		testLinkConfig.setProjectId(projectId);
		testLinkConfig.setPlanId(planId);
		testLinkConfig.setBuild(build);
		testLinkService.setTestLinkConfig(testLinkConfig);
		AutomationTestCase[] testCases = testLinkService.getTestCasesForPlan();
		for (AutomationTestCase t : testCases) {
			t.setStatus(testJobManager.getStatus(String.valueOf(t.getId())));
		}
		model.addAttribute("testCases", testCases);
		return "testList";
	}

	@RequestMapping(value = "/run/{testCaseId}", method = RequestMethod.GET)
	@ResponseBody
	public AutomationTestCase runTestCase(@PathVariable Integer testCaseId,
			Model model, @ModelAttribute("testConfig") TestConfig testConfig,
			@ModelAttribute("testLinkConfig") TestLinkConfig testLinkConfig) {
		TestLinkService testLinkService = new TestLinkService();
		testLinkService.setTestLinkConfig(testLinkConfig);
		AutomationTestCase testCase = testLinkService.getTestCase(testCaseId);
		createTestLinkBuild(testLinkService, testLinkConfig.getBuild());
		run(testLinkService, testConfig, testCase);
		return testCase;
	}

	@RequestMapping(value = "/job/list/waiting", method = RequestMethod.GET)
	public String getWaitingJobList(Model model) {
		model.addAttribute("jobList", testJobManager.getWaitingJobList());
		return "waitingJobList";
	}

	@RequestMapping(value = "/runner/list", method = RequestMethod.GET)
	public String getTestJobRunnerList(Model model) {
		model.addAttribute("runnerList", testJobManager.getTestJobRunnerList());
		return "runnerList";
	}

	private void createTestLinkBuild(TestLinkService testLinkService,
			String build) {
		boolean hasBuild = false;
		Build[] builds = testLinkService.getBuildsForPlan();
		if (null != builds) {
			for (int i = 0; i < builds.length; i++) {
				if (build.equals(builds[i].getName())) {
					hasBuild = true;
					break;
				}
			}
		}
		if (!hasBuild) {
			testLinkService.createBuild();
		}
	}

	private void run(final TestLinkService testLinkService,
			TestConfig testConfig, final AutomationTestCase tc) {
		testJobManager.push(String.valueOf(tc.getId()), testConfig, tc
				.getAutomationKey(), new TestData(tc.getInputDataProperties(),
				tc.getOutputProperties()), new TestJobCallback() {
			@Override
			public void finish(TestResult result) {
				Integer executionId = testLinkService.reportResult(tc.getId(),
						result);
				testLinkService.uploadExecutionAttachments(executionId,
						result.getScreenshotList());
			}
		});
	}
}
