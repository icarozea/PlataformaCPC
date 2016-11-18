package com.plataforma.cpc.modelo;

import com.plataforma.cpc.dao.DaoUsuario;
import com.plataforma.cpc.to.UsuarioTo;

public class UsuarioBean {
	
	public UsuarioTo validarUsuario(String nombreUsuario, String contrasenia){
		DaoUsuario daoUsuario = new DaoUsuario();
		UsuarioTo usuarioTo = new UsuarioTo();
		usuarioTo.setNombreUsuario(nombreUsuario);
		usuarioTo.setContrasena(contrasenia);
		System.out.println("Usuario ingresado: "+ usuarioTo.toString());
		return daoUsuario.consultarUsuario(usuarioTo);
	}

}
