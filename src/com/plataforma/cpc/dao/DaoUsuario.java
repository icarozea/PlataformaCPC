package com.plataforma.cpc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.plataforma.cpc.to.UsuarioTo;
import com.plataforma.cpc.utils.ConexionOracle;

/**
 *
 * @author Ovidio Zea
 */
public class DaoUsuario extends ConexionOracle {
    
    
    public Connection connection;

    public ArrayList<UsuarioTo> consultarUsuarios(UsuarioTo usuario){
    	
    	PreparedStatement ps=null;
    	ResultSet rs =null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	ArrayList<UsuarioTo> usuarios = new ArrayList<UsuarioTo>();
    	
    	String sql = "SELECT ID_USUARIO, NOM_USUARIO, CONTRASENA, CORREO FROM CPC_USUARIO ";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()){
				UsuarioTo usuarioTo = new UsuarioTo();
				usuarioTo.setIdUsuario(rs.getInt("ID_USUARIO"));
				usuarioTo.setNombreUsuario(rs.getString("NOM_USUARIO")); 
				usuarioTo.setContrasena(rs.getString("CONTRASENA"));
				usuarioTo.setCorreo(rs.getString("CORREO"));
				usuarios.add(usuarioTo);
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
    	return usuarios;
    }
    
    public UsuarioTo consultarUsuario(UsuarioTo usuario){

    	PreparedStatement ps=null;
    	ResultSet rs =null;
    	UsuarioTo usuarioTo = new UsuarioTo();
    	ConexionOracle conexionOracle = new ConexionOracle();
    	String sql = "SELECT ID_USUARIO, NOM_USUARIO, CONTRASENA, CORREO FROM CPC_USUARIO ";
    		sql+= "WHERE UPPER(NOM_USUARIO) = UPPER(?) AND CONTRASENA = ? ";
    	System.out.println("Consulta: "+sql);
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);		
			ps.setString(1, usuario.getNombreUsuario());
			ps.setString(2, usuario.getContrasena());
			rs = ps.executeQuery();
			
			while (rs.next()){
				usuarioTo.setIdUsuario(rs.getInt("ID_USUARIO"));
				usuarioTo.setNombreUsuario(rs.getString("NOM_USUARIO")); 
				usuarioTo.setContrasena(rs.getString("CONTRASENA"));
				usuarioTo.setCorreo(rs.getString("CORREO"));
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
    	return usuarioTo;
    }
    
    public boolean crearUsuario(UsuarioTo usuario){
    	
    	boolean retorno;
    	PreparedStatement ps=null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	String sql = "INSERT INTO CPC_USUARIO (ID_USUARIO, NOM_USUARIO, CONTRASENA, CORREO) VALUES (USUARIO_SEQ.NEXTVAL, ?, ?, ?)";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, usuario.getNombreUsuario());
			ps.setString(2, usuario.getContrasena());
			ps.setString(3, usuario.getCorreo());
			
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
    
    public boolean actualizarUsuario(UsuarioTo usuario){
    	
    	boolean retorno;
    	PreparedStatement ps=null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	String sql = "UPDATE CPC_USUARIO SET NOM_USUARIO = ? , CONTRASENA = ?, CORREO = ? WHERE ID_USUARIO = ?";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, usuario.getNombreUsuario());
			ps.setString(2, usuario.getContrasena());
			ps.setString(3, usuario.getCorreo());
			ps.setInt(4, usuario.getIdUsuario());
			
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
    
    public boolean eliminarUsuario(UsuarioTo usuario){
    	
    	boolean retorno;
    	PreparedStatement ps=null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	String sql = "DELETE FROM CPC_USUARIO WHERE ID_USUARIO = ?";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, usuario.getIdUsuario());
			
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
    
    public static void main(String args[]) {
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
    }
   
}
