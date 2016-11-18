package com.plataforma.cpc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.plataforma.cpc.to.EpsTo;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.TipoDocumentoTo;
import com.plataforma.cpc.to.UsuarioTo;
import com.plataforma.cpc.utils.ConexionOracle;

public class DaoPersona extends ConexionOracle{
	
    public Connection connection;
    
    public ArrayList<PersonaTo> consultarPersonasFiltro(PersonaTo persona){
    	
    	PreparedStatement ps=null;
    	ResultSet rs =null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	ArrayList<PersonaTo> personas = new ArrayList<PersonaTo>();
    	int numeroParametros = 0;
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,USUARIO_ID_USUARIO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL FROM CPC_PERSONA WHERE 1 = 1 ";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			
			if(persona.getTipoDocumento().getIdTipoDocumento()!= null){
	 			sql+= "AND TIPO_DOCUMENTO_ID_DOCUMENTO = ? AND NUMERO_DOCUMENTO = ? ";
	 			numeroParametros++;
	 			ps.setInt(numeroParametros, persona.getTipoDocumento().getIdTipoDocumento());
	 			numeroParametros++;
	 			ps.setString(numeroParametros, persona.getNumeroDocumento());
	 			
	 		}
	 		if (persona.getPrimerNombre()!= null){
	 			sql+= "AND PRIMER_NOMBRE = ? ";
	 			numeroParametros++;
	 			ps.setString(numeroParametros, persona.getPrimerNombre());
	 		}
	 		
	 		if (persona.getPrimerApellido()!= null){
	 			sql+= "AND PRIMER_APELLIDO = ? ";
	 			numeroParametros++;
	 			ps.setString(numeroParametros, persona.getPrimerApellido());
	 		}
						
			ps.setInt(1, persona.getPerfil().getIdPerfil());
			rs = ps.executeQuery();
			
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
				personaTo.setTelefono(rs.getInt("TELEFONO"));
				
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
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conexionOracle.cerrar();
				this.connection.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
    	return personas;
    }

    public ArrayList<PersonaTo> consultarPersonasPerfil(PersonaTo persona){
    	
    	PreparedStatement ps=null;
    	ResultSet rs =null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	ArrayList<PersonaTo> personas = new ArrayList<PersonaTo>();
    	
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,USUARIO_ID_USUARIO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL FROM CPC_PERSONA WHERE PERFIL_ID_PERFIL = ? ";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, persona.getPerfil().getIdPerfil());
			rs = ps.executeQuery();
			
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
				personaTo.setTelefono(rs.getInt("TELEFONO"));
				
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
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conexionOracle.cerrar();
				this.connection.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
    	return personas;
    }
    
    public PersonaTo consultarPersona (PersonaTo persona){

    	PreparedStatement ps=null;
    	ResultSet rs =null;
    	PersonaTo personaTo = new PersonaTo();
    	ConexionOracle conexionOracle = new ConexionOracle();
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,USUARIO_ID_USUARIO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL FROM CPC_PERSONA WHERE ID_PERSONA = ?";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);		
			ps.setInt(1, persona.getIdPersona());
			rs = ps.executeQuery();
			
			while (rs.next()){
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
				personaTo.setTelefono(rs.getInt("TELEFONO"));
				
				usuarioTo.setIdUsuario(rs.getInt("USUARIO_ID_USUARIO"));
				personaTo.setUsuario(usuarioTo);
				tipoDocumentoTo.setIdTipoDocumento(rs.getInt("TIPO_DOCUMENTO_ID_DOCUMENTO"));
				personaTo.setTipoDocumento(tipoDocumentoTo);
				epsTo.setIdEPS(rs.getInt("EPS_ID_EPS"));
				personaTo.setEps(epsTo);
				perfilTo.setIdPerfil(rs.getInt("PERFIL_ID_PERFIL"));
				personaTo.setPerfil(perfilTo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conexionOracle.cerrar();
				this.connection.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
    	return personaTo;
    }
    
    public boolean crearPersona(PersonaTo persona){
    	
    	boolean retorno;
    	PreparedStatement ps=null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	String sql = "INSERT INTO CPC_PERSONA (ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,USUARIO_ID_USUARIO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL)"
    				+ "VALUES (PERSONA_SEQ.NEXTVAL, ?,?,?,?,?,?,?,?,?,?,?)";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, persona.getPrimerNombre());
			ps.setString(2, persona.getSegundoNombre());
			ps.setString(3, persona.getPrimerApellido());
			ps.setString(4, persona.getSegundoApellido());
			ps.setString(5, persona.getNumeroDocumento());
			ps.setString(6,persona.getDireccion());
			ps.setInt(7, persona.getTelefono());
			ps.setInt(8, persona.getUsuario().getIdUsuario());
			ps.setInt(9, persona.getTipoDocumento().getIdTipoDocumento());
			ps.setInt(10, persona.getEps().getIdEPS());
			ps.setInt(11, persona.getPerfil().getIdPerfil());
			
			ps.executeUpdate();
			retorno = Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
		}finally{
			try {
				conexionOracle.cerrar();
				this.connection.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
    	return retorno;
    }
    
    public boolean actualizarPersona(PersonaTo persona){
    	
    	boolean retorno;
    	PreparedStatement ps=null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	String sql = "UPDATE CPC_PERSONA SET PRIMER_NOMBRE = ?, SEGUNDO_NOMBRE = ?, PRIMER_APELLIDO = ?, SEGUNDO_APELLIDO = ?,NUMERO_DOCUMENTO = ?,DIRECCION = ?,TELEFONO = ?,USUARIO_ID_USUARIO = ?,TIPO_DOCUMENTO_ID_DOCUMENTO = ?, EPS_ID_EPS = ?, PERFIL_ID_PERFIL = ? WHERE ID_PERSONA = ?";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, persona.getPrimerNombre());
			ps.setString(2, persona.getSegundoNombre());
			ps.setString(3, persona.getPrimerApellido());
			ps.setString(4, persona.getSegundoApellido());
			ps.setString(5, persona.getNumeroDocumento());
			ps.setString(6,persona.getDireccion());
			ps.setInt(7, persona.getTelefono());
			ps.setInt(8, persona.getUsuario().getIdUsuario());
			ps.setInt(9, persona.getTipoDocumento().getIdTipoDocumento());
			ps.setInt(10, persona.getEps().getIdEPS());
			ps.setInt(11, persona.getPerfil().getIdPerfil());
			ps.setInt(12, persona.getIdPersona());
			
			ps.executeUpdate();
			retorno = Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
		}finally{
			try {
				conexionOracle.cerrar();
				this.connection.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
    	return retorno;
    }
    
    public boolean eliminarPersona(PersonaTo persona){
    	
    	boolean retorno;
    	PreparedStatement ps=null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	String sql = "DELETE FROM CPC_PERSONA WHERE ID_PERSONA = ?";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, persona.getIdPersona());
			
			ps.executeUpdate();
			retorno = Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
		}finally{
			try {
				conexionOracle.cerrar();
				this.connection.close();
				ps.close();
			} catch (SQLException e) {
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
