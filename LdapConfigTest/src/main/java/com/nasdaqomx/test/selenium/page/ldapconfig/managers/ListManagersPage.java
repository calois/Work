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
		assertUrl(URL, this.getSimpleUrl(), false);
		try {
			addManager = getElement(By.xpath(ADD_MANAGER_LOCATOR));
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public AddManagerPage clickAddManager() {
		this.addManager.click();
		return createPageObject(AddManagerPage.class);
	}

	public boolean isManagerListed(String userName) {
		return isPresent(By.id("row-".concat(userName)));
	}

	public EditManagerDetailsPage editManager(String userName) {
		this.getEditLink(userName).click();
		return createPageObject(EditManagerDetailsPage.class);
	}

	public void resetPassword(String userName) {
		this.getResetPasswordLink(userName).click();
	}

	public void lockAccount(String userName) {
		this.getLockAccountLink(userName).click();
	}

	private WebElement getEditLink(String userName) {
		return getElement(By
				.xpath("//table[@class='LdapListing']/tbody/tr[@id='row-"
						+ userName + "']//a[contains(.,'" + userName + "')]"));
	}

	private WebElement getResetPasswordLink(String userName) {
		return getElement(By
				.xpath("//table[@class='LdapListing']/tbody/tr[@id='row-"
						+ userName
						+ "']//a[contains(.,'Reset') and contains(.,'password')]"));
	}

	private WebElement getLockAccountLink(String userName) {
		return getElement(By
				.xpath("//table[@class='LdapListing']/tbody/tr[@id='row-"
						+ userName
						+ "']//a[contains(.,'Lock') and contains(.,'account')]"));
	}

}
