package com.educomser.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.educomser.app.entities.Usuario;
import com.educomser.app.repository.UsuarioRepository;

@Service("usuarioDetailsServiceImpl")
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	// Metodo abstracto que carga un usuario a partir del nombre de usuario
	// verifica si el usuario existe en la base de datos
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		// Obtener el estado activo o inactivo del usuario
		boolean isActive = usuario.getStatus()==1?true:false;
		// Obtener el rol del usuario, por ejemplo
		byte rol = 1;
		if(usuario != null) {
			return new User(usuario.getUsername(), usuario.getPassword(), 
							isActive, isActive, isActive, isActive,
							buildGranted(rol));
		}
		throw new UsernameNotFoundException(username);
	}

	// Metodo que verifica a que roles pertenece un usuario
	public List<GrantedAuthority> buildGranted(byte rol) {
		String[] roles = { "LECTOR", "USUARIO", "ADMINISTRADOR" };
		List<GrantedAuthority> auths = new ArrayList<>();
		for (int i = 0; i < rol; i++) {
			auths.add(new SimpleGrantedAuthority(roles[i]));
		}
		return auths;
	}

}
