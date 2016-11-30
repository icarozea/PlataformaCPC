package com.plataforma.cpc.modelo;

import com.plataforma.cpc.dao.DaoPersona;
import com.plataforma.cpc.to.PersonaTo;


public class UsuarioBean {
	
	public PersonaTo validarUsuario(String nombreUsuario, String contrasenia){
		DaoPersona daoPersona= new DaoPersona();
		PersonaTo persona = new PersonaTo();
		persona = daoPersona.consultarPersonaUsuario(nombreUsuario,contrasenia);
		return persona;
	}

}
