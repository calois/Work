package com.nasdaqomx.test.selenium.page.ldapconfig.metamarkets;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class MetamarketBasePage extends LdapconfigBasePage {

	private static final By CODE_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]/td[@class='LdapClientName']");
	private static final By DETAILS_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Details']");
	private static final By REMOVE_METAMARKET_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Remove Metamarket']");

	private WebElement code;
	private WebElement details;
	private WebElement removeMetamarket;

	public MetamarketBasePage(TestManager testManager) {
		super(testManager);
		try {
			code = findElement(CODE_LOCATOR);
			details = findElement(DETAILS_LOCATOR);
			removeMetamarket = findElement(REMOVE_METAMARKET_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getMetamarketCodeInMenu() {
		return this.code.getText();
	}

	public void toMetamarketDetails() {
		this.details.click();
	}

	public void toRemoveMetamarket() {
		this.removeMetamarket.click();
	}

}
