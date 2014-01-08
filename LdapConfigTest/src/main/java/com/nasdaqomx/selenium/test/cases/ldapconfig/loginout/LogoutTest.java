package com.nasdaqomx.selenium.test.cases.ldapconfig.loginout;

import com.nasdaqomx.selenium.test.base.AbstractTest;
import com.nasdaqomx.selenium.test.base.anno.TestBefore;
import com.nasdaqomx.selenium.test.page.ldapconfig.LoginPage;
import com.nasdaqomx.selenium.test.page.ldapconfig.MainPage;

public class LogoutTest extends AbstractTest {

	private MainPage mainPage;

	@TestBefore
	public void before() {
		mainPage = createPageObject(LoginPage.class).loginAs(
				getInputData("username"), getInputData("password"));
		assertEquals(getOutputData("defaultUrl"), mainPage.getUrl());
	}

	public void testLogout() {
		verifyEquals(getOutputData("msg"), mainPage.clickLogout().getMessage());
	}
}
