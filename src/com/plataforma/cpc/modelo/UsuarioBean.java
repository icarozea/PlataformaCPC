package com.plataforma.cpc.modelo;

import java.util.ArrayList;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.plataforma.cpc.dao.DaoPersona;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.utils.BCrypt;

/**
 * Modelo que maneja la sesión de usuario dentro del sistema
 */
public class UsuarioBean {

	//-------------------------------------------------------------------------------------------
	// Funciones
	//-------------------------------------------------------------------------------------------
	
	/**
	 * Valida un usuario dado el numero de documento y la clave capturadas en el formulario del index
	 * @param nombreUsuario Numero de documento del usuario
	 * @param contrasenia Clave del usuario
	 * @return Una persona con la información completa si la validación fue exitosa, una persona con numero 
	 * de documento específico si la clave no pudo ser validada o una persona con los campos nulos si no
	 * se encontro en la base de datos. 
	 */
	public ArrayList<PersonaTo> validarUsuario(String nombreUsuario, String contrasenia){
		DaoPersona daoPersona= new DaoPersona();
		ArrayList<PersonaTo> perfiles = new ArrayList<PersonaTo>();
		perfiles = daoPersona.consultarPersonaUsuario(nombreUsuario);
		if(perfiles.size() > 0){
			String encryptedPass = perfiles.get(0).getPassword();
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			
			if(!passwordEncryptor.checkPassword(contrasenia, encryptedPass))
				perfiles.get(0).setNumeroDocumento("000");
		}
		
		return perfiles;
	}
}