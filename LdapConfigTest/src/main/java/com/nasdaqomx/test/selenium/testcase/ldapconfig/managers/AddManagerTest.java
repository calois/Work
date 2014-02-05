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

public class AddManagerTest extends AbstractTest {

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
	private String comments;
	private String expectedUserId;
	private String expectedSurname;
	private String expectedFullName;
	private String expectedEmail;
	private String expectedPhone;
	private String expectedMobile;
	private String expectedUserTimezone;
	private String expectedUserLanguage;
	private String expectedComments;

	@TestBefore
	public void before() {
		listManagersPage = createPageObject(LoginPage.class).loginAs(
				getInputData("username"), getInputData("password"))
				.toManagers();
	}

	private void setData() {
		userId = getInputData("userId");
		surname = getInputData("surname");
		fullName = getInputData("fullName");
		email = getInputData("email");
		phone = getInputData("phone");
		mobile = getInputData("mobile");
		userTimezone = getInputData("userTimezone");
		userLanguage = getInputData("userLanguage");
		userLanguage = getInputData("userLanguage");
		comments = getInputData("comments");
		expectedUserId = TestUtils.isEmpty(userId) ? getOutputData("userId")
				: userId;
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
		expectedComments = TestUtils.isEmpty(comments) ? getOutputData("comments")
				: comments;
	}

	public void testAddManagerSuccess() {
		// get data
		setData();
		// go to add manager page
		AddManagerPage addManagerPage = listManagersPage.toAddManager();
		// fill in the page
		addManagerPage.typeUserId(userId);
		addManagerPage.typeSurname(surname);
		addManagerPage.typeFullName(fullName);
		addManagerPage.typeEmail(email);
		addManagerPage.typePhone(phone);
		addManagerPage.typeMobile(mobile);
		addManagerPage.selectUserTimezone(userTimezone);
		addManagerPage.selectUserLanguage(userLanguage);
		// submit add and go to managers tab
		listManagersPage = addManagerPage.submitAdd().toManagers();
		// if the useId is listed continue test, otherwise stop test
		assertTrue(String.format(
				"Expect '%s listed in the List Managers Page'", userId),
				listManagersPage.isManagerListed(userId));
		// verify the status, lastLogin and accountExpires
		verifyEquals(getOutputData("status"),
				listManagersPage.getStatus(userId));
		verifyEquals(getOutputData("lastLogin"),
				listManagersPage.getLastLogin(userId));
		verifyEquals(getOutputData("accountExpires"),
				listManagersPage.getAccountExpires(userId));
		// go to edit manager page
		EditManagerDetailsPage editPage = listManagersPage
				.toEditManager(userId);
		// verify the values in each field with input value
		verifyEquals(expectedUserId, editPage.getUserId());
		verifyEquals(expectedSurname, editPage.getSurname());
		verifyEquals(expectedFullName, editPage.getFullName());
		verifyEquals(expectedEmail, editPage.getEmail());
		verifyEquals(expectedPhone, editPage.getPhone());
		verifyEquals(expectedMobile, editPage.getMobile());
		verifyEquals(expectedUserTimezone, editPage.getUserTimezone());
		verifyEquals(expectedUserLanguage, editPage.getUserLanguage());

		// for purpose of removing the added user
		removeUserId = userId;
		lastPage = editPage;
	}

	public void testAddManagerFail() {
		// get the total number of managers in the list before add
		int totalManager = listManagersPage.getTotalNumber();
		// get data
		setData();
		// go to add manager page
		AddManagerPage addManagerPage = listManagersPage.toAddManager();
		// fill in the page
		addManagerPage.typeUserId(userId);
		addManagerPage.typeSurname(surname);
		addManagerPage.typeFullName(fullName);
		addManagerPage.typeEmail(email);
		addManagerPage.typePhone(phone);
		addManagerPage.typeMobile(mobile);
		addManagerPage.selectUserTimezone(userTimezone);
		addManagerPage.selectUserLanguage(userLanguage);
		addManagerPage.typeComments(comments);
		// submit add and stay in the same page
		addManagerPage = addManagerPage.submitAddExpectingFailure();
		// verify error message
		verifyEquals(getOutputData("userIdErrMsg"),
				addManagerPage.getUserIdErrMsg());
		verifyEquals(getOutputData("surnameErrMsg"),
				addManagerPage.getSurnameErrMsg());
		verifyEquals(getOutputData("fullNameErrMsg"),
				addManagerPage.getFullNameErrMsg());
		verifyEquals(getOutputData("emailErrMsg"),
				addManagerPage.getEmailErrMsg());
		verifyEquals(getOutputData("phoneErrMsg"),
				addManagerPage.getPhoneErrMsg());
		verifyEquals(getOutputData("mobileErrMsg"),
				addManagerPage.getMobileErrMsg());
		verifyEquals(getOutputData("userTimezoneErrMsg"),
				addManagerPage.getUserTimezoneErrMsg());
		verifyEquals(getOutputData("userLanguageErrMsg"),
				addManagerPage.getUserLanguageErrMsg());
		verifyEquals(getOutputData("commentsErrMsg"),
				addManagerPage.getCommentsErrMsg());

		// verify the values in each field
		verifyEquals(expectedUserId, addManagerPage.getUserId());
		verifyEquals(expectedSurname, addManagerPage.getSurname());
		verifyEquals(expectedFullName, addManagerPage.getFullName());
		verifyEquals(expectedEmail, addManagerPage.getEmail());
		verifyEquals(expectedPhone, addManagerPage.getPhone());
		verifyEquals(expectedMobile, addManagerPage.getMobile());
		verifyEquals(expectedUserTimezone, addManagerPage.getUserTimezone());
		verifyEquals(expectedUserLanguage, addManagerPage.getUserLanguage());
		verifyEquals(expectedComments, addManagerPage.getComments());

		// go to managers tab
		listManagersPage = addManagerPage.toManagers();
		// no managers are added
		verifyEquals(totalManager, listManagersPage.getTotalNumber());
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