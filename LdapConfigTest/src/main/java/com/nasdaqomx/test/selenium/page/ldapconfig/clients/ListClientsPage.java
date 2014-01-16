package com.nasdaqomx.test.selenium.page.ldapconfig.clients;

import com.nasdaqomx.test.selenium.base.AbstractTest;
import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.MainPage;

public class ListClientsPage extends MainPage {

	private static final String URL = "listClients.view";

	public ListClientsPage(TestManager testManager) {
		super(testManager);
		AbstractTest.assertEquals(getProject(), URL, this.getSimpleUrl());
		// TODO Auto-generated constructor stub
	}

}
