package com.nasdaqomx.test.selenium.page.ldapconfig.metamarkets;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class ListMetamarketsPage extends LdapconfigBasePage {

	private static final String URL = "listMetaMarkets.view";

	private static final By ADD_METAMARKET_LOCATOR = By
			.xpath("//div[@class='LdapListingOptions']/span[@class='LdapSubMenu']/a[text()='Add metamarket']");

	private static final By METAMARKETS_LIST_LOCATOR = By.className("LdapListing");
	private static final By METAMARKET_ROWS_LOCATOR = By.xpath("//tr[@id]");
	// followed by metamarket name
	private static final String METAMARKET_ROW_ID_PREFIX = "row-";
	private static final By METAMARKET_IMAGE_LOCATOR = By.xpath("./td[1]//a");
	private static final By METAMARKET_CODE_LOCATOR = By.xpath("./td[2]//a");
	private static final By DESCRIPTION_LOCATOR = By.xpath("./td[3]");
	private static final By REMOVE_LOCATOR = By
			.xpath("./td[4]//a[contains(.,'Remove') and contains(.,'metamarket')]");

	private WebElement addMetamarket;
	private WebElement metamarketsTable;

	public ListMetamarketsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			addMetamarket = findElement(ADD_METAMARKET_LOCATOR);
			metamarketsTable = findElement(METAMARKETS_LIST_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getDescription(String metamarketCode) {
		return getMetamarketRow(metamarketCode).findElement(DESCRIPTION_LOCATOR)
				.getText();
	}

	public AddMetamarketPage toAddMetamarket() {
		this.addMetamarket.click();
		return createPageObject(AddMetamarketPage.class);
	}

	public int getTotalNumber() {
		return metamarketsTable.findElements(METAMARKET_ROWS_LOCATOR).size();
	}

	public boolean isMetamarketListed(String metamarketCode) {
		try {
			return isTextContainedAfterWait(getMetamarketRow(metamarketCode)
					.findElement(METAMARKET_CODE_LOCATOR), metamarketCode);
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public EditMetamarketDetailsPage toEditMetamarketByClickingImage(String metamarketCode) {
		this.getMetamarketImageLink(metamarketCode).click();
		return createPageObject(EditMetamarketDetailsPage.class);
	}

	public EditMetamarketDetailsPage toEditMetamarketByClickingCode(String metamarketCode) {
		this.getMetamarketCodeLink(metamarketCode).click();
		return createPageObject(EditMetamarketDetailsPage.class);
	}

	public RemoveMetamarketPage toRemoveMetamarket(String metamarketCode) {
		this.getRemoveLink(metamarketCode).click();
		return createPageObject(RemoveMetamarketPage.class);
	}

	private WebElement getMetamarketImageLink(String metamarketCode) {
		return getMetamarketRow(metamarketCode).findElement(METAMARKET_IMAGE_LOCATOR);
	}

	private WebElement getMetamarketCodeLink(String metamarketCode) {
		return getMetamarketRow(metamarketCode).findElement(METAMARKET_CODE_LOCATOR);
	}

	private WebElement getRemoveLink(String metamarketCode) {
		return getMetamarketRow(metamarketCode).findElement(REMOVE_LOCATOR);
	}

	private WebElement getMetamarketRow(String marketCode) {
		return metamarketsTable.findElement(By.id(METAMARKET_ROW_ID_PREFIX
				.concat(marketCode)));
	}

}
