package com.educomser.app.service.impl;

import org.springframework.stereotype.Service;
import com.educomser.app.service.SaludoService;

@Service("saludoService")
public class SaludoServiceImpl implements SaludoService {

	@Override
	public String saludar(String nombre) {
		String mensaje = "Buenas noches "+nombre;
		return mensaje;
	}

}
