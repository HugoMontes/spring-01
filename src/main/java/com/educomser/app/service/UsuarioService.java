package com.educomser.app.service;

import java.util.List;

import com.educomser.app.model.UsuarioModel;

public interface UsuarioService {
	public abstract UsuarioModel guardar(UsuarioModel usuarioModel);
	public abstract UsuarioModel actualizar(UsuarioModel usuarioModel);
	public abstract void eliminar(UsuarioModel usuarioModel);
	public abstract List<UsuarioModel> obtenerTodos();
	public abstract UsuarioModel buscarPorId(int id);
	public abstract List<UsuarioModel> buscarPorNombre(String nombre);
}
