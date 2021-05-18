package com.educomser.app.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.educomser.app.model.UsuarioModel;
import com.educomser.app.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private static final Log LOG = LogFactory.getLog(UsuarioController.class);
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService usuarioService;
	
	@GetMapping("/")
	public ModelAndView index() {
		LOG.info("Call: index");
		ModelAndView mv=new ModelAndView("usuario/index");
		mv.addObject("usuarios", usuarioService.obtenerTodos());
		return mv;
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		LOG.info("Call: nuevo");
        model.addAttribute("usuario", new UsuarioModel());
        return "usuario/nuevo";
    }
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute("usuario") UsuarioModel usuario, RedirectAttributes redirAttrs){
		LOG.info("Call: guardar --PARAM: "+usuario.toString());
		usuarioService.guardar(usuario);
		redirAttrs.addFlashAttribute("message", "El registro se ha guardado correctamente.");
		return "redirect:/usuario/nuevo";
	}	
}
