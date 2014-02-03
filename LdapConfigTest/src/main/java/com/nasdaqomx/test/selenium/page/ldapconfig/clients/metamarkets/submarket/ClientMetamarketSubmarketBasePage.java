package com.nasdaqomx.test.selenium.page.ldapconfig.clients.metamarkets.submarket;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.metamarkets.ClientMetamarketBasePage;

public class ClientMetamarketSubmarketBasePage extends ClientMetamarketBasePage {

	public ClientMetamarketSubmarketBasePage(TestManager testManager) {
		super(testManager);
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
