package com.nasdaqomx.ldapconfig.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.ldapconfig.test.base.AbstractTest;
import com.nasdaqomx.ldapconfig.test.base.TestObject;
import com.nasdaqomx.ldapconfig.test.base.page.AbstractPageObject;

public class MainPage extends AbstractPageObject {

	private final String MANAGERS_LOCATOR = "//div[@id='mainMenu']//a[text()='Managers']";
	private final String MARKETS_LOCATOR = "//div[@id='mainMenu']//a[text()='Markets']";
	private final String METARMARKETS_LOCATOR = "//div[@id='mainMenu']//a[text()='Metamarkets']";
	private final String CLIENTS_LOCATOR = "//div[@id='mainMenu']//a[text()='Clients']";
	private final String REPORTS_LOCATOR = "//div[@id='mainMenu']//a[text()='Reports']";
	private final String NOTIFICATIONS_LOCATOR = "//div[@id='mainMenu']//a[text()='Notifications']";
	private final String REMOTE_ADMIN_LOCATOR = "//div[@id='mainMenu']//a[text()='Remote Admin']";
	private final String LOGOUT_LOCATOR = "//div[@id='mainMenu']//a[text()='Logout']";

	private WebElement managers;
	private WebElement markets;
	private WebElement metamarkets;
	private WebElement clients;
	private WebElement reports;
	private WebElement notifications;
	private WebElement remoteAdmin;
	private WebElement logout;

	public MainPage(TestObject testObject) {
		super(testObject);
		try {
			managers = getBy(By.xpath(MANAGERS_LOCATOR));
			markets = getBy(By.xpath(MARKETS_LOCATOR));
			metamarkets = getBy(By.xpath(METARMARKETS_LOCATOR));
			clients = getBy(By.xpath(CLIENTS_LOCATOR));
			reports = getBy(By.xpath(REPORTS_LOCATOR));
			notifications = getBy(By.xpath(NOTIFICATIONS_LOCATOR));
			remoteAdmin = getBy(By.xpath(REMOTE_ADMIN_LOCATOR));
			logout = getBy(By.xpath(LOGOUT_LOCATOR));
		} catch (NoSuchElementException e) {
			AbstractTest.fail(e.getMessage());
		}
	}

	public void clickManagers() {
		this.managers.click();
	}

	public void clickMarkets() {
		this.markets.click();
	}

	public void clickMetamarkets() {
		this.metamarkets.click();
	}

	public void clickClients() {
		this.clients.click();
	}

	public void clickReports() {
		this.reports.click();
	}

	public void clickNotifications() {
		this.notifications.click();
	}

	public void clickRemoteAdmins() {
		this.remoteAdmin.click();
	}

	public LogoutPage clickLogout() {
		this.logout.click();
		return createPageObject(LogoutPage.class);
	}

}
