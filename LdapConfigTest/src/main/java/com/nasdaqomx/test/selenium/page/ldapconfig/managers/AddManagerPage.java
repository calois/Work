package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import com.nasdaqomx.test.selenium.base.TestManager;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;

public class AddManagerPage extends LdapconfigBasePage {

	private static final String URL = "addManager.view";

	// cannot use equal because of line characters in label
	private static final By USER_ID_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'User id:')]/following-sibling::td/input");
	private static final By USER_ID_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'User id:')]/following-sibling::td[@class='LdapErrorMsg']");
	private static final By SURNAME_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'Surname:')]/following-sibling::td/input");
	private static final By SURNAME_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'Surname:')]/following-sibling::td[@class='LdapErrorMsg']");
	private static final By FULL_NAME_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'Full name:')]/following-sibling::td/input");
	private static final By FULL_NAME_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'Full name:')]/following-sibling::td[@class='LdapErrorMsg']");

	// cannot use equal because of "&nbsp;" in label;
	private static final By EMAIL_ADDRESS_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'EMail address:')]/following-sibling::td/input");
	private static final By EMAIL_ADDRESS_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'EMail address:')]/following-sibling::td[@class='LdapErrorMsg']");
	private static final By PHONE_NUMBER_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'Phone number:')]/following-sibling::td/input");
	private static final By PHONE_NUMBER_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'Phone number:')]/following-sibling::td[@class='LdapErrorMsg']");

	// label directly under td rather than div;
	private static final By MOBILE_NUMBER_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Mobile number:']/following-sibling::td/input");
	private static final By MOBILE_NUMBER_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Mobile number:']/following-sibling::td[@class='LdapErrorMsg']");

	private static final By USER_TIMEZONE_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='User Timezone:']/following-sibling::td/select");
	private static final By USER_TIMEZONE_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='User Timezone:']/following-sibling::td[@class='ldapErrorMsg']");
	private static final By USER_LANGUAGE_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='User Language:']/following-sibling::td/select");
	private static final By USER_LANGUAGE_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='User Language:']/following-sibling::td[@class='ldapErrorMsg']");
	private static final By COMMENTS_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Comments:']/following-sibling::td/input");
	private static final By COMMENTS_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Comments:']/following-sibling::td[@class='ldapErrorMsg']");

	private static final By ADD_MANAGER_BTN_LOCATOR = By
			.xpath("//form/div[@class='LdapListingOptions']/input[@value='Add manager']");
	private static final By ADD_MANAGER_MENU_LOCATOR = By
			.xpath("//div[@id='mainMenu']//td[@class='LdapSubMenuSelected']/a[text()='Add Manager']");

	private WebElement userId;
	private WebElement userIdErrMsg;
	private WebElement surname;
	private WebElement surnameErrMsg;
	private WebElement fullName;
	private WebElement fullNameErrMsg;
	private WebElement email;
	private WebElement emailErrMsg;
	private WebElement phone;
	private WebElement phoneErrMsg;
	private WebElement mobile;
	private WebElement mobileErrMsg;
	private Select userTimezone;
	private WebElement userTimezoneErrMsg;
	private Select userLanguage;
	private WebElement userLanguageErrMsg;
	private WebElement comments;
	private WebElement commentsErrMsg;
	private WebElement addManagerBtn;
	private WebElement addManagerMenu;

	public AddManagerPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			userId = findElement(USER_ID_LOCATOR);
			userIdErrMsg = findElement(USER_ID_ERROR_MSG_LOCATOR);
			surname = findElement(SURNAME_LOCATOR);
			surnameErrMsg = findElement(SURNAME_ERROR_MSG_LOCATOR);
			fullName = findElement(FULL_NAME_LOCATOR);
			fullNameErrMsg = findElement(FULL_NAME_ERROR_MSG_LOCATOR);
			email = findElement(EMAIL_ADDRESS_LOCATOR);
			emailErrMsg = findElement(EMAIL_ADDRESS_ERROR_MSG_LOCATOR);
			phone = findElement(PHONE_NUMBER_LOCATOR);
			phoneErrMsg = findElement(PHONE_NUMBER_ERROR_MSG_LOCATOR);
			mobile = findElement(MOBILE_NUMBER_LOCATOR);
			mobileErrMsg = findElement(MOBILE_NUMBER_ERROR_MSG_LOCATOR);
			userTimezone = new Select(findElement(USER_TIMEZONE_LOCATOR));
			userTimezoneErrMsg = findElement(USER_TIMEZONE_ERROR_MSG_LOCATOR);
			userLanguage = new Select(findElement(USER_LANGUAGE_LOCATOR));
			userLanguageErrMsg = findElement((USER_LANGUAGE_ERROR_MSG_LOCATOR));
			comments = findElement(COMMENTS_LOCATOR);
			commentsErrMsg = findElement(COMMENTS_ERROR_MSG_LOCATOR);
			addManagerBtn = findElement(ADD_MANAGER_BTN_LOCATOR);
			addManagerMenu = findElement(ADD_MANAGER_MENU_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		} catch (UnexpectedTagNameException e) {
			fail(e.getMessage());
		}
	}

	public String getUserId() {
		return this.userId.getAttribute("value");
	}

	public void typeUserId(String value) {
		if (null != value) {
			this.userId.sendKeys(value);
		}
	}

	public String getUserIdErrMsg() {
		return this.userIdErrMsg.getText();
	}

	public String getSurname() {
		return this.surname.getAttribute("value");
	}

	public void typeSurname(String value) {
		if (null != value) {
			this.surname.sendKeys(value);
		}
	}

	public String getSurnameErrMsg() {
		return this.surnameErrMsg.getText();
	}

	public String getFullName() {
		return this.fullName.getAttribute("value");
	}

	public void typeFullName(String value) {
		if (null != value) {
			this.fullName.sendKeys(value);
		}
	}

	public String getFullNameErrMsg() {
		return this.fullNameErrMsg.getText();
	}

	public String getEmail() {
		return this.email.getAttribute("value");
	}

	public void typeEmail(String value) {
		if (null != value) {
			this.email.sendKeys(value);
		}
	}

	public String getEmailErrMsg() {
		return this.emailErrMsg.getText();
	}

	public String getPhone() {
		return this.phone.getAttribute("value");
	}

	public void typePhone(String value) {
		if (null != value) {
			this.phone.sendKeys(value);
		}
	}

	public String getPhoneErrMsg() {
		return this.phoneErrMsg.getText();
	}

	public String getMobile() {
		return this.mobile.getAttribute("value");
	}

	public void typeMobile(String value) {
		if (null != value) {
			this.mobile.sendKeys(value);
		}
	}

	public String getMobileErrMsg() {
		return this.mobileErrMsg.getText();
	}

	public String getUserTimezone() {
		return this.userTimezone.getFirstSelectedOption().getText();
	}

	public void selectUserTimezone(String value) {
		if (null != value) {
			this.userTimezone.selectByVisibleText(value);
		}
	}

	public String getUserTimezoneErrMsg() {
		return this.userTimezoneErrMsg.getText();
	}

	public String getUserLanguage() {
		return this.userLanguage.getFirstSelectedOption().getText();
	}

	public void selectUserLanguage(String value) {
		if (null != value) {
			this.userLanguage.selectByVisibleText(value);
		}
	}

	public String getUserLanguageErrMsg() {
		return this.userLanguageErrMsg.getText();
	}

	public String getComments() {
		return this.comments.getAttribute("value");
	}

	public void typeComments(String value) {
		if (null != value) {
			this.comments.sendKeys(value);
		}
	}

	public String getCommentsErrMsg() {
		return this.commentsErrMsg.getText();
	}

	public EditManagerPermissionsPage submitAdd() {
		this.addManagerBtn.click();
		return createPageObject(EditManagerPermissionsPage.class);
	}

	public AddManagerPage submitAddExpectingFailure() {
		this.addManagerBtn.click();
		return createPageObject(AddManagerPage.class);
	}

	public AddManagerPage reset() {
		this.addManagerMenu.click();
		return this;
	}
}
