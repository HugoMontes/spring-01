package com.educomser.app.service;

import java.util.List;

import com.educomser.app.dto.UsuarioDto;

public interface UsuarioService {
	public abstract UsuarioDto guardar(UsuarioDto usuarioDto);
	public abstract UsuarioDto actualizar(UsuarioDto usuarioDto);
	public abstract void eliminar(UsuarioDto usuarioDto);
	public abstract List<UsuarioDto> obtenerTodos();
	public abstract UsuarioDto buscarPorId(int id);
	public abstract List<UsuarioDto> buscarPorNombre(String nombre);
}
