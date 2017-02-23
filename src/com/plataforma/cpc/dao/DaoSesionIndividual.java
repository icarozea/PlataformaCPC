package com.plataforma.cpc.dao;

import java.sql.ResultSet;
import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.to.SesionIndividualTo;
import com.plataforma.cpc.utils.ConexionOracle;

public class DaoSesionIndividual extends ConexionOracle{
	
	public Conexion conexionActual;
	
	public DaoSesionIndividual(){
		
	}
	
	public boolean crearReporteSesionIndividual(SesionIndividualTo sesion, Integer idTratamiento, Integer idCita, String estado, boolean fallo){
		boolean retorno;
		conexionActual = new ConexionOracle();
		
		String sqlReporte = "INSERT INTO REPORTE_SESION (ID_SESION,FECHA,NOMBRE_PROFESIONAL,OBJETIVO_SESION,DESCRIPCION_SESION,TAREAS_ASIGNADAS,ACTIVIDADES_PROX_SESION,ES_FALLO,RECIBO) ";
		sqlReporte +="VALUES (REPORTE_SESION_SEQ.NEXTVAL,TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI'),?,?,?,?,?,?,?)";
		
		String sqlAvance = "UPDATE TRATAMIENTO SET NUM_CITA_ACTUAL = NUM_CITA_ACTUAL + 1, PENDIENTE = 1 WHERE ID_TRATAMIENTO = ? ";
		
		String sqlEstado = "UPDATE CITA SET ESTADO = ? WHERE ID_CITA = ? ";
		try {
			conexionActual.conectar();
			conexionActual.iniciarTransaccion();
			
			conexionActual.prepararSentencia(sqlReporte);	
			conexionActual.agregarAtributo(1, sesion.getFecha());
			conexionActual.agregarAtributo(2, sesion.getNombreProfesional());
			conexionActual.agregarAtributo(3, sesion.getObjetivo());
			conexionActual.agregarAtributo(4, sesion.getDescripcion());
			conexionActual.agregarAtributo(5, sesion.getTareasAsignadas());
			conexionActual.agregarAtributo(6, sesion.getActividadesProximaSesion());
			conexionActual.agregarAtributo(7,sesion.isFallo()?1:0);
			conexionActual.agregarAtributo(8,sesion.getNumRecibo());
			conexionActual.ejecutarActualizacion();
			
			conexionActual.prepararSentencia(sqlEstado);
			conexionActual.agregarAtributo(1, estado);
			conexionActual.agregarAtributo(2, idCita); 
			conexionActual.ejecutarActualizacion();
			
			if(!fallo){
				conexionActual.prepararSentencia(sqlAvance);
				conexionActual.agregarAtributo(1, idTratamiento);
				conexionActual.ejecutarActualizacion();
			}
			
			conexionActual.commit();
			retorno = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
			try {
                conexionActual.rollback();
            } catch(Exception exep) {
            	exep.printStackTrace();
            }
		}finally{
			try {
				conexionActual.cerrarTransaccion();
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return retorno;
	}
	
	public SesionIndividualTo consultarReporteSesionporCita(Integer idCita){
		ResultSet rs =null;
		conexionActual = new ConexionOracle();
		SesionIndividualTo sesionIndividual = new SesionIndividualTo();
		String sql = "SELECT RS.ID_SESION, RS.FECHA, RS.NOMBRE_PROFESIONAL, RS.OBJETIVO_SESION, RS.DESCRIPCION_SESION,";
				sql+= "RS.TAREAS_ASIGNADAS, RS.ACTIVIDADES_PROX_SESION, RS.ES_FALLO, RS.RECIBO";
				sql+= "FROM CITA CITA, REPORTE_SESION RS ";
				sql+= "WHERE ID_CITA = ? ";
				sql+= "AND CITA.ID_REPORTE = RS.ID_SESION";
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idCita);

			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){			
				sesionIndividual.setIdSesion(rs.getInt("ID_SESION"));
				sesionIndividual.setFecha(rs.getString("FECHA"));
				sesionIndividual.setNombreProfesional(rs.getString("NOMBRE_PROFESIONAL"));
				sesionIndividual.setObjetivo(rs.getString("OBJETIVO_SESION"));
				sesionIndividual.setDescripcion(rs.getString("DESCRIPCION_SESION"));
				sesionIndividual.setTareasAsignadas(rs.getString("TAREAS_ASIGNADAS"));
				sesionIndividual.setActividadesProximaSesion(rs.getString("ACTIVIDADES_PROX_SESION"));
				sesionIndividual.setFallo(rs.getInt("ES_FALLO")>0?true:false);
				sesionIndividual.setNumRecibo(rs.getInt("RECIBO"));
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
		return sesionIndividual;
	}
}