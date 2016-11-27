package com.plataforma.cpc.dao;


import java.sql.ResultSet;
import java.util.ArrayList;

import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.to.EpsTo;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.TipoDocumentoTo;
import com.plataforma.cpc.to.UsuarioTo;
import com.plataforma.cpc.utils.ConexionOracle;

public class DaoPersona {
	
	public Conexion conexionActual;
    
    public ArrayList<PersonaTo> consultarPersonasFiltro(PersonaTo persona){
    	
    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	ArrayList<PersonaTo> personas = new ArrayList<PersonaTo>();
    	int numeroParametros = 0;
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,USUARIO_ID_USUARIO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL FROM PERSONA WHERE 1 = 1 ";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			
			if(persona.getTipoDocumento().getIdTipoDocumento()!= null){
	 			sql+= "AND TIPO_DOCUMENTO_ID_DOCUMENTO = ? AND NUMERO_DOCUMENTO = ? ";
	 			numeroParametros++;
	 			conexionActual.agregarAtributo(numeroParametros, persona.getTipoDocumento().getIdTipoDocumento());
	 			numeroParametros++;
	 			conexionActual.agregarAtributo(numeroParametros, persona.getNumeroDocumento()); 			
	 		}
	 		if (persona.getPrimerNombre()!= null){
	 			sql+= "AND PRIMER_NOMBRE = ? ";
	 			numeroParametros++;
	 			conexionActual.agregarAtributo(numeroParametros, persona.getPrimerNombre()); 
	 		}
	 		
	 		if (persona.getPrimerApellido()!= null){
	 			sql+= "AND PRIMER_APELLIDO = ? ";
	 			numeroParametros++;
	 			conexionActual.agregarAtributo(numeroParametros, persona.getPrimerApellido()); 
	 		}
					
	 		conexionActual.agregarAtributo(1, persona.getPerfil().getIdPerfil()); 
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				PersonaTo personaTo = new PersonaTo();	
				UsuarioTo usuarioTo = new UsuarioTo();
				TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
				EpsTo epsTo = new EpsTo();
				PerfilTo perfilTo = new PerfilTo();
				
				personaTo.setIdPersona(rs.getInt("ID_PERSONA"));
				personaTo.setPrimerNombre(rs.getString("PRIMER_NOMBRE")); 
				personaTo.setSegundoNombre(rs.getString("SEGUNDO_NOMBRE"));
				personaTo.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
				personaTo.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
				personaTo.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
				personaTo.setDireccion(rs.getString("DIRECCION"));
				personaTo.setTelefono(rs.getLong("TELEFONO"));
				personaTo.setCorreo(rs.getString("CORREO"));
				
				usuarioTo.setIdUsuario(rs.getInt("USUARIO_ID_USUARIO"));
				personaTo.setUsuario(usuarioTo);
				tipoDocumentoTo.setIdTipoDocumento(rs.getInt("TIPO_DOCUMENTO_ID_DOCUMENTO"));
				personaTo.setTipoDocumento(tipoDocumentoTo);
				epsTo.setIdEPS(rs.getInt("EPS_ID_EPS"));
				personaTo.setEps(epsTo);
				perfilTo.setIdPerfil(rs.getInt("PERFIL_ID_PERFIL"));
				personaTo.setPerfil(perfilTo);
				personas.add(personaTo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
    	return personas;
    }

    public ArrayList<PersonaTo> consultarPersonasPerfil(PersonaTo persona){
    	
    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	ArrayList<PersonaTo> personas = new ArrayList<PersonaTo>();
    	
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL, PERSONA_ID_SUPERIOR FROM PERSONA WHERE PERFIL_ID_PERFIL = ? ";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getPerfil().getIdPerfil()); 
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				PersonaTo personaTo = new PersonaTo();	
				//UsuarioTo usuarioTo = new UsuarioTo();
				TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
				EpsTo epsTo = new EpsTo();
				PerfilTo perfilTo = new PerfilTo();
				
				personaTo.setIdPersona(rs.getInt("ID_PERSONA"));
				personaTo.setPrimerNombre(rs.getString("PRIMER_NOMBRE")); 
				personaTo.setSegundoNombre(rs.getString("SEGUNDO_NOMBRE"));
				personaTo.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
				personaTo.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
				personaTo.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
				personaTo.setDireccion(rs.getString("DIRECCION"));
				personaTo.setTelefono(rs.getLong("TELEFONO"));
				personaTo.setCorreo(rs.getString("CORREO"));
				
				//usuarioTo.setIdUsuario(rs.getInt("USUARIO_ID_USUARIO"));
				//personaTo.setUsuario(usuarioTo);
				tipoDocumentoTo.setIdTipoDocumento(rs.getInt("TIPO_DOCUMENTO_ID_DOCUMENTO"));
				personaTo.setTipoDocumento(tipoDocumentoTo);
				epsTo.setIdEPS(rs.getInt("EPS_ID_EPS"));
				personaTo.setEps(epsTo);
				perfilTo.setIdPerfil(rs.getInt("PERFIL_ID_PERFIL"));
				personaTo.setPerfil(perfilTo);
				personaTo.setSuperior(rs.getInt("PERSONA_ID_SUPERIOR"));
				personas.add(personaTo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
    	return personas;
    }
    
    public ArrayList<PersonaTo> consultarAsignados(Integer idSuperior){
    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	ArrayList<PersonaTo> personas = new ArrayList<PersonaTo>();
    	
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL FROM PERSONA WHERE PERSONA_ID_SUPERIOR = ? ";
    	
    	try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idSuperior); 
			rs = conexionActual.ejecutarSentencia();
			

			while (rs.next()){
				PersonaTo personaTo = new PersonaTo();	
				TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
				EpsTo epsTo = new EpsTo();
				PerfilTo perfilTo = new PerfilTo();
				
				personaTo.setIdPersona(rs.getInt("ID_PERSONA"));
				personaTo.setPrimerNombre(rs.getString("PRIMER_NOMBRE")); 
				personaTo.setSegundoNombre(rs.getString("SEGUNDO_NOMBRE"));
				personaTo.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
				personaTo.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
				personaTo.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
				personaTo.setDireccion(rs.getString("DIRECCION"));
				personaTo.setTelefono(rs.getLong("TELEFONO"));
				personaTo.setCorreo(rs.getString("CORREO"));
				tipoDocumentoTo.setIdTipoDocumento(rs.getInt("TIPO_DOCUMENTO_ID_DOCUMENTO"));
				personaTo.setTipoDocumento(tipoDocumentoTo);
				epsTo.setIdEPS(rs.getInt("EPS_ID_EPS"));
				personaTo.setEps(epsTo);
				perfilTo.setIdPerfil(rs.getInt("PERFIL_ID_PERFIL"));
				personaTo.setPerfil(perfilTo);
				personas.add(personaTo);
			}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
		}
    	finally{
    		try {
    			conexionActual.cerrar();
    			rs.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	return personas;
    }
    
    public PersonaTo consultarPersona (PersonaTo persona){

    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	PersonaTo personaTo = new PersonaTo();
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL FROM PERSONA WHERE ID_PERSONA = ?";
    	 	
		try {
			DaoUtilidades utils = new DaoUtilidades();
			
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getIdPersona()); 		
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
				EpsTo epsTo = new EpsTo();
				PerfilTo perfilTo = new PerfilTo();
				
				personaTo.setIdPersona(rs.getInt("ID_PERSONA"));
				personaTo.setPrimerNombre(rs.getString("PRIMER_NOMBRE")); 
				personaTo.setSegundoNombre(rs.getString("SEGUNDO_NOMBRE"));
				personaTo.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
				personaTo.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
				personaTo.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
				personaTo.setDireccion(rs.getString("DIRECCION"));
				personaTo.setTelefono(rs.getLong("TELEFONO"));
				personaTo.setCorreo(rs.getString("CORREO"));
				tipoDocumentoTo = utils.buscarTipoDocumento(rs.getInt("TIPO_DOCUMENTO_ID_DOCUMENTO"));
				personaTo.setTipoDocumento(tipoDocumentoTo);
				epsTo = utils.buscarEps((rs.getInt("EPS_ID_EPS")));
				personaTo.setEps(epsTo);
				perfilTo = utils.buscarPerfil((rs.getInt("PERFIL_ID_PERFIL")));
				personaTo.setPerfil(perfilTo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
    	return personaTo;
    }
    
    public boolean crearPersona(PersonaTo persona){
    	
    	boolean retorno = Boolean.FALSE;
    	conexionActual = new ConexionOracle();
    	String sql = "INSERT INTO PERSONA (ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL,PERSONA_ID_SUPERIOR)"
    				+ "VALUES (PERSONA_SEQ.NEXTVAL, ?,?,?,?,?,?,?,?,?,?,?,?)";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getPrimerNombre()); 	
			conexionActual.agregarAtributo(2, persona.getSegundoNombre()); 
			conexionActual.agregarAtributo(3, persona.getPrimerApellido()); 
			conexionActual.agregarAtributo(4, persona.getSegundoApellido()); 
			conexionActual.agregarAtributo(5, persona.getNumeroDocumento());
			conexionActual.agregarAtributo(6,persona.getDireccion());
			conexionActual.agregarAtributo(7, persona.getTelefono());
			conexionActual.agregarAtributo(8, persona.getCorreo());
			conexionActual.agregarAtributo(9, persona.getTipoDocumento().getIdTipoDocumento());
			conexionActual.agregarAtributo(10, persona.getEps().getIdEPS());
			conexionActual.agregarAtributo(11, persona.getPerfil().getIdPerfil());
			conexionActual.agregarAtributo(12, 0);
			
			conexionActual.ejecutarActualizacion();
			retorno = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
		}finally{
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
    	return retorno;
    }
    
    public boolean actualizarPersona(PersonaTo persona){
    	
    	boolean retorno =  Boolean.FALSE;
    	conexionActual = new ConexionOracle();
    	String sql = "UPDATE PERSONA SET PRIMER_NOMBRE = ?, SEGUNDO_NOMBRE = ?, PRIMER_APELLIDO = ?, SEGUNDO_APELLIDO = ?,NUMERO_DOCUMENTO = ?,DIRECCION = ?,TELEFONO = ?,CORREO = ?,TIPO_DOCUMENTO_ID_DOCUMENTO = ?, EPS_ID_EPS = ?, PERFIL_ID_PERFIL = ?, PERSONA_ID_SUPERIOR = ? WHERE ID_PERSONA = ?";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getPrimerNombre());
			conexionActual.agregarAtributo(2, persona.getSegundoNombre());
			conexionActual.agregarAtributo(3, persona.getPrimerApellido());
			conexionActual.agregarAtributo(4, persona.getSegundoApellido());
			conexionActual.agregarAtributo(5, persona.getNumeroDocumento());
			conexionActual.agregarAtributo(6,persona.getDireccion());
			conexionActual.agregarAtributo(7, persona.getTelefono());
			conexionActual.agregarAtributo(8, persona.getCorreo());
			conexionActual.agregarAtributo(9, persona.getTipoDocumento().getIdTipoDocumento());
			conexionActual.agregarAtributo(10, persona.getEps().getIdEPS());
			conexionActual.agregarAtributo(11, persona.getPerfil().getIdPerfil());
			conexionActual.agregarAtributo(12, persona.getSuperior());
			conexionActual.agregarAtributo(13, persona.getIdPersona());
			
			conexionActual.ejecutarActualizacion();
			retorno = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
		}finally{
			try {
				conexionActual.cerrar();
				
			} catch (Exception e) {
				e.printStackTrace();
				retorno = Boolean.FALSE;
				return retorno;
			}
		}	
    	return retorno;
    }
    
    public boolean eliminarPersona(PersonaTo persona){
    	
    	boolean retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "DELETE FROM PERSONA WHERE ID_PERSONA = ?";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getIdPersona());
			
			conexionActual.ejecutarActualizacion();
			retorno = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
		}finally{
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
    	return retorno;
    }
    
    /*public static void main(String args[]) {
    	DaoUsuario daoUsuario = new DaoUsuario();
    	
    	//daoUsuario.consultarDaoUsuarios(daoUsuario);
    	//daoUsuario.consultarDaoUsuario(daoUsuario);
    	//daoUsuario.crearDaoUsuario(daoUsuario);
    	//daoUsuario.modificarDaoUsuario(daoUsuario);
    	daoUsuario.eliminarDaoUsuario(daoUsuario);
    }
    public void eliminarDaoUsuario(DaoUsuario daoUsuario){
     	 
    	UsuarioTo usuario = new UsuarioTo();
    	usuario.setIdUsuario(new Integer(3));
   
    	if (daoUsuario.eliminarUsuario(usuario)){
    		System.out.println("Eliminado"); 
    	}else {
    		System.out.println("No eliminado");
    	}
    }
    public void modificarDaoUsuario(DaoUsuario daoUsuario){
      	 
    	UsuarioTo usuario = new UsuarioTo();
    	usuario.setIdUsuario(new Integer(4));
    	usuario.setNombreUsuario("alberto");
    	usuario.setContrasena("alberto123");
    	usuario.setCorreo("alberto@gmail.com");
   
    	if (daoUsuario.actualizarUsuario(usuario)){
    		System.out.println("Actualizado"); 
    	}else {
    		System.out.println("No actualizado");
    	}
    }
    
    public void crearDaoUsuario(DaoUsuario daoUsuario){
   	 
    	UsuarioTo usuario = new UsuarioTo();
    	usuario.setNombreUsuario("vanessa");
    	usuario.setContrasena("vanessa123");
    	usuario.setCorreo("vanessa@gmail.com");
   
    	if (daoUsuario.crearUsuario(usuario)){
    		System.out.println("Creado"); 
    	}else {
    		System.out.println("No creado");
    	}
    }
    
    public void consultarDaoUsuario(DaoUsuario daoUsuario){
    	 
    	UsuarioTo usuario = new UsuarioTo();
    	usuario.setIdUsuario(new Integer(4));
    	
    	usuario = daoUsuario.consultarUsuario(usuario);
    	if (usuario != null){
    		System.out.println(usuario.toString()); 
    	}else {
    		System.out.println("No trae datos");
    	}
    }
    public void consultarDaoUsuarios(DaoUsuario daoUsuario){
 
    	ArrayList<UsuarioTo> usuarios = new ArrayList<UsuarioTo>();
    	UsuarioTo usuario = new UsuarioTo();
    	
    	usuarios = daoUsuario.consultarUsuarios(usuario);
    	if (usuarios.size()>0){
    		for (UsuarioTo usuarioTo: usuarios){
    			System.out.println(usuarioTo.toString()); 
    		}
    	}else {
    		System.out.println("No trae datos");
    	}
    }*/

}
