package com.plataforma.cpc.to;

public class reporteValoracionTo {

	private Integer idValoracion;
	private Integer idCita;
	private String motivo;
	private String personaReporta;
	private String comportamiento;
	private String hipotesis;
	private String servicioRemitido;
	
	public Integer getIdValoracion() {
		return idValoracion;
	}
	public void setIdValoracion(Integer idValoracion) {
		this.idValoracion = idValoracion;
	}
	public Integer getIdCita() {
		return idCita;
	}
	public void setIdCita(Integer idCita) {
		this.idCita = idCita;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getPersonaReporta() {
		return personaReporta;
	}
	public void setPersonaReporta(String personaReporta) {
		this.personaReporta = personaReporta;
	}
	public String getComportamiento() {
		return comportamiento;
	}
	public void setComportamiento(String comportamiento) {
		this.comportamiento = comportamiento;
	}
	public String getHipotesis() {
		return hipotesis;
	}
	public void setHipotesis(String hipotesis) {
		this.hipotesis = hipotesis;
	}
	public String getServicioRemitido() {
		return servicioRemitido;
	}
	public void setServicioRemitido(String servicioRemitido) {
		this.servicioRemitido = servicioRemitido;
	}
}