package com.educomser.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	// Inyectar la clase usuarioDetailServiceImpl
	@Autowired
	@Qualifier("usuarioDetailsServiceImpl")
	private UserDetailsService userDetailsService;
	
	public WebSecurity(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	// Implementar los metodos haciendo clic derecho >
	// Source > Override/Implement Methods... y seleccionar
	// - configure(AuthenticationManagerBuilder)
	// - configure(HttpSecurity)

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Se define la clase que recupera los usuarios y el algoritmo para procesar las passwords
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.cors().and()
		.csrf().disable()
		.authorizeRequests()
		// Permitir el acceso a /login sin autenticarse
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		// Cualquier otrapeticion requiere de autenticacion
		.anyRequest().authenticated().and()
			// Las peticiones /login pasan previamente por este filtro
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			// Otras peticiones pasan por este filtro para validar el token
			.addFilter(new JWTAuthorizationFilter(authenticationManager()))
		// Excepciones/validaciones de usuario no autorizado
		.exceptionHandling()
			.authenticationEntryPoint(new BasicAuthenticationEntryPoint())
		// Desactiva login por formulario
		.and().formLogin().disable();
	}	
}
