package com.plataforma.cpc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.to.SesionIndividualTo;
import com.plataforma.cpc.to.TratamientoTo;
import com.plataforma.cpc.utils.ConexionOracle;

public class DaoSesionIndividual extends ConexionOracle{
	
	public Conexion conexionActual;
	
	public DaoSesionIndividual(){
		
	}
	
	public boolean crearReporteSesionIndividual(SesionIndividualTo sesion){
		boolean retorno;
		conexionActual = new ConexionOracle();
		
		String sql = "INSERT INTO REPORTE_SESION (ID_SESION,CITA_ID_CITA,FECHA,NOMBRE_PROFESIONAL,OBJETIVO_SESION,DESCRIPCION_SESION,TAREAS_ASIGNADAS,ACTIVIDADES_PROX_SESION) ";
			   sql +="VALUES (REPORTE_SESION_SEQ.NEXTVAL,?,TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI'),?,?,?,?,?)";
		
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);	
			conexionActual.agregarAtributo(1, sesion.getCitaId()); 
			conexionActual.agregarAtributo(2, sesion.getFecha());
			conexionActual.agregarAtributo(3, sesion.getNombreProfesional());
			conexionActual.agregarAtributo(4, sesion.getObjetivo());
			conexionActual.agregarAtributo(5, sesion.getDescripcion());
			conexionActual.agregarAtributo(6, sesion.getTareasAsignadas());
			conexionActual.agregarAtributo(7, sesion.getActividadesProximaSesion());
			conexionActual.ejecutarActualizacion();
			retorno = Boolean.TRUE;
			
		} catch (Exception e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
			
		}finally{
			
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return retorno;
	}
	
	public ArrayList<SesionIndividualTo> consultarReporteSesionporCita(Integer idCita){
		ResultSet rs =null;
		conexionActual = new ConexionOracle();
		ArrayList<SesionIndividualTo> sesiones = new ArrayList<SesionIndividualTo>();
		String sql = "SELECT RS.ID_SESION, RS.CITA_ID_CITA, RS.FECHA, RS.NOMBRE_PROFESIONAL,RS.OBJETIVO_SESION,RS.DESCRIPCION_SESION,";
				sql+= "RS.TAREAS_ASIGNADAS,RS.ACTIVIDADES_PROX_SESION ";
				sql+= "FROM CITA CITA, REPORTE_SESION RS ";
				sql+= "WHERE ID_CITA = ? ";
				sql+= "AND CITA.ID_CITA = RS.CITA_ID_CITA";
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idCita);

			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){
				SesionIndividualTo sesionIndividual = new SesionIndividualTo();
				sesionIndividual.setIdSesion(rs.getInt(""));
				sesionIndividual.setCitaId(rs.getString(""));
				sesionIndividual.setFecha(rs.getString(""));
				sesionIndividual.setNombreProfesional(rs.getString(""));
				sesionIndividual.setObjetivo(rs.getString(""));
				sesionIndividual.setDescripcion(rs.getString(""));
				sesionIndividual.setTareasAsignadas(rs.getString(""));
				sesionIndividual.setActividadesProximaSesion(rs.getString(""));
				sesiones.add(sesionIndividual);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return sesiones;
	}

}
