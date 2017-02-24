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
	private String objetivo;
	private String descripcion;
	private String tareasAsignadas;
	private String actividadesProximaSesion;
	private boolean fallo;
	private Integer numRecibo;

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
}