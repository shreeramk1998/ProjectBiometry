package com.biometry.app;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

import com.biometry.app.mailing.EmailHandler;
import com.biometry.app.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@SpringBootApplication
public class BiometryApplication implements CommandLineRunner {
	@Autowired
	ReportService reportService;
	public static void main(String[] args) {
		System.out.println("SPRING VERSION "+SpringVersion.getVersion());
		SpringApplication.run(BiometryApplication.class, args);
		
	}
	@Override
	public void run(String... args) {
//		try {
//			reportService.exportReport();
//		} catch (FileNotFoundException | JRException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		emailHandler.sendEmail();
	}
}
