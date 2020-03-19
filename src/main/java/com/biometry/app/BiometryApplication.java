package com.biometry.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class BiometryApplication {

    public static void main(String[] args) {
    	System.out.println("SPRING VERSION "+SpringVersion.getVersion());
        SpringApplication.run(BiometryApplication.class, args);
    }

}
