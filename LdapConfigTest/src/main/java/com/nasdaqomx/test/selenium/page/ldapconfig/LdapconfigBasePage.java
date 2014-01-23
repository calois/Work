package com.nasdaqomx.test.selenium.page.ldapconfig;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.base.page.AbstractPageObject;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.ListClientsPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.ListManagersPage;

public class LdapconfigBasePage extends AbstractPageObject {

	private static final String TITLE_CLASS = "LdapHeader";
	private static final String MANAGERS_XPATH = "//div[@id='mainMenu']//a[text()='Managers']";
	private static final String MARKETS_XPATH = "//div[@id='mainMenu']//a[text()='Markets']";
	private static final String METARMARKETS_XPATH = "//div[@id='mainMenu']//a[text()='Metamarkets']";
	private static final String CLIENTS_XPATH = "//div[@id='mainMenu']//a[text()='Clients']";
	private static final String REPORTS_XPATH = "//div[@id='mainMenu']//a[text()='Reports']";
	private static final String NOTIFICATIONS_XPATH = "//div[@id='mainMenu']//a[text()='Notifications']";
	private static final String REMOTE_ADMIN_XPATH = "//div[@id='mainMenu']//a[text()='Remote Admin']";
	private static final String LOGOUT_ID = "logout";

	private WebElement logout;

	public LdapconfigBasePage(TestManager testManager) {
		super(testManager);
		try {
			logout = getElement(By.id(LOGOUT_ID));
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public ListManagersPage toManagers() {
		getElement(By.xpath(MANAGERS_XPATH)).click();
		return createPageObject(ListManagersPage.class);
	}

	// TODO should return markets page
	public void toMarkets() {
		getElement(By.xpath(MARKETS_XPATH)).click();
	}

	// TODO should return metamarkets page
	public void toMetamarkets() {
		getElement(By.xpath(METARMARKETS_XPATH)).click();
	}

	public ListClientsPage toClients() {
		getElement(By.xpath(CLIENTS_XPATH)).click();
		return createPageObject(ListClientsPage.class);
	}

	// TODO should return reports page
	public void toReports() {
		getElement(By.xpath(REPORTS_XPATH)).click();
	}

	// TODO should return notifications page
	public void toNotifications() {
		getElement(By.xpath(NOTIFICATIONS_XPATH)).click();
	}

	// TODO should return RemoteAdmins page
	public void toRemoteAdmins() {
		getElement(By.xpath(REMOTE_ADMIN_XPATH)).click();
	}

	public LogoutPage logout() {
		this.logout.click();
		return createPageObject(LogoutPage.class);
	}

	public String getCurrentTitle() {
		return getElement(By.className(TITLE_CLASS)).getText();
	}

}
