package com.nasdaqomx.test.selenium.page.ldapconfig.clients.metamarkets.metahouse;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class EditClientMetamarketMetahouseDetailsPage extends ClientMetamarketMetahouseBasePage {

	private static final String URL = "editClientMetaHouse.view";

	public EditClientMetamarketMetahouseDetailsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
