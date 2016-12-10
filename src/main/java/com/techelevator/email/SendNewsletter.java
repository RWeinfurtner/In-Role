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
public class SendNewsletter {
	
	private String toEmailAddress;
	
	public void send(String toEmailAddress) {
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
			message.setSubject("Thank you for signing up with In Role");
			message.setText("Congratulations!"
				+ "\n \n You've just been signed up to receive Hourly email updates from IN ROLE! "
				+ "In Role is a collaborative web-based project designed to allow all participants in a multi-variable selection process to input data, and generate reporting tools. "	
				+ "we are excited to have the opportunity to send you newsletters EVERY HOUR updating you on the progress of this site, and since it will take us about 15 minutes to document what has been accomplished since the last email, it will feel like you are getting an email every 45 minutes...THAT'S EVEN BETTER!" 
				+ "\n \n Thank you again for signing up, we look forward to sending you emails until we can get the unsubscribe feature working properly! \n \n Sincerly, "
				+ "\n \n The In Role Development Team");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public String getToEmailAddress() {
		return toEmailAddress;
	}

	public void setToEmailAddress(String toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}
}