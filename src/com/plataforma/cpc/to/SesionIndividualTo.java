package com.plataforma.cpc.to;

import java.util.Date;

/**
 * Representa el modelo relacional para el reporte de una cita previamente creada
 */
public class SesionIndividualTo {

	//------------------------------------------------------------------------------------
	// Atributos
	//------------------------------------------------------------------------------------
	
	private Integer idSesion;
	private String fecha;
	private String nombreProfesional;
	private String nombreAsesorProfesional;
	private String objetivo;
	private String descripcion;
	private String tareasAsignadas;
	private String actividadesProximaSesion;
	private boolean fallo;
	private Integer numRecibo;
	private Integer idCita;
	private ComentariosTo comentarios;

	//------------------------------------------------------------------------------------
	// Getters y Setters
	//------------------------------------------------------------------------------------
	
	public Integer getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(Integer idSesion) {
		this.idSesion = idSesion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getNombreProfesional() {
		return nombreProfesional;
	}
	public void setNombreProfesional(String nombreProfesional) {
		this.nombreProfesional = nombreProfesional;
	}
	public String getNombreAsesorProfesional() {
		return nombreAsesorProfesional;
	}
	public void setNombreAsesorProfesional(String nombreAsesorProfesional) {
		this.nombreAsesorProfesional = nombreAsesorProfesional;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTareasAsignadas() {
		return tareasAsignadas;
	}
	public void setTareasAsignadas(String tareasAsignadas) {
		this.tareasAsignadas = tareasAsignadas;
	}
	public String getActividadesProximaSesion() {
		return actividadesProximaSesion;
	}
	public void setActividadesProximaSesion(String actividadesProximaSesion) {
		this.actividadesProximaSesion = actividadesProximaSesion;
	}
	public boolean isFallo() {
		return fallo;
	}
	public void setFallo(boolean fallo) {
		this.fallo = fallo;
	}
	public Integer getNumRecibo() {
		return numRecibo;
	}
	public void setNumRecibo(Integer numRecibo) {
		this.numRecibo = numRecibo;
	}
	public Integer getIdCita() {
		return idCita;
	}
	public void setIdCita(Integer idCita) {
		this.idCita = idCita;
	}
	public ComentariosTo getComentarios() {
		return comentarios;
	}
	public void setComentarios(ComentariosTo comentarios) {
		this.comentarios = comentarios;
	}
	@Override
	public String toString() {
		return "SesionIndividualTo [idSesion=" + idSesion + ", fecha=" + fecha + ", nombreProfesional="
				+ nombreProfesional + ", objetivo=" + objetivo + ", descripcion=" + descripcion + ", tareasAsignadas="
				+ tareasAsignadas + ", actividadesProximaSesion=" + actividadesProximaSesion + ", fallo=" + fallo
				+ ", numRecibo=" + numRecibo + ", idCita=" + idCita + ", comentarios=" + comentarios + "]";
	}
}