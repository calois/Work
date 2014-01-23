package com.nasdaqomx.test.selenium.testcase.ldapconfig.loginout;

import com.nasdaqomx.test.selenium.base.AbstractTest;
import com.nasdaqomx.test.selenium.base.anno.TestBefore;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;
import com.nasdaqomx.test.selenium.page.ldapconfig.LoginPage;

public class LogoutTest extends AbstractTest {

	private LdapconfigBasePage defaultPage;

	@TestBefore
	public void before() {
		defaultPage = createPageObject(LoginPage.class).loginAs(
				getInputData("username"), getInputData("password"));
	}

	public void testLogout() {
		verifyEquals(getOutputData("msg"), defaultPage.logout()
				.getMessage());
	}
}