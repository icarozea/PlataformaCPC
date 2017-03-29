package com.plataforma.cpc.to;

public class SesionIndividualPreviewTo {

	private Integer idCita;
	private String salon;
	private String fecha;
	private String primerNombrePaciente;
	private String segundoNombrePaciente;
	private String primerApellidoPaciente;
	private String segundoApellidoPaciente;
	private String idReporte;
	private String estado;
	
	public SesionIndividualPreviewTo(){
		
	}

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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getPrimerNombrePaciente() {
		return primerNombrePaciente;
	}

	public void setPrimerNombrePaciente(String primerNombrePaciente) {
		this.primerNombrePaciente = primerNombrePaciente;
	}

	public String getSegundoNombrePaciente() {
		return segundoNombrePaciente;
	}

	public void setSegundoNombrePaciente(String segundoNombrePaciente) {
		this.segundoNombrePaciente = segundoNombrePaciente;
	}

	public String getPrimerApellidoPaciente() {
		return primerApellidoPaciente;
	}

	public void setPrimerApellidoPaciente(String primerApellidoPaciente) {
		this.primerApellidoPaciente = primerApellidoPaciente;
	}

	public String getSegundoApellidoPaciente() {
		return segundoApellidoPaciente;
	}

	public void setSegundoApellidoPaciente(String segundoApellidoPaciente) {
		this.segundoApellidoPaciente = segundoApellidoPaciente;
	}

	public String getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(String idReporte) {
		this.idReporte = idReporte;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "SesionIndividualPreviewTo [idCita=" + idCita + ", salon=" + salon + ", fecha=" + fecha
				+ ", primerNombrePaciente=" + primerNombrePaciente + ", segundoNombrePaciente=" + segundoNombrePaciente
				+ ", primerApellidoPaciente=" + primerApellidoPaciente + ", segundoApellidoPaciente="
				+ segundoApellidoPaciente + ", idReporte=" + idReporte + ", estado=" + estado + "]";
	}

	
}
