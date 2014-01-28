package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class ListManagersPage extends LdapconfigBasePage {

	private static final String URL = "listManagers.view";

	private static final By ADD_MANAGER_LOCATOR = By
			.xpath("//div[@class='LdapListingOptions']/span[@class='LdapSubMenu']/a[contains(.,'Add') and contains(.,'manager')]");
	private static final By MANAGERS_LIST_LOCATOR = By.className("LdapListing");
	private static final By MANAGER_ROWS_LOCATOR = By.xpath("//tr[@id]");
	// followed by user id
	private static final String MANAGER_ROW_ID_PREFIX = "row-";
	private static final By USER_NAME_LOCATOR = By.xpath("./td[1]//a");
	private static final By COMMON_NAME_LOCATOR = By.xpath("./td[2]");
	private static final By RESET_PASSWORD_LOCATOR = By
			.xpath("./td[3]//a[contains(.,'Reset') and contains(.,'password')]");
	private static final By LOCK_ACCOUNT_LOCATOR = By
			.xpath("./td[3]//a[contains(.,'Lock') and contains(.,'account')]");
	private static final By STATUS_LOCATOR = By.xpath("./td[4]");
	private static final By LAST_LOGIN_LOCATOR = By.xpath("./td[5]");
	private static final By ACCOUNT_EXPIRES_LOCATOR = By.xpath("./td[6]");

	private WebElement addManager;
	private WebElement managersTable;

	public ListManagersPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			addManager = findElement(ADD_MANAGER_LOCATOR);
			managersTable = findElement(MANAGERS_LIST_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public AddManagerPage toAddManager() {
		this.addManager.click();
		return createPageObject(AddManagerPage.class);
	}

	public boolean isManagerListed(String userId) {
		try {
			return isTextContainedAfterWait(
					getManagerRow(userId).findElement(USER_NAME_LOCATOR),
					userId);
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public EditManagerDetailsPage toEditManager(String userId) {
		this.getEditLink(userId).click();
		return createPageObject(EditManagerDetailsPage.class);
	}

	public String getUserName(String userId) {
		return getManagerRow(userId).findElement(USER_NAME_LOCATOR).getText();
	}

	public String getCommonName(String userId) {
		return getManagerRow(userId).findElement(COMMON_NAME_LOCATOR).getText();
	}

	public void toResetPassword(String userId) {
		this.getResetPasswordLink(userId).click();
	}

	public void toLockAccount(String userId) {
		this.getLockAccountLink(userId).click();
	}

	public String getStatus(String userId) {
		return getManagerRow(userId).findElement(STATUS_LOCATOR).getText();
	}

	public String getLastLogin(String userId) {
		return getManagerRow(userId).findElement(LAST_LOGIN_LOCATOR).getText();
	}

	public String getAccountExpires(String userId) {
		return getManagerRow(userId).findElement(ACCOUNT_EXPIRES_LOCATOR)
				.getText();
	}

	public int getTotalNumber() {
		return managersTable.findElements(MANAGER_ROWS_LOCATOR).size();
	}

	private WebElement getEditLink(String userId) {
		return getManagerRow(userId).findElement(USER_NAME_LOCATOR);
	}

	private WebElement getResetPasswordLink(String userId) {
		return getManagerRow(userId).findElement(RESET_PASSWORD_LOCATOR);
	}

	private WebElement getLockAccountLink(String userId) {
		return getManagerRow(userId).findElement(LOCK_ACCOUNT_LOCATOR);
	}

	private WebElement getManagerRow(String userId) {
		return managersTable.findElement(By.id(MANAGER_ROW_ID_PREFIX
				.concat(userId)));
	}
}
