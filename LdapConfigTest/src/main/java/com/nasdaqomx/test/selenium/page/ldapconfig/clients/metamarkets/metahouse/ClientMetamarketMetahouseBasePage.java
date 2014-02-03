package com.nasdaqomx.test.selenium.page.ldapconfig.clients.metamarkets.metahouse;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.metamarkets.ClientMetamarketBasePage;

public class ClientMetamarketMetahouseBasePage extends ClientMetamarketBasePage {

	public ClientMetamarketMetahouseBasePage(TestManager testManager) {
		super(testManager);
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}
}
