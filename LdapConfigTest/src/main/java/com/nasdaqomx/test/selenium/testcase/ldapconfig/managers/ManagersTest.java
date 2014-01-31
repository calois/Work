package com.nasdaqomx.test.selenium.testcase.ldapconfig.managers;

import com.nasdaqomx.test.selenium.base.AbstractTest;
import com.nasdaqomx.test.selenium.base.TestUtils;
import com.nasdaqomx.test.selenium.base.anno.TestAfter;
import com.nasdaqomx.test.selenium.base.anno.TestBefore;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;
import com.nasdaqomx.test.selenium.page.ldapconfig.LoginPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.AddManagerPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.EditManagerDetailsPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.ListManagersPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.RemoveManagerPage;

public class ManagersTest extends AbstractTest {

	private ListManagersPage listManagersPage;
	private LdapconfigBasePage lastPage;
	private String removeUserId;
	private String userId;
	private String surname;
	private String fullName;
	private String email;
	private String phone;
	private String mobile;
	private String userTimezone;
	private String userLanguage;
	private String originalSurname;
	private String originalFullName;
	private String originalEmail;
	private String originalPhone;
	private String originalMobile;
	private String originalUserTimezone;
	private String originalUserLanguage;
	private String expectedSurname;
	private String expectedFullName;
	private String expectedEmail;
	private String expectedPhone;
	private String expectedMobile;
	private String expectedUserTimezone;
	private String expectedUserLanguage;

	@TestBefore
	public void before() {
		setData();
		listManagersPage=createPageObject(LoginPage.class)
				.loginAs(getInputData("username"), getInputData("password"))
				.toManagers();
		if(!listManagersPage.isManagerListed(userId)){
			AddManagerPage addManagerPage = listManagersPage.toAddManager();
			addManagerPage.typeUserId(userId);
			addManagerPage.typeSurname(originalSurname);
			addManagerPage.typeFullName(originalFullName);
			addManagerPage.typeEmail(originalEmail);
			addManagerPage.typePhone(originalPhone);
			addManagerPage.typeMobile(originalMobile);
			addManagerPage.selectUserTimezone(originalUserTimezone);
			addManagerPage.selectUserLanguage(originalUserLanguage);
			listManagersPage = addManagerPage.submitAdd().toManagers();
		}
		// for purpose of removing the added user
		removeUserId = userId;
		lastPage = listManagersPage;
	}

	private void setData() {
		userId = getInputData("userId");
		originalSurname = getInputData("preSurname");
		originalFullName = getInputData("preFullName");
		originalEmail = getInputData("preEmail");
		originalPhone = getInputData("prePhone");
		originalMobile = getInputData("preMobile");
		originalUserTimezone = getInputData("preUserTimezone");
		originalUserLanguage = getInputData("preUserLanguage");
		surname = getInputData("surname");
		fullName = getInputData("fullName");
		email = getInputData("email");
		phone = getInputData("phone");
		mobile = getInputData("mobile");
		userTimezone = getInputData("userTimezone");
		userLanguage = getInputData("userLanguage");
		expectedSurname = TestUtils.isEmpty(surname) ? getOutputData("surname")
				: surname;
		expectedFullName = TestUtils.isEmpty(fullName) ? getOutputData("fullName")
				: fullName;
		expectedEmail = TestUtils.isEmpty(email) ? getOutputData("email")
				: email;
		expectedPhone = TestUtils.isEmpty(phone) ? getOutputData("phone")
				: phone;
		expectedMobile = TestUtils.isEmpty(mobile) ? getOutputData("mobile")
				: mobile;
		expectedUserTimezone = TestUtils.isEmpty(userTimezone) ? getOutputData("userTimezone")
				: userTimezone;
		expectedUserLanguage = TestUtils.isEmpty(userLanguage) ? getOutputData("userLanguage")
				: userLanguage;
	}

	public void testEditManagerSuccess() {
		// go to edit manager page
		EditManagerDetailsPage editPage = listManagersPage
				.toEditManager(userId);
		// edit
		editPage.typeSurname(surname);
		editPage.typeFullName(fullName);
		editPage.typeEmail(email);
		editPage.typePhone(phone);
		editPage.typeMobile(mobile);
		editPage.selectUserTimezone(userTimezone);
		editPage.selectUserLanguage(userLanguage);
		// submit and go back
		editPage = editPage.submitEdit().toManagers().toEditManager(userId);
		// verify the values in each field with input value
		verifyEquals(expectedSurname, editPage.getSurname());
		verifyEquals(expectedFullName, editPage.getFullName());
		verifyEquals(expectedEmail, editPage.getEmail());
		verifyEquals(expectedPhone, editPage.getPhone());
		verifyEquals(expectedMobile, editPage.getMobile());
		verifyEquals(expectedUserTimezone, editPage.getUserTimezone());
		verifyEquals(expectedUserLanguage, editPage.getUserLanguage());

		// for purpose of removing the added user
		lastPage = editPage;
	}

	public void testEditManagerFail() {
		// go to edit manager page
		EditManagerDetailsPage editPage = listManagersPage
				.toEditManager(userId);
		// edit
		if (null == surname) {
			editPage.clearSurname();
		} else if (!originalSurname.equals(surname)) {
			editPage.typeSurname(surname);
		}
		if (null == fullName) {
			editPage.clearFullName();
		} else if (!originalFullName.equals(fullName)) {
			editPage.typeFullName(fullName);
		}
		if (null == email) {
			editPage.clearEmail();
		} else if (!originalEmail.equals(email)) {
			editPage.typeEmail(email);
		}
		if (null == phone) {
			editPage.clearPhone();
		} else if (!originalPhone.equals(phone)) {
			editPage.typePhone(phone);
		}
		if (null == mobile) {
			editPage.clearMobile();
		} else if (!originalMobile.equals(mobile)) {
			editPage.typeMobile(mobile);
		}
		if (!originalUserTimezone.equals(userTimezone)) {
			editPage.selectUserTimezone(userTimezone);
		}
		if (!originalUserLanguage.equals(userLanguage)) {
			editPage.selectUserLanguage(userLanguage);
		}
		// submit add and stay in the same page
		editPage = editPage.submitEditExpectingFailure();
		// verify error message
		verifyEquals(getOutputData("userIdErrMsg"), editPage.getUserIdErrMsg());
		verifyEquals(getOutputData("surnameErrMsg"),
				editPage.getSurnameErrMsg());
		verifyEquals(getOutputData("fullNameErrMsg"),
				editPage.getFullNameErrMsg());
		verifyEquals(getOutputData("emailErrMsg"), editPage.getEmailErrMsg());
		verifyEquals(getOutputData("phoneErrMsg"), editPage.getPhoneErrMsg());
		verifyEquals(getOutputData("mobileErrMsg"), editPage.getMobileErrMsg());
		verifyEquals(getOutputData("userTimezoneErrMsg"),
				editPage.getUserTimezoneErrMsg());
		verifyEquals(getOutputData("userLanguageErrMsg"),
				editPage.getUserLanguageErrMsg());
		verifyEquals(getOutputData("commentsErrMsg"),
				editPage.getCommentsErrMsg());
		verifyEquals(getOutputData("pwdExpiresErrMsg"),
				editPage.getPasswordExpiresErrMsg());
		// verify the values in each field
		verifyEquals(expectedSurname, editPage.getSurname());
		verifyEquals(expectedFullName, editPage.getFullName());
		verifyEquals(expectedEmail, editPage.getEmail());
		verifyEquals(expectedPhone, editPage.getPhone());
		verifyEquals(expectedMobile, editPage.getMobile());
		verifyEquals(expectedUserTimezone, editPage.getUserTimezone());
		verifyEquals(expectedUserLanguage, editPage.getUserLanguage());
		// check the details are not overridden
		editPage = editPage.toManagers().toEditManager(userId);
		verifyEquals(originalSurname, editPage.getSurname());
		verifyEquals(originalFullName, editPage.getFullName());
		verifyEquals(originalEmail, editPage.getEmail());
		verifyEquals(originalPhone, editPage.getPhone());
		verifyEquals(originalMobile, editPage.getMobile());
		verifyEquals(originalUserTimezone, editPage.getUserTimezone());
		verifyEquals(originalUserLanguage, editPage.getUserLanguage());
		// for purpose of removing the added user
		lastPage = editPage;
	}

	public void testRemoveManager() {
		// get the total number of managers in the list before add
		int expectedTotalManagers = listManagersPage.getTotalNumber() - 1;
		RemoveManagerPage removePage = listManagersPage.toEditManager(userId)
				.toRemoveManager();
		verifyEquals(userId, removePage.getRemoveUserId());
		verifyEquals(originalFullName, removePage.getRemoveFullName());
		verifyEquals(getOutputData("removeMsg"), removePage.getRemoveMsg());
		listManagersPage = removePage.submitRemove();
		verifyEquals(expectedTotalManagers, listManagersPage.getTotalNumber());
		verifyFalse(String.format(
				"Expect '%s NOT listed in the List Managers Page'", userId),
				listManagersPage.isManagerListed(userId));
		removeUserId = null;
	}

	@TestAfter
	public void after() {
		// remove the user if added
		if (!TestUtils.isEmpty(removeUserId)) {
			listManagersPage = lastPage.toManagers();
			if (listManagersPage.isManagerListed(removeUserId)) {
				listManagersPage = listManagersPage.toEditManager(removeUserId)
						.toRemoveManager().submitRemove();
			}
		}
	}
}