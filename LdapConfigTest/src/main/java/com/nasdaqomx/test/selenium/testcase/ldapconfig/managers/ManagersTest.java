package com.nasdaqomx.test.selenium.testcase.ldapconfig.managers;

import com.nasdaqomx.test.selenium.base.AbstractTest;
import com.nasdaqomx.test.selenium.base.anno.TestAfter;
import com.nasdaqomx.test.selenium.base.anno.TestBefore;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;
import com.nasdaqomx.test.selenium.page.ldapconfig.LoginPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.AddManagerPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.EditManagerDetailsPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.ListManagersPage;

public class ManagersTest extends AbstractTest {

	private ListManagersPage listManagersPage;
	private LdapconfigBasePage lastPage;
	private String removeUserId;

	@TestBefore
	public void before() {
		listManagersPage = createPageObject(LoginPage.class).loginAs(
				getInputData("username"), getInputData("password"))
				.toManagers();
	}

	public void testAddManager() {
		AddManagerPage addManagerPage = listManagersPage.addManager();
		String userId = getInputData("userId");
		String surname = getInputData("surname");
		String fullName = getInputData("fullName");
		String email = getInputData("email");
		String phone = getInputData("phone");
		String mobile = getInputData("mobile");
		String userTimezone = getInputData("userTimezone");
		String userLanguage = getInputData("userLanguage");

		addManagerPage.typeUserId(userId);
		addManagerPage.typeSurname(surname);
		addManagerPage.typeFullName(fullName);
		addManagerPage.typeEmail(email);
		addManagerPage.typePhone(phone);
		addManagerPage.typeMobile(mobile);
		addManagerPage.selectUserTimezone(userTimezone);
		addManagerPage.selectUserLanguage(userLanguage);

		listManagersPage = addManagerPage.submitAdd().toManagers();
		assertTrue("\"" + userId
				+ "\" is not listed in the List Managers Page.",
				listManagersPage.isManagerListed(userId));
		verifyEquals(getOutputData("status"),
				listManagersPage.getStatus(userId));
		verifyEquals(getOutputData("lastLogin"),
				listManagersPage.getLastLogin(userId));
		verifyEquals(getOutputData("accountExpires"),
				listManagersPage.getAccountExpires(userId));
		EditManagerDetailsPage editPage = listManagersPage.editManager(userId);
		verifyEquals(userId, editPage.getUserId());
		verifyEquals(surname, editPage.getSurname());
		verifyEquals(fullName, editPage.getFullName());
		verifyEquals(email, editPage.getEmail());
		verifyEquals(phone, editPage.getPhone());
		verifyEquals(mobile, editPage.getMobile());
		verifyEquals(userTimezone, editPage.getUserTimezone());
		verifyEquals(userLanguage, editPage.getUserLanguage());

		removeUserId = userId;
		lastPage = editPage;
	}

	@TestAfter
	public void after() {
		listManagersPage = lastPage.toManagers();
		if (listManagersPage.isManagerListed(removeUserId)) {
			listManagersPage = listManagersPage.editManager(removeUserId)
					.removeManager().submitRemove();
		}
	}
}