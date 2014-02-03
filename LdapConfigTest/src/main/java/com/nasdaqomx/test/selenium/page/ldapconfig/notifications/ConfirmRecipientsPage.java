package com.nasdaqomx.test.selenium.page.ldapconfig.notifications;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class ConfirmRecipientsPage extends LdapconfigBasePage {

	private static final String URL = "notification.view";

	public ConfirmRecipientsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
