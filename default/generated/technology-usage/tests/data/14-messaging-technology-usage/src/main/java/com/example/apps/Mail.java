package com.example.apps;

import java.util.Properties;
import java.util.Date;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; //1
// Commented out optional mail libraries
// import com.sun.mail.smtp.SMTPMessage;
// import org.simplejavamail.email.Email;
// import org.apache.commons.mail.Email; // 7

public class Mail {
	
	private static class MyMessagingException extends MessagingException {
		public MyMessagingException(String msg) {
			super(msg);
		}
	}
	
	public static void main(String argv[]) {
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "my-mail-server");
	    Session session = Session.getInstance(props, null); // 9, 10

	    try {
	        MimeMessage msg = new MimeMessage(session); // 11, 12,
	        msg.setFrom(new InternetAddress("me@example.com"));
	        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("you@example.com")); // 13
	        msg.setSubject("JavaMail hello world example");
	        msg.setSentDate(new Date());
	        msg.setText("Hello, world!\n");
	        Transport.send(msg, "me@example.com", "my-password"); // 14, 15???
	    } catch (MessagingException mex) { // 16 - catch the parent class
	        System.out.println("send failed, exception: " + mex); // 17???
	    }
	}
}
