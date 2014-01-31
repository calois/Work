package com.nasdaqomx.test.selenium.page.ldapconfig.markets;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;

public class RemoveMarketPage extends MarketBasePage {

	private static final String URL = "removeMarket.view";
	private static final By REMOVER_MARKET_BTN_LOCATOR = By
			.xpath("//form/input[@value='Yes! Remove market']");
	private static final By REMOVER_TABLE_LOCATOR = By.className("LdapListing");
	private static final By REMOVER_MSG_LOCATOR = By.xpath("//form/p");
	private static final By MARKET_ID_LOCATOR = By
			.xpath("//td[contains(.,'Market id')]/following-sibling::td");
	private static final By MARKET_NAME_LOCATOR = By
			.xpath("//td[contains(.,'Market name')]/following-sibling::td");

	private WebElement removeMarketBtn;
	private WebElement removeTable;
	private WebElement removeMsg;
	private WebElement removeMarketId;
	private WebElement removeMarketName;

	public RemoveMarketPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			removeMarketBtn = findElement(REMOVER_MARKET_BTN_LOCATOR);
			removeTable = findElement(REMOVER_TABLE_LOCATOR);
			removeMsg = findElement(REMOVER_MSG_LOCATOR);
			removeMarketId = removeTable.findElement(MARKET_ID_LOCATOR);
			removeMarketName = removeTable.findElement(MARKET_NAME_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getRemoveMsg() {
		return removeMsg.getText();
	}

	public String getRemoveMarketId() {
		return removeMarketId.getText();
	}

	public String getRemoveMarketName() {
		return removeMarketName.getText();
	}

	public ListMarketsPage submitRemove() {
		this.removeMarketBtn.click();
		return createPageObject(ListMarketsPage.class);
	}

}
