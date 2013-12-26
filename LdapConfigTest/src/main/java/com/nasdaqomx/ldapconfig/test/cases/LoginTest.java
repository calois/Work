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
		createPageObject(LoginPage.class);
		System.out.println(getData("test2"));
		assertEquals("asdasd", getExpect("test1"));
	}
}
