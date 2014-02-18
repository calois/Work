package com.nasdaqomx.test.selenium.testcase.ldapconfig.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.search.FlagTerm;
import javax.mail.search.SubjectTerm;

import com.nasdaqomx.test.selenium.base.EmailService;

/**
 * 
 */

/**
 * @author Abel
 * @date 18/02/2014
 */
public class LdapEmailService extends EmailService {

	private Message messages[];
	private String mailFromLdap;

	/**
	 * @param host
	 * @param username
	 * @param password
	 * @throws MessagingException
	 */
	public LdapEmailService(String host, String username, String password)
			throws Exception {
		super(host, username, password);
	}

	public void readMailByUserId(String userId) throws Exception {
		try {
			if (null != inbox) {
				for (int i = 0; i < 5; i++) {
					messages = inbox.search(new FlagTerm(new Flags(Flag.SEEN),
							false), inbox.search(new SubjectTerm(
							"Ldapconfig: Your password has been reset!")));
					// Wait for 10 seconds
					if (messages.length == 0) {
						Thread.sleep(10000);
					} else {
						break;
					}
				}
				for (Message mail : messages) {
					String line;
					StringBuffer buffer = new StringBuffer();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(mail.getInputStream()));
					while ((line = reader.readLine()) != null) {
						buffer.append(line);
					}
					String content = buffer.toString();
					if (userId.equals(getUserName(content))) {
						mailFromLdap = content;
						deleteMessage(mail);
						break;
					} else {
						unReadMessage(mail);
					}
				}
			}
		} finally {
			close();
		}
	}

	public String getMailContent() {
		return this.mailFromLdap;
	}

	public String getUserName() {
		return getUserName(mailFromLdap);
	}

	public String getPassword() {
		return getPassword(mailFromLdap);
	}

	private String getUserName(String content) {
		return null == content ? null : content.split("Username:")[1]
				.split(" ")[1];
	}

	private String getPassword(String content) {
		return null == content ? null : content.split("Password:")[1]
				.split(" ")[1];
	}

}
