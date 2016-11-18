package com.plataforma.cpc.to;

public class TipoDocumentoTo {
	private Integer idTipoDocumento;
	private String sigla;
	private String nombreDocumento;
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	@Override
	public String toString() {
		return "TipoDocumentoTo [idTipoDocumento=" + idTipoDocumento + ", sigla=" + sigla + ", nombreDocumento="
				+ nombreDocumento + "]";
	}
	
	

}
