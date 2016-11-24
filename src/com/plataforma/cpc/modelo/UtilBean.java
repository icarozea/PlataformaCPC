package com.plataforma.cpc.modelo;

import java.util.ArrayList;

import com.plataforma.cpc.dao.DaoUtilidades;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.TipoDocumentoTo;

public class UtilBean {
	
	public ArrayList<PerfilTo>  consultarPerfil() throws Exception{
		PerfilTo perfil = new PerfilTo();
		DaoUtilidades daoUtilidades = new DaoUtilidades();
		ArrayList<PerfilTo> perfiles = new ArrayList<PerfilTo>();
		perfiles = daoUtilidades.consultarPerfiles(perfil);
		return perfiles;
	}
	
	public ArrayList<TipoDocumentoTo> consultarTipoDocumento() throws Exception{
		TipoDocumentoTo tipoDocumento = new TipoDocumentoTo();
		DaoUtilidades daoUtilidades = new DaoUtilidades();
		ArrayList<TipoDocumentoTo> listaDocumentos = new ArrayList<TipoDocumentoTo>();
		listaDocumentos = daoUtilidades.consultarTipoDocumentos(tipoDocumento);
		return listaDocumentos;
	}

}
