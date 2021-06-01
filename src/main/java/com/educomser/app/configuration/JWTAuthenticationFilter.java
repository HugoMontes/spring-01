package com.educomser.app.configuration;

import static com.educomser.app.configuration.Constants.HEADER_AUTHORIZACION_KEY;
import static com.educomser.app.configuration.Constants.SUPER_SECRET_KEY;
import static com.educomser.app.configuration.Constants.TOKEN_BEARER_PREFIX;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.educomser.app.entities.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	// Implementar los metodos haciendo clic derecho >
	// Source > Override/Implement Methods... y seleccionar
	// - attemptAuthentication(HttpServletRequest, HttpServletResponse)
	// - successfulAuthentication(HttpServletRequest, HttpServletResponse, FilterChain, Authentication)

	// El metodo attemptAuthentication se ejecuta cuando el usuario 
	// se esta autenticando
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			// Obtener los datos de la peticion (username y password) en un objeto
			InputStream body = request.getInputStream();
			Usuario usuario = new ObjectMapper().readValue(body, Usuario.class);
			// Retornar un usuario con un token
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword(), new ArrayList<>()));
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException();
		}		
	}

	// El metodo successfulAuthentication se ejecuta cuando el usuario es valido
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// Si la autenticacion es exitosa, agregar el token a la respuesta
		// Metodo que crea el jwt y lo envia al cliente en el header de la respuesta
		String token = Jwts.builder().setIssuedAt(new Date())
				.setSubject(authResult.getName())
				// Hash con el que se firma la clave
				.signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();
		// Agregar al encabezado el token
		response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);		
	}
}
