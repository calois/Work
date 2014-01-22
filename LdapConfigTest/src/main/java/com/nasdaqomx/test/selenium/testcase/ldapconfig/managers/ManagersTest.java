package com.nasdaqomx.test.selenium.testcase.ldapconfig.managers;

import com.nasdaqomx.test.selenium.base.AbstractTest;
import com.nasdaqomx.test.selenium.base.anno.TestBefore;
import com.nasdaqomx.test.selenium.page.ldapconfig.LoginPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.AddManagerPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.ListManagersPage;

public class ManagersTest extends AbstractTest {

	private ListManagersPage listManagersPage;

	@TestBefore
	public void before() {
		listManagersPage = createPageObject(LoginPage.class).loginAs(
				getInputData("username"), getInputData("password"))
				.clickManagers();
	}

	public void testAddManager() {
		AddManagerPage addManagerPage = listManagersPage.clickAddManager();
		String userId = getInputData("userId");
		String surname = getInputData("surname");
		String fullName = getInputData("fullName");
		String email = getInputData("email");
		String phone = getInputData("phone");
		String mobile = getInputData("mobile");
		String userTimezone = getInputData("userTimezone");
		String userLanguage = getInputData("userLanguage");
		String comments = getInputData("comments");

		addManagerPage.typeUserId(userId);
		addManagerPage.typeSurname(surname);
		addManagerPage.typeFullName(fullName);
		addManagerPage.typeEmail(email);
		addManagerPage.typePhone(phone);
		addManagerPage.typeMobile(mobile);
		addManagerPage.selectUserTimezone(userTimezone);
		addManagerPage.selectUserLanguage(userLanguage);
		addManagerPage.typeComments(comments);
		assertTrue("", addManagerPage.clickAddManagerBtn().clickManagers().isManagerListed(userId));
		
	}
}