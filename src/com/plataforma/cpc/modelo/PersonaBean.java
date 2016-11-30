package com.plataforma.cpc.modelo;

import java.util.ArrayList;

import com.plataforma.cpc.dao.DaoPersona;
import com.plataforma.cpc.to.EpsTo;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.TipoDocumentoTo;
import com.plataforma.cpc.to.UsuarioTo;

public class PersonaBean {
	
	public boolean ingresarPersona(String nombre1,String nombre2, String apellido1, String apellido2, Integer tipoDocumento,String numeroDocumento, String direccion, Long telefono, String correo, Integer idPerfil, String password, Integer eps){
		PersonaTo persona = new PersonaTo();
		
		persona.setPrimerNombre(nombre1);
		persona.setSegundoNombre(nombre2);
		persona.setPrimerApellido(apellido1);
		persona.setSegundoApellido(apellido2);
		
		TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
		tipoDocumentoTo.setIdTipoDocumento(tipoDocumento);
		persona.setTipoDocumento(tipoDocumentoTo);
		
		persona.setNumeroDocumento(numeroDocumento);
		persona.setDireccion(direccion);
		persona.setTelefono(telefono);
		persona.setCorreo(correo);
		
		PerfilTo perfilTo = new PerfilTo();
		perfilTo.setIdPerfil(idPerfil);
		persona.setPerfil(perfilTo);
		persona.setPassword(password);
		
		EpsTo epsTo = new EpsTo();
		epsTo.setIdEPS(eps);
		persona.setEps(epsTo);

		DaoPersona daoPersona = new DaoPersona();

		return daoPersona.crearPersona(persona);
	}
	
	public ArrayList<PersonaTo> consultarPracticantes(){
		
		PersonaTo persona = new PersonaTo();
		PerfilTo perfil = new PerfilTo();
		perfil.setIdPerfil(3);
		persona.setPerfil(perfil);
		
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.consultarPersonasPerfil(persona);
	}
	
	public ArrayList<PersonaTo> consultarPacientes(){
		
		PersonaTo persona = new PersonaTo();
		PerfilTo perfil = new PerfilTo();
		perfil.setIdPerfil(4);
		persona.setPerfil(perfil);
		
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.consultarPersonasPerfil(persona);
	}
	
	public ArrayList<PersonaTo> consultarSupervisores(){
		
		PersonaTo persona = new PersonaTo();
		PerfilTo perfil = new PerfilTo();
		perfil.setIdPerfil(2);
		persona.setPerfil(perfil);
		
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.consultarPersonasPerfil(persona);
	}
	
	public ArrayList<PersonaTo> consultarAdministradores(){
		
		PersonaTo persona = new PersonaTo();
		PerfilTo perfil = new PerfilTo();
		perfil.setIdPerfil(1);
		persona.setPerfil(perfil);
		
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.consultarPersonasPerfil(persona);
	}
	
	public ArrayList<PersonaTo> consultarPersonasFiltro(PersonaTo persona){	
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.consultarPersonasPerfil(persona);
	}
	
	public ArrayList<PersonaTo> consultarAsignados(Integer idSuperior){
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.consultarAsignados(idSuperior);
	}
	
	public PersonaTo consultarPersona(PersonaTo persona){
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.consultarPersona(persona);
	}
	
	public boolean modificarPersona(Integer id, String nombre1,String nombre2, String apellido1, String apellido2, Integer tipoDocumento,String numeroDocumento, String direccion, Long telefono, String correo, Integer idPerfil, String password, Integer eps, Integer idSuperior ){
		PersonaTo persona = new PersonaTo();
		
		persona.setIdPersona(id);
		persona.setPrimerNombre(nombre1);
		persona.setSegundoNombre(nombre2);
		persona.setPrimerApellido(apellido1);
		persona.setSegundoApellido(apellido2);
		
		TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
		tipoDocumentoTo.setIdTipoDocumento(tipoDocumento);
		persona.setTipoDocumento(tipoDocumentoTo);
		
		persona.setNumeroDocumento(numeroDocumento);
		persona.setDireccion(direccion);
		persona.setTelefono(telefono);
		persona.setCorreo(correo);
		
		PerfilTo perfilTo = new PerfilTo();
		perfilTo.setIdPerfil(idPerfil);
		persona.setPerfil(perfilTo);
		persona.setPassword(password);
		EpsTo epsTo = new EpsTo();
		epsTo.setIdEPS(eps);
		persona.setEps(epsTo);
		persona.setSuperior(idSuperior);

		DaoPersona daoPersona = new DaoPersona();

		return daoPersona.actualizarPersona(persona);
	}
	
	public boolean modificarPersona(PersonaTo persona){
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.actualizarPersona(persona);
	}
	
	public boolean elminarPersona(PersonaTo persona){
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.eliminarPersona(persona);
	}
	


}
