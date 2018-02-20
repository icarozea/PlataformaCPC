package com.plataforma.cpc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.to.EpsTo;
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
	private ArrayList<EpsTo> 			eps;
	
	//-------------------------------------------------------------------------------------------------------
	// Constructor
	//-------------------------------------------------------------------------------------------------------
	
	public DaoUtilidades() throws Exception{
		perfiles = consultarPerfiles(new PerfilTo());
		documentos = consultarTipoDocumentos(new TipoDocumentoTo());
		eps = consultarEPS(new EpsTo());
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
				throw new Exception("Error en la conexión con la base de datos: " + e.getMessage());
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
	 * Consulta todas las eps presentes en el sistema y retorna un arreglo con la información de cada una
	 * @param tipoDocumento Objeto tipo EpsTo para modelar la información de las distintas Eps
	 * @return ArrayList de objetos tipo EpsTo con toda la información en Base de Datos
	 */
    public ArrayList<EpsTo> consultarEPS(EpsTo eps) throws Exception{
    	
    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	ArrayList<EpsTo> listaEps = new ArrayList<EpsTo>();
    	
    	String sql = "SELECT ID_EPS, NOMBRE_EPS FROM EPS ";
    	 	
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
		} catch (SQLException e) {
			throw new Exception("Error al tratar de cargar las eps");
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (SQLException e) {
				throw new Exception("Error en la conexión con la base de datos");
			}
		}	
    	return listaEps;
    }
    
    /**
     * Busca un perfil especifico dado su nombre y retorna su informacion en un objeto tipo PerfilTo
     * @param nombre Nombre del perfil tal como aparece en la BD
     * @return Objeto tipo PerfilTo
     */
    public PerfilTo buscarPerfil(int id){
    	for(int i = 0; i < perfiles.size(); i++){
    		if(perfiles.get(i).getIdPerfil() == id)
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
    
    /**
     * Busca un tipo de documento especifico dado su id de la base de datos y retorna su informacion en un objeto tipo TipoDocumentoTo
     * @param sigla Sigla del documento tal como aparece en la BD
     * @return Objeto tipo TipoDocumentoTo
     */
    public TipoDocumentoTo buscarTipoDocumento(int id){
    	for(int i = 0; i < documentos.size(); i++){
    		if(documentos.get(i).getIdTipoDocumento() == id)
    			return documentos.get(i);
    	}
    	return null;
    }
    
    /**
     * Busca una eps específica dado su id y retorna su informacion en un objeto tipo EpsTo
     * @param id Id de la eps tal como aparece en la BD
     * @return Objeto tipo EpsTo
     */
    public EpsTo buscarEps(int id){
    	for(int i = 0; i < eps.size(); i++){
    		if(eps.get(i).getIdEPS() == id)
    			return eps.get(i);
    	}
    	return null;
    }
    
    /**
     * Busca y retorna el máximo de cupos actuales definidos en el sistema que se pueden asignar a un practicante
     * @return Un entero con el máximo de cupos actuales
     */
    public int buscarCuposActuales() throws Exception{
    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	int cupos = 0;
    	
    	String sql = "SELECT NUMERO FROM CUPOS ";
    	
    	try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				cupos = rs.getInt("NUMERO");
			}
    	}
    	catch (SQLException e) {
			throw new Exception("Error al tratar de cargar el maximo de cupos");
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (SQLException e) {
				throw new Exception("Error en la conexión con la base de datos");
			}
		}	
    	return cupos;  	
    }
    
    /**
     * Actualiza el máximo de cupos de pacientes que se muestra para cada practicante
     * @param numero Maximo que se desea implementar
     * @return Si la operación fue exitosa o no
     * @throws Exception Si hubo algun problema en la conexión con la base de datos
     */
    public boolean actualizarCupos(Integer numero) throws Exception{
    	conexionActual = new ConexionOracle();
    	boolean actualizado = false;
    	
    	String sql = "UPDATE CUPOS SET NUMERO = ?";
    	
    	try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, numero);
			
			conexionActual.ejecutarActualizacion();
			actualizado = true;
			
    	}
    	catch (SQLException e) {
			throw new Exception("Error al tratar de cargar el maximo de cupos");
		}finally{
			try {
				conexionActual.cerrar();
			} catch (SQLException e) {
				throw new Exception("Error en la conexión con la base de datos");
			}
		}	
    	return actualizado;
    }
    
    /**
     * Retorna una cadena de caracteres con un nuevo codigo de histora clinica correspondiente al consecutivo actual
     * @param avanzarConsecutivo Determina si el numero de consecutivo debe avanzar tras hacer la consulta
     * @return Cadena de caracteres con el codigo correspondiente o null si hubo algun error en la operacion
     */
    public String consultarConsecutivoHistoria(boolean avanzarConsecutivo) {
    	conexionActual = new ConexionOracle();
    	String consecutivo = "";
    	int ano = 0;
    	String semestre = "";
    	int cons = 0;
    	
    	String sql = "SELECT ANO, SEMESTRE, CONSECUTIVO FROM CONSECUTIVO_HISTORIA";
    	String avance = "UPDATE CONSECUTIVO_HISTORIA SET CONSECUTIVO = CONSECUTIVO + 1";
    			
    	try {
    		ResultSet rs = null;
    		conexionActual.conectar();
    		conexionActual.iniciarTransaccion();
    		
    		conexionActual.prepararSentencia(sql);
    		rs = conexionActual.ejecutarSentencia();
    		
    		while(rs.next()){
    			ano = rs.getInt("ANO");
    			semestre = rs.getString("SEMESTRE");
    			cons = rs.getInt("CONSECUTIVO");	
    		}
    		
    		rs.close();
    		
    		if(avanzarConsecutivo){
    			conexionActual.prepararSentencia(avance);
    			conexionActual.ejecutarActualizacion();
    		}
    		
    		conexionActual.commit();
			
    		consecutivo = ano + "-" + semestre + "-" + cons;
    	}
    	catch (Exception e) {
    		consecutivo = null;
    		e.printStackTrace();
    		try {
				conexionActual.rollback();
			} catch(Exception exep) {
				exep.printStackTrace();
			}
		}finally{	
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	
    	return consecutivo;
    }
    
    /**
     * Reinicia la generación automatica de consecutivos dado los parametros recibidos
     * @param ano El año que el nuevo consecutivo va a mostrar
     * @param semestre El semestre que el consecutivo va a mostrar
     * @return Verdadero si la operación fue exitosa, falso de lo contrario
     */
    public boolean reiniciarConsecutivoHistoriaClinica(int ano, String semestre, int consecutivo){
    	boolean retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "UPDATE CONSECUTIVO_HISTORIA SET ANO = ?, SEMESTRE = ?, CONSECUTIVO = ?";
    	
    	try {
    		conexionActual.conectar();
    		
    		conexionActual.prepararSentencia(sql);
    		conexionActual.agregarAtributo(1, ano);
    		conexionActual.agregarAtributo(2, semestre);
    		conexionActual.agregarAtributo(3, consecutivo);

    		conexionActual.ejecutarActualizacion();
    		
    		retorno = Boolean.TRUE;
    	}
    	catch (Exception e) {
    		retorno = Boolean.FALSE;
    		e.printStackTrace();
		}finally{	
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	return retorno;
    }
}