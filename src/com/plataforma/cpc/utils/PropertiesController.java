package com.plataforma.cpc.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.plataforma.cpc.utils.Propiedades;

@WebListener
public class PropertiesController implements ServletContextListener{
	

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
//		System.out.println("Se ha iniciado el contexto de PlataformaCPC\nBase de datos:");
//		Propiedades cargue = Propiedades.getInstance();
//		System.out.println("Host:" + cargue.valorPropiedad("HOST"));
//		System.out.println("SID: " + cargue.valorPropiedad("SID"));
//		System.out.println("Port: " + cargue.valorPropiedad("PUERTO"));
		
	}
}
