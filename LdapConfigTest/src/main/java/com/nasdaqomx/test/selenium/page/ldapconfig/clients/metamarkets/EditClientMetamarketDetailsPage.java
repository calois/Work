package com.nasdaqomx.test.selenium.page.ldapconfig.clients.metamarkets;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class EditClientMetamarketDetailsPage extends ClientMetamarketBasePage {

	private static final String URL = "editClientMetaMarket.view";

	public EditClientMetamarketDetailsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
