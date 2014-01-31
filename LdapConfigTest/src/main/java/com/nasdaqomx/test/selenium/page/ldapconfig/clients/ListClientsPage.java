package com.nasdaqomx.test.selenium.page.ldapconfig.clients;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class ListClientsPage extends LdapconfigBasePage {

	private static final String URL = "listClients.view";

	private static final By ADD_CLIENT_LOCATOR = By
			.xpath("//div[@class='LdapListingOptions']/span[@class='LdapSubMenu']/a[text()='Add client']");

	private static final By CLIENTS_LIST_LOCATOR = By.className("LdapListing");
	private static final By CLIENT_ROWS_LOCATOR = By.xpath("//tr[@id]");
	// followed by client name
	private static final String CLIENT_ROW_ID_PREFIX = "row-";
	private static final By CLIENT_IMAGE_LOCATOR = By.xpath("./td[1]//a");
	private static final By CLIENT_NAME_LOCATOR = By.xpath("./td[2]//a");
	private static final By DESCRIPTION_LOCATOR = By.xpath("./td[3]");
	private static final By CLIENT_URL_LOCATOR = By.xpath("./td[4]//a");
	private static final By EDIT_LOCATOR = By
			.xpath("./td[5]//a[text()='Edit']");
	private static final By COPY_LOCATOR = By
			.xpath("./td[5]//a[text()='Copy']");
	private static final By REMOVE_LOCATOR = By
			.xpath("./td[5]//a[text()='Remove']");

	private WebElement addClient;
	private WebElement clientsTable;

	public ListClientsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			addClient = findElement(ADD_CLIENT_LOCATOR);
			clientsTable = findElement(CLIENTS_LIST_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getDescription(String clientName) {
		return getClientRow(clientName).findElement(DESCRIPTION_LOCATOR)
				.getText();
	}

	public AddClientPage toAddClient() {
		this.addClient.click();
		return createPageObject(AddClientPage.class);
	}

	public int getTotalNumber() {
		return clientsTable.findElements(CLIENT_ROWS_LOCATOR).size();
	}

	public boolean isClientListed(String clientName) {
		try {
			return isTextContainedAfterWait(getClientRow(clientName)
					.findElement(CLIENT_NAME_LOCATOR), clientName);
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	// TODO return client details page
	public void toEditClientByClickingImage(String clientName) {
		this.getClientImageLink(clientName).click();
	}

	// TODO return client details page
	public void toEditClientByClickingEdit(String clientName) {
		this.getEditLink(clientName).click();
	}

	// TODO return client users page
	public void toUsersByClickingName(String clientName) {
		this.getClientNameLink(clientName).click();
	}

	// TODO return copy client page
	public void toCopyClient(String clientName) {
		this.getCopyLink(clientName).click();
	}

	// TODO return remove client page
	public RemoveClientPage toRemoveClient(String clientName) {
		this.getRemoveLink(clientName).click();
		return createPageObject(RemoveClientPage.class);
	}

	// TODO return SMARTS broker login page
	public void toSmartsBrokerLogn(String clientName) {
		this.getClientUrlLink(clientName).click();
	}

	private WebElement getClientImageLink(String clientName) {
		return getClientRow(clientName).findElement(CLIENT_IMAGE_LOCATOR);
	}

	private WebElement getClientNameLink(String clientName) {
		return getClientRow(clientName).findElement(CLIENT_NAME_LOCATOR);
	}

	private WebElement getClientUrlLink(String clientName) {
		return getClientRow(clientName).findElement(CLIENT_URL_LOCATOR);
	}

	private WebElement getEditLink(String clientName) {
		return getClientRow(clientName).findElement(EDIT_LOCATOR);
	}

	private WebElement getCopyLink(String clientName) {
		return getClientRow(clientName).findElement(COPY_LOCATOR);
	}

	private WebElement getRemoveLink(String clientName) {
		return getClientRow(clientName).findElement(REMOVE_LOCATOR);
	}

	private WebElement getClientRow(String clientName) {
		return clientsTable.findElement(By.id(CLIENT_ROW_ID_PREFIX
				.concat(clientName)));
	}

}
