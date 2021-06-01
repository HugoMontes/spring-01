package com.educomser.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.educomser.app.converter.UsuarioConverter;
import com.educomser.app.dto.UsuarioDto;
import com.educomser.app.entities.Usuario;
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
	public UsuarioDto guardar(UsuarioDto usuarioDto) {
		// Convertir a una entidad para guardar
		Usuario usuario = usuarioConverter.dtoToEntity(usuarioDto);
		// Ingresar la fecha de creacion y actualizacion
		usuario.setCreatedAt(new java.util.Date());
		usuario.setUpdatedAt(new java.util.Date());
		// Cifrar la contraseña
		usuario.setPassword(MD5.getMd5(usuario.getPassword()));
		// Guardar en la base de datos
		usuario = usuarioRepository.save(usuario);
		// Convertir en un modelo para retornar
		return usuarioConverter.entityToDto(usuario);
	}

	@Override
	public UsuarioDto actualizar(UsuarioDto usuarioDto) {
		// Convertir a una entidad para actualizar
		Usuario usuario = usuarioConverter.dtoToEntity(usuarioDto);
		// Regla de negocio: No permitir editar la fecha de creacion
		// Buscar usuario antes de editar y setear fecha creacion
		Usuario usuarioFind = usuarioRepository.findById(usuario.getId());
		usuario.setCreatedAt(usuarioFind.getCreatedAt());
		// Cifrar el password editado
		usuario.setPassword(MD5.getMd5(usuario.getPassword()));
		// Cambiar la fecha de actualizacion
		usuario.setUpdatedAt(new java.util.Date());
		// Actualizar en la base de datos
		usuario = usuarioRepository.save(usuario);
		// Convertir en un modelo para retornar
		return usuarioConverter.entityToDto(usuario);
	}

	@Override
	public void eliminar(UsuarioDto usuarioDto) {
		// Convertir a una entidad para actualizar
		Usuario usuario = usuarioConverter.dtoToEntity(usuarioDto);
		// Eliminar de la base de datos
		usuarioRepository.delete(usuario);
	}

	@Override
	public List<UsuarioDto> obtenerTodos() {
		// Obtener todos los registros de la base de datos
		List<Usuario> usuarios = usuarioRepository.findAll();
		// Convertir listado de entidades a modelos
		List<UsuarioDto> usuariosModel = new ArrayList<UsuarioDto>();
		for (Usuario usuario : usuarios) {
			usuariosModel.add(usuarioConverter.entityToDto(usuario));
		}
		return usuariosModel;
	}

	@Override
	public UsuarioDto buscarPorId(int id) {
		// Obtener usuario de la base de datos
		Usuario usuario = usuarioRepository.findById(id);
		// Retornar un modelo
		return usuarioConverter.entityToDto(usuario);
	}

	@Override
	public List<UsuarioDto> buscarPorNombre(String nombre) {
		// Buscar registros por nombre
		List<Usuario> usuarios = usuarioRepository.findByNombre(nombre);
		// Convertir listado de entidades a modelos
		List<UsuarioDto> usuariosModel = new ArrayList<UsuarioDto>();
		for (Usuario usuario : usuarios) {
			usuariosModel.add(usuarioConverter.entityToDto(usuario));
		}
		return usuariosModel;
	}

	@Override
	public List<UsuarioDto> obtenerTodosPaginados(Pageable pageable) {
		// Buscar registros paginados
		List<Usuario> usuarios = usuarioRepository.findAll(pageable).getContent();
		// Convertir listado de entidades a modelos
		List<UsuarioDto> usuariosModel = new ArrayList<UsuarioDto>();
		for (Usuario usuario : usuarios) {
			usuariosModel.add(usuarioConverter.entityToDto(usuario));
		}
		return usuariosModel;
	}
}
