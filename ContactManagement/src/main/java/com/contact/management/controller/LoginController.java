package com.contact.management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/contact/auth")
public class LoginController {

	@GetMapping("/process")
	public String process() {
		return "User Authenticated!!";
	}

}
