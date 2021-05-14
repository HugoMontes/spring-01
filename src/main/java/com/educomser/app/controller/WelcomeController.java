package com.educomser.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

	@GetMapping("/saludo")
	public String index() {
		return "welcome/index";
	}
}
