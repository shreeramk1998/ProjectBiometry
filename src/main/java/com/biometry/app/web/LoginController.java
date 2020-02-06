package com.biometry.app.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class LoginController {
    
	@GetMapping
    public @ResponseBody String home() {
        return "Home called";
    }
}