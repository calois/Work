package com.nasdaqomx.test.selenium.page.ldapconfig.clients.markets;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.ClientBasePage;

public class ClientMarketBasePage extends ClientBasePage {

	public ClientMarketBasePage(TestManager testManager) {
		super(testManager);
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
