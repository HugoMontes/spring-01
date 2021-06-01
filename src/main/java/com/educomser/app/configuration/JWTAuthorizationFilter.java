package com.educomser.app.configuration;

import static com.educomser.app.configuration.Constants.HEADER_AUTHORIZACION_KEY;
import static com.educomser.app.configuration.Constants.SUPER_SECRET_KEY;
import static com.educomser.app.configuration.Constants.TOKEN_BEARER_PREFIX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	// Adicionar el constructor
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	// Implementar los metodos haciendo clic derecho >
	// Source > Override/Implement Methods... y seleccionar
	// - doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)

	// Metodo para validar el token enviado por el cliente
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Obtener el token que viene en el encabezado de la peticion
		String header = request.getHeader(HEADER_AUTHORIZACION_KEY);
		// Si existe un token presente lo validamos
		if (header != null) {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}

	// Metodo que procesa el token y recupera el usuario.
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_AUTHORIZACION_KEY);
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey(SUPER_SECRET_KEY)
					// El método parseClaimsJws es el que valida
					.parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, ""))
					.getBody()
					.getSubject();
			if (user != null) {
				// Recordemos que para las demas peticiones que no son /login
				// no se requiere una autenticacion por username y password
				// por este motivo podemos devolver un 
				// UsernamePasswordAuthenticationToken sin password
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}
}
