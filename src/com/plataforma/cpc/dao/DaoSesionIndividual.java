package com.plataforma.cpc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.to.ComentariosTo;
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
		
		String sqlEstado = "UPDATE CITA SET ESTADO = ?, ID_REPORTE = REPORTE_SESION_SEQ.CURRVAL WHERE ID_CITA = ? ";
		
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
	
	public boolean actualizarReporteSesionIndividual(SesionIndividualTo sesion){
		boolean retorno;
		conexionActual = new ConexionOracle();
		
		String actualizacion = "UPDATE REPORTE_SESION SET OBJETIVO_SESION = ?, DESCRIPCION_SESION = ?, TAREAS_ASIGNADAS = ?, ACTIVIDADES_PROX_SESION = ? ";
		actualizacion+= "WHERE ID_SESION = ? "; 
		
		String sqlEstado = "UPDATE CITA SET ESTADO = 'pendiente' WHERE ID_REPORTE = ? ";
		
		Integer idSesion = sesion.getIdSesion();
		try {
			conexionActual.conectar();
			conexionActual.iniciarTransaccion();
			
			conexionActual.prepararSentencia(actualizacion);	
			conexionActual.agregarAtributo(1, sesion.getObjetivo());
			conexionActual.agregarAtributo(2, sesion.getDescripcion());
			conexionActual.agregarAtributo(3, sesion.getTareasAsignadas());
			conexionActual.agregarAtributo(4, sesion.getActividadesProximaSesion());
			conexionActual.agregarAtributo(5, idSesion);
			conexionActual.ejecutarActualizacion();
			
			conexionActual.prepararSentencia(sqlEstado);
			conexionActual.agregarAtributo(1, idSesion); 
			conexionActual.ejecutarActualizacion();
			
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
				sql+= "RS.TAREAS_ASIGNADAS, RS.ACTIVIDADES_PROX_SESION, RS.ES_FALLO, RS.RECIBO, RS.ID_COMENTARIOS ";
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
				Integer idComentarios = rs.getInt("ID_COMENTARIOS");
				if(idComentarios != null){
					ComentariosTo comentarios = new ComentariosTo();
					comentarios.setIdComentarios(idComentarios);
					sesionIndividual.setComentarios(comentarios);
				}
					
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
	
	public ArrayList<SesionIndividualTo> consultarReporteSesionporPracticante(Integer idPracticante){
		ResultSet rs =null;
		ArrayList<SesionIndividualTo> reportes = new ArrayList<SesionIndividualTo>();
		conexionActual = new ConexionOracle();

		String sql = "SELECT RS.ID_SESION, RS.FECHA, RS.NOMBRE_PROFESIONAL, RS.OBJETIVO_SESION, RS.DESCRIPCION_SESION,";
				sql+= "RS.TAREAS_ASIGNADAS, RS.ACTIVIDADES_PROX_SESION, RS.ES_FALLO, RS.RECIBO, RS.ID_COMENTARIOS ";
				sql+= "FROM REPORTE_SESION RS INNER JOIN CITA CITA ";
				sql+= "ON CITA.ID_PRACTICANTE = ? AND CITA.ID_REPORTE = RS.ID_SESION ORDER BY CITA.ESTADO DESC, RS.FECHA DESC ";
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idPracticante);

			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){
				SesionIndividualTo sesionIndividual = new SesionIndividualTo();
				sesionIndividual.setIdSesion(rs.getInt("ID_SESION"));
				sesionIndividual.setFecha(rs.getString("FECHA"));
				sesionIndividual.setNombreProfesional(rs.getString("NOMBRE_PROFESIONAL"));
				sesionIndividual.setObjetivo(rs.getString("OBJETIVO_SESION"));
				sesionIndividual.setDescripcion(rs.getString("DESCRIPCION_SESION"));
				sesionIndividual.setTareasAsignadas(rs.getString("TAREAS_ASIGNADAS"));
				sesionIndividual.setActividadesProximaSesion(rs.getString("ACTIVIDADES_PROX_SESION"));
				sesionIndividual.setFallo(rs.getInt("ES_FALLO")>0?true:false);
				sesionIndividual.setNumRecibo(rs.getInt("RECIBO"));
				Integer idComentarios = rs.getInt("ID_COMENTARIOS");
				if(idComentarios != null){
					ComentariosTo comentarios = new ComentariosTo();
					comentarios.setIdComentarios(idComentarios);
					sesionIndividual.setComentarios(comentarios);
				}
				reportes.add(sesionIndividual);
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
		return reportes;
	}
	
	public boolean guardarComentarios(Integer idReporte, ComentariosTo comentarios){
		boolean retorno;
		conexionActual = new ConexionOracle();
		
		String guardar = "INSERT INTO COMENTARIOS_REPORTE (ID_COMENTARIOS,COM_OBJETIVO,COM_DESCRIPCION,COM_TAREAS,COM_ACTIVIDADES) ";
		guardar +="VALUES (COMENTARIOS_REPORTE_SEQ.NEXTVAL,?,?,?,?)";
		
		String asociar = "UPDATE REPORTE_SESION SET ID_COMENTARIOS = COMENTARIOS_REPORTE_SEQ.CURRVAL WHERE ID_SESION = ? ";
		
		String actualizar = "UPDATE CITA SET ESTADO = 'revision' WHERE ID_REPORTE = ?";
		try {
			conexionActual.conectar();
			conexionActual.iniciarTransaccion();
			
			conexionActual.prepararSentencia(guardar);	
			conexionActual.agregarAtributo(1, comentarios.getComentariosObjetivo());
			conexionActual.agregarAtributo(2, comentarios.getComentariosDescripcion());
			conexionActual.agregarAtributo(3, comentarios.getComentariosTareas());
			conexionActual.agregarAtributo(4, comentarios.getComentariosActividades());
			
			conexionActual.ejecutarActualizacion();
			
			conexionActual.prepararSentencia(asociar);
			conexionActual.agregarAtributo(1, idReporte);
			
			conexionActual.ejecutarActualizacion();
			
			conexionActual.prepararSentencia(actualizar);
			conexionActual.agregarAtributo(1, idReporte);
			
			conexionActual.ejecutarActualizacion();
			
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
	
	public boolean actualizarComentarios(ComentariosTo comentarios){
		boolean retorno;
		conexionActual = new ConexionOracle();
		
		String actualizar = "UPDATE COMENTARIOS_REPORTE SET COM_OBJETIVO = ?, COM_DESCRIPCION = ?, COM_TAREAS = ?, COM_ACTIVIDADES = ? ";
		actualizar +="WHERE ID_COMENTARIOS = ?";
	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(actualizar);	
			conexionActual.agregarAtributo(1, comentarios.getComentariosObjetivo());
			conexionActual.agregarAtributo(2, comentarios.getComentariosDescripcion());
			conexionActual.agregarAtributo(3, comentarios.getComentariosTareas());
			conexionActual.agregarAtributo(4, comentarios.getComentariosActividades());
			conexionActual.agregarAtributo(5, comentarios.getIdComentarios());
			
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
	
	public ComentariosTo consultarComentarios(Integer idComentarios){
		
		ResultSet rs =null;
		conexionActual = new ConexionOracle();
		ComentariosTo comentarios = new ComentariosTo();
		
		String sql = "SELECT ID_COMENTARIOS,COM_OBJETIVO,COM_DESCRIPCION,COM_TAREAS,COM_ACTIVIDADES ";
		sql+= "FROM COMENTARIOS_REPORTE WHERE ID_COMENTARIOS = ? ";
		
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idComentarios);

			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){
				comentarios.setIdComentarios(idComentarios);
				comentarios.setComentariosObjetivo(rs.getString("COM_OBJETIVO"));
				comentarios.setComentariosDescripcion("COM_DESCRIPCION");
				comentarios.setComentariosTareas("COM_TAREAS");
				comentarios.setComentariosActividades("COM_ACTIVIDADES");
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
		
		return comentarios;
	}
	
	public boolean aceptarReporte(Integer idReporte){
		boolean retorno = false;
		ResultSet rs = null;
		conexionActual = new ConexionOracle();
		
		String buscar = "SELECT ID_TRATAMIENTO FROM CITA WHERE ID_REPORTE = ? ";
		String actualizar = "UPDATE CITA SET ESTADO = 'cerrada' WHERE ID_REPORTE = ? ";
		String desbloquear = "UPDATE TRATAMIENTO SET PENDIENTE = 0 WHERE ID_TRATAMIENTO = ? ";
	
		try {
			conexionActual.conectar();
			conexionActual.iniciarTransaccion();
			
			conexionActual.prepararSentencia(buscar);	
			conexionActual.agregarAtributo(1, idReporte);
			
			rs = conexionActual.ejecutarSentencia();
			Integer idTratamiento = 0;
			
			while(rs.next()){
				idTratamiento = rs.getInt("ID_TRATAMIENTO");
			}
			
			if(idTratamiento > 0){
				conexionActual.prepararSentencia(actualizar);	
				conexionActual.agregarAtributo(1, idReporte);
			
				conexionActual.ejecutarActualizacion();
				
				conexionActual.prepararSentencia(desbloquear);	
				conexionActual.agregarAtributo(1, idTratamiento); 

				conexionActual.ejecutarActualizacion();
				
				conexionActual.commit();
				retorno = Boolean.TRUE;
			}
			else
				retorno = Boolean.FALSE;
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
				rs.close();
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return retorno;
	}
}