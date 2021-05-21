package com.educomser.app.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioDto {

	private int id;
	@NotEmpty(message = "nombre es requerido")
	@Size(min=2, max=30, message = "Caracteres entre 2 y 30")
	private String nombre;
	@NotEmpty(message="apellidoPaterno es requerido")
	private String apellidoPaterno;
	private String apellidoMaterno;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date fechaNacimiento;
	@NotEmpty(message = "username es requerido")
	private String username;
	@NotEmpty(message = "password es requerido")
	private String password;
	@NotEmpty(message = "email es requerido")
	@Email(message = "El email no es valido")
	private String email;
	private short status = 1;
	
	public UsuarioDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", username="
				+ username + ", password=" + password + ", email=" + email + ", status=" + status + "]";
	}
}
