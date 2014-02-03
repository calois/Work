package com.nasdaqomx.test.selenium.page.ldapconfig.clients.metamarkets;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.ClientBasePage;

public class ListClientMetamarketsPage extends ClientBasePage {

	private static final String URL = "listContacts.view";

	public ListClientMetamarketsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
