package com.plataforma.cpc.modelo;

import com.plataforma.cpc.dao.DaoCitas;
import com.plataforma.cpc.to.CitaTo;

public class CitaBean {
	
	public boolean ingresarCita(CitaTo cita){
		DaoCitas daoCitas = new DaoCitas();
		return daoCitas.crearCita(cita);
	}

}
