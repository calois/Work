package com.nasdaqomx.test.selenium.testcase.ldapconfig.loginout;

import com.nasdaqomx.test.selenium.base.AbstractTest;
import com.nasdaqomx.test.selenium.base.anno.TestBefore;
import com.nasdaqomx.test.selenium.page.ldapconfig.LoginPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.ListClientsPage;

public class LogoutTest extends AbstractTest {

	private ListClientsPage defaultPage;

	@TestBefore
	public void before() {
		defaultPage = createPageObject(LoginPage.class).loginAs(
				getInputData("username"), getInputData("password"));
	}

	public void testLogout() {
		verifyEquals(getOutputData("msg"), defaultPage.clickLogout()
				.getMessage());
	}
}