package com.educomser.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.educomser.app.service.SaludoService;

@Controller
@RequestMapping("/saludo")
public class SaludoController {

	// Inyectar el servicio
	@Autowired
	@Qualifier("saludoService")
	private SaludoService saludoService;

	@GetMapping("/{nombre}")
	public ModelAndView index(@PathVariable("nombre") String nombre) {
		ModelAndView mv = new ModelAndView("/saludo/index");
		// Llamar al metodo del servicio de negocio
		mv.addObject("mensaje", saludoService.saludar(nombre));
		return mv;
	}
}
