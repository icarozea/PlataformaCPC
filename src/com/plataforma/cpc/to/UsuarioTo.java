package com.plataforma.cpc.to;

public class UsuarioTo {
	private Integer idUsuario;
	private String nombreUsuario;
	private String contrasena;
	private String correo;
	
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	@Override
	public String toString() {
		return "UsuarioTo [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", contrasena=" + contrasena
				+ ", correo=" + correo + "]";
	}

}
