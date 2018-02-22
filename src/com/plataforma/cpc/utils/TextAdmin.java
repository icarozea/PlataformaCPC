package com.plataforma.cpc.utils;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import static java.nio.charset.StandardCharsets.*;
import org.jasypt.util.text.StrongTextEncryptor;

import com.plataforma.cpc.interfaces.Conexion;

/**
 * Clase de utilidad encargade de codificar y decodificar los textos que representan los reportes de sesión y valoración, para su correcto
 * almacenamiento en la base de datos
 * @author sebastian.gilp
 */
public class TextAdmin {

	//-------------------------------------------------------------------------------------------------------------------------
	// Constantes
	//-------------------------------------------------------------------------------------------------------------------------
	
	private final static String ENC_KEY = "76aa35"; // Clave de encriptación del texto. Puede guardarse en un properties para mayor seguridad en el futuro
	
	//-------------------------------------------------------------------------------------------------------------------------
	// Funciones
	//-------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Genera un objeto Clob para almacenamiento en la base de datos a partir del texto que recibe por parámetro
	 * El texto que se guarda se cifra y es necesario desencriptarlo para procesarlo
	 * @param texto Contenido que se va a guardar
	 * @param conexion Conexión a la base de datos para generar el objeto Clob
	 * @return Clob conteniendo el texto cifrado
	 * @throws Exception Si hay problemas en la generación del Clob o en el cifrado del texto 
	 */
	public static Clob generarClob(String texto, Conexion conexion) throws Exception {
		try {
			Clob clob = conexion.crearClob();
			StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
			textEncryptor.setPassword(ENC_KEY);
			String myEncryptedText = textEncryptor.encrypt(texto);
			clob.setString(1, myEncryptedText);
			return clob;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Error al convertir el texto");
		}
	}
	
	/**
	 * Lee un objeto tipo Clob y desencripta el texto que contiene. 
	 * El texto debe haber sido encriptado con la llave que posee esta implementación
	 * @param clob Objeto tipo Clob que se obtiene dela BD
	 * @return Texto plano en formato UTF-8
	 * @throws Exception Si hay porblemas desencriptando el texto
	 */
	public static String getTexto(Clob clob) throws Exception {
		StringBuilder sb = new StringBuilder();
		String returnText = "";
	    try {
	    	Reader reader = clob.getCharacterStream();
	        BufferedReader br = new BufferedReader(reader);
	        int b;
	        while(-1 != (b = br.read()))
	        {
	            sb.append((char)b);
	        }
	        br.close();
	        
	        String encText = sb.toString();
	        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
			textEncryptor.setPassword(ENC_KEY);
			String plainText = textEncryptor.decrypt(encText);
			returnText = parseUTF(plainText);
	        
	    } catch (Exception e) {
	        throw new Exception("Error al convertir los datos recuperados de la BD");
	    }
	    return returnText;
	}
	
	/**
	 * Formatea una cadena de texto a UTF-8 para su correcta visualización
	 * @param text Cadena de texto original
	 * @return Cadena de texto formateada a UTF-8
	 */
	public static String parseUTF(String text) {
		if(!(text == null)) {
			if(!text.equals("")) {
				byte[] ptext = text.getBytes(UTF_8); 
				String returnText = new String(ptext, UTF_8); 
				return returnText;
			}
		}
		return "";
	}
}