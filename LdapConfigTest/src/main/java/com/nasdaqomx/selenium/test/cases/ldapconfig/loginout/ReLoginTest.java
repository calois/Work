package com.nasdaqomx.selenium.test.cases.ldapconfig.loginout;

import com.nasdaqomx.selenium.test.base.anno.TestAfter;
import com.nasdaqomx.selenium.test.base.anno.TestBefore;
import com.nasdaqomx.selenium.test.cases.ldapconfig.LdapConfigBaseTest;
import com.nasdaqomx.selenium.test.page.ldapconfig.LoginPage;
import com.nasdaqomx.selenium.test.page.ldapconfig.LogoutPage;

public class ReLoginTest extends LdapConfigBaseTest {

	private LogoutPage logoutPage;

	@TestBefore
	public void before() {
		logoutPage = createPageObject(LoginPage.class).loginAs(
				getInputData("username"), getInputData("password"))
				.clickLogout();
	}

	@TestAfter
	public void after() {
	}

	public void testLoginLink() {
		logoutPage.clickLoginLink().loginAs(getInputData("username"),
				getInputData("password"));
	}
}
