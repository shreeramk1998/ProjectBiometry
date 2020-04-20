package com.biometry.app.mailing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailHandler {

	@Autowired
    private JavaMailSender javaMailSender;
	
	public String sendEmail(String to , String subject , String matter) {
		System.out.println("****************************************************");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);

        msg.setSubject(subject);
        msg.setText(matter);
        try {
        	javaMailSender.send(msg);
			
		} catch (RuntimeException r) {
			return "Could not send email !";
		}
        return "Successfully sent activation link";

    }
}
