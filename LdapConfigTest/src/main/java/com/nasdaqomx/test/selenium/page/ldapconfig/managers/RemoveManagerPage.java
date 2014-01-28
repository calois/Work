package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;

public class RemoveManagerPage extends ManagerBasePage {

	private static final String URL = "removeManager.view";
	private static final By REMOVER_MANAGER_BTN_LOCATOR = By
			.xpath("//form/input[@value='Yes! Remove manager']");
	private static final By REMOVER_TABLE_LOCATOR = By.className("LdapListing");
	private static final By REMOVER_MSG_LOCATOR = By.xpath("//form/p");
	private static final By USER_ID_LOCATOR = By
			.xpath("//td[contains(.,'User id')]/following-sibling::td");
	private static final By FULL_NAME_LOCATOR = By
			.xpath("//td[contains(.,'Full name')]/following-sibling::td");

	private WebElement removeManagerBtn;
	private WebElement removeTable;
	private WebElement removeMsg;
	private WebElement removeUserId;
	private WebElement removeFullName;

	public RemoveManagerPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			removeManagerBtn = findElement(REMOVER_MANAGER_BTN_LOCATOR);
			removeTable = findElement(REMOVER_TABLE_LOCATOR);
			removeMsg = findElement(REMOVER_MSG_LOCATOR);
			removeUserId = removeTable.findElement(USER_ID_LOCATOR);
			removeFullName = removeTable.findElement(FULL_NAME_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getRemoveMsg() {
		return removeMsg.getText();
	}

	public String getRemoveUserId() {
		return removeUserId.getText();
	}

	public String getRemoveFullName() {
		return removeFullName.getText();
	}

	public ListManagersPage submitRemove() {
		this.removeManagerBtn.click();
		return createPageObject(ListManagersPage.class);
	}

}
