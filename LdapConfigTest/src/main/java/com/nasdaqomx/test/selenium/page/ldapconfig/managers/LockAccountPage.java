package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class LockAccountPage extends ManagerBasePage {

	private static final String URL = "resetManagerPassword.view";

	public LockAccountPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}
}
