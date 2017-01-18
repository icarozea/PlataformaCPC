package com.plataforma.cpc.to;

/**
 * Representa el modelo relacional para el reporte de una cita previamente creada
 */
public class ReporteTo {

	//------------------------------------------------------------------------------------
	// Atributos
	//------------------------------------------------------------------------------------
	
	private Integer idReporte;
	private Integer numeroSesion;
	private String 	objetivo;
	private String 	descripcion;
	private String 	tareasAsignadas;
	private String 	actividadesProximaSesion;
	
	//------------------------------------------------------------------------------------
	// Getters y Setters
	//------------------------------------------------------------------------------------
	
	public Integer getIdReporte() {
		return idReporte;
	}
	
	public void setIdReporte(Integer idReporte) {
		this.idReporte = idReporte;
	}
	
	public Integer getNumeroSesion() {
		return numeroSesion;
	}
	
	public void setNumeroSesion(Integer numeroSesion) {
		this.numeroSesion = numeroSesion;
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
}
