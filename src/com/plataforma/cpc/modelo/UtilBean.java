package com.plataforma.cpc.modelo;

import java.util.ArrayList;

import com.plataforma.cpc.dao.DaoPersona;
import com.plataforma.cpc.dao.DaoUtilidades;
import com.plataforma.cpc.to.LocalidadTo;
import com.plataforma.cpc.to.MunicipioTo;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.TipoDocumentoTo;

public class UtilBean {
	
	public ArrayList<PerfilTo>  consultarPerfiles() throws Exception{
		PerfilTo perfil = new PerfilTo();
		DaoUtilidades daoUtilidades = new DaoUtilidades();
		ArrayList<PerfilTo> perfiles = new ArrayList<PerfilTo>();
		perfiles = daoUtilidades.consultarPerfiles(perfil);
		return perfiles;
	}
	
	public ArrayList<TipoDocumentoTo> consultarTiposDocumento() throws Exception{
		TipoDocumentoTo tipoDocumento = new TipoDocumentoTo();
		DaoUtilidades daoUtilidades = new DaoUtilidades();
		ArrayList<TipoDocumentoTo> listaDocumentos = new ArrayList<TipoDocumentoTo>();
		listaDocumentos = daoUtilidades.consultarTipoDocumentos(tipoDocumento);
		return listaDocumentos;
	}
	
	public ArrayList<MunicipioTo> consultarMunicipios(){
		DaoPersona persona = new DaoPersona();
		return persona.ConsultarMunicipios();
	}
	
	public ArrayList<LocalidadTo> consultarLocalidades(){
		DaoPersona persona = new DaoPersona();
		return persona.ConsultarLocalidades();
	}
}