package com.nasdaqomx.test.selenium.testcase.ldapconfig.utils;

import com.nasdaqomx.test.selenium.base.TestUtils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//you can use the code below in anywhere
		LdapEmailService service = TestUtils.getApplicationContext().getBean(
				LdapEmailService.class);
		try {
			service.readMailByUserId("123123");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
