package com.nasdaqomx.test.selenium.page.ldapconfig.clients;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class ListClientsPage extends LdapconfigBasePage {

	private static final String URL = "listClients.view";

	public ListClientsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl(), false);
		// TODO Auto-generated constructor stub
	}

}
