package com.educomser.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeRestController {

	@GetMapping("/hola")
	public String index() {
		return "Hola Spring boot desde un controlador rest";
	}
}
