package com.nasdaqomx.test.selenium.page.ldapconfig.clients.contacts.market;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.contacts.ClientContactBasePage;

public class ClientContactMarketBasePage extends ClientContactBasePage {

	public ClientContactMarketBasePage(TestManager testManager) {
		super(testManager);
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
