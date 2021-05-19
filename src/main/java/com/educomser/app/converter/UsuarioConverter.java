package com.educomser.app.converter;

import org.springframework.stereotype.Component;

import com.educomser.app.entities.Usuario;
import com.educomser.app.dto.UsuarioDto;

@Component("usuarioConverter")
public class UsuarioConverter {

	// Transforma de un Entity a un dto
	public UsuarioDto entityToDto(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(usuario.getId());
		usuarioDto.setNombre(usuario.getNombre());
		usuarioDto.setApellidoPaterno(usuario.getApellidoPaterno());
		usuarioDto.setApellidoMaterno(usuario.getApellidoMaterno());
		usuarioDto.setFechaNacimiento(usuario.getFechaNacimiento());
		usuarioDto.setUsername(usuario.getUsername());
		usuarioDto.setPassword(usuario.getPassword());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setStatus(usuario.getStatus());
		return usuarioDto;
	}

	// Transforma de un dto a un Entity
	public Usuario dtoToEntity(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDto.getId());
		usuario.setNombre(usuarioDto.getNombre());
		usuario.setApellidoPaterno(usuarioDto.getApellidoPaterno());
		usuario.setApellidoMaterno(usuarioDto.getApellidoMaterno());
		usuario.setFechaNacimiento(usuarioDto.getFechaNacimiento());
		usuario.setUsername(usuarioDto.getUsername());
		usuario.setPassword(usuarioDto.getPassword());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setStatus(usuarioDto.getStatus());
		return usuario;
	}
}
