package com.nasdaqomx.ldapconfig.test;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;

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
		return "home";
	}

	@RequestMapping(value = "/testCases", method = RequestMethod.GET)
	public String testCase(@RequestParam String url,
			@RequestParam String projectName, @RequestParam String planName,
			Model model) {
		testService.getProp().setProperty("baseUrl", url);
		TestProject testProject = testLinkService
				.getTestProjectByName(projectName);
		TestPlan testPlan = testLinkService.getTestPlanByName(projectName,
				planName);
		AutomationTestCase[] testCases = testLinkService.getTestCasesForPlan(
				testPlan.getId(), testProject.getId());
		model.addAttribute("testProject", testProject);
		model.addAttribute("testPlan", testPlan);
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
