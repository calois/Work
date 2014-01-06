package com.nasdaqomx.ldapconfig.test.cases;

import com.nasdaqomx.ldapconfig.test.base.AbstractTest;
import com.nasdaqomx.ldapconfig.test.base.anno.TestAfter;
import com.nasdaqomx.ldapconfig.test.base.anno.TestBefore;
import com.nasdaqomx.ldapconfig.test.page.LoginPage;

public class LoginTest extends AbstractTest {

	@TestBefore
	public void before() {
		System.out.println("before");
	}

	@TestAfter
	public void after() {
		System.out.println("after");
	}

	public void testLogin() {
		LoginPage loginPage = createPageObject(LoginPage.class);
		loginPage.login(getInputData("username"), getInputData("password"));
		assertEquals("asdasd", getOutputData("test1"));
	}
}
