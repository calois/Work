package com.nasdaqomx.test.selenium.page.ldapconfig.clients.users;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class CopyClientUserPage extends ClientUserBasePage {

	private static final String URL = "copyUser.view";

	public CopyClientUserPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
