package com.nasdaqomx.test.selenium.page.ldapconfig.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.nasdaqomx.test.selenium.base.TestManager;

public class RemoveManagerPage extends ManagerBasePage {

	private static final String URL = "removeManager.view";
	private static final By REMOVER_MANAGER_LOCATOR = By
			.xpath("//form/input[@value='Yes! Remove manager']");

	private WebElement removeManager;

	public RemoveManagerPage(TestManager testManager) {
		super(testManager);
		assertUrl(URL, this.getSimpleUrl());
		try {
			removeManager = findElement(REMOVER_MANAGER_LOCATOR);
		} catch (NoSuchElementException e) {
			fail(e.getMessage());
		}
	}

	public ListManagersPage submitRemove() {
		this.removeManager.click();
		return createPageObject(ListManagersPage.class);
	}

}
