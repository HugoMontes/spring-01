package com.educomser.app.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.educomser.app.entities.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Serializable>, PagingAndSortingRepository<Usuario, Serializable> {
	// Implementar consultas automaticas
	// Buscar por id
	public abstract Usuario findById(int id);
	
	// Buscar por nombre
	public abstract List<Usuario> findByNombre(String nombre);
	
	// Devuelve datos paginados
	public abstract Page<Usuario> findAll(Pageable pageable);
	
	// Buscar por nombre de usuario
	public abstract Usuario findByUsername(String username);
}
