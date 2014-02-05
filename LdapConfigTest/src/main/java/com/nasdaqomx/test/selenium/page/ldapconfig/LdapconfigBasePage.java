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

	private static final By MAIN_MENU_LOCATOR = By.id("mainMenu");
	private static final By SELECTED_MENU_LOCATOR = By
			.className("LdapSubMenuSelected");
	private static final By MANAGERS_LOCATOR = By.linkText("Managers");
	private static final By MARKETS_LOCATOR = By.linkText("Markets");
	private static final By METARMARKETS_LOCATOR = By.linkText("Metamarkets");
	private static final By CLIENTS_LOCATOR = By.linkText("Clients");
	private static final By REPORTS_LOCATOR = By.linkText("Reports");
	private static final By NOTIFICATIONS_LOCATOR = By
			.linkText("Notifications");
	private static final By REMOTE_ADMIN_LOCATOR = By.linkText("Remote Admin");
	private static final By LOGOUT_LOCATOR = By.id("logout");

	private static final By TITLE_LOCATOR = By.className("LdapHeader");
	private static final By CONTENT_LOCATOR = By.id("content");

	private WebElement logout;
	private WebElement mainMenu;

	public LdapconfigBasePage(TestManager testManager) {
		super(testManager);
		try {
			mainMenu = findElement(MAIN_MENU_LOCATOR);
			logout = mainMenu.findElement(LOGOUT_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public ListManagersPage toManagers() {
		mainMenu.findElement(MANAGERS_LOCATOR).click();
		return createPageObject(ListManagersPage.class);
	}

	public ListMarketsPage toMarkets() {
		mainMenu.findElement(MARKETS_LOCATOR).click();
		return createPageObject(ListMarketsPage.class);
	}

	public ListMetamarketsPage toMetamarkets() {
		mainMenu.findElement(METARMARKETS_LOCATOR).click();
		return createPageObject(ListMetamarketsPage.class);
	}

	public ListClientsPage toClients() {
		mainMenu.findElement(CLIENTS_LOCATOR).click();
		return createPageObject(ListClientsPage.class);
	}

	public ReportsPage toReports() {
		mainMenu.findElement(REPORTS_LOCATOR).click();
		return createPageObject(ReportsPage.class);
	}

	public EditMessagePage toNotifications() {
		mainMenu.findElement(NOTIFICATIONS_LOCATOR).click();
		return createPageObject(EditMessagePage.class);
	}

	public RemoteAdminPage toRemoteAdmins() {
		mainMenu.findElement(REMOTE_ADMIN_LOCATOR).click();
		return createPageObject(RemoteAdminPage.class);
	}

	public LogoutPage toLogout() {
		this.logout.click();
		return createPageObject(LogoutPage.class);
	}

	public String getPageTitle() {
		return findElement(CONTENT_LOCATOR).findElement(TITLE_LOCATOR)
				.getText();
	}

	public String getSelectedMenu() {
		return mainMenu.findElement(SELECTED_MENU_LOCATOR)
				.findElement(By.tagName("a")).getText();
	}
}
