package com.nasdaqomx.test.selenium.page.ldapconfig.clients.markets.house;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.markets.ClientMarketBasePage;

public class ClientMarketHouseBasePage extends ClientMarketBasePage {

	public ClientMarketHouseBasePage(TestManager testManager) {
		super(testManager);
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
