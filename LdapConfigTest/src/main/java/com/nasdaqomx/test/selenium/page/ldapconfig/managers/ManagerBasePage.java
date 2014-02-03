package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class ManagerBasePage extends LdapconfigBasePage {

	private static final By ID_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]/td[@class='LdapClientName']");
	private static final By DETAILS_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Details']");
	private static final By PERMISSIONS_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Permissions']");
	private static final By RESET_PASSWORD_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Reset Password']");
	private static final By LOCK_ACCOUNT_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Lock Account']");
	private static final By REMOVE_MANAGER_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Remove Manager']");

	private WebElement id;
	private WebElement details;
	private WebElement permission;
	private WebElement resetPassword;
	private WebElement lockAccount;
	private WebElement removeManager;

	public ManagerBasePage(TestManager testManager) {
		super(testManager);
		try {
			id = findElement(ID_LOCATOR);
			details = findElement(DETAILS_LOCATOR);
			permission = findElement(PERMISSIONS_LOCATOR);
			resetPassword = findElement(RESET_PASSWORD_LOCATOR);
			lockAccount = findElement(LOCK_ACCOUNT_LOCATOR);
			removeManager = findElement(REMOVE_MANAGER_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getUserIdInMenu() {
		return this.id.getText();
	}

	public EditManagerDetailsPage toManagerDetails() {
		this.details.click();
		return createPageObject(EditManagerDetailsPage.class);
	}

	public EditManagerPermissionsPage toManagerPermissions() {
		this.permission.click();
		return createPageObject(EditManagerPermissionsPage.class);
	}

	public ResetManagerPasswordPage toResetManagerPassword() {
		this.resetPassword.click();
		return createPageObject(ResetManagerPasswordPage.class);
	}

	public LockAccountPage toLockAccount() {
		this.lockAccount.click();
		return createPageObject(LockAccountPage.class);
	}

	public RemoveManagerPage toRemoveManager() {
		this.removeManager.click();
		return createPageObject(RemoveManagerPage.class);
	}
}
