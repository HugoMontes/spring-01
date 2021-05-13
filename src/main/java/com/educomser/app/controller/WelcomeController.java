package com.educomser.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

	@GetMapping("/saludo")
	public String index() {
		return "Bienvenidos al curso de Spring boot";
	}
}
