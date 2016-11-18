package com.plataforma.cpc.to;

public class PersonaTo {
	private Integer idPersona;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String numeroDocumento;
	private String direccion;
	private Integer telefono;
	private UsuarioTo usuario;
	private TipoDocumentoTo tipoDocumento;
	private EpsTo eps;
	private PerfilTo perfil;
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
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
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
	@Override
	public String toString() {
		return "PersonaTo [idPersona=" + idPersona + ", primerNombre=" + primerNombre + ", segundoNombre="
				+ segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido
				+ ", numeroDocumento=" + numeroDocumento + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", usuario=" + usuario + ", tipoDocumento=" + tipoDocumento + ", eps=" + eps + ", perfil=" + perfil
				+ "]";
	}
	
	
	
	
}
