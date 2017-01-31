package com.plataforma.cpc.modelo;

import java.util.ArrayList;

import com.plataforma.cpc.dao.DaoCitas;
import com.plataforma.cpc.dao.DaoSesionIndividual;
import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.SesionIndividualTo;
import com.plataforma.cpc.to.TratamientoTo;

public class HistoriaClinicaBean {
	
	public ArrayList<TratamientoTo> concultarTratamientosxPaciente (Integer idPaciente){		
		DaoCitas dao = new DaoCitas();
		ArrayList<TratamientoTo> tratamientos = new ArrayList<TratamientoTo>();
		tratamientos = dao.concultarTratamientosPaciente(idPaciente);
		return tratamientos;
	}
	public ArrayList<CitaTo> consultarCitasxTratamiento(Integer idPaciente, Integer idTratamiento){
		ArrayList<CitaTo> citas = new ArrayList<CitaTo>();
		DaoCitas dao = new DaoCitas();
		citas = dao.consultarCitasPacienteTratamiento(idPaciente, idTratamiento);
		return citas;
	}
	
	public ArrayList<SesionIndividualTo> consultarReportesSesion(Integer idCita){
		ArrayList<SesionIndividualTo> sesiones = new ArrayList<SesionIndividualTo>();
		DaoSesionIndividual dao = new DaoSesionIndividual();
		sesiones = dao.consultarReporteSesionporCita(idCita);
		return sesiones;
	}

}
