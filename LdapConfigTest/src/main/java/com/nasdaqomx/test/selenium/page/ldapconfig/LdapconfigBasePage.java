package com.nasdaqomx.test.selenium.page.ldapconfig;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.base.page.AbstractPageObject;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.ListClientsPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.ListManagersPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.markets.ListMarketsPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.metamarkets.ListMetamarketsPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.notifications.EditMessagePage;
import com.nasdaqomx.test.selenium.page.ldapconfig.remoteadmin.RemoteAdminPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.reports.ReportsPage;

public class LdapconfigBasePage extends AbstractPageObject {

	private static final By TITLE_LOCATOR = By.className("LdapHeader");
	private static final By MANAGERS_LOCATOR = By
			.xpath("//div[@id='mainMenu']//a[text()='Managers']");
	private static final By MARKETS_LOCATOR = By
			.xpath("//div[@id='mainMenu']//a[text()='Markets']");
	private static final By METARMARKETS_LOCATOR = By
			.xpath("//div[@id='mainMenu']//a[text()='Metamarkets']");
	private static final By CLIENTS_LOCATOR = By
			.xpath("//div[@id='mainMenu']//a[text()='Clients']");
	private static final By REPORTS_LOCATOR = By
			.xpath("//div[@id='mainMenu']//a[text()='Reports']");
	private static final By NOTIFICATIONS_LOCATOR = By
			.xpath("//div[@id='mainMenu']//a[text()='Notifications']");
	private static final By REMOTE_ADMIN_LOCATOR = By
			.xpath("//div[@id='mainMenu']//a[text()='Remote Admin']");
	private static final By LOGOUT_LOCATOR = By.id("logout");

	private WebElement logout;

	public LdapconfigBasePage(TestManager testManager) {
		super(testManager);
		try {
			logout = findElement(LOGOUT_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public ListManagersPage toManagers() {
		findElement(MANAGERS_LOCATOR).click();
		return createPageObject(ListManagersPage.class);
	}

	public ListMarketsPage toMarkets() {
		findElement(MARKETS_LOCATOR).click();
		return createPageObject(ListMarketsPage.class);
	}

	public ListMetamarketsPage toMetamarkets() {
		findElement(METARMARKETS_LOCATOR).click();
		return createPageObject(ListMetamarketsPage.class);
	}

	public ListClientsPage toClients() {
		findElement(CLIENTS_LOCATOR).click();
		return createPageObject(ListClientsPage.class);
	}

	public ReportsPage toReports() {
		findElement(REPORTS_LOCATOR).click();
		return createPageObject(ReportsPage.class);
	}

	public EditMessagePage toNotifications() {
		findElement(NOTIFICATIONS_LOCATOR).click();
		return createPageObject(EditMessagePage.class);
	}

	public RemoteAdminPage toRemoteAdmins() {
		findElement(REMOTE_ADMIN_LOCATOR).click();
		return createPageObject(RemoteAdminPage.class);
	}

	public LogoutPage toLogout() {
		this.logout.click();
		return createPageObject(LogoutPage.class);
	}

	public String getPageTitle() {
		return findElement(TITLE_LOCATOR).getText();
	}
}
