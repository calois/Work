package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.nasdaqomx.test.selenium.base.TestManager;

public class EditManagerPermissionsPage extends ManagerBasePage {

	private static final String URL = "editManager_Permissions.view";

	private static final By UPDATE_BTN_LOCATOR = By
			.xpath("//form/div[@class='LdapListingOptions']/input[@value='Update all manager permissions']");
	// permission table
	private static final By PERMISSIONS_TABLE_LOCATOR = By
			.xpath("//form/table[1]");
	private static final By PERMISSIONS_ROWS_LOCATOR = By.xpath("//tr[@id]");
	private static final By PERMISSIONS_HEADER_LOCATOR = By.xpath("//tr[1]");
	private static final By PERMISSIONS_CLIENT_ID_LOCATOR = By.xpath("./td[1]");
	private static final By PERMISSIONS_MARKET_CODE_LOCATOR = By
			.xpath("./td[2]");
	private static final By PERMISSIONS_ROLE_LOCATOR = By.xpath("./td[3]");
	// security roles
	private static final By SECURITY_ROLES_TABLE_LOCATOR = By
			.xpath("//form/table[2]");
	private static final By SECURITY_ROLES_ROWS_LOCATOR = By.xpath("//tr[@id]");
	private static final By SECURITY_ROLES_HEADER_LOCATOR = By.xpath("//tr[1]");
	private static final By SECURITY_ROLES_SECURITY_ROLE_LOCATOR = By
			.xpath("./td[1]");
	private static final By SECURITY_ROLES_ALLOWED_LOCATOR = By
			.xpath("./td[2]");

	private WebElement updateBtn;
	private WebElement permissionsTable;
	private WebElement permissionsTableHeader;
	private List<WebElement> permissionRows;
	private WebElement securityRolesTable;
	private WebElement securityRolesHeader;
	private List<WebElement> securityRoleRows;

	public EditManagerPermissionsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			updateBtn = findElement(UPDATE_BTN_LOCATOR);
			permissionsTable = findElement(PERMISSIONS_TABLE_LOCATOR);
			permissionsTableHeader = permissionsTable.findElement(PERMISSIONS_HEADER_LOCATOR);
			permissionRows = permissionsTable
					.findElements(PERMISSIONS_ROWS_LOCATOR);
			securityRolesTable = findElement(SECURITY_ROLES_TABLE_LOCATOR);
			securityRolesHeader=securityRolesTable.findElement(SECURITY_ROLES_HEADER_LOCATOR);
			securityRoleRows = securityRolesTable
					.findElements(SECURITY_ROLES_ROWS_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	private WebElement getPermissionRow(int rowNumber) {
		return permissionRows.get(rowNumber - 1);
	}

	public List<String> getPermissionClientId(int rowNumber) {
		List<String> values = new ArrayList<>();
		List<WebElement> selected = new Select(getPermissionRow(rowNumber)
				.findElement(PERMISSIONS_CLIENT_ID_LOCATOR))
				.getAllSelectedOptions();
		for (WebElement element : selected) {
			values.add(element.getText());
		}
		return values;
	}

	public List<String> getPermissionMarketCode(int rowNumber) {
		List<String> values = new ArrayList<>();
		List<WebElement> selected = new Select(getPermissionRow(rowNumber)
				.findElement(PERMISSIONS_MARKET_CODE_LOCATOR))
				.getAllSelectedOptions();
		for (WebElement element : selected) {
			values.add(element.getText());
		}
		return values;
	}

	public List<String> getPermissionRole(int rowNumber) {
		List<String> values = new ArrayList<>();
		List<WebElement> selected = new Select(getPermissionRow(rowNumber)
				.findElement(PERMISSIONS_ROLE_LOCATOR)).getAllSelectedOptions();
		for (WebElement element : selected) {
			values.add(element.getText());
		}
		return values;
	}

	public int getTotalPermissionRows() {
		return permissionRows.size();
	}

	public int getTotalSecurityRoleRows() {
		return permissionRows.size();
	}

	public EditManagerPermissionsPage submitEdit() {
		this.updateBtn.click();
		return createPageObject(EditManagerPermissionsPage.class);
	}

}
