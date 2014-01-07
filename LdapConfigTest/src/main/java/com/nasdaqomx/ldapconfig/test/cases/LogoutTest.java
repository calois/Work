package com.nasdaqomx.ldapconfig.test.cases;

import com.nasdaqomx.ldapconfig.test.base.AbstractTest;
import com.nasdaqomx.ldapconfig.test.base.anno.TestBefore;
import com.nasdaqomx.ldapconfig.test.page.LoginPage;
import com.nasdaqomx.ldapconfig.test.page.MainPage;

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
