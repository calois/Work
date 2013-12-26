package com.nasdaqomx.ldapconfig.test.page;

import com.nasdaqomx.ldapconfig.test.base.TestObject;
import com.nasdaqomx.ldapconfig.test.base.page.AbstractPageObject;

public class LoginPage extends AbstractPageObject {
	public LoginPage(TestObject testObject) {
		super(testObject);
		load("/");
	}
}
