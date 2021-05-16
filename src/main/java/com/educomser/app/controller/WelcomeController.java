package com.educomser.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

	@GetMapping("/inicio")
	public String index() {
		return "welcome/index";
	}

	@RequestMapping(value = "/plantilla/uno", method = RequestMethod.GET)
	public String ejemploString(Model model) {
		// Retorna simplemente el nombre de la vista, util cuando se
		// envian pocos o ningún dato a la vista.
		model.addAttribute("nombre", "Juan");
		return "welcome/saludo";
	}

	@RequestMapping(value = "/plantilla/dos", method = RequestMethod.GET)
	public ModelAndView ejemploMv() {
		// Retorna el nombre de la vista.Util cuando se envias varios datos a
		// la vista
		ModelAndView mv = new ModelAndView("welcome/saludo");
		mv.addObject("nombre", "Lucas");
		return mv;
	}

	// Primera forma de enviar parametros
	// http://localhost:8080/welcome/parametros/uno?nombre=Pedro
	@GetMapping("/parametros/uno")
	public ModelAndView parametrosUno(@RequestParam(name = "nombre") String nombre) {
		ModelAndView mv = new ModelAndView("welcome/saludo");
		mv.addObject("nombre", nombre);
		return mv;
	}

	// Segunda forma de enviar parametros
	// http://localhost:8080/welcome/parametros/dos/Maria
	@GetMapping("/parametros/dos/{nombre}")
	public ModelAndView parametrosDos(@PathVariable("nombre") String nombre) {
		ModelAndView mv = new ModelAndView("welcome/saludo");
		mv.addObject("nombre", nombre);
		return mv;
	}

}
