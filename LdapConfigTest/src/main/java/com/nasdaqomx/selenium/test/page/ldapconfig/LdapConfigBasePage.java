package com.nasdaqomx.selenium.test.page.ldapconfig;

import com.nasdaqomx.selenium.test.base.TestApp;
import com.nasdaqomx.selenium.test.base.TestManager;
import com.nasdaqomx.selenium.test.base.anno.PageObject;
import com.nasdaqomx.selenium.test.base.page.AbstractPageObject;

@PageObject(TestApp.LDAP_CONFIG)
public abstract class LdapConfigBasePage extends AbstractPageObject {

	public LdapConfigBasePage(TestManager testManager) {
		super(testManager);
	}
}