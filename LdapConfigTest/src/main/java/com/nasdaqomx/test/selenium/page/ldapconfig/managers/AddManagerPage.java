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
	private static final String USER_ID_XPATH = "//form/table[@class='LdapListing']//td[contains(text(),'User id:')]/following-sibling::td/input";
	private static final String USER_ID_ERROR_MSG_XPATH = "//form/table[@class='LdapListing']//td[contains(text(),'User id:')]/following-sibling::td[@class='LdapErrorMsg']";
	private static final String SURNAME_XPATH = "//form/table[@class='LdapListing']//td[contains(text(),'Surname:')]/following-sibling::td/input";
	private static final String SURNAME_ERROR_MSG_XPATH = "//form/table[@class='LdapListing']//td[contains(text(),'Surname:')]/following-sibling::td[@class='LdapErrorMsg']";
	private static final String FULL_NAME_XPATH = "//form/table[@class='LdapListing']//td[contains(text(),'Full name:')]/following-sibling::td/input";
	private static final String FULL_NAME_ERROR_MSG_XPATH = "//form/table[@class='LdapListing']//td[contains(text(),'Full name:')]/following-sibling::td[@class='LdapErrorMsg']";

	// cannot use equal because of "&nbsp;" in label;
	private static final String EMAIL_ADDRESS_XPATH = "//form/table[@class='LdapListing']//td[contains(text(),'EMail address:')]/following-sibling::td/input";
	private static final String EMAIL_ADDRESS_ERROR_MSG_XPATH = "//form/table[@class='LdapListing']//td[contains(text(),'EMail address:')]/following-sibling::td[@class='LdapErrorMsg']";
	private static final String PHONE_NUMBER_XPATH = "//form/table[@class='LdapListing']//td[contains(text(),'Phone number:')]/following-sibling::td/input";
	private static final String PHONE_NUMBER_ERROR_MSG_XPATH = "//form/table[@class='LdapListing']//td[contains(text(),'Phone number:')]/following-sibling::td[@class='LdapErrorMsg']";

	// label directly under td rather than div;
	private static final String MOBILE_NUMBER_XPATH = "//form/table[@class='LdapListing']//td[text()='Mobile number:']/following-sibling::td/input";
	private static final String MOBILE_NUMBER_ERROR_MSG_XPATH = "//form/table[@class='LdapListing']//td[text()='Mobile number:']/following-sibling::td[@class='LdapErrorMsg']";

	private static final String USER_TIMEZONE_XPATH = "//form/table[@class='LdapListing']//td[div='User Timezone:']/following-sibling::td/select";
	private static final String USER_TIMEZONE_ERROR_MSG_XPATH = "//form/table[@class='LdapListing']//td[div='User Timezone:']/following-sibling::td[@class='ldapErrorMsg']";
	private static final String USER_LANGUAGE_XPATH = "//form/table[@class='LdapListing']//td[div='User Language:']/following-sibling::td/select";
	private static final String USER_LANGUAGE_ERROR_MSG_XPATH = "//form/table[@class='LdapListing']//td[div='User Language:']/following-sibling::td[@class='ldapErrorMsg']";
	private static final String COMMENTS_XPATH = "//form/table[@class='LdapListing']//td[div='Comments:']/following-sibling::td/input";
	private static final String COMMENTS_ERROR_MSG_XPATH = "//form/table[@class='LdapListing']//td[div='Comments:']/following-sibling::td[@class='ldapErrorMsg']";

	private static final String ADD_MANAGER_BTN_XPATH = "//form/div[@class='LdapListingOptions']/input[@value='Add manager']";
	private static final String ADD_MANAGER_MENU_XPATH = "//div[@id='mainMenu']//td[@class='LdapSubMenuSelected']/a[text()='Add Manager']";

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
			userId = getElement(By.xpath(USER_ID_XPATH));
			userIdErrMsg = getElement(By.xpath(USER_ID_ERROR_MSG_XPATH));
			surname = getElement(By.xpath(SURNAME_XPATH));
			surnameErrMsg = getElement(By.xpath(SURNAME_ERROR_MSG_XPATH));
			fullName = getElement(By.xpath(FULL_NAME_XPATH));
			fullNameErrMsg = getElement(By.xpath(FULL_NAME_ERROR_MSG_XPATH));
			email = getElement(By.xpath(EMAIL_ADDRESS_XPATH));
			emailErrMsg = getElement(By.xpath(EMAIL_ADDRESS_ERROR_MSG_XPATH));
			phone = getElement(By.xpath(PHONE_NUMBER_XPATH));
			phoneErrMsg = getElement(By.xpath(PHONE_NUMBER_ERROR_MSG_XPATH));
			mobile = getElement(By.xpath(MOBILE_NUMBER_XPATH));
			mobileErrMsg = getElement(By.xpath(MOBILE_NUMBER_ERROR_MSG_XPATH));
			userTimezone = new Select(
					getElement(By.xpath(USER_TIMEZONE_XPATH)));
			userTimezoneErrMsg = getElement(By
					.xpath(USER_TIMEZONE_ERROR_MSG_XPATH));
			userLanguage = new Select(
					getElement(By.xpath(USER_LANGUAGE_XPATH)));
			userLanguageErrMsg = getElement(By
					.xpath(USER_LANGUAGE_ERROR_MSG_XPATH));
			comments = getElement(By.xpath(COMMENTS_XPATH));
			commentsErrMsg = getElement(By.xpath(COMMENTS_ERROR_MSG_XPATH));
			addManagerBtn = getElement(By.xpath(ADD_MANAGER_BTN_XPATH));
			addManagerMenu = getElement(By.xpath(ADD_MANAGER_MENU_XPATH));
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
