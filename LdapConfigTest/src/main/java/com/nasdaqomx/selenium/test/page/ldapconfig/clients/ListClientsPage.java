package com.nasdaqomx.selenium.test.page.ldapconfig.clients;

import com.nasdaqomx.selenium.test.base.AbstractTest;
import com.nasdaqomx.selenium.test.base.TestManager;
import com.nasdaqomx.selenium.test.page.ldapconfig.MainPage;

public class ListClientsPage extends MainPage {

	private static final String URL = "listClients.view";

	public ListClientsPage(TestManager testManager) {
		super(testManager);
		AbstractTest.assertEquals(getProject(), URL, this.getSimpleUrl());
		// TODO Auto-generated constructor stub
	}

}
