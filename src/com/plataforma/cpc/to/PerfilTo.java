package com.plataforma.cpc.to;

public class PerfilTo {
	private Integer idPerfil;
	private String nombrePerfil;
	private String permiso;
	public Integer getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getNombrePerfil() {
		return nombrePerfil;
	}
	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}
	public String getPermiso() {
		return permiso;
	}
	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}
	@Override
	public String toString() {
		return "PerfilTo [idPerfil=" + idPerfil + ", nombrePerfil=" + nombrePerfil + ", permiso=" + permiso + "]";
	}
	
	

}
