package com.plataforma.cpc.to;

public class PersonaTo {
	
	private 	Integer 		idPersona;
	private 	Integer			codigoEstudiante;
	private 	String 			primerNombre;
	private 	String 			segundoNombre;
	private 	String 			primerApellido;
	private 	String 			segundoApellido;
	private 	String 			numeroDocumento;
	private 	String 			direccion;
	private 	Long 			telefono;
	private 	Long 			otroTelefono;
	private 	String 			correo;
	private 	UsuarioTo 		usuario;
	private 	TipoDocumentoTo tipoDocumento;
	private 	EpsTo 			eps;
	private 	PerfilTo 		perfil;
	private 	Integer 		superior;
	private		String			jornada;
	private		String			password;
	private 	PersonaDetalleTo detalle;
	private		HistoriaClinicaTo historiaClinica;
	
	public Integer getCodigoEstudiante() {
		return codigoEstudiante;
	}
	public void setCodigoEstudiante(Integer codigoEstudiante) {
		this.codigoEstudiante = codigoEstudiante;
	}
	public Long getOtroTelefono() {
		return otroTelefono;
	}
	public void setOtroTelefono(Long otroTelefono) {
		this.otroTelefono = otroTelefono;
	}
	public String getJornada() {
		return jornada;
	}
	public void setJornada(String jornada) {
		this.jornada = jornada;
	}
	public Integer getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getDireccion() {
		return direccion;
	}
	public Long getTelefono() {
		return telefono;
	}
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public UsuarioTo getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioTo usuario) {
		this.usuario = usuario;
	}
	public TipoDocumentoTo getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumentoTo tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public EpsTo getEps() {
		return eps;
	}
	public void setEps(EpsTo eps) {
		this.eps = eps;
	}
	public PerfilTo getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilTo perfil) {
		this.perfil = perfil;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSuperior() {
		return superior;
	}
	public void setSuperior(Integer superior) {
		this.superior = superior;
	}
	public PersonaDetalleTo getDetalle() {
		return detalle;
	}
	public void setDetalle(PersonaDetalleTo detalle) {
		this.detalle = detalle;
	}
	public HistoriaClinicaTo getHistoriaClinica() {
		return historiaClinica;
	}
	public void setHistoriaClinica(HistoriaClinicaTo historiaClinica) {
		this.historiaClinica = historiaClinica;
	}
}
