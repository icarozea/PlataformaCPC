package com.plataforma.cpc.modelo;

import java.util.ArrayList;

import com.plataforma.cpc.dao.DaoEPS;
import com.plataforma.cpc.to.EpsTo;

public class EpsBean {
	
	public boolean ingresarEPS(String nombreEPS){
		EpsTo epsTo = new EpsTo();
		DaoEPS daoEPS = new DaoEPS();
		epsTo.setNombreEPS(nombreEPS);
		return daoEPS.crearEPS(epsTo);
	}
	
	public ArrayList<EpsTo> consultarEPS(EpsTo eps){
		DaoEPS daoEPS = new DaoEPS();
		return daoEPS.consultarEPSs(eps);
	}
	
	public EpsTo consultaEPS(EpsTo eps){
		DaoEPS daoEPS = new DaoEPS();
		return daoEPS.consultaEps(eps);
	}
	
	public boolean modificaEPS(EpsTo eps){
		DaoEPS daoEPS = new DaoEPS();
		return daoEPS.actualizarEPS(eps);
	}
	
	public boolean elminaEPS(EpsTo eps){
		DaoEPS daoEPS = new DaoEPS();
		return daoEPS.eliminarEPS(eps);
	}

}
