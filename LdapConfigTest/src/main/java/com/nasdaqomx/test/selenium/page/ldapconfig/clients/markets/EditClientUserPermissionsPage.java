package com.nasdaqomx.test.selenium.page.ldapconfig.clients.markets;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class EditClientUserPermissionsPage extends ClientMarketBasePage {

	private static final String URL = "editClientMarketUsers.view";

	public EditClientUserPermissionsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
