package com.plataforma.cpc.interfaces;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
	
	public void agregarAtributo(int numAtributo, Timestamp atributo) throws Exception;
	
	/**
	 * Define el contrato para añadir un atributo de tipo Date a una sentencia previamente preparada
	 * @param atributo Atributo que se desea agregar a la sentencia
	 * @throws Exception Si no hay una sentencia previa
	 */
	public void agregarAtributo(int numAtributo, Date atributo) throws Exception;
	
	
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
	
	/**
	 * Define el contrato para cerrar una conexión previa con la base de datos
	 * @throws Exception Si se genera un error en el proceso
	 */
	public void cerrar() throws Exception;

	
}