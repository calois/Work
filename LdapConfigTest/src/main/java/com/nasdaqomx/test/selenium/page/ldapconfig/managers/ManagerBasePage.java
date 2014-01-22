package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class ManagerBasePage extends LdapconfigBasePage {

	private static final String ID_LOCATOR = "//div[@id='mainMenu']//tr[3]/td[@class='LdapClientName']";
	private static final String DETAILS_LOCATOR = "//div[@id='mainMenu']//a[text()='Details']";
	private static final String PERMISSIONS_LOCATOR = "//div[@id='mainMenu']//a[text()='Permissions']";
	private static final String RESET_PASSWORD_LOCATOR = "//div[@id='mainMenu']//a[text()='Reset Password']";
	private static final String LOCK_ACCOUNT_LOCATOR = "//div[@id='mainMenu']//a[text()='Lock Account']";
	private static final String REMOVE_MANAGER_LOCATOR = "//div[@id='mainMenu']//a[text()='Remove Manager']";

	private WebElement id;
	private WebElement details;
	private WebElement permission;
	private WebElement resetPassword;
	private WebElement lockAccount;
	private WebElement removeManager;

	public ManagerBasePage(TestManager testManager) {
		super(testManager);
		try {
			id = getElement(By.xpath(ID_LOCATOR));
			details = getElement(By.xpath(DETAILS_LOCATOR));
			permission = getElement(By.xpath(PERMISSIONS_LOCATOR));
			resetPassword = getElement(By.xpath(RESET_PASSWORD_LOCATOR));
			lockAccount = getElement(By.xpath(LOCK_ACCOUNT_LOCATOR));
			removeManager = getElement(By.xpath(REMOVE_MANAGER_LOCATOR));
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getId() {
		return this.id.getText();
	}

	public EditManagerDetailsPage clickDetails() {
		this.details.click();
		return createPageObject(EditManagerDetailsPage.class);
	}

	public EditManagerPermissionsPage clickPermissions() {
		this.permission.click();
		return createPageObject(EditManagerPermissionsPage.class);
	}

	public void clickRestPassword() {
		this.resetPassword.click();
	}

	public void clickLockAccount() {
		this.lockAccount.click();
	}

	public void clickRemoveManager() {
		this.removeManager.click();
	}
}
