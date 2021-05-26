package com.educomser.app.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<Object> guardar(@RequestBody @Valid UsuarioDto usuario) {
		LOG.info("Call: guardar");
		// Una vez que se guarda retorna el registro guardado
		usuario = usuarioService.guardar(usuario);
		// Preparar una respuesta
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", "El usuario se ha guardado correctamente.");
		body.put("usuario", usuario);
		// Retornar la respuesta
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Object> actualizar(@RequestBody @Valid UsuarioDto usuario) {
		LOG.info("Call: actualizar");
		usuario = usuarioService.actualizar(usuario);
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("message", "El usuario se ha editado correctamente.");
		body.put("usuario", usuario);
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object> eliminarUsuario(@PathVariable("id") int id) {
		LOG.info("Call: eliminar");
		try {
			UsuarioDto usuario = usuarioService.buscarPorId(id);
			usuarioService.eliminar(usuario);
			Map<String, Object> body = new LinkedHashMap<>();
			body.put("message", "El usuario se ha eliminado correctamente.");
			body.put("usuario", usuario);
			return new ResponseEntity<>(body, HttpStatus.OK);
		} catch(NullPointerException ex) {
			Map<String, Object> body = new LinkedHashMap<>();
			body.put("message", "No existe el usuario con id "+id);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}		
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Object> buscar(@PathVariable("id") int id) {
		LOG.info("Call: buscar");
		try {
			UsuarioDto usuario = usuarioService.buscarPorId(id);
			Map<String, Object> body = new LinkedHashMap<>();
			body.put("usuario", usuario);
			return new ResponseEntity<>(body, HttpStatus.OK);
		} catch(NullPointerException ex) {
			Map<String, Object> body = new LinkedHashMap<>();
			body.put("message", "No existe el usuario con id "+id);
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		}
	}
	
//	@GetMapping("/listar")
//	public List<UsuarioDto> listar(){
//		return usuarioService.obtenerTodos();
//	}	
	
	@GetMapping("/listar")
	public List<UsuarioDto> listar(Pageable pageable){
		return usuarioService.obtenerTodosPaginados(pageable);
	}	
}
