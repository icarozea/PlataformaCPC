package com.plataforma.cpc.to;

public class ComentariosTo {

	private Integer	idComentarios;
	private String	comentariosObjetivo;
	private String 	comentariosDescripcion;
	private String 	comentariosTareas;
	private String 	comentariosActividades;
	
	public ComentariosTo(){
		
	}

	public Integer getIdComentarios() {
		return idComentarios;
	}

	public void setIdComentarios(Integer idComentarios) {
		this.idComentarios = idComentarios;
	}

	public String getComentariosObjetivo() {
		return comentariosObjetivo;
	}

	public void setComentariosObjetivo(String comentariosObjetivo) {
		this.comentariosObjetivo = comentariosObjetivo;
	}

	public String getComentariosDescripcion() {
		return comentariosDescripcion;
	}

	public void setComentariosDescripcion(String comentariosDescripcion) {
		this.comentariosDescripcion = comentariosDescripcion;
	}

	public String getComentariosTareas() {
		return comentariosTareas;
	}

	public void setComentariosTareas(String comentariosTareas) {
		this.comentariosTareas = comentariosTareas;
	}

	public String getComentariosActividades() {
		return comentariosActividades;
	}

	public void setComentariosActividades(String comentariosActividades) {
		this.comentariosActividades = comentariosActividades;
	}

	@Override
	public String toString() {
		return "ComentariosTo [idComentarios=" + idComentarios + ", comentariosObjetivo=" + comentariosObjetivo
				+ ", comentariosDescripcion=" + comentariosDescripcion + ", comentariosTareas=" + comentariosTareas
				+ ", comentariosActividades=" + comentariosActividades + "]";
	}
}