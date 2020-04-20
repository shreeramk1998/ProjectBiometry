package com.biometry.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biometry.app.entity.ConfirmationToken;
import com.biometry.app.entity.User;
import com.biometry.app.mailing.EmailHandler;
import com.biometry.app.repository.ConfirmationTokenRepo;
import com.biometry.app.repository.UserRepository;
@Component
public class RegisterUser {

	@Autowired
	EmailHandler emailHandler;
	@Autowired
	ConfirmationTokenRepo confirmationTokenRepo;
	@Autowired 
	UserRepository userRepository;
	
	
	public String confirmUserAccount(User user) {
		if(user==null) return "Failed";
		if(confirmationTokenRepo.existsByUser(user)) {
				confirmationTokenRepo.delete(confirmationTokenRepo.findByUser(user));
		}
		ConfirmationToken confToken = new ConfirmationToken(user);
		if(confirmationTokenRepo.save(confToken)==null) return "Failed";
		String matter = "Welcome to Project biometry \n"
				+ "Please click on the below link for email activation or open in new tab\n \n \n"
				+ "http://localhost:8080/confirm-account?token="+confToken.getConfirmationToken()+"\n \n \n"
						+ "Please use the Auto-Generated Password while you Log In : "+user.getPassword()+"\n \n \n "
						+ "Thank You! :)";
		return emailHandler.sendEmail(user.getUserName(), "Activation Link", matter);
		
		
	}
}
