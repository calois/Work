package com.nasdaqomx.test.selenium.page.ldapconfig.markets;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class EditMarketDetailsPage extends MarketBasePage {

	private static final String URL = "editMarket.view";

	public EditMarketDetailsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
