package com.plataforma.cpc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.TipoDocumentoTo;
import com.plataforma.cpc.to.UsuarioTo;
import com.plataforma.cpc.utils.ConexionOracle;

/**
 * DAO de utilidades varias relacionadas con consultar información sobre la creación de personas
 */
public class DaoUtilidades {
	
	//-------------------------------------------------------------------------------------------------------
	// Atributos
	//-------------------------------------------------------------------------------------------------------
	
	public 	Conexion 					conexionActual;
	private ArrayList<PerfilTo> 		perfiles;
	private ArrayList<TipoDocumentoTo> 	documentos;
	
	//-------------------------------------------------------------------------------------------------------
	// Constructor
	//-------------------------------------------------------------------------------------------------------
	
	public DaoUtilidades() throws Exception{
		perfiles = consultarPerfiles(new PerfilTo());
		documentos = consultarTipoDocumentos(new TipoDocumentoTo());
	}
	
	//-------------------------------------------------------------------------------------------------------
	// Operaciones
	//-------------------------------------------------------------------------------------------------------
	
	/**
	 * Consulta todos los perfiles presentes en el sistema y retorna un arreglo con la información de cada uno
	 * @param perfil Objeto tipo PerfilTo para modelar la información de los disintos perfiles
	 * @return ArrayList de objetos tipo PerfilTo con toda la información en Base de Datos
	 */
    public ArrayList<PerfilTo> consultarPerfiles(PerfilTo perfil) throws Exception{
    	
    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	ArrayList<PerfilTo> perfiles = new ArrayList<PerfilTo>();
    	
    	String sql = "SELECT ID_PERFIL, NOMBRE_PERFIL, PERMISO FROM PERFIL ";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				PerfilTo perfilTo = new PerfilTo();
				perfilTo.setIdPerfil(rs.getInt("ID_PERFIL"));
				perfilTo.setNombrePerfil(rs.getString("NOMBRE_PERFIL"));
				perfilTo.setPermiso(rs.getString("PERMISO"));
				perfiles.add(perfilTo);
			}
		} catch (Exception e) {
			throw new Exception("Error al tratar de cargar los Perfiles");
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (Exception e) {
				throw new Exception("Error en la conexión con la base de datos");
			}
		}	
    	return perfiles;
    }
    
    /**
	 * Consulta todos los tipos de documento presentes en el sistema y retorna un arreglo con la información de cada uno
	 * @param tipoDocumento Objeto tipo TipoDocumentoTo para modelar la información de los distintos documentos
	 * @return ArrayList de objetos tipo TipoDocumentoTo con toda la información en Base de Datos
	 */
    public ArrayList<TipoDocumentoTo> consultarTipoDocumentos(TipoDocumentoTo tipoDocumento) throws Exception{
    	
    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	ArrayList<TipoDocumentoTo> listaDocumentos = new ArrayList<TipoDocumentoTo>();
    	
    	String sql = "SELECT ID_DOCUMENTO, SIGLA, NOMBRE_DOCUMENTO FROM TIPO_DOCUMENTO ";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
				tipoDocumentoTo.setIdTipoDocumento(rs.getInt("ID_DOCUMENTO"));
				tipoDocumentoTo.setSigla(rs.getString("SIGLA")); 
				tipoDocumentoTo.setNombreDocumento(rs.getString("NOMBRE_DOCUMENTO"));
				listaDocumentos.add(tipoDocumentoTo);
			}
		} catch (SQLException e) {
			throw new Exception("Error al tratar de cargar los tipos de documento");
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (SQLException e) {
				throw new Exception("Error en la conexión con la base de datos");
			}
		}	
    	return listaDocumentos;
    }
    
    /**
     * Busca un perfil especifico dado su nombre y retorna su informacion en un objeto tipo PerfilTo
     * @param nombre Nombre del perfil tal como aparece en la BD
     * @return Objeto tipo PerfilTo
     */
    public PerfilTo buscarPerfil(String nombre){
    	for(int i = 0; i < perfiles.size(); i++){
    		if(perfiles.get(i).getNombrePerfil().equals(nombre))
    			return perfiles.get(i);
    	}
    	return null;
    }
    
    /**
     * Busca un tipo de documento especifico dado su sigla y retorna su informacion en un objeto tipo TipoDocumentoTo
     * @param sigla Sigla del documento tal como aparece en la BD
     * @return Objeto tipo TipoDocumentoTo
     */
    public TipoDocumentoTo buscarTipoDocumento(String sigla){
    	for(int i = 0; i < documentos.size(); i++){
    		if(documentos.get(i).getSigla().equals(sigla))
    			return documentos.get(i);
    	}
    	return null;
    }
    
    private void cerrarConexionActual() throws Exception{

    }
    
//    public static void main(String args[]) {
//    	DaoUtilidades daoUtilidades = new DaoUtilidades();
//    	
//    	daoUtilidades.consultarDocumentos(daoUtilidades);
//    	//daoUsuario.consultarDaoUsuario(daoUsuario);
//    	//daoUsuario.crearDaoUsuario(daoUsuario);
//    	//daoUsuario.modificarDaoUsuario(daoUsuario);
//    	//daoUsuario.eliminarDaoUsuario(daoUsuario);
//    }
//    
//    public void consultarDocumentos(DaoUtilidades daoUtilidades){
//   	 
//    	ArrayList<TipoDocumentoTo> listaDocumentos = new ArrayList<TipoDocumentoTo>();
//    	TipoDocumentoTo tipoDocumento = new TipoDocumentoTo();
//    	
//    	listaDocumentos = daoUtilidades.consultarTipoDocumentos(tipoDocumento);
//    	if (listaDocumentos.size()>0){
//    		for (TipoDocumentoTo tipoDocumentoTo: listaDocumentos){
//    			System.out.println(tipoDocumentoTo.toString()); 
//    		}
//    	}else {
//    		System.out.println("No trae datos");
//    	}
//    }
//    
//    public void consultarPerfiles(DaoUtilidades daoUtilidades){
//    	 
//    	ArrayList<PerfilTo> perfiles = new ArrayList<PerfilTo>();
//    	PerfilTo perfil = new PerfilTo();
//    	
//    	perfiles = daoUtilidades.consultarPerfiles(perfil);
//    	if (perfiles.size()>0){
//    		for (PerfilTo perfilTo: perfiles){
//    			System.out.println(perfilTo.toString()); 
//    		}
//    	}else {
//    		System.out.println("No trae datos");
//    	}
//    }
}