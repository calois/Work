package com.nasdaqomx.test.selenium.page.ldapconfig.clients;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.contacts.ListClientContactsPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.markets.ListClientMarketsPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.metamarkets.ListClientMetamarketsPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.users.ListClientUsersPage;

public class ClientBasePage extends LdapconfigBasePage {

	private static final By NAME_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]/td[@class='LdapClientName']");
	private static final By DETAILS_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Details']");
	private static final By AUTHORISATION_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Authorisation']");
	private static final By UPLOADS_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Uploads']");
	private static final By ALERT_STATUSES_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Alert Statuses']");
	private static final By USERS_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Users']");
	private static final By CONTACTS_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Contacts']");
	private static final By MARKETS_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Markets']");
	private static final By METAMARKETS_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Metamarkets']");
	private static final By COPY_CLIENT_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Copy Client']");
	private static final By REMOVE_CLIENT_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Remove Client']");

	private WebElement name;
	private WebElement details;
	private WebElement authorisation;
	private WebElement uploads;
	private WebElement alertStatuses;
	private WebElement users;
	private WebElement contacts;
	private WebElement markets;
	private WebElement metamarkets;
	private WebElement copyClient;
	private WebElement removeClient;

	public ClientBasePage(TestManager testManager) {
		super(testManager);
		try {
			name = findElement(NAME_LOCATOR);
			details = findElement(DETAILS_LOCATOR);
			authorisation = findElement(AUTHORISATION_LOCATOR);
			uploads = findElement(UPLOADS_LOCATOR);
			alertStatuses = findElement(ALERT_STATUSES_LOCATOR);
			users = findElement(USERS_LOCATOR);
			contacts = findElement(CONTACTS_LOCATOR);
			markets = findElement(MARKETS_LOCATOR);
			metamarkets = findElement(METAMARKETS_LOCATOR);
			copyClient = findElement(COPY_CLIENT_LOCATOR);
			removeClient = findElement(REMOVE_CLIENT_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getClientNameInMenu() {
		return this.name.getText();
	}

	public EditClientDetailsPage toClientDetails() {
		this.details.click();
		return createPageObject(EditClientDetailsPage.class);
	}

	public EditClientAuthenticationPage toClientAuthorisation() {
		this.authorisation.click();
		return createPageObject(EditClientAuthenticationPage.class);
	}

	public EditUploadConfigsPage toClientUploads() {
		this.uploads.click();
		return createPageObject(EditUploadConfigsPage.class);
	}

	public AlertManagementStatusesPage toClientAlertStatuses() {
		this.alertStatuses.click();
		return createPageObject(AlertManagementStatusesPage.class);
	}

	public ListClientUsersPage toClientUsers() {
		this.users.click();
		return createPageObject(ListClientUsersPage.class);
	}

	public ListClientContactsPage toClientContacts() {
		this.contacts.click();
		return createPageObject(ListClientContactsPage.class);
	}

	public ListClientMarketsPage toClientMarkets() {
		this.markets.click();
		return createPageObject(ListClientMarketsPage.class);
	}

	public ListClientMetamarketsPage toClientMetamarkets() {
		this.metamarkets.click();
		return createPageObject(ListClientMetamarketsPage.class);
	}

	public CopyClientPage toCopyClient() {
		this.copyClient.click();
		return createPageObject(CopyClientPage.class);
	}

	public RemoveClientPage toRemoveClient() {
		this.removeClient.click();
		return createPageObject(RemoveClientPage.class);
	}

}
