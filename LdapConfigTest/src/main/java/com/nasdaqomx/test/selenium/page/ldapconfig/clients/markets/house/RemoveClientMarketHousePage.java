package com.nasdaqomx.test.selenium.page.ldapconfig.clients.markets.house;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class RemoveClientMarketHousePage extends ClientMarketHouseBasePage {

	private static final String URL = "removeClientHouse.view";

	public RemoveClientMarketHousePage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
