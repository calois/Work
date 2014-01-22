package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class EditManagerDetailsPage extends ManagerBasePage {

	private static final String URL = "editManager.view";

	private static final String USER_ID_LOCATOR = "//form/table[@class='LdapListing']//td[div='User id:']/following-sibling::td/span";
	private static final String USER_ID_ERROR_MSG_LOCATOR = "//form/table[@class='LdapListing']//td[div='User id:']/following-sibling::td[2]";
	private static final String SURNAME_LOCATOR = "//form/table[@class='LdapListing']//td[div='Surname:']/following-sibling::td/input";
	private static final String SURNAME_ERROR_MSG_LOCATOR = "//form/table[@class='LdapListing']//td[div='Surname:']/following-sibling::td[@class='LdapErrorMsg']";
	private static final String FULL_NAME_LOCATOR = "//form/table[@class='LdapListing']//td[div='Full name:']/following-sibling::td/input";
	private static final String FULL_NAME_ERROR_MSG_LOCATOR = "//form/table[@class='LdapListing']//td[div='Full name:']/following-sibling::td[@class='LdapErrorMsg']";
	private static final String EMAIL_ADDRESS_LOCATOR = "//form/table[@class='LdapListing']//td[div='EMail address:']/following-sibling::td/input";
	private static final String EMAIL_ADDRESS_ERROR_MSG_LOCATOR = "//form/table[@class='LdapListing']//td[div='EMail address:']/following-sibling::td[@class='LdapErrorMsg']";

	// cannot use equal because of "&nbsp;" in label;
	private static final String PHONE_NUMBER_LOCATOR = "//form/table[@class='LdapListing']//td[contains(text(),'Phone number:')]/following-sibling::td/input";
	private static final String PHONE_NUMBER_ERROR_MSG_LOCATOR = "//form/table[@class='LdapListing']//td[contains(text(),'Phone number:')]/following-sibling::td[@class='LdapErrorMsg']";

	// label directly under td rather than div;
	private static final String MOBILE_NUMBER_LOCATOR = "//form/table[@class='LdapListing']//td[text()='Mobile number:']/following-sibling::td/input";
	private static final String MOBILE_NUMBER_ERROR_MSG_LOCATOR = "//form/table[@class='LdapListing']//td[text()='Mobile number:']/following-sibling::td[@class='LdapErrorMsg']";

	// class= ldapErrorMsg; different from above
	private static final String USER_TIMEZONE_LOCATOR = "//form/table[@class='LdapListing']//td[div='User Timezone:']/following-sibling::td/select";
	private static final String USER_TIMEZONE_ERROR_MSG_LOCATOR = "//form/table[@class='LdapListing']//td[div='User Timezone:']/following-sibling::td[@class='ldapErrorMsg']";
	private static final String USER_LANGUAGE_LOCATOR = "//form/table[@class='LdapListing']//td[div='User Language:']/following-sibling::td/select";
	private static final String USER_LANGUAGE_ERROR_MSG_LOCATOR = "//form/table[@class='LdapListing']//td[div='User Language:']/following-sibling::td[@class='ldapErrorMsg']";
	private static final String COMMENTS_LOCATOR = "//form/table[@class='LdapListing']//td[div='Comments:']/following-sibling::td/input";
	private static final String COMMENTS_ERROR_MSG_LOCATOR = "//form/table[@class='LdapListing']//td[div='Comments:']/following-sibling::td[@class='ldapErrorMsg']";

	// have extra space at the end of label;
	private static final String PASSWORD_EXPRIES_LOCATOR = "//form/table[@class='LdapListing']//td[div='Password expires: ']/following-sibling::td[1]";
	private static final String PASSWORD_EXPRIES_ERROR_MSG_LOCATOR = "//form/table[@class='LdapListing']//td[div='Password expires: ']/following-sibling::td[2]";

	private static final String UPDATE_BTN_LOCATOR = "//form/div[@class='LdapListingOptions']/input[@value='Update managers details']";

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
	private WebElement passwordExpires;
	private WebElement passwordExpiresErrMsg;
	private WebElement updateBtn;

	public EditManagerDetailsPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			userId = getElement(By.xpath(USER_ID_LOCATOR));
			userIdErrMsg = getElement(By.xpath(USER_ID_ERROR_MSG_LOCATOR));
			surname = getElement(By.xpath(SURNAME_LOCATOR));
			surnameErrMsg = getElement(By.xpath(SURNAME_ERROR_MSG_LOCATOR));
			fullName = getElement(By.xpath(FULL_NAME_LOCATOR));
			fullNameErrMsg = getElement(By.xpath(FULL_NAME_ERROR_MSG_LOCATOR));
			email = getElement(By.xpath(EMAIL_ADDRESS_LOCATOR));
			emailErrMsg = getElement(By.xpath(EMAIL_ADDRESS_ERROR_MSG_LOCATOR));
			phone = getElement(By.xpath(PHONE_NUMBER_LOCATOR));
			phoneErrMsg = getElement(By.xpath(PHONE_NUMBER_ERROR_MSG_LOCATOR));
			mobile = getElement(By.xpath(MOBILE_NUMBER_LOCATOR));
			mobileErrMsg = getElement(By.xpath(MOBILE_NUMBER_ERROR_MSG_LOCATOR));
			userTimezone = new Select(
					getElement(By.xpath(USER_TIMEZONE_LOCATOR)));
			userTimezoneErrMsg = getElement(By
					.xpath(USER_TIMEZONE_ERROR_MSG_LOCATOR));
			userLanguage = new Select(
					getElement(By.xpath(USER_LANGUAGE_LOCATOR)));
			userLanguageErrMsg = getElement(By
					.xpath(USER_LANGUAGE_ERROR_MSG_LOCATOR));
			comments = getElement(By.xpath(COMMENTS_LOCATOR));
			commentsErrMsg = getElement(By.xpath(COMMENTS_ERROR_MSG_LOCATOR));
			passwordExpires = getElement(By.xpath(PASSWORD_EXPRIES_LOCATOR));
			passwordExpiresErrMsg = getElement(By
					.xpath(PASSWORD_EXPRIES_ERROR_MSG_LOCATOR));
			updateBtn = getElement(By.xpath(UPDATE_BTN_LOCATOR));
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		} catch (UnexpectedTagNameException e) {
			fail(e.getMessage());
		}
	}

	public String getUserId() {
		return this.userId.getText().trim();
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

	public void typeUserTimezone(String value) {
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

	public void typeUserLanguage(String value) {
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

	public String getPasswordExpires() {
		return this.passwordExpires.getText();
	}

	public String getPasswordExpiresErrMsg() {
		return this.passwordExpiresErrMsg.getText();
	}

	public EditManagerPermissionsPage submitEdit() {
		this.updateBtn.click();
		return createPageObject(EditManagerPermissionsPage.class);
	}

}
