package com.nasdaqomx.test.selenium.base;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

/**
 * 
 */

/**
 * @author Abel
 * @date 18/02/2014
 */
public class EmailService {

	private String host = "outlook-au.nasdaqomx.com";
	private String username = "ORG\\autsmb";
	private String password = "87TrW34Xv7";
	private Session session = null;
	private Store store = null;
	protected Folder inbox = null;

	public EmailService(String host, String username, String password)
			throws MessagingException {
		this.host = host;
		this.username = username;
		this.password = password;
		connect();
	}

	private void connect() throws MessagingException {
		Properties properties = new Properties();
		properties.setProperty("mail.store.protocol", "imaps");
		session = Session.getDefaultInstance(properties, null);
		store = session.getStore("imaps");
		store.connect(host, username, password);
		inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_WRITE);
	}

	protected void deleteMessage(Message message) throws MessagingException {
		message.setFlag(Flags.Flag.DELETED, true);
	}

	protected void unReadMessage(Message message) throws MessagingException {
		message.setFlag(Flags.Flag.SEEN, false);
	}

	protected void close() throws MessagingException {
		if (null != inbox && null != store) {
			inbox.close(true);
			store.close();
		}
	}
}
