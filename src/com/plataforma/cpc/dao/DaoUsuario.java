package com.plataforma.cpc.dao;


import java.sql.ResultSet;
import java.util.ArrayList;

import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.to.UsuarioTo;
import com.plataforma.cpc.utils.ConexionOracle;

/**
 *
 * @author Ovidio Zea
 */
public class DaoUsuario {
    
    
    public Conexion conexionActual;

    public ArrayList<UsuarioTo> consultarUsuarios(UsuarioTo usuario){
    	
    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	ArrayList<UsuarioTo> usuarios = new ArrayList<UsuarioTo>();
    	
    	String sql = "SELECT ID_USUARIO, NOM_USUARIO, CONTRASENA, CORREO FROM CPC_USUARIO ";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				UsuarioTo usuarioTo = new UsuarioTo();
				usuarioTo.setIdUsuario(rs.getInt("ID_USUARIO"));
				usuarioTo.setNombreUsuario(rs.getString("NOM_USUARIO")); 
				usuarioTo.setContrasena(rs.getString("CONTRASENA"));
				usuarioTo.setCorreo(rs.getString("CORREO"));
				usuarios.add(usuarioTo);
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
    	return usuarios;
    }
    
    public UsuarioTo consultarUsuario(UsuarioTo usuario){

    	ResultSet rs =null;
    	UsuarioTo usuarioTo = new UsuarioTo();
    	conexionActual = new ConexionOracle();
    	String sql = "SELECT ID_USUARIO, NOM_USUARIO, CONTRASENA, CORREO FROM CPC_USUARIO ";
    		sql+= "WHERE UPPER(NOM_USUARIO) = UPPER(?) AND CONTRASENA = ? ";
    	System.out.println("Consulta: "+sql);
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1,usuario.getNombreUsuario());	
			conexionActual.agregarAtributo(2,usuario.getContrasena());
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				usuarioTo.setIdUsuario(rs.getInt("ID_USUARIO"));
				usuarioTo.setNombreUsuario(rs.getString("NOM_USUARIO")); 
				usuarioTo.setContrasena(rs.getString("CONTRASENA"));
				usuarioTo.setCorreo(rs.getString("CORREO"));
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
    	return usuarioTo;
    }
    
    public boolean crearUsuario(UsuarioTo usuario){
    	
    	boolean retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "INSERT INTO CPC_USUARIO (ID_USUARIO, NOM_USUARIO, CONTRASENA, CORREO) VALUES (USUARIO_SEQ.NEXTVAL, ?, ?, ?)";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1,usuario.getNombreUsuario());	
			conexionActual.agregarAtributo(2,usuario.getContrasena());
			conexionActual.agregarAtributo(3,usuario.getCorreo());
			conexionActual.ejecutarSentencia();
			
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
    
    public boolean actualizarUsuario(UsuarioTo usuario){
    	
    	boolean retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "UPDATE CPC_USUARIO SET NOM_USUARIO = ? , CONTRASENA = ?, CORREO = ? WHERE ID_USUARIO = ?";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1,usuario.getNombreUsuario());	
			conexionActual.agregarAtributo(2,usuario.getContrasena());	
			conexionActual.agregarAtributo(3,usuario.getCorreo());
			conexionActual.agregarAtributo(4,usuario.getIdUsuario());
			conexionActual.ejecutarSentencia();
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
    
    public boolean eliminarUsuario(UsuarioTo usuario){
    	
    	boolean retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "DELETE FROM CPC_USUARIO WHERE ID_USUARIO = ?";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1,usuario.getIdUsuario());
			conexionActual.ejecutarSentencia();
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
