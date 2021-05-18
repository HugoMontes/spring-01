package com.educomser.app.ws.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/rest/welcome")
public class WelcomeRestController {

	@GetMapping("/hola")
	public String index() {
		return "Hola Spring boot deste un controlador rest";
	}
}
