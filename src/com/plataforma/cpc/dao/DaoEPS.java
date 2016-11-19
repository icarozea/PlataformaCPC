package com.plataforma.cpc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.to.EpsTo;
import com.plataforma.cpc.utils.ConexionOracle;

public class DaoEPS extends ConexionOracle {
	
	public Conexion conexionActual;

    public ArrayList<EpsTo> consultarEPSs(EpsTo eps){
    	
    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	ArrayList<EpsTo> listaEps = new ArrayList<EpsTo>();
    	
    	String sql = "SELECT ID_EPS, NOMBRE_EPS FROM CPC_EPS ";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				EpsTo epsTo = new EpsTo();
				epsTo.setIdEPS(rs.getInt("ID_EPS"));
				epsTo.setNombreEPS(rs.getString("NOMBRE_EPS")); 
				listaEps.add(epsTo);
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
    	return listaEps;
    }
    
    public EpsTo consultaEps(EpsTo eps){

    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	EpsTo epsTo = new EpsTo();
   
    	String sql = "SELECT ID_EPS, NOMBRE_EPS FROM CPC_EPS WHERE ID_EPS = ? ";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, eps.getIdEPS()); 
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				epsTo.setIdEPS(rs.getInt("ID_EPS"));
				epsTo.setNombreEPS(rs.getString("NOMBRE_EPS")); 
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
    	return epsTo;
    }
    
    public boolean crearEPS(EpsTo eps){
    	
    	boolean retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "INSERT INTO CPC_EPS (ID_EPS, NOMBRE_EPS) VALUES (EPS_SEQ.NEXTVAL,?)";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, eps.getNombreEPS()); 
		
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
    
    public boolean actualizarEPS(EpsTo eps){
    	
    	boolean retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "UPDATE CPC_EPS SET NOMBRE_EPS = ? WHERE ID_EPS = ?";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, eps.getNombreEPS()); 
			conexionActual.agregarAtributo(2, eps.getIdEPS());
			
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
    
    public boolean eliminarEPS(EpsTo eps){
    	
    	boolean retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "DELETE FROM CPC_EPS WHERE ID_EPS = ?";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, eps.getIdEPS()); 
			
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
    
    public static void main(String args[]) {
    	DaoEPS daoEps = new DaoEPS();
    	
    	//daoUsuario.consultarDaoUsuarios(daoUsuario);
    	//daoUsuario.consultarDaoUsuario(daoUsuario);
    	//daoUsuario.crearDaoUsuario(daoUsuario);
    	//daoUsuario.modificarDaoUsuario(daoUsuario);
    	daoEps.eliminarDaoUsuario(daoEps);
    }
    public void eliminarDaoUsuario(DaoEPS daoEps){
     	 
    	EpsTo eps = new EpsTo();
    	eps.setIdEPS(101);
    	eps.setNombreEPS("saludCoop1");
   
    	if (daoEps.actualizarEPS(eps)){
    		System.out.println("Actualizado"); 
    	}else {
    		System.out.println("No actualizado");
    	}
    }
    /*
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
