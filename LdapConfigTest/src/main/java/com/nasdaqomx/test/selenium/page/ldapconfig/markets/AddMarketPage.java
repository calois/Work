package com.nasdaqomx.test.selenium.page.ldapconfig.markets;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class AddMarketPage extends LdapconfigBasePage {

	private static final String URL = "addClient.view";

	private static final By MARKET_CODE_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Market code:']/following-sibling::td/input");
	private static final By MARKET_CODE_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Market code:']/following-sibling::td[@class='LdapErrorMsg']");
	private static final By MARKET_NAME_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Market name:']/following-sibling::td/input");
	private static final By MARKET_NAME_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Market name:']/following-sibling::td[@class='LdapErrorMsg']");

	private static final By IMAGE_UPLOAD_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Image upload:']/following-sibling::td/input");
	private static final By IMAGE_UPLOAD_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Image upload:']/following-sibling::td[@class='ldapErrorMsg']");

	private static final By SUPPORT_EMAIL_ADDRESS_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Support Email Address:']/following-sibling::td/input");
	private static final By SUPPORT_EMAIL_ADDRESS_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Support Email Address:']/following-sibling::td[@class='ldapErrorMsg']");

	private static final By ADD_MARKET_BTN_LOCATOR = By
			.xpath("//form/div[@class='LdapListingOptions']/input[@value='Add market']");
	private static final By ADD_MARKET_MENU_LOCATOR = By
			.xpath("//div[@id='mainMenu']//td[@class='LdapSubMenuSelected']/a[text()='Add Market']");

	private WebElement marketCode;
	private WebElement marketCodeErrMsg;
	private WebElement marketName;
	private WebElement marketNameErrMsg;
	private WebElement imageUpload;
	private WebElement imageUploadErrMsg;
	private WebElement supportEmailAddress;
	private WebElement supportEmailAddressErrMsg;
	private WebElement addMarketBtn;
	private WebElement addMarketMenu;

	public AddMarketPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			marketCode = findElement(MARKET_CODE_LOCATOR);
			marketCodeErrMsg = findElement(MARKET_CODE_ERROR_MSG_LOCATOR);
			marketName = findElement(MARKET_NAME_LOCATOR);
			marketNameErrMsg = findElement(MARKET_NAME_ERROR_MSG_LOCATOR);
			imageUpload = findElement(IMAGE_UPLOAD_LOCATOR);
			imageUploadErrMsg = findElement(IMAGE_UPLOAD_ERROR_MSG_LOCATOR);
			supportEmailAddress = findElement(SUPPORT_EMAIL_ADDRESS_LOCATOR);
			supportEmailAddressErrMsg = findElement(SUPPORT_EMAIL_ADDRESS_ERROR_MSG_LOCATOR);
			addMarketBtn = findElement(ADD_MARKET_BTN_LOCATOR);
			addMarketMenu = findElement(ADD_MARKET_MENU_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getMarketCode() {
		return this.marketCode.getAttribute("value");
	}

	public void clearMarketCode() {
		this.marketCode.clear();
	}

	public void typeMarketCode(String value) {
		if (null != value) {
			clearMarketCode();
			this.marketCode.sendKeys(value);
		}
	}

	public String getMarketCodeErrMsg() {
		return this.marketCodeErrMsg.getText();
	}

	public String getImageUpload() {
		return this.imageUpload.getAttribute("value");
	}

	public void clearImageUpload() {
		this.imageUpload.clear();
	}

	public void uploadImage(String value) {
		if (null != value) {
			clearImageUpload();
			this.imageUpload.sendKeys(value);
		}
	}

	public String getImageUploadErrMsg() {
		return this.imageUploadErrMsg.getText();
	}

	public String getMarketName() {
		return this.marketName.getAttribute("value");
	}

	public void clearMarketName() {
		this.marketName.clear();
	}

	public void typeMarketName(String value) {
		if (null != value) {
			clearMarketName();
			this.marketName.sendKeys(value);
		}
	}

	public String getMarketNameErrMsg() {
		return this.marketNameErrMsg.getText();
	}

	public String getSupportEmailAddress() {
		return this.supportEmailAddress.getAttribute("value");
	}

	public void clearSupportEmailAddress() {
		this.supportEmailAddress.clear();
	}

	public void typeSupportEmailAddress(String value) {
		if (null != value) {
			clearSupportEmailAddress();
			this.supportEmailAddress.sendKeys(value);
		}
	}

	public String getUrlOfMarketServiceErrMsg() {
		return this.supportEmailAddressErrMsg.getText();
	}

	public ListMarketsPage submitAdd() {
		this.addMarketBtn.click();
		return createPageObject(ListMarketsPage.class);
	}

	public AddMarketPage reset() {
		this.addMarketMenu.click();
		return this;
	}

}
