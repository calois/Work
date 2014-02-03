package com.nasdaqomx.test.selenium.page.ldapconfig.clients.markets;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.ClientBasePage;

public class ListClientMarketsPage extends ClientBasePage {

	private static final String URL = "listContacts.view";

	public ListClientMarketsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
