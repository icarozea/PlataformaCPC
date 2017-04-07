package com.plataforma.cpc.modelo;

import com.plataforma.cpc.dao.DaoPersona;
import com.plataforma.cpc.to.PersonaDetalleTo;

public class PersonaDetalleBean {
	
	public boolean ingresarDetallePersona(PersonaDetalleTo persona){
		DaoPersona daoPersona = new DaoPersona();
		return daoPersona.crearPersonaDetalle(persona);		
	}

}
