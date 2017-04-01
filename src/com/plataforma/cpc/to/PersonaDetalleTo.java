package com.plataforma.cpc.to;

public class PersonaDetalleTo {
	
	private Integer personaId;
	private String sexo;
	private String edad;
	private String acudiente;
	private String proceso;
	private String perteneceU;
	private String facultad;
	private String semestre;
	private String problematica;
	private String observación;
	private String nombreModifica;
	
	public Integer getPersonaId() {
		return personaId;
	}
	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getAcudiente() {
		return acudiente;
	}
	public void setAcudiente(String acudiente) {
		this.acudiente = acudiente;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	public String getPerteneceU() {
		return perteneceU;
	}
	public void setPerteneceU(String perteneceU) {
		this.perteneceU = perteneceU;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getProblematica() {
		return problematica;
	}
	public void setProblematica(String problematica) {
		this.problematica = problematica;
	}
	public String getObservación() {
		return observación;
	}
	public void setObservación(String observación) {
		this.observación = observación;
	}
	public String getNombreModifica() {
		return nombreModifica;
	}
	public void setNombreModifica(String nombreModifica) {
		this.nombreModifica = nombreModifica;
	}
	
	@Override
	public String toString() {
		return "PersonaDetalleTo [personaId=" + personaId + ", sexo=" + sexo + ", edad=" + edad + ", acudiente="
				+ acudiente + ", proceso=" + proceso + ", perteneceU=" + perteneceU + ", facultad=" + facultad
				+ ", semestre=" + semestre + ", problematica=" + problematica + ", observación=" + observación
				+ ", nombreModifica=" + nombreModifica + "]";
	}
	
	
}
