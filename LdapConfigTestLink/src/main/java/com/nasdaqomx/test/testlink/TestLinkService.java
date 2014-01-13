package com.nasdaqomx.test.testlink;

import java.net.MalformedURLException;
import java.net.URL;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.model.Attachment;
import br.eti.kinoshita.testlinkjavaapi.model.Build;
import br.eti.kinoshita.testlinkjavaapi.model.TestCase;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import br.eti.kinoshita.testlinkjavaapi.model.TestSuite;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

import com.nasdaqomx.selenium.test.base.TestResult;
import com.nasdaqomx.selenium.test.base.TestResultStatus;

public class TestLinkService {

	private static final ExecutionType AUTOMATED = ExecutionType.AUTOMATED;
	private static final boolean GETSTEPINFO = true;
	private static final boolean OVERWRITE_TEST_RESULT = true;
	private TestLinkAPI api;
	private TestLinkConfig testLinkConfig;
	private boolean debug = true;

	public void setTestLinkConfig(TestLinkConfig testLinkConfig) {
		this.testLinkConfig = testLinkConfig;
		if (!debug) {
			try {
				this.api = new TestLinkAPI(new URL(testLinkConfig.getUrl()),
						testLinkConfig.getDevKey());
			} catch (TestLinkAPIException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	public AutomationTestCase[] getTestCasesForSuite() {
		TestCase[] testCases = api.getTestCasesForTestSuite(
				testLinkConfig.getSuiteId(), null, null);
		AutomationTestCase[] t = new AutomationTestCase[testCases.length];
		for (int i = 0; i < testCases.length; i++) {
			t[i] = convertTestCase(getTestCaseById(testCases[i].getId()));
		}
		return t;
	}

	public AutomationTestCase[] getTestCasesForPlan() {
		if (debug) {
			AutomationTestCase[] atc = new AutomationTestCase[1];
			atc[0] = new AutomationTestCase(new TestCase());
			TestResult tr = new TestResult();
			tr.setMessage("com.nasdaqomx.selenium.test.base.TestException: Fail to create page objectLoginPage at com.nasdaqomx.selenium.test.base.AbstractTest.createPageObject(AbstractTest.java:28) at com.nasdaqomx.selenium.test.cases.ldapconfig.loginout.LoginTest.before(LoginTest.java:13) at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57) at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.lang.reflect.Method.invoke(Method.java:606) at com.nasdaqomx.selenium.test.base.TestService.run(TestService.java:31) at com.nasdaqomx.test.HomeController.runTestCase(HomeController.java:198) at com.nasdaqomx.test.HomeController.runTestCase(HomeController.java:161) at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57) at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.lang.reflect.Method.invoke(Method.java:606) at org.springframework.web.method.support.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:219) at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:132) at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:104) at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandleMethod(RequestMappingHandlerAdapter.java:745) at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:686) at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:80) at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:925) at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:856) at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:936) at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:838) at javax.servlet.http.HttpServlet.service(HttpServlet.java:647) at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:812) at javax.servlet.http.HttpServlet.service(HttpServlet.java:728) at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:305) at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:210) at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51) at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:243) at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:210) at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:222) at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:123) at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:502) at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:171) at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:100) at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:953) at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:118) at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:408) at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1041) at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:603) at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:312) at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145) at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615) at java.lang.Thread.run(Thread.java:744) Caused by: java.lang.reflect.InvocationTargetException at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:57) at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.lang.reflect.Constructor.newInstance(Constructor.java:526) at com.nasdaqomx.selenium.test.base.AbstractTest.createPageObject(AbstractTest.java:24) ... 44 more Caused by: java.lang.RuntimeException: Expected URL Starts with: \"fdslogin.jsp\" but actual: \"login.jsp at com.nasdaqomx.selenium.test.base.page.AbstractPageObject.fail(AbstractPageObject.java:123) at com.nasdaqomx.selenium.test.page.ldapconfig.LoginPage.<init>(LoginPage.java:49) ... 49 more");
			tr.setStatus(TestResultStatus.BLOCKED);
			atc[0].setExternalId("LDAPC-12345");
			atc[0].setSummary("Login with wrong username and right password");
			atc[0].setAutomationKey("ldapconfig.loginout.LoginTest.testLoginFail");
			atc[0].setInputData("username=autoTest1234 password=Test1234 usernameAfter=autoTest passwordAfter=Test1234");
			atc[0].setOutputData("msg=Username or password invalid please try again");
			atc[0].setTestResult(tr);
			return atc;
		}
		TestCase[] testCases = api.getTestCasesForTestPlan(
				testLinkConfig.getPlanId(), null, null, null, null, null, null,
				null, AUTOMATED, GETSTEPINFO, null);
		AutomationTestCase[] t = new AutomationTestCase[testCases.length];
		for (int i = 0; i < testCases.length; i++) {
			t[i] = convertTestCase(getTestCaseById(testCases[i].getId()));
		}
		return t;
	}

	private AutomationTestCase convertTestCase(TestCase testCase) {
		AutomationTestCase t = new AutomationTestCase(testCase);
		t.setAutomationKey(getAutomationKey(testCase));
		t.setInputData(getInputData(testCase));
		t.setOutputData(getOutputData(testCase));
		return t;
	}

	public AutomationTestCase getTestCase(Integer testCaseId) {
		return convertTestCase(getTestCaseById(testCaseId));
	}

	public Integer reportResult(Integer testCaseId, TestResult result) {
		if (TestResultStatus.INVALID.equals(result.getStatus())) {
			return null;
		} else {
			return api.reportTCResult(testCaseId, null,
					testLinkConfig.getPlanId(),
					ExecutionStatus.valueOf(result.getStatus().toString()),
					null, testLinkConfig.getBuild(), result.getMessage(),
					false, null, null, null, null, OVERWRITE_TEST_RESULT)
					.getExecutionId();
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

	public TestPlan[] getTestPlansForProject() {
		if (debug) {
			TestPlan testPlan = new TestPlan();
			testPlan.setId(1);
			testPlan.setName("asdasd");
			return new TestPlan[] { testPlan };
		}
		return api.getProjectTestPlans(testLinkConfig.getProjectId());
	}

	public Build[] getBuildsForPlan() {
		if (debug) {
			Build b = new Build();
			b.setId(1);
			b.setName("asd");
			return new Build[] { b };
		}
		return api.getBuildsForTestPlan(testLinkConfig.getPlanId());
	}

	public TestSuite[] getTestSuitesForPlan() {
		return api.getTestSuitesForTestPlan(testLinkConfig.getPlanId());
	}

	public TestSuite[] getTestSuitesForSuite() {
		return api.getTestSuitesForTestSuite(testLinkConfig.getSuiteId());
	}

	public TestPlan getTestPlanByName() {
		return api.getTestPlanByName(testLinkConfig.getPlanName(),
				testLinkConfig.getProjectName());
	}

	public TestProject getTestProjectByName() {
		return api.getTestProjectByName(testLinkConfig.getProjectName());
	}

	public Build createBuild() {
		return api.createBuild(testLinkConfig.getPlanId(),
				testLinkConfig.getBuild(), null);
	}

	public boolean checkDevKey(TestLinkAPI api, String devKey) {
		return api.checkDevKey(devKey);
	}

	private String getCustomFieldValue(TestCase testCase, String fieldName) {
		return api.getTestCaseCustomFieldDesignValue(testCase.getId(), null,
				testCase.getVersion(), testLinkConfig.getProjectId(),
				fieldName, null).getValue();
	}

	private String getAutomationKey(TestCase testCase) {
		return getCustomFieldValue(testCase,
				testLinkConfig.getAutomationKeyFieldName());
	}

	private String getInputData(TestCase testCase) {
		return getCustomFieldValue(testCase,
				testLinkConfig.getInputDataFieldName());
	}

	private String getOutputData(TestCase testCase) {
		return getCustomFieldValue(testCase,
				testLinkConfig.getOutputDataFieldName());

	}

	private TestCase getTestCaseById(Integer id) {
		return api.getTestCase(id, null, null);
	}
}
