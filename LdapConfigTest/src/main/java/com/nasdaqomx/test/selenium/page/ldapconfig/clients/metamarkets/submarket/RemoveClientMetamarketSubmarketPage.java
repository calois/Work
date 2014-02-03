package com.nasdaqomx.test.selenium.page.ldapconfig.clients.metamarkets.submarket;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class RemoveClientMetamarketSubmarketPage extends ClientMetamarketSubmarketBasePage {

	private static final String URL = "removeMetaMarketSubMarket.view";

	public RemoveClientMetamarketSubmarketPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
