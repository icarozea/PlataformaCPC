package com.plataforma.cpc.to;

import java.time.LocalDateTime;
import java.util.Date;

public class CitaTo {
	private Integer idCita;
	private String salon;
	private LocalDateTime fechaSolicitud;
	private LocalDateTime fechaCita;
	private PersonaTo practicante;
	private PersonaTo paciente;
	private String estado;
	
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
	@Override
	public String toString() {
		return "CitaTo [idCita=" + idCita + ", salon=" + salon + ", fechaSolicitud=" + fechaSolicitud + ", fechaCita="
				+ fechaCita + ", practicante=" + practicante + ", paciente=" + paciente + ", estado=" + estado + "]";
	}
	
	
	
}