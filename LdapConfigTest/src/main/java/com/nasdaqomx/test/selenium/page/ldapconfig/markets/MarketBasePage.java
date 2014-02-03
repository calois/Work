package com.nasdaqomx.test.selenium.page.ldapconfig.markets;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class MarketBasePage extends LdapconfigBasePage {

	private static final By CODE_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]/td[@class='LdapClientName']");
	private static final By DETAILS_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Details']");
	private static final By REMOVE_MARKET_LOCATOR = By
			.xpath("//div[@id='mainMenu']/table//tr[3]//a[text()='Remove Market']");

	private WebElement code;
	private WebElement details;
	private WebElement removeMarket;

	public MarketBasePage(TestManager testManager) {
		super(testManager);
		try {
			code = findElement(CODE_LOCATOR);
			details = findElement(DETAILS_LOCATOR);
			removeMarket = findElement(REMOVE_MARKET_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getMarketCodeInMenu() {
		return this.code.getText();
	}

	public EditMarketDetailsPage toMarketDetails() {
		this.details.click();
		return createPageObject(EditMarketDetailsPage.class);
	}

	public RemoveMarketPage toRemoveMarket() {
		this.removeMarket.click();
		return createPageObject(RemoveMarketPage.class);
	}

}
