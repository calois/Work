package com.nasdaqomx.selenium.test.cases.ldapconfig.loginout;

import com.nasdaqomx.selenium.test.base.anno.TestBefore;
import com.nasdaqomx.selenium.test.cases.ldapconfig.LdapConfigBaseTest;
import com.nasdaqomx.selenium.test.page.ldapconfig.LoginPage;
import com.nasdaqomx.selenium.test.page.ldapconfig.MainPage;

public class LogoutTest extends LdapConfigBaseTest {

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
