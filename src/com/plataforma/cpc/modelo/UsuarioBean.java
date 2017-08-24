package com.plataforma.cpc.modelo;

import com.plataforma.cpc.dao.DaoPersona;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.utils.BCrypt;

/**
 * Modelo que maneja la sesi�n de usuario dentro del sistema
 */
public class UsuarioBean {

//-------------------------------------------------------------------------------------------
// Funciones
//-------------------------------------------------------------------------------------------
	
	/**
	 * Valida un usuario dado el numero de documento y la clave capturadas en el formulario del index
	 * @param nombreUsuario Numero de documento del usuario
	 * @param contrasenia Clave del usuario
	 * @return Una persona con la informaci�n completa si la validaci�n fue exitosa, una persona con numero 
	 * de documento espec�fico si la clave no pudo ser validada o una persona con los campos nulos si no
	 * se encontro en la base de datos. 
	 */
	public PersonaTo validarUsuario(String nombreUsuario, String contrasenia){
		DaoPersona daoPersona= new DaoPersona();
		PersonaTo persona = new PersonaTo();
		persona = daoPersona.consultarPersonaUsuario(nombreUsuario);
		String hashedPass = persona.getPassword();

		if(!BCrypt.checkpw(contrasenia, hashedPass))
			persona.setNumeroDocumento("000");
		
		persona.setPassword("");
		return persona;
	}
}