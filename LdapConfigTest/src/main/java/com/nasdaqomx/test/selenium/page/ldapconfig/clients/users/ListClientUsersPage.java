package com.nasdaqomx.test.selenium.page.ldapconfig.clients.users;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.ClientBasePage;

public class ListClientUsersPage extends ClientBasePage {

	private static final String URL = "listUsers.view";

	public ListClientUsersPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
