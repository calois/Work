package com.nasdaqomx.test.selenium.page.ldapconfig.clients.contacts;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class RemoveClientContactPage extends ClientContactBasePage {

	private static final String URL = "removeContact.view";

	public RemoveClientContactPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
