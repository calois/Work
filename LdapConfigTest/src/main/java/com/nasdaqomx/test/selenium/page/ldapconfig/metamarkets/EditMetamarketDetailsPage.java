package com.nasdaqomx.test.selenium.page.ldapconfig.metamarkets;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class EditMetamarketDetailsPage extends MetamarketBasePage {

	private static final String URL = "editMetaMarket.view";

	public EditMetamarketDetailsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
