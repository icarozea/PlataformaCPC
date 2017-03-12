package com.plataforma.cpc.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class CargueInicial {
	
	private static final String RUTA_PROPERTIES = "C:/Users/black/OneDrive/Documentos/GitHub/PlataformaCPC/WebContent/resources/variablesCPC.properties";
	
	 public void leerArchivoPropiedades() {
		 
		 try {
		   
		/**Creamos un Objeto de tipo Properties*/
		   Properties propiedades = new Properties();
		   //propiedades.load(this.getClass().getResourceAsStream(this.RUTA_PROPERTIES));
		    
		   /**Cargamos el archivo desde la ruta especificada*/
		   propiedades.load(new FileInputStream(this.RUTA_PROPERTIES));
		 
		   /**Obtenemos los parametros definidos en el archivo*/
		   String rutaArchivosJasper = propiedades.getProperty("RUTA_JASPER");
		 
		   /**Imprimimos los valores*/
		   System.out.println("rutaArchivosJasper: "+rutaArchivosJasper);
		       
		    
		  } catch (FileNotFoundException e) {
		   System.out.println("Error, El archivo no exite");
		  } catch (IOException e) {
		   System.out.println("Error, No se puede leer el archivo");
		  }
		 }

}
