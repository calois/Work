package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import com.nasdaqomx.test.selenium.base.TestManager;

public class EditManagerDetailsPage extends ManagerBasePage {

	private static final String URL = "editManager.view";

	private static final By USER_ID_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='User id:']/following-sibling::td/span");
	private static final By USER_ID_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='User id:']/following-sibling::td[2]");
	private static final By SURNAME_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Surname:']/following-sibling::td/input");
	private static final By SURNAME_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Surname:']/following-sibling::td[@class='LdapErrorMsg']");
	private static final By FULL_NAME_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Full name:']/following-sibling::td/input");
	private static final By FULL_NAME_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Full name:']/following-sibling::td[@class='LdapErrorMsg']");
	private static final By EMAIL_ADDRESS_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='EMail address:']/following-sibling::td/input");
	private static final By EMAIL_ADDRESS_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='EMail address:']/following-sibling::td[@class='LdapErrorMsg']");

	// cannot use equal because of "&nbsp;" in label;
	private static final By PHONE_NUMBER_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'Phone number:')]/following-sibling::td/input");
	private static final By PHONE_NUMBER_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[contains(text(),'Phone number:')]/following-sibling::td[@class='LdapErrorMsg']");

	// label directly under td rather than div;
	private static final By MOBILE_NUMBER_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Mobile number:']/following-sibling::td/input");
	private static final By MOBILE_NUMBER_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[text()='Mobile number:']/following-sibling::td[@class='LdapErrorMsg']");

	// class= ldapErrorMsg; different from above
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

	// have extra space at the end of label;
	private static final By PASSWORD_EXPRIES_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Password expires: ']/following-sibling::td[1]");
	private static final By PASSWORD_EXPRIES_ERROR_MSG_LOCATOR = By
			.xpath("//form/table[@class='LdapListing']//td[div='Password expires: ']/following-sibling::td[2]");

	private static final By UPDATE_BTN_LOCATOR = By
			.xpath("//form/div[@class='LdapListingOptions']/input[@value='Update managers details']");

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
			userLanguageErrMsg = findElement(USER_LANGUAGE_ERROR_MSG_LOCATOR);
			comments = findElement(COMMENTS_LOCATOR);
			commentsErrMsg = findElement(COMMENTS_ERROR_MSG_LOCATOR);
			passwordExpires = findElement(PASSWORD_EXPRIES_LOCATOR);
			passwordExpiresErrMsg = findElement(PASSWORD_EXPRIES_ERROR_MSG_LOCATOR);
			updateBtn = findElement(UPDATE_BTN_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		} catch (UnexpectedTagNameException e) {
			fail(e.getMessage());
		}
	}

	public String getUserId() {
		return this.userId.getText().trim();
	}

	public String getUserIdErrMsg() {
		return this.userIdErrMsg.getText();
	}

	public String getSurname() {
		return this.surname.getAttribute("value");
	}

	public void clearSurname() {
		this.surname.clear();
	}

	public void typeSurname(String value) {
		if (null != value) {
			clearSurname();
			this.surname.sendKeys(value);
		}
	}

	public String getSurnameErrMsg() {
		return this.surnameErrMsg.getText();
	}

	public String getFullName() {
		return this.fullName.getAttribute("value");
	}

	public void clearFullName() {
		this.fullName.clear();
	}

	public void typeFullName(String value) {
		if (null != value) {
			clearFullName();
			this.fullName.sendKeys(value);
		}
	}

	public String getFullNameErrMsg() {
		return this.fullNameErrMsg.getText();
	}

	public String getEmail() {
		return this.email.getAttribute("value");
	}

	public void clearEmail() {
		this.email.clear();
	}

	public void typeEmail(String value) {
		if (null != value) {
			clearEmail();
			this.email.sendKeys(value);
		}
	}

	public String getEmailErrMsg() {
		return this.emailErrMsg.getText();
	}

	public String getPhone() {
		return this.phone.getAttribute("value");
	}

	public void clearPhone() {
		this.phone.clear();
	}

	public void typePhone(String value) {
		if (null != value) {
			clearPhone();
			this.phone.sendKeys(value);
		}
	}

	public String getPhoneErrMsg() {
		return this.phoneErrMsg.getText();
	}

	public String getMobile() {
		return this.mobile.getAttribute("value");
	}

	public void clearMobile() {
		this.mobile.clear();
	}

	public void typeMobile(String value) {
		if (null != value) {
			clearMobile();
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

	public void deselectUserTimezone(String value) {
		if (null != value) {
			this.userTimezone.deselectByVisibleText(value);
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

	public void deselectUserLanguage(String value) {
		if (null != value) {
			this.userLanguage.deselectByVisibleText(value);
		}
	}

	public String getUserLanguageErrMsg() {
		return this.userLanguageErrMsg.getText();
	}

	public String getComments() {
		return this.comments.getAttribute("value");
	}

	public void clearComments() {
		this.comments.clear();
	}

	public void typeComments(String value) {
		if (null != value) {
			clearComments();
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

	public EditManagerDetailsPage submitEditExpectingFailure() {
		this.updateBtn.click();
		return createPageObject(EditManagerDetailsPage.class);
	}
}
