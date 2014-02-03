package com.nasdaqomx.test.selenium.page.ldapconfig.clients.markets;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class RemoveClientMarketPage extends ClientMarketBasePage {

	private static final String URL = "removeClientMarket.view";

	public RemoveClientMarketPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
