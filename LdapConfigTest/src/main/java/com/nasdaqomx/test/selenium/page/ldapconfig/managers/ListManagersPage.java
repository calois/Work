package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class ListManagersPage extends LdapconfigBasePage {

	private static final String URL = "listManagers.view";
	private static final String ADD_MANAGER_XPATH = "//div[@class='LdapListingOptions']/span[@class='LdapSubMenu']/a[contains(.,'Add') and contains(.,'manager')]";
	private static final String MANAGERS_LIST_CLASS = "LdapListing";
	private static final String MANAGER_ROWS_XPATH = "//tr[@id]";
	// followed by user id
	private static final String MANAGER_ROW_ID = "row-";
	// followed by contains user id
	private static final String USER_NAME_XPATH = "./td[1]//a";
	private static final String COMMON_NAME_XPATH = "./td[2]";
	private static final String RESET_PASSWORD_XPATH = "./td[3]//a[contains(.,'Reset') and contains(.,'password')]";
	private static final String LOCK_ACCOUNT_XPATH = "./td[3]//a[contains(.,'Lock') and contains(.,'account')]";
	private static final String STATUS_XPATH = "./td[4]";
	private static final String LAST_LOGIN_XPATH = "./td[5]";
	private static final String ACCOUNT_EXPIRES_XPATH = "./td[6]";

	private WebElement addManager;
	private WebElement managersTable;

	public ListManagersPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			addManager = getElement(By.xpath(ADD_MANAGER_XPATH));
			managersTable = getElement(By.className(MANAGERS_LIST_CLASS));
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public AddManagerPage toAddManager() {
		this.addManager.click();
		return createPageObject(AddManagerPage.class);
	}

	public boolean isManagerListed(String userId) {
		return isPresent(By.id(MANAGER_ROW_ID.concat(userId)));
	}

	public EditManagerDetailsPage toEditManager(String userId) {
		this.getEditLink(userId).click();
		return createPageObject(EditManagerDetailsPage.class);
	}

	public String getCommonName(String userId) {
		return getElement(getManagerRow(userId), By.xpath(COMMON_NAME_XPATH))
				.getText();
	}

	public void toResetPassword(String userId) {
		this.getResetPasswordLink(userId).click();
	}

	public void toLockAccount(String userId) {
		this.getLockAccountLink(userId).click();
	}

	public String getStatus(String userId) {
		return getElement(getManagerRow(userId), By.xpath(STATUS_XPATH))
				.getText();
	}

	public String getLastLogin(String userId) {
		return getElement(getManagerRow(userId), By.xpath(LAST_LOGIN_XPATH))
				.getText();
	}

	public String getAccountExpires(String userId) {
		return getElement(getManagerRow(userId),
				By.xpath(ACCOUNT_EXPIRES_XPATH)).getText();
	}

	private WebElement getEditLink(String userId) {
		return getElement(
				getManagerRow(userId),
				By.xpath(USER_NAME_XPATH.concat("[contains(.,'" + userId
						+ "')]")));
	}

	private WebElement getResetPasswordLink(String userId) {
		return getElement(getManagerRow(userId), By.xpath(RESET_PASSWORD_XPATH));
	}

	private WebElement getLockAccountLink(String userId) {
		return getElement(getManagerRow(userId), By.xpath(LOCK_ACCOUNT_XPATH));
	}

	private WebElement getManagerRow(String userId) {
		return getElement(managersTable, By.id(MANAGER_ROW_ID.concat(userId)));
	}

	public int getTotalManager() {
		return managersTable.findElements(By.xpath(MANAGER_ROWS_XPATH)).size();
	}

}
