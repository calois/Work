package com.nasdaqomx.test.selenium.page.ldapconfig.clients;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;

public class RemoveClientPage extends ClientBasePage {

	private static final String URL = "removeClient.view";
	private static final By REMOVER_CLIENT_BTN_LOCATOR = By
			.xpath("//form/input[@value='Yes! Remove client']");
	private static final By REMOVER_TABLE_LOCATOR = By.className("LdapListing");
	private static final By REMOVER_MSG_LOCATOR = By.xpath("//form/p");
	private static final By CLIENT_ID_LOCATOR = By
			.xpath("//td[contains(.,'Client id')]/following-sibling::td");
	private static final By CLIENT_NAME_LOCATOR = By
			.xpath("//td[contains(.,'Client name')]/following-sibling::td");

	private WebElement removeClientBtn;
	private WebElement removeTable;
	private WebElement removeMsg;
	private WebElement removeClientId;
	private WebElement removeClientName;

	public RemoveClientPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			removeClientBtn = findElement(REMOVER_CLIENT_BTN_LOCATOR);
			removeTable = findElement(REMOVER_TABLE_LOCATOR);
			removeMsg = findElement(REMOVER_MSG_LOCATOR);
			removeClientId = removeTable.findElement(CLIENT_ID_LOCATOR);
			removeClientName = removeTable.findElement(CLIENT_NAME_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getRemoveMsg() {
		return removeMsg.getText();
	}

	public String getRemoveClientId() {
		return removeClientId.getText();
	}

	public String getRemoveClientName() {
		return removeClientName.getText();
	}

	public ListClientsPage submitRemove() {
		this.removeClientBtn.click();
		return createPageObject(ListClientsPage.class);
	}

}
