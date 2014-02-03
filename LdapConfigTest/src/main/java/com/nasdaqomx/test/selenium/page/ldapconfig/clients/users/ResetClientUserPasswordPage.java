package com.nasdaqomx.test.selenium.page.ldapconfig.clients.users;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class ResetClientUserPasswordPage extends ClientUserBasePage {

	private static final String URL = "resetPassword.view";

	public ResetClientUserPasswordPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
