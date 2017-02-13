package com.saleon.service;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class Mail
{
	private MailSender mailSender;
	private String subject = "Order Confirmation ItemNo: ";
	private String msg;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String to, Long OrderId, String role, String postUserName) {

		subject = subject +OrderId;
		
		if(role.equals("post")){
			msg = "Your Order has been placed";
		}
		else{
			msg = "Your Order has been bought by " + postUserName;
		}
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}
}