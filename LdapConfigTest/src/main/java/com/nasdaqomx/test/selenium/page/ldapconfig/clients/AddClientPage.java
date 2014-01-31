package com.nasdaqomx.test.selenium.page.ldapconfig.clients;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class AddClientPage extends LdapconfigBasePage {

	private static final String URL = "addClient.view";

	// cannot use equal because of line characters in label
	private static final By CLIENT_ID_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'Client id:')]/following-sibling::td/input");
	private static final By CLIENT_ID_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'Client id:')]/following-sibling::td[@class='LdapErrorMsg']");
	private static final By CLIENT_NAME_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'Client name:')]/following-sibling::td/input");
	private static final By CLIENT_NAME_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'Client name:')]/following-sibling::td[@class='LdapErrorMsg']");

	private static final By IMAGE_FILE_UPLOAD_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Image file upload:']/following-sibling::td/input");
	private static final By IMAGE_FILE_UPLOAD_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Image file upload:']/following-sibling::td[@class='LdapErrorMsg']");

	private static final By URL_OF_CLIENT_SERVICE_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='URL of client service:']/following-sibling::td/input");
	private static final By URL_OF_CLIENT_SERVICE_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='URL of client service:']/following-sibling::td[@class='LdapErrorMsg']");

	private static final By INTERNAL_HOSTNAME_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Internal hostname or IP address of client:']/following-sibling::td/input");
	private static final By INTERNAL_HOSTNAME_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Internal hostname or IP address of client:']/following-sibling::td[@class='LdapErrorMsg']");

	private static final By ADD_CLIENT_BTN_LOCATOR = By
			.xpath("//form/div[@class='LdapListingOptions']/input[@value='Add client']");
	private static final By ADD_CLIENT_MENU_LOCATOR = By
			.xpath("//div[@id='mainMenu']//td[@class='LdapSubMenuSelected']/a[text()='Add Client']");

	private WebElement clientId;
	private WebElement clientIdErrMsg;
	private WebElement clientName;
	private WebElement clientNameErrMsg;
	private WebElement imageFileUpload;
	private WebElement imageFileUploadErrMsg;
	private WebElement url;
	private WebElement urlErrMsg;
	private WebElement internalHostname;
	private WebElement internalHostnameErrMsg;
	private WebElement addClientBtn;
	private WebElement addClientMenu;

	public AddClientPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			clientId = findElement(CLIENT_ID_LOCATOR);
			clientIdErrMsg = findElement(CLIENT_ID_ERROR_MSG_LOCATOR);
			clientName = findElement(CLIENT_NAME_LOCATOR);
			clientNameErrMsg = findElement(CLIENT_NAME_ERROR_MSG_LOCATOR);
			imageFileUpload = findElement(IMAGE_FILE_UPLOAD_LOCATOR);
			imageFileUploadErrMsg = findElement(IMAGE_FILE_UPLOAD_ERROR_MSG_LOCATOR);
			url = findElement(URL_OF_CLIENT_SERVICE_LOCATOR);
			urlErrMsg = findElement(URL_OF_CLIENT_SERVICE_ERROR_MSG_LOCATOR);
			internalHostname = findElement(INTERNAL_HOSTNAME_LOCATOR);
			internalHostnameErrMsg = findElement(INTERNAL_HOSTNAME_ERROR_MSG_LOCATOR);
			addClientBtn = findElement(ADD_CLIENT_BTN_LOCATOR);
			addClientMenu = findElement(ADD_CLIENT_MENU_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public String getClientId() {
		return this.clientId.getAttribute("value");
	}

	public void clearClientId() {
		this.clientId.clear();
	}

	public void typeClientId(String value) {
		if (null != value) {
			clearClientId();
			this.clientId.sendKeys(value);
		}
	}

	public String getClientIdErrMsg() {
		return this.clientIdErrMsg.getText();
	}

	public String getImageFileUpload() {
		return this.imageFileUpload.getAttribute("value");
	}

	public void clearImageFileUpload() {
		this.imageFileUpload.clear();
	}

	public void uploadImageFile(String value) {
		if (null != value) {
			clearImageFileUpload();
			this.imageFileUpload.sendKeys(value);
		}
	}

	public String getImageFileUploadErrMsg() {
		return this.imageFileUploadErrMsg.getText();
	}

	public String getClientName() {
		return this.clientName.getAttribute("value");
	}

	public void clearClientName() {
		this.clientName.clear();
	}

	public void typeClientName(String value) {
		if (null != value) {
			clearClientName();
			this.clientName.sendKeys(value);
		}
	}

	public String getClientNameErrMsg() {
		return this.clientNameErrMsg.getText();
	}

	public String getUrlOfClientService() {
		return this.url.getAttribute("value");
	}

	public void clearUrlOfClientService() {
		this.url.clear();
	}

	public void typeUrlOfClientService(String value) {
		if (null != value) {
			clearUrlOfClientService();
			this.url.sendKeys(value);
		}
	}

	public String getUrlOfClientServiceErrMsg() {
		return this.urlErrMsg.getText();
	}

	public String getInternalHostname() {
		return this.internalHostname.getAttribute("value");
	}

	public void clearInternalHostname() {
		this.internalHostname.clear();
	}

	public void typeInternalHostname(String value) {
		if (null != value) {
			clearInternalHostname();
			this.internalHostname.sendKeys(value);
		}
	}

	public String getInternalHostnameErrMsg() {
		return this.internalHostnameErrMsg.getText();
	}

	public ListClientsPage submitAdd() {
		this.addClientBtn.click();
		return createPageObject(ListClientsPage.class);
	}

	public AddClientPage reset() {
		this.addClientMenu.click();
		return this;
	}

}
