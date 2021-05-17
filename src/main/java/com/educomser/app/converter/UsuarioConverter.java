package com.educomser.app.converter;

import org.springframework.stereotype.Component;

import com.educomser.app.entities.Usuario;
import com.educomser.app.model.UsuarioModel;

@Component("usuarioConverter")
public class UsuarioConverter {

	// Transforma de un Entity a un model
	public UsuarioModel entityToModel(Usuario usuario) {
		UsuarioModel usuarioModel = new UsuarioModel();
		usuarioModel.setId(usuario.getId());
		usuarioModel.setNombre(usuario.getNombre());
		usuarioModel.setApellidoPaterno(usuario.getApellidoPaterno());
		usuarioModel.setApellidoMaterno(usuario.getApellidoMaterno());
		usuarioModel.setFechaNacimiento(usuario.getFechaNacimiento());
		usuarioModel.setUsername(usuario.getUsername());
		usuarioModel.setPassword(usuario.getPassword());
		usuarioModel.setEmail(usuario.getEmail());
		usuarioModel.setStatus(usuario.getStatus());
		return usuarioModel;
	}

	// Transforma de un Model a un Entity
	public Usuario modelToEntity(UsuarioModel usuarioModel) {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioModel.getId());
		usuario.setNombre(usuarioModel.getNombre());
		usuario.setApellidoPaterno(usuarioModel.getApellidoPaterno());
		usuario.setApellidoMaterno(usuarioModel.getApellidoMaterno());
		usuario.setFechaNacimiento(usuarioModel.getFechaNacimiento());
		usuario.setUsername(usuarioModel.getUsername());
		usuario.setPassword(usuarioModel.getPassword());
		usuario.setEmail(usuarioModel.getEmail());
		usuario.setStatus(usuarioModel.getStatus());
		return usuario;
	}
}
