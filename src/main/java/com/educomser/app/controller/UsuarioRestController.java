package com.educomser.app.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educomser.app.dto.UsuarioDto;
import com.educomser.app.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

	private static final Log LOG = LogFactory.getLog(UsuarioRestController.class);

	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioService usuarioService;

	@PostMapping("/guardar")
	public ResponseEntity<UsuarioDto> guardar(@RequestBody @Valid UsuarioDto usuario) {
		LOG.info("Call: guardar");
		// Una vez que se guarda retorna el registro guardado
		usuario = usuarioService.guardar(usuario);
		return new ResponseEntity<UsuarioDto>(usuario, HttpStatus.OK);
	}
	
}
