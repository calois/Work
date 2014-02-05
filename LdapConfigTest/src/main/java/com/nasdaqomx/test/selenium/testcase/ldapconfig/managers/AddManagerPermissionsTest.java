package com.nasdaqomx.test.selenium.testcase.ldapconfig.managers;

import java.util.ArrayList;
import java.util.List;

import com.nasdaqomx.test.selenium.base.AbstractTest;
import com.nasdaqomx.test.selenium.base.TestUtils;
import com.nasdaqomx.test.selenium.base.anno.TestAfter;
import com.nasdaqomx.test.selenium.base.anno.TestBefore;
import com.nasdaqomx.test.selenium.page.ldapconfig.LdapconfigBasePage;
import com.nasdaqomx.test.selenium.page.ldapconfig.LoginPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.AddClientPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.clients.ListClientsPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.AddManagerPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.EditManagerPermissionsPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.managers.ListManagersPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.markets.AddMarketPage;
import com.nasdaqomx.test.selenium.page.ldapconfig.markets.ListMarketsPage;

public class AddManagerPermissionsTest extends AbstractTest {

	private EditManagerPermissionsPage editManagerPermissionsPage;
	private LdapconfigBasePage lastOpertaionPage;
	private String username;
	private String password;
	private String removeUserId;
	private String userId;
	private String permissionClientId;
	private String permissionMarketCode;
	private String permissionRole;
	private String clientId;
	private String marketCode;
	private String originalSurname;
	private String originalFullName;
	private String originalEmail;
	private String originalPhone;
	private String originalMobile;
	private String originalUserTimezone;
	private String originalUserLanguage;
	private String removeClientId;
	private String originalClientName;
	private String originalUrl;
	private String removeMarketCode;
	private String originalMarketName;
	private String originalSupportEmail;

	@TestBefore
	public void before() {
		setData();
		ListClientsPage listClientsPage = createPageObject(LoginPage.class)
				.loginAs(username, password).toClients();
		// add the client if not exist
		if (!listClientsPage.isClientListed(clientId)) {
			AddClientPage addClientPage = listClientsPage.toAddClient();
			addClientPage.typeClientId(clientId);
			addClientPage.typeClientName(originalClientName);
			addClientPage.typeUrlOfClientService(originalUrl);
			listClientsPage = addClientPage.submitAdd();

		}
		// add the market if not exist
		ListMarketsPage listMarketsPage = listClientsPage.toMarkets();
		if (!listMarketsPage.isMarketListed(marketCode)) {
			AddMarketPage addMarketPage = listMarketsPage.toAddMarket();
			addMarketPage.typeMarketCode(marketCode);
			addMarketPage.typeMarketName(originalMarketName);
			addMarketPage.typeSupportEmailAddress(originalSupportEmail);
			listMarketsPage = addMarketPage.submitAdd();

		}
		// remove the manager if exist
		ListManagersPage listManagersPage = listMarketsPage.toManagers();
		if (listManagersPage.isManagerListed(userId)) {
			listManagersPage = listManagersPage.toEditManager(userId)
					.toRemoveManager().submitRemove();
		}
		// add the manager
		AddManagerPage addManagerPage = listManagersPage.toAddManager();
		addManagerPage.typeUserId(userId);
		addManagerPage.typeSurname(originalSurname);
		addManagerPage.typeFullName(originalFullName);
		addManagerPage.typeEmail(originalEmail);
		addManagerPage.typePhone(originalPhone);
		addManagerPage.typeMobile(originalMobile);
		addManagerPage.selectUserTimezone(originalUserTimezone);
		addManagerPage.selectUserLanguage(originalUserLanguage);
		editManagerPermissionsPage = addManagerPage.submitAdd();
		// for purpose of removing the added user, client and market
		removeUserId = userId;
		removeClientId = clientId;
		removeMarketCode = marketCode;
		lastOpertaionPage = editManagerPermissionsPage;
	}

	private void setData() {
		username = getInputData("username");
		password = getInputData("password");
		userId = getInputData("userId");
		originalSurname = getInputData("preSurname");
		originalFullName = getInputData("preFullName");
		originalEmail = getInputData("preEmail");
		originalPhone = getInputData("prePhone");
		originalMobile = getInputData("preMobile");
		originalUserTimezone = getInputData("preUserTimezone");
		originalUserLanguage = getInputData("preUserLanguage");
		clientId = getInputData("clientId");
		originalClientName = getInputData("preClientName");
		originalUrl = getInputData("preUrl");
		marketCode = getInputData("marketCode");
		originalMarketName = getInputData("preMarketName");
		originalSupportEmail = getInputData("preSupportEmail");
		permissionClientId = getInputData("permissionClientId");
		permissionMarketCode = getInputData("permissionMarketCode");
		permissionRole = getInputData("permissionRole");
	}

	public void testAddManagerPermissions() {
		List<String> list = new ArrayList<>();
		int total = editManagerPermissionsPage.getTotalPermissionRows();
		// verify the initial row number and value
		verifyEquals(1, total);
		list.add("");
		verifyEquals(list, editManagerPermissionsPage.getClientId(1));
		verifyEquals(list, editManagerPermissionsPage.getMarketCode(1));
		verifyEquals(list, editManagerPermissionsPage.getRole(1));
		list.clear();
		// add permission
		editManagerPermissionsPage.selectClientId(1, permissionClientId);
		editManagerPermissionsPage.selectMarketCode(1, permissionMarketCode);
		editManagerPermissionsPage.selectRole(1, permissionRole);
		editManagerPermissionsPage = editManagerPermissionsPage.submitEdit();
		// verify the results after add
		verifyEquals(total + 1,
				editManagerPermissionsPage.getTotalPermissionRows());
		list.add(permissionClientId);
		verifyEquals(list, editManagerPermissionsPage.getClientId(1));
		list.clear();
		list.add(permissionMarketCode);
		verifyEquals(list, editManagerPermissionsPage.getMarketCode(1));
		list.clear();
		list.add(permissionRole);
		verifyEquals(list, editManagerPermissionsPage.getRole(1));
		list.clear();
		list.add("");
		verifyEquals(list, editManagerPermissionsPage.getClientId(2));
		verifyEquals(list, editManagerPermissionsPage.getMarketCode(2));
		verifyEquals(list, editManagerPermissionsPage.getRole(2));
		list.clear();
		// re-login to verify the results
		editManagerPermissionsPage = editManagerPermissionsPage.toLogout()
				.toLogin().loginAs(username, password).toManagers()
				.toEditManager(userId).toManagerPermissions();
		verifyEquals(total + 1,
				editManagerPermissionsPage.getTotalPermissionRows());
		list.add(permissionClientId);
		verifyEquals(list, editManagerPermissionsPage.getClientId(1));
		list.clear();
		list.add(permissionMarketCode);
		verifyEquals(list, editManagerPermissionsPage.getMarketCode(1));
		list.clear();
		list.add(permissionRole);
		verifyEquals(list, editManagerPermissionsPage.getRole(1));
		list.clear();
		list.add("");
		verifyEquals(list, editManagerPermissionsPage.getClientId(2));
		verifyEquals(list, editManagerPermissionsPage.getMarketCode(2));
		verifyEquals(list, editManagerPermissionsPage.getRole(2));
		list.clear();
		// for purpose of removing the added user, client and market
		lastOpertaionPage = editManagerPermissionsPage;
	}

	@TestAfter
	public void after() {
		// remove the client if added
		if (!TestUtils.isEmpty(removeClientId)) {
			ListClientsPage listClientsPage = lastOpertaionPage.toClients();
			if (listClientsPage.isClientListed(removeClientId)) {
				listClientsPage = listClientsPage
						.toRemoveClient(removeClientId).submitRemove();
			}
			lastOpertaionPage = listClientsPage;
		}
		// remove the market if added
		if (!TestUtils.isEmpty(removeMarketCode)) {
			ListMarketsPage listMarketsPage = lastOpertaionPage.toMarkets();
			if (listMarketsPage.isMarketListed(removeMarketCode)) {
				listMarketsPage = listMarketsPage.toRemoveMarket(
						removeMarketCode).submitRemove();
			}
			lastOpertaionPage = listMarketsPage;
		}
		// remove the user if added
		if (!TestUtils.isEmpty(removeUserId)) {
			ListManagersPage listManagersPage = lastOpertaionPage.toManagers();
			if (listManagersPage.isManagerListed(removeUserId)) {
				listManagersPage = listManagersPage.toEditManager(removeUserId)
						.toRemoveManager().submitRemove();
			}
		}
	}
}