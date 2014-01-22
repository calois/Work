package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class ListManagersPage extends LdapconfigBasePage {

	private static final String URL = "listManagers.view";
	private static final String ADD_MANAGER_LOCATOR = "//div[@class='LdapListingOptions']/span[@class='LdapSubMenu']/a[contains(.,'Add') and contains(.,'manager')]";

	private WebElement addManager;

	public ListManagersPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			addManager = getElement(By.xpath(ADD_MANAGER_LOCATOR));
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public AddManagerPage addManager() {
		this.addManager.click();
		return createPageObject(AddManagerPage.class);
	}

	public boolean isManagerListed(String userId) {
		return isPresent(By.id("row-".concat(userId)));
	}

	public EditManagerDetailsPage editManager(String userId) {
		this.getEditLink(userId).click();
		return createPageObject(EditManagerDetailsPage.class);
	}

	public String getCommonName(String userId) {
		return getElement(
				By.xpath("//table[@class='LdapListing']/tbody/tr[@id='row-"
						+ userId + "']/td[2]")).getText();
	}

	public String getStatus(String userId) {
		return getElement(
				By.xpath("//table[@class='LdapListing']/tbody/tr[@id='row-"
						+ userId + "']/td[4]")).getText();
	}

	public String getLastLogin(String userId) {
		return getElement(
				By.xpath("//table[@class='LdapListing']/tbody/tr[@id='row-"
						+ userId + "']/td[5]")).getText();
	}

	public String getAccountExpires(String userId) {
		return getElement(
				By.xpath("//table[@class='LdapListing']/tbody/tr[@id='row-"
						+ userId + "']/td[6]")).getText();
	}

	public void resetPassword(String userId) {
		this.getResetPasswordLink(userId).click();
	}

	public void lockAccount(String userId) {
		this.getLockAccountLink(userId).click();
	}

	private WebElement getEditLink(String userId) {
		return getElement(By
				.xpath("//table[@class='LdapListing']/tbody/tr[@id='row-"
						+ userId + "']/td[1]//a[contains(.,'" + userId + "')]"));
	}

	private WebElement getResetPasswordLink(String userId) {
		return getElement(By
				.xpath("//table[@class='LdapListing']/tbody/tr[@id='row-"
						+ userId
						+ "']/td[3]//a[contains(.,'Reset') and contains(.,'password')]"));
	}

	private WebElement getLockAccountLink(String userId) {
		return getElement(By
				.xpath("//table[@class='LdapListing']/tbody/tr[@id='row-"
						+ userId
						+ "']/td[3]//a[contains(.,'Lock') and contains(.,'account')]"));
	}

}
