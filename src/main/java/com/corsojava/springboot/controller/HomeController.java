package com.corsojava.springboot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@GetMapping
	public String home() {
		return "redirect:fotos";
	}
	
	@GetMapping("/testTLS")
	public String testTLS(Authentication auth) {
		System.out.println("Login OK -- nome = " + auth.getName());
		return "testTLS";
	}

}
