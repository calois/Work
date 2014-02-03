package com.nasdaqomx.test.selenium.page.ldapconfig.remoteadmin;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class RemoteAdminBasePage extends LdapconfigBasePage {

	public RemoteAdminBasePage(TestManager testManager) {
		super(testManager);
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
