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
	private String citaId;
	private String fecha;
	private String nombreProfesional;
	private String objetivo;
	private String descripcion;
	private String tareasAsignadas;
	private String actividadesProximaSesion;

	//------------------------------------------------------------------------------------
	// Getters y Setters
	//------------------------------------------------------------------------------------
	
	public Integer getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(Integer idSesion) {
		this.idSesion = idSesion;
	}
	public String getCitaId() {
		return citaId;
	}
	public void setCitaId(String citaId) {
		this.citaId = citaId;
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
	@Override
	public String toString() {
		return "SesionIndividualTo [idSesion=" + idSesion + ", citaId=" + citaId + ", fecha=" + fecha
				+ ", nombreProfesional=" + nombreProfesional + ", objetivo=" + objetivo + ", descripcion=" + descripcion
				+ ", tareasAsignadas=" + tareasAsignadas + ", actividadesProximaSesion=" + actividadesProximaSesion
				+ "]";
	}

}
