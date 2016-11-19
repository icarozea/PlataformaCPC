package com.plataforma.cpc.modelo;

import java.util.ArrayList;

import com.plataforma.cpc.dao.DaoPersona;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.TipoDocumentoTo;
import com.plataforma.cpc.to.UsuarioTo;

public class PersonaBean {
	
	public boolean ingresarPersona(String nombre1,String nombre2, String apellido1, String apellido2, Integer tipoDocumento,String numeroDocumento, String direccion, Integer telefono, String correo, Integer idPerfil, String password ){
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

		DaoPersona daoPersona = new DaoPersona();

		return daoPersona.crearPersona(persona);
	}
	
	public ArrayList<PersonaTo> consultarPracticantes(){
		
		PersonaTo persona = new PersonaTo();
		PerfilTo perfil = new PerfilTo();
		perfil.setIdPerfil(2);//2 es el numero que indica que la persona es un practicante
		persona.setPerfil(perfil);
		
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.consultarPersonasPerfil(persona);
	}
	
	public ArrayList<PersonaTo> consultarPacientes(){
		
		PersonaTo persona = new PersonaTo();
		PerfilTo perfil = new PerfilTo();
		perfil.setIdPerfil(3);//2 es el numero que indica que la persona es un practicante
		persona.setPerfil(perfil);
		
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.consultarPersonasPerfil(persona);
	}
	
	public ArrayList<PersonaTo> consultarPersonasFiltro(PersonaTo persona){	
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.consultarPersonasPerfil(persona);
	}
	
	public PersonaTo consultarPersona(PersonaTo persona){
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.consultarPersona(persona);
	}
	
	public boolean modificarPersona(String nombre1,String nombre2, String apellido1, String apellido2, Integer tipoDocumento,String numeroDocumento, String direccion, Integer telefono, String correo, Integer idPerfil, String password ){
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

		DaoPersona daoPersona = new DaoPersona();

		return daoPersona.actualizarPersona(persona);
	}
	
	public boolean elminarPersona(PersonaTo persona){
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.eliminarPersona(persona);
	}

}
