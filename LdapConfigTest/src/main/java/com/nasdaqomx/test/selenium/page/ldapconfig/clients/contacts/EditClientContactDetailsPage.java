package com.nasdaqomx.test.selenium.page.ldapconfig.clients.contacts;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class EditClientContactDetailsPage extends ClientContactBasePage {

	private static final String URL = "editContact.view";

	public EditClientContactDetailsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
