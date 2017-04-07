package com.plataforma.cpc.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Interfaz que define una conexión genérica a la base de datos
 */
public interface Conexion {
	
	/**
	 * Define el contrato para retornar un objeto tipo Connection para interactuar con la base de datos
	 * @return Connection
	 */
	public Connection getConexion();
	
	/**
	 * Define el contrato para definir manualmente el(los) driver(s) necesarios para manejar la conexión con una base de datos
	 * @throws Exception Si se genera un error en el proceso
	 */
	public void registrarDriver() throws Exception;
	
	/**
	 * Define el contrato para establecer la conexión con la base de datos
	 * @throws Exception Si se genera un error en el proceso
	 */
	public void conectar() throws Exception;
	
	/**
	 * Define el contrato para alista una sentencia SQL que será ejecutada en el futuro
	 * @param sentencia String con la sentencia SQL que se ejecutará
	 * @throws Exception Si se genera un error en el proceso
	 */
	public void prepararSentencia(String sentencia) throws Exception;
	
	/**
	 * Define el contrato para alista una sentencia SQL que será ejecutada en el futuro, el segundo parametro es un arreglo con el nombre d ela columna que se desea recuperar la secuencia
	 * @param sentencia String con la sentencia SQL que se ejecutará
	 * @throws Exception Si se genera un error en el proceso
	 */
	public void prepararSentencia(String sentencia, String generarColumna[]) throws Exception;
	
	/**
	 * Define el contrato para alista una sentencia SQL que será ejecutada en el futuro
	 * @param sentencia String con la sentencia SQL que se ejecutará
	 * @throws Exception Si se genera un error en el proceso
	 */
	public PreparedStatement recuperarSentencia() throws Exception;
	
	/**
	 * Define el contrato para añadir un atributo de tipo String a una sentencia previamente preparada
	 * @param atributo Atributo que se desea agregar a la sentencia
	 * @throws Exception Si no hay una sentencia previa
	 */
	public void agregarAtributo(int numAtributo, String atributo) throws Exception;
	
	/**
	 * Define el contrato para añadir un atributo de tipo entero a una sentencia previamente preparada
	 * @param atributo Atributo que se desea agregar a la sentencia
	 * @throws Exception Si no hay una sentencia previa
	 */
	public void agregarAtributo(int numAtributo, int atributo) throws Exception;
	
	/**
	 * Define el contrato para añadir un atributo de tipo Date a una sentencia previamente preparada
	 * @param atributo Atributo que se desea agregar a la sentencia
	 * @throws Exception Si no hay una sentencia previa
	 */
	public void agregarAtributo(int numAtributo, LocalDateTime atributo) throws Exception;
	
	
	public void agregarAtributo(int numAtributo, Long atributo) throws SQLException;
	
	/**
	 * Define el contrato para ejecutar una sententica sql previamente preparada 
	 * @return ResultSet con los resultados de la sentencia
	 * @throws Exception Si se genera un error en el proceso
	 */
	public ResultSet ejecutarSentencia() throws Exception;
	
	/**
	 * Define el contrato para ejecutar una sententica sql de actualización
	 * @throws Exception Si se genera un error en el proceso
	 */
	public void ejecutarActualizacion() throws Exception;
	
	public int recuperarLlavePrimaria() throws SQLException;
	
	public void iniciarTransaccion() throws Exception;
	
	public void commit() throws Exception;
	
	public void rollback() throws Exception;
	
	public void cerrarTransaccion() throws Exception;
	
	/**
	 * Define el contrato para cerrar una conexión previa con la base de datos
	 * @throws Exception Si se genera un error en el proceso
	 */
	public void cerrar() throws Exception;	
}