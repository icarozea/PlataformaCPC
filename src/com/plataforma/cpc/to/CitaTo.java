package com.plataforma.cpc.to;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Representa el modelo relacional de una cita 
 */
public class CitaTo {
	
	//-------------------------------------------------------------------------------------
	// Atributos
	//-------------------------------------------------------------------------------------
	
	private Integer 			idCita;
	private String 				salon;
	private LocalDateTime 		fechaSolicitud;
	private LocalDateTime 		fechaCita;
	private PersonaTo 			practicante;
	private PersonaTo 			paciente;
	private String 				estado;
	private TratamientoTo 		tratamiento;
	private SesionIndividualTo 	reporte;
	private boolean				valoracion;
	private Integer				numCita;
	
	//-------------------------------------------------------------------------------------
	// Getters y Setters
	//-------------------------------------------------------------------------------------
	
	public  Integer getIdCita() {
		return idCita;
	}
	
	public void setIdCita(Integer idCita) {
		this.idCita = idCita;
	}
	
	public String getSalon() {
		return salon;
	}
	
	public void setSalon(String salon) {
		this.salon = salon;
	}
	
	public LocalDateTime getFechaSolicitud() {
		return fechaSolicitud;
	}
	
	public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	public LocalDateTime getFechaCita() {
		return fechaCita;
	}
	
	public void setFechaCita(LocalDateTime fechaCita) {
		this.fechaCita = fechaCita;
	}
	
	public PersonaTo getPracticante() {
		return practicante;
	}
	
	public void setPracticante(PersonaTo practicante) {
		this.practicante = practicante;
	}
	
	public PersonaTo getPaciente() {
		return paciente;
	}
	
	public void setPaciente(PersonaTo paciente) {
		this.paciente = paciente;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public TratamientoTo getTratamiento() {
		return tratamiento;
	}
	
	public void setTratamiento(TratamientoTo tratamiento) {
		this.tratamiento = tratamiento;
	}
	
	public SesionIndividualTo getReporte() {
		return reporte;
	}
	
	public void setReporte(SesionIndividualTo reporte) {
		this.reporte = reporte;
	}

	public boolean isValoracion() {
		return valoracion;
	}

	public void setValoracion(boolean esValoracion) {
		this.valoracion = esValoracion;
	}

	public Integer getNumCita() {
		return numCita;
	}

	public void setNumCita(Integer numCita) {
		this.numCita = numCita;
	}	
}