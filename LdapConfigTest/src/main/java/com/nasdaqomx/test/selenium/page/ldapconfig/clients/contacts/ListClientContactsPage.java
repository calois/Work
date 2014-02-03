package com.nasdaqomx.test.selenium.page.ldapconfig.clients.contacts;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.ClientBasePage;

public class ListClientContactsPage extends ClientBasePage {

	private static final String URL = "listContacts.view";

	public ListClientContactsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
