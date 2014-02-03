package com.nasdaqomx.test.selenium.page.ldapconfig.remoteadmin;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class RemoteAdminPage extends LdapconfigBasePage {

	private static final String URL = "remoteAdmin.view";

	public RemoteAdminPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
