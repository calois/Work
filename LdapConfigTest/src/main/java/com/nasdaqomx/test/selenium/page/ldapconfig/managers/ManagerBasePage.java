package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class ManagerBasePage extends LdapconfigBasePage {

	private static final String ID_XPATH = "//div[@id='mainMenu']//tr[3]/td[@class='LdapClientName']";
	private static final String DETAILS_XPATH = "//div[@id='mainMenu']//a[text()='Details']";
	private static final String PERMISSIONS_XPATH = "//div[@id='mainMenu']//a[text()='Permissions']";
	private static final String RESET_PASSWORD_XPATH = "//div[@id='mainMenu']//a[text()='Reset Password']";
	private static final String LOCK_ACCOUNT_XPATH = "//div[@id='mainMenu']//a[text()='Lock Account']";
	private static final String REMOVE_MANAGER_XPATH = "//div[@id='mainMenu']//a[text()='Remove Manager']";

	private WebElement id;
	private WebElement details;
	private WebElement permission;
	private WebElement resetPassword;
	private WebElement lockAccount;
	private WebElement removeManager;

	public ManagerBasePage(TestManager testManager) {
		super(testManager);
		try {
			id = getElement(By.xpath(ID_XPATH));
			details = getElement(By.xpath(DETAILS_XPATH));
			permission = getElement(By.xpath(PERMISSIONS_XPATH));
			resetPassword = getElement(By.xpath(RESET_PASSWORD_XPATH));
			lockAccount = getElement(By.xpath(LOCK_ACCOUNT_XPATH));
			removeManager = getElement(By.xpath(REMOVE_MANAGER_XPATH));
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getUserIdInMenu() {
		return this.id.getText();
	}

	public EditManagerDetailsPage toDetails() {
		this.details.click();
		return createPageObject(EditManagerDetailsPage.class);
	}

	public EditManagerPermissionsPage toPermissions() {
		this.permission.click();
		return createPageObject(EditManagerPermissionsPage.class);
	}

	public void toResetPassword() {
		this.resetPassword.click();
	}

	public void toLockAccount() {
		this.lockAccount.click();
	}

	public RemoveManagerPage toRemoveManager() {
		this.removeManager.click();
		return createPageObject(RemoveManagerPage.class);
	}
}
