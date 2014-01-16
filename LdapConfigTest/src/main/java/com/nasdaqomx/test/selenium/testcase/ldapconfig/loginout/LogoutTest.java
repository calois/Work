package com.nasdaqomx.test.selenium.testcase.ldapconfig.loginout;

import com.nasdaqomx.test.selenium.base.AbstractTest;
import com.nasdaqomx.test.selenium.base.anno.TestBefore;
import com.nasdaqomx.test.selenium.page.ldapconfig.LoginPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.MainPage;

public class LogoutTest extends AbstractTest {

	private MainPage mainPage;

	@TestBefore
	public void before() {
		mainPage = createPageObject(LoginPage.class).loginAs(
				getInputData("username"), getInputData("password"));
	}

	public void testLogout() {
		verifyEquals(getOutputData("msg"), mainPage.clickLogout().getMessage());
	}
}
