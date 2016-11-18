package com.plataforma.cpc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.TipoDocumentoTo;
import com.plataforma.cpc.to.UsuarioTo;
import com.plataforma.cpc.utils.ConexionOracle;

public class DaoUtilidades extends ConexionOracle{
	
	public Connection connection;

    public ArrayList<PerfilTo> consultarPerfiles(PerfilTo perfil){
    	
    	PreparedStatement ps=null;
    	ResultSet rs =null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	ArrayList<PerfilTo> perfiles = new ArrayList<PerfilTo>();
    	
    	String sql = "SELECT ID_PERFIL, NOMBRE_PERFIL, PERMISO FROM CPC_PERFIL ";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()){
				PerfilTo perfilTo = new PerfilTo();
				perfilTo.setIdPerfil(rs.getInt("ID_PERFIL"));
				perfilTo.setNombrePerfil(rs.getString("NOMBRE_PERFIL"));
				perfilTo.setPermiso(rs.getString("PERMISO"));
				perfiles.add(perfilTo);
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
    	return perfiles;
    }
    
    public ArrayList<TipoDocumentoTo> consultarTipoDocumentos(TipoDocumentoTo tipoDocumento){
    	
    	PreparedStatement ps=null;
    	ResultSet rs =null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	ArrayList<TipoDocumentoTo> listaDocumentos = new ArrayList<TipoDocumentoTo>();
    	
    	String sql = "SELECT ID_DOCUMENTO, SIGLA, NOMBRE_DOCUMENTO FROM CPC_TIPO_DOCUMENTO ";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()){
				TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
				tipoDocumentoTo.setIdTipoDocumento(rs.getInt("ID_DOCUMENTO"));
				tipoDocumentoTo.setSigla(rs.getString("SIGLA")); 
				tipoDocumentoTo.setNombreDocumento(rs.getString("NOMBRE_DOCUMENTO"));
				listaDocumentos.add(tipoDocumentoTo);
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
    	return listaDocumentos;
    }
    
    public static void main(String args[]) {
    	DaoUtilidades daoUtilidades = new DaoUtilidades();
    	
    	daoUtilidades.consultarDocumentos(daoUtilidades);
    	//daoUsuario.consultarDaoUsuario(daoUsuario);
    	//daoUsuario.crearDaoUsuario(daoUsuario);
    	//daoUsuario.modificarDaoUsuario(daoUsuario);
    	//daoUsuario.eliminarDaoUsuario(daoUsuario);
    }
    
    public void consultarDocumentos(DaoUtilidades daoUtilidades){
   	 
    	ArrayList<TipoDocumentoTo> listaDocumentos = new ArrayList<TipoDocumentoTo>();
    	TipoDocumentoTo tipoDocumento = new TipoDocumentoTo();
    	
    	listaDocumentos = daoUtilidades.consultarTipoDocumentos(tipoDocumento);
    	if (listaDocumentos.size()>0){
    		for (TipoDocumentoTo tipoDocumentoTo: listaDocumentos){
    			System.out.println(tipoDocumentoTo.toString()); 
    		}
    	}else {
    		System.out.println("No trae datos");
    	}
    }
    
    public void consultarPerfiles(DaoUtilidades daoUtilidades){
    	 
    	ArrayList<PerfilTo> perfiles = new ArrayList<PerfilTo>();
    	PerfilTo perfil = new PerfilTo();
    	
    	perfiles = daoUtilidades.consultarPerfiles(perfil);
    	if (perfiles.size()>0){
    		for (PerfilTo perfilTo: perfiles){
    			System.out.println(perfilTo.toString()); 
    		}
    	}else {
    		System.out.println("No trae datos");
    	}
    }
}
