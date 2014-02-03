package com.nasdaqomx.test.selenium.page.ldapconfig.remoteadmin;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class OperationsPage extends RemoteAdminBasePage {

	private static final String URL = "remoteAdminOperations.view";

	public OperationsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
