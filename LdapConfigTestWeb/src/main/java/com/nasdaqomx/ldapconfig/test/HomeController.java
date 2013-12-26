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

import com.nasdaqomx.ldapconfig.test.base.DriverType;
import com.nasdaqomx.ldapconfig.test.base.TestData;
import com.nasdaqomx.ldapconfig.test.base.TestResult;
import com.nasdaqomx.ldapconfig.test.base.TestService;
import com.nasdaqomx.ldapconfig.test.testlink.NasdaqTestCase;
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
		return "home";
	}

	@RequestMapping(value = "/testCases", method = RequestMethod.GET)
	public String testCase(@RequestParam String projectName,
			@RequestParam String planName, Model model) {
		TestPlan testPlan = testLinkService.getTestPlanByName(projectName,
				planName);
		NasdaqTestCase[] testCases = testLinkService
				.getTestCasesForTestPlan(testPlan.getId());
		model.addAttribute("testPlans", testPlan);
		model.addAttribute("testCases", testCases);
		return "testCase";
	}

	@RequestMapping(value = "/testCase/{testCaseId}", method = RequestMethod.GET)
	public String runTestCase(@PathVariable Integer testCaseId, Model model) {
		NasdaqTestCase testCase = testLinkService.getTestCase(testCaseId);
		TestResult result = testService.test(DriverType.CHROME, testCase
				.getTestKey(), new TestData(testCase.getDataProperties(),
				testCase.getExceptProperties()));
		model.addAttribute("result", result);
		return "testCaseResult";
	}
}
