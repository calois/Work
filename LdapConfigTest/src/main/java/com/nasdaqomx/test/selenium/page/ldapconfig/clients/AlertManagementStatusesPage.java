package com.nasdaqomx.test.selenium.page.ldapconfig.clients;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class AlertManagementStatusesPage extends ClientBasePage {

	private static final String URL = "editClient_AlertStatuses.view";

	public AlertManagementStatusesPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
