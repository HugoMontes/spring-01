package com.educomser.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.educomser.app.model.Persona;

@Controller
@RequestMapping("/persona")
public class PersonaController {

	private static List<Persona> personasList;
	private static final Log LOGGER = LogFactory.getLog(PersonaController.class);

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
	// @Valid: Indica que se tiene que validar con las anotaciones en el modelo
	// BindingResult: Encapsula los errores para enviarselos al formulario
	@PostMapping("/reporte")
	public ModelAndView reporte(@Valid @ModelAttribute("persona") Persona persona, BindingResult bindingResult) {
		LOGGER.info("METHOD: reporte | PARAMS: " + persona);
		// Instanciar un ModelAndView
		ModelAndView mv = new ModelAndView("persona/reporte");
		// Verificar que cumplan las condiciones en el modelo
		if (bindingResult.hasErrors()) {
			mv.setViewName("persona/formulario");
		} else {
			mv.setViewName("persona/reporte");
			mv.addObject("persona", persona);
		}
		// Obtener el nombre dinamicamente de la plantilla
		LOGGER.info("TEMPLATE: " + mv.getViewName() + " | DATA: " + persona);
		return mv;
	}

	// Primera forma
	@GetMapping("/redireccion/uno")
	public String redireccionUno() {
		return "redirect:/persona/formulario";
	}

	// Segunda forma
	@GetMapping("/redireccion/dos")
	public RedirectView redireccionDos() {
		return new RedirectView("/persona/listar");
	}

	// Generar error 500
	@GetMapping("/calcular")
	public RedirectView calcular() {
		int i = 6 / 0;
		return new RedirectView("/persona/listar");
	}
}
