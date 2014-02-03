package com.nasdaqomx.test.selenium.page.ldapconfig.clients.metamarkets;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.ClientBasePage;

public class ClientMetamarketBasePage extends ClientBasePage {

	public ClientMetamarketBasePage(TestManager testManager) {
		super(testManager);
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
