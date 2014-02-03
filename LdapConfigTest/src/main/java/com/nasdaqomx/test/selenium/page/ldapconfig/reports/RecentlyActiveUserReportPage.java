package com.nasdaqomx.test.selenium.page.ldapconfig.reports;

import org.openqa.selenium.NoSuchElementException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class RecentlyActiveUserReportPage extends LdapconfigBasePage {

	private static final String URL = "reportActiveUsers.view";

	public RecentlyActiveUserReportPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

}
