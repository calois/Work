package com.nasdaqomx.test.selenium.page.ldapconfig.markets;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class ListMarketsPage extends LdapconfigBasePage {

	private static final String URL = "listMarkets.view";

	private static final By ADD_MARKET_LOCATOR = By
			.xpath("//div[@class='LdapListingOptions']/span[@class='LdapSubMenu']/a[text()='Add market']");

	private static final By MARKETS_LIST_LOCATOR = By.className("LdapListing");
	private static final By MARKET_ROWS_LOCATOR = By.xpath("//tr[@id]");
	// followed by market name
	private static final String MARKET_ROW_ID_PREFIX = "row-";
	private static final By MARKET_IMAGE_LOCATOR = By.xpath("./td[1]//a");
	private static final By MARKET_CODE_LOCATOR = By.xpath("./td[2]//a");
	private static final By DESCRIPTION_LOCATOR = By.xpath("./td[3]");
	private static final By REMOVE_LOCATOR = By
			.xpath("./td[4]//a[contains(.,'Remove') and contains(.,'market')]");

	private WebElement addMarket;
	private WebElement marketsTable;

	public ListMarketsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			addMarket = findElement(ADD_MARKET_LOCATOR);
			marketsTable = findElement(MARKETS_LIST_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getDescription(String marketCode) {
		return getMarketRow(marketCode).findElement(DESCRIPTION_LOCATOR)
				.getText();
	}

	public AddMarketPage toAddMarket() {
		this.addMarket.click();
		return createPageObject(AddMarketPage.class);
	}

	public int getTotalNumber() {
		return marketsTable.findElements(MARKET_ROWS_LOCATOR).size();
	}

	public boolean isMarketListed(String marketCode) {
		try {
			return isTextContainedAfterWait(getMarketRow(marketCode)
					.findElement(MARKET_CODE_LOCATOR), marketCode);
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public EditMarketDetailsPage toEditMarketByClickingImage(String marketCode) {
		this.getMarketImageLink(marketCode).click();
		return createPageObject(EditMarketDetailsPage.class);
	}

	public EditMarketDetailsPage toEditMarketByClickingCode(String marketCode) {
		this.getMarketCodeLink(marketCode).click();
		return createPageObject(EditMarketDetailsPage.class);
	}

	public RemoveMarketPage toRemoveMarket(String marketCode) {
		this.getRemoveLink(marketCode).click();
		return createPageObject(RemoveMarketPage.class);
	}

	private WebElement getMarketImageLink(String marketCode) {
		return getMarketRow(marketCode).findElement(MARKET_IMAGE_LOCATOR);
	}

	private WebElement getMarketCodeLink(String marketCode) {
		return getMarketRow(marketCode).findElement(MARKET_CODE_LOCATOR);
	}

	private WebElement getRemoveLink(String marketCode) {
		return getMarketRow(marketCode).findElement(REMOVE_LOCATOR);
	}

	private WebElement getMarketRow(String marketCode) {
		return marketsTable.findElement(By.id(MARKET_ROW_ID_PREFIX
				.concat(marketCode)));
	}

}
