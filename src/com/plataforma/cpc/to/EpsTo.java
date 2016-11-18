package com.plataforma.cpc.to;

public class EpsTo {
	private Integer idEPS;
	private String nombreEPS;
	
	public Integer getIdEPS() {
		return idEPS;
	}
	public void setIdEPS(Integer idEPS) {
		this.idEPS = idEPS;
	}
	public String getNombreEPS() {
		return nombreEPS;
	}
	public void setNombreEPS(String nombreEPS) {
		this.nombreEPS = nombreEPS;
	}
	@Override
	public String toString() {
		return "EpsTo [idEPS=" + idEPS + ", nombreEPS=" + nombreEPS + "]";
	}
	
	

}
