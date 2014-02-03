package com.nasdaqomx.test.selenium.page.ldapconfig.clients.contacts.market;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class RemoveClientContactMarketPage extends ClientContactMarketBasePage {

	private static final String URL = "removeContactMarket.view";

	public RemoveClientContactMarketPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
