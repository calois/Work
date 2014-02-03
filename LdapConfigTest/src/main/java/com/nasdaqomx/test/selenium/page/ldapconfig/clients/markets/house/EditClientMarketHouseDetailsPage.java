package com.nasdaqomx.test.selenium.page.ldapconfig.clients.markets.house;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class EditClientMarketHouseDetailsPage extends ClientMarketHouseBasePage {

	private static final String URL = "editClientHouse.view";

	public EditClientMarketHouseDetailsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}