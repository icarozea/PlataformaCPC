package com.plataforma.cpc.utils;

import java.io.IOException;
import java.util.Properties;

public class Propiedades {
	
	private static Propiedades propiedades;
	public static final String RUTA_JASPER ="RUTA_JASPER";
	private Properties properti = null;
		
	private Propiedades(){	
		
		/**Creamos un Objeto de tipo Properties*/
		properti = new Properties();
		    
		/**Cargamos el archivo desde la ruta especificada*/
		try {
			properti.load(Propiedades.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Propiedades getInstance(){
		if(propiedades == null)
			propiedades = new Propiedades();
		return propiedades;
	}
	
	public String valorPropiedad(String llave){
		String valor = properti.getProperty(llave);
		return valor;
	}
}