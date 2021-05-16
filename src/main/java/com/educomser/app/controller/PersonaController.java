package com.educomser.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.educomser.app.model.Persona;

@Controller
@RequestMapping("/persona")
public class PersonaController {

	private static List<Persona> personasList;

	static {
		personasList = new ArrayList<Persona>();
		personasList.add(new Persona("Juan", 34));
		personasList.add(new Persona("Marcos", 29));
		personasList.add(new Persona("Lucas", 16));
		personasList.add(new Persona("Mateo", 79));
	}

	@GetMapping("/detalle")
	public String index(Model model) {
		Persona per = new Persona("Alex", 65);
		model.addAttribute("persona", per);
		return "persona/index";
	}

	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("personas", personasList);
		return "persona/listar";
	}

	// Mostrar la vista del formulario
	@GetMapping("/formulario")
	public String formulario(Model model) {
		model.addAttribute("persona", new Persona());
		return "persona/formulario";
	}

	// Obtener los datos de la petición POST
	@PostMapping("/reporte")
	public ModelAndView reporte(@ModelAttribute("persona") Persona persona) {
		ModelAndView mv = new ModelAndView("persona/reporte");
		mv.addObject("persona", persona);
		return mv;
	}
}
