package com.nasdaqomx.test.selenium.page.ldapconfig.clients;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class CopyClientPage extends ClientBasePage {

	private static final String URL = "copyClient.view";

	public CopyClientPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
