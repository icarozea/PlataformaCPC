package com.plataforma.cpc.to;

import java.time.LocalDateTime;

/**
 * Represente el modelo relacional de un Tratamiento
 */
public class TratamientoTo {

	//---------------------------------------------------------------------------------------------------------
	// Atributos
	//---------------------------------------------------------------------------------------------------------
	
	private Integer 		idTratamiento;
	private PersonaTo 		paciente;
	private String			estado;
	private LocalDateTime 	fechaInicio;
	private LocalDateTime 	fechaCierre;
	private String			tipo;
	
	//---------------------------------------------------------------------------------------------------------
	// Getters y Setters
	//---------------------------------------------------------------------------------------------------------
	
	public Integer getIdTratamiento() {
		return idTratamiento;
	}
	
	public void setIdTratamiento(Integer idTratamiento) {
		this.idTratamiento = idTratamiento;
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
	
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public LocalDateTime getFechaCierre() {
		return fechaCierre;
	}
	
	public void setFechaCierre(LocalDateTime fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}