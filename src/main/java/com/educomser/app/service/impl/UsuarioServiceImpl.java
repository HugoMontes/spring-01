package com.educomser.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.educomser.app.converter.UsuarioConverter;
import com.educomser.app.entities.Usuario;
import com.educomser.app.model.UsuarioModel;
import com.educomser.app.repository.UsuarioRepository;
import com.educomser.app.service.UsuarioService;
import com.educomser.app.util.MD5;

@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	@Autowired
	@Qualifier("usuarioConverter")
	private UsuarioConverter usuarioConverter;

	@Override
	public UsuarioModel guardar(UsuarioModel usuarioModel) {
		// Convertir a una entidad para guardar
		Usuario usuario = usuarioConverter.modelToEntity(usuarioModel);
		// Ingresar la fecha de creacion y actualizacion
		usuario.setCreatedAt(new java.util.Date());
		usuario.setUpdatedAt(new java.util.Date());
		// Cifrar la contraseña
		usuario.setPassword(MD5.getMd5(usuario.getPassword()));
		// Guardar en la base de datos
		usuario = usuarioRepository.save(usuario);
		// Convertir en un modelo para retornar
		return usuarioConverter.entityToModel(usuario);
	}

	@Override
	public UsuarioModel actualizar(UsuarioModel usuarioModel) {
		// Convertir a una entidad para actualizar
		Usuario usuario = usuarioConverter.modelToEntity(usuarioModel);
		// Cambiar la fecha de actualizacion
		usuario.setUpdatedAt(new java.util.Date());
		// Actualizar en la base de datos
		usuario = usuarioRepository.save(usuario);
		// Convertir en un modelo para retornar
		return usuarioConverter.entityToModel(usuario);
	}

	@Override
	public void eliminar(UsuarioModel usuarioModel) {
		// Convertir a una entidad para actualizar
		Usuario usuario = usuarioConverter.modelToEntity(usuarioModel);
		// Eliminar de la base de datos
		usuarioRepository.delete(usuario);
	}

	@Override
	public List<UsuarioModel> obtenerTodos() {
		// Obtener todos los registros de la base de datos
		List<Usuario> usuarios = usuarioRepository.findAll();
		// Convertir listado de entidades a modelos
		List<UsuarioModel> usuariosModel = new ArrayList<UsuarioModel>();
		for (Usuario usuario : usuarios) {
			usuariosModel.add(usuarioConverter.entityToModel(usuario));
		}
		return usuariosModel;
	}

	@Override
	public UsuarioModel buscarPorId(int id) {
		// Obtener usuario de la base de datos
		Usuario usuario = usuarioRepository.findById(id);
		// Retornar un modelo
		return usuarioConverter.entityToModel(usuario);
	}

	@Override
	public List<UsuarioModel> buscarPorNombre(String nombre) {
		// Buscar registros por nombre
		List<Usuario> usuarios = usuarioRepository.findByNombre(nombre);
		// Convertir listado de entidades a modelos
		List<UsuarioModel> usuariosModel = new ArrayList<UsuarioModel>();
		for (Usuario usuario : usuarios) {
			usuariosModel.add(usuarioConverter.entityToModel(usuario));
		}
		return usuariosModel;
	}
}
