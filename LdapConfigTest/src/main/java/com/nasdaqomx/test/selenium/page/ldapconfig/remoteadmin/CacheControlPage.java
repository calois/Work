package com.nasdaqomx.test.selenium.page.ldapconfig.remoteadmin;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class CacheControlPage extends RemoteAdminBasePage {

	private static final String URL = "remoteAdminCacheControl.view";

	public CacheControlPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
