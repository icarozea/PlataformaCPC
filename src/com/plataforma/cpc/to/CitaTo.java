package com.plataforma.cpc.to;

import java.util.Date;

public class CitaTo {
	private Integer idCita;
	private String salon;
	private Date fechaSolicitud;
	private Date fechaCita;
	private PersonaTo practicante;
	private PersonaTo paciente;
	public Integer getIdCita() {
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
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public Date getFechaCita() {
		return fechaCita;
	}
	public void setFechaCita(Date fechaCita) {
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
	@Override
	public String toString() {
		return "CitaTo [idCita=" + idCita + ", salon=" + salon + ", fechaSolicitud=" + fechaSolicitud + ", fechaCita="
				+ fechaCita + ", practicante=" + practicante + ", paciente=" + paciente + "]";
	}
	
}