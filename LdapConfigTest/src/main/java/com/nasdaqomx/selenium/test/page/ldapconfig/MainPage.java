package com.nasdaqomx.selenium.test.page.ldapconfig;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.selenium.test.base.AbstractTest;
import com.nasdaqomx.selenium.test.base.TestManager;
import com.nasdaqomx.selenium.test.base.page.AbstractPageObject;
import com.nasdaqomx.selenium.test.page.ldapconfig.clients.ListClients;

public class MainPage extends AbstractPageObject {

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

	public MainPage(TestManager testManager) {
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
			AbstractTest.fail(getProject(), e.getMessage());
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

	public ListClients clickClients() {
		this.clients.click();
		return createPageObject(ListClients.class);
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
