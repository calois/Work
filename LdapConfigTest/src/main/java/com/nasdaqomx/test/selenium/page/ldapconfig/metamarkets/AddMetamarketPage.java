package com.nasdaqomx.test.selenium.page.ldapconfig.metamarkets;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class AddMetamarketPage extends LdapconfigBasePage {

	private static final String URL = "addMetaMarket.view";

	private static final By METAMARKET_CODE_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Metamarket code:']/following-sibling::td/input");
	private static final By METAMARKET_CODE_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Metamarket code:']/following-sibling::td[@class='LdapErrorMsg']");
	private static final By METAMARKET_NAME_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Metamarket name:']/following-sibling::td/input");
	private static final By METAMARKET_NAME_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Metamarket name:']/following-sibling::td[@class='LdapErrorMsg']");

	private static final By IMAGE_UPLOAD_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Image upload:']/following-sibling::td/input");
	private static final By IMAGE_UPLOAD_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Image upload:']/following-sibling::td[@class='ldapErrorMsg']");

	private static final By SUPPORT_EMAIL_ADDRESS_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Support Email Address:']/following-sibling::td/input");
	private static final By SUPPORT_EMAIL_ADDRESS_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Support Email Address:']/following-sibling::td[@class='ldapErrorMsg']");

	private static final By ADD_METAMARKET_BTN_LOCATOR = By
			.xpath("//form/div[@class='LdapListingOptions']/input[@value='Add metamarket']");
	private static final By ADD_METAMARKET_MENU_LOCATOR = By
			.xpath("//div[@id='mainMenu']//td[@class='LdapSubMenuSelected']/a[text()='Add Metamarket']");

	private WebElement metamarketCode;
	private WebElement metamarketCodeErrMsg;
	private WebElement metamarketName;
	private WebElement metamarketNameErrMsg;
	private WebElement imageUpload;
	private WebElement imageUploadErrMsg;
	private WebElement supportEmailAddress;
	private WebElement supportEmailAddressErrMsg;
	private WebElement addMetamarketBtn;
	private WebElement addMetamarketMenu;

	public AddMetamarketPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			metamarketCode = findElement(METAMARKET_CODE_LOCATOR);
			metamarketCodeErrMsg = findElement(METAMARKET_CODE_ERROR_MSG_LOCATOR);
			metamarketName = findElement(METAMARKET_NAME_LOCATOR);
			metamarketNameErrMsg = findElement(METAMARKET_NAME_ERROR_MSG_LOCATOR);
			imageUpload = findElement(IMAGE_UPLOAD_LOCATOR);
			imageUploadErrMsg = findElement(IMAGE_UPLOAD_ERROR_MSG_LOCATOR);
			supportEmailAddress = findElement(SUPPORT_EMAIL_ADDRESS_LOCATOR);
			supportEmailAddressErrMsg = findElement(SUPPORT_EMAIL_ADDRESS_ERROR_MSG_LOCATOR);
			addMetamarketBtn = findElement(ADD_METAMARKET_BTN_LOCATOR);
			addMetamarketMenu = findElement(ADD_METAMARKET_MENU_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getMetamarketCode() {
		return this.metamarketCode.getAttribute("value");
	}

	public void clearMetamarketCode() {
		this.metamarketCode.clear();
	}

	public void typeMetamarketCode(String value) {
		if (null != value) {
			clearMetamarketCode();
			this.metamarketCode.sendKeys(value);
		}
	}

	public String getMetamarketCodeErrMsg() {
		return this.metamarketCodeErrMsg.getText();
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

	public String getMetamarketName() {
		return this.metamarketName.getAttribute("value");
	}

	public void clearMetamarketName() {
		this.metamarketName.clear();
	}

	public void typeMetamarketName(String value) {
		if (null != value) {
			clearMetamarketName();
			this.metamarketName.sendKeys(value);
		}
	}

	public String getMetamarketNameErrMsg() {
		return this.metamarketNameErrMsg.getText();
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

	public String getUrlOfMetamarketServiceErrMsg() {
		return this.supportEmailAddressErrMsg.getText();
	}

	public ListMetamarketsPage submitAdd() {
		this.addMetamarketBtn.click();
		return createPageObject(ListMetamarketsPage.class);
	}

	public AddMetamarketPage reset() {
		this.addMetamarketMenu.click();
		return this;
	}

}
