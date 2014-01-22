package com.nasdaqomx.test.selenium.page.ldapconfig;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.base.page.AbstractPageObject;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.ListClientsPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.ListManagersPage;

public class LdapconfigBasePage extends AbstractPageObject {

	private static final String TITLE_LOCATOR = "//h2[@class='LdapHeader']";
	private static final String MANAGERS_LOCATOR = "//div[@id='mainMenu']//a[text()='Managers']";
	private static final String MARKETS_LOCATOR = "//div[@id='mainMenu']//a[text()='Markets']";
	private static final String METARMARKETS_LOCATOR = "//div[@id='mainMenu']//a[text()='Metamarkets']";
	private static final String CLIENTS_LOCATOR = "//div[@id='mainMenu']//a[text()='Clients']";
	private static final String REPORTS_LOCATOR = "//div[@id='mainMenu']//a[text()='Reports']";
	private static final String NOTIFICATIONS_LOCATOR = "//div[@id='mainMenu']//a[text()='Notifications']";
	private static final String REMOTE_ADMIN_LOCATOR = "//div[@id='mainMenu']//a[text()='Remote Admin']";
	private static final String LOGOUT_LOCATOR = "logout";

	private WebElement managers;
	private WebElement markets;
	private WebElement metamarkets;
	private WebElement clients;
	private WebElement reports;
	private WebElement notifications;
	private WebElement remoteAdmin;
	private WebElement logout;

	public LdapconfigBasePage(TestManager testManager) {
		super(testManager);
		try {
			managers = getElement(By.xpath(MANAGERS_LOCATOR));
			markets = getElement(By.xpath(MARKETS_LOCATOR));
			metamarkets = getElement(By.xpath(METARMARKETS_LOCATOR));
			clients = getElement(By.xpath(CLIENTS_LOCATOR));
			reports = getElement(By.xpath(REPORTS_LOCATOR));
			notifications = getElement(By.xpath(NOTIFICATIONS_LOCATOR));
			remoteAdmin = getElement(By.xpath(REMOTE_ADMIN_LOCATOR));
			logout = getElement(By.id(LOGOUT_LOCATOR));
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public ListManagersPage toManagers() {
		this.managers.click();
		return createPageObject(ListManagersPage.class);
	}

	public void toMarkets() {
		this.markets.click();
	}

	public void toMetamarkets() {
		this.metamarkets.click();
	}

	public ListClientsPage toClients() {
		this.clients.click();
		return createPageObject(ListClientsPage.class);
	}

	public void toReports() {
		this.reports.click();
	}

	public void toNotifications() {
		this.notifications.click();
	}

	public void toRemoteAdmins() {
		this.remoteAdmin.click();
	}

	public LogoutPage logout() {
		this.logout.click();
		return createPageObject(LogoutPage.class);
	}

	public String getCurrentTitle() {
		return getElement(By.xpath(TITLE_LOCATOR)).getText();
	}

}
