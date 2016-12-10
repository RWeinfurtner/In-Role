package com.techelevator.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class SendAccessNotificationEmail {
	
	public void send(String toEmailAddress, String messageBody) {
		final String username = "InRoleTechElevator@gmail.com";
		final String password = "wintrich";	


		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("InRoleTechElevator@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toEmailAddress));
			message.setSubject("An account has been created for you with In Role!");
			message.setText("Dear " + toEmailAddress + ", \n" + messageBody);
			
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getSignUpMessage(String link) {
		return "\n \n Congratulations!  \n \n An account has just been created for you with In Role. "
			+ "In Role is a collaborative web-based project designed to allow all participants in a multi-variable selection process to gather and input data, and then generate reporting tools. "	
			+ "Please follow this link:   " + link + "  to log into In Role for the first time."
			+ "\n \n Once you have logged in you will be given the opportunity to update your account details and view important details of upcoming In Role events."
			+ "\n \n Thank you again for your interest in In Role! \n \n Sincerly, "
			+ "\n \n The In Role Administrative Team";
	}
	
	public String getStudentEventMessage(String link) {
		return "\n \n Hello, Student!  \n \n An event has been scheduled, and you're invited! "	
				+ "Please follow this link:   " + link
				+ "\n \n You with be prompted for some event preferences that will be used to create your personal schedule."
				+ "\n \n Good luck! \n \n Sincerely, "
				+ "\n \n The In Role Team";
	}

}