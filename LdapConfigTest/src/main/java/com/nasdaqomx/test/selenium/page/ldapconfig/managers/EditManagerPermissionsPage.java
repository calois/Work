package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

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
	private static final By PERMISSIONS_ROWS_LOCATOR = By.xpath(".//tr[@id]");
	private static final By PERMISSIONS_HEADERS_LOCATOR = By
			.xpath(".//tr[1]/th");
	private static final By PERMISSIONS_CLIENT_ID_LOCATOR = By
			.xpath("./td[1]/select");
	private static final By PERMISSIONS_MARKET_CODE_LOCATOR = By
			.xpath("./td[2]/select");
	private static final By PERMISSIONS_ROLE_LOCATOR = By
			.xpath("./td[3]/select");
	// security roles
	private static final By SECURITY_ROLES_TABLE_LOCATOR = By
			.xpath("//form/table[2]");
	private static final By SECURITY_ROLES_ROWS_LOCATOR = By
			.xpath(".//tr[@id]");
	private static final By SECURITY_ROLES_HEADER_LOCATOR = By
			.xpath(".//tr[1]/th");
	private static final By SECURITY_ROLES_SECURITY_ROLE_LOCATOR = By
			.xpath("./td[1]");
	private static final By SECURITY_ROLES_ALLOWED_LOCATOR = By
			.xpath("./td[2]/input[@type='checkbox']");

	private WebElement updateBtn;
	private WebElement permissionsTable;
	private List<WebElement> permissionsTableHeaders;
	private List<WebElement> permissionRows;
	private WebElement securityRolesTable;
	private List<WebElement> securityRolesHeaders;
	private List<WebElement> securityRoleRows;

	public EditManagerPermissionsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			updateBtn = findElement(UPDATE_BTN_LOCATOR);
			permissionsTable = findElement(PERMISSIONS_TABLE_LOCATOR);
			permissionsTableHeaders = permissionsTable
					.findElements(PERMISSIONS_HEADERS_LOCATOR);
			permissionRows = permissionsTable
					.findElements(PERMISSIONS_ROWS_LOCATOR);
			securityRolesTable = findElement(SECURITY_ROLES_TABLE_LOCATOR);
			securityRolesHeaders = securityRolesTable
					.findElements(SECURITY_ROLES_HEADER_LOCATOR);
			securityRoleRows = securityRolesTable
					.findElements(SECURITY_ROLES_ROWS_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public List<String> getClientId(int rowNumber) {
		return getTextList(getClientIdSelect(rowNumber).getAllSelectedOptions());
	}

	public void selectClientId(int rowNumber, String value) {
		getClientIdSelect(rowNumber).selectByVisibleText(value);
	}

	public List<String> getMarketCode(int rowNumber) {
		return getTextList(getMarketCodeSelect(rowNumber)
				.getAllSelectedOptions());
	}

	public void selectMarketCode(int rowNumber, String value) {
		getMarketCodeSelect(rowNumber).selectByVisibleText(value);
	}

	public List<String> getRole(int rowNumber) {
		return getTextList(getRoleSelect(rowNumber).getAllSelectedOptions());
	}

	public void selectRole(int rowNumber, String value) {
		getRoleSelect(rowNumber).selectByVisibleText(value);
	}

	public String getSecurityRole(int rowNumber) {
		return getSecurityRolesRow(rowNumber).findElement(
				SECURITY_ROLES_SECURITY_ROLE_LOCATOR).getText();
	}

	public boolean isAllowed(int rowNumber) {
		return Boolean.valueOf(getSecurityRolesRow(rowNumber).findElement(
				SECURITY_ROLES_ALLOWED_LOCATOR).getAttribute("value"));
	}

	public int getTotalPermissionRows() {
		return permissionRows.size();
	}

	public int getTotalSecurityRoleRows() {
		return securityRoleRows.size();
	}

	public List<String> getPermissionsHeaders() {
		return getTextList(permissionsTableHeaders);
	}

	public List<String> getSecurityRolesHeaders() {
		return getTextList(securityRolesHeaders);
	}

	public EditManagerPermissionsPage submitEdit() {
		this.updateBtn.click();
		return createPageObject(EditManagerPermissionsPage.class);
	}

	private WebElement getSecurityRolesRow(int rowNumber) {
		return securityRoleRows.get(rowNumber - 1);
	}

	private WebElement getPermissionRow(int rowNumber) {
		return permissionRows.get(rowNumber - 1);
	}

	private Select getClientIdSelect(int rowNumber) {
		return new Select(getPermissionRow(rowNumber).findElement(
				PERMISSIONS_CLIENT_ID_LOCATOR));
	}

	private Select getMarketCodeSelect(int rowNumber) {
		return new Select(getPermissionRow(rowNumber).findElement(
				PERMISSIONS_MARKET_CODE_LOCATOR));
	}

	private Select getRoleSelect(int rowNumber) {
		return new Select(getPermissionRow(rowNumber).findElement(
				PERMISSIONS_ROLE_LOCATOR));
	}

}
