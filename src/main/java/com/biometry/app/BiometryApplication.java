package com.biometry.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

import com.biometry.app.mailing.EmailHandler;

@SpringBootApplication
public class BiometryApplication implements CommandLineRunner {
	@Autowired
	EmailHandler emailHandler;
	public static void main(String[] args) {
		System.out.println("SPRING VERSION "+SpringVersion.getVersion());
		SpringApplication.run(BiometryApplication.class, args);
	}
	@Override
	public void run(String... args) {
		
//		emailHandler.sendEmail();
	}
}
