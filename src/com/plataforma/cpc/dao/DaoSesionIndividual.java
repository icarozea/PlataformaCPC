package com.plataforma.cpc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.ComentariosTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.SesionIndividualPreviewTo;
import com.plataforma.cpc.to.SesionIndividualTo;
import com.plataforma.cpc.to.reporteValoracionTo;
import com.plataforma.cpc.utils.ConexionOracle;

public class DaoSesionIndividual extends ConexionOracle{

	public Conexion conexionActual;

	public DaoSesionIndividual(){

	}

	public boolean crearReporteSesionIndividual(SesionIndividualTo sesion, Integer idTratamiento, Integer idCita, String estado, boolean fallo){
		boolean retorno;
		conexionActual = new ConexionOracle();

		String sqlReporte = "INSERT INTO REPORTE_SESION (ID_SESION,ID_CITA,FECHA,NOMBRE_PROFESIONAL,OBJETIVO_SESION,DESCRIPCION_SESION,TAREAS_ASIGNADAS,ACTIVIDADES_PROX_SESION,ES_FALLO,RECIBO) ";
		sqlReporte +="VALUES (REPORTE_SESION_SEQ.NEXTVAL,?,TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI'),?,?,?,?,?,?,?)";

		String sqlAvance = "UPDATE TRATAMIENTO SET NUM_CITA_ACTUAL = NUM_CITA_ACTUAL + 1, PENDIENTE = 1 WHERE ID_TRATAMIENTO = ? ";

		String sqlEstado = "UPDATE CITA SET ESTADO = ? WHERE ID_CITA = ? ";

		try {
			conexionActual.conectar();
			conexionActual.iniciarTransaccion();

			conexionActual.prepararSentencia(sqlReporte);
			conexionActual.agregarAtributo(1, idCita);
			conexionActual.agregarAtributo(2, sesion.getFecha());
			conexionActual.agregarAtributo(3, sesion.getNombreProfesional());
			conexionActual.agregarAtributo(4, sesion.getObjetivo());
			conexionActual.agregarAtributo(5, sesion.getDescripcion());
			conexionActual.agregarAtributo(6, sesion.getTareasAsignadas());
			conexionActual.agregarAtributo(7, sesion.getActividadesProximaSesion());
			conexionActual.agregarAtributo(8,sesion.isFallo()?1:0);
			conexionActual.agregarAtributo(9,sesion.getNumRecibo());
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

		String sqlEstado = "UPDATE CITA SET ESTADO = 'pendiente' WHERE ID_CITA = (SELECT ID_CITA FROM REPORTE_SESION WHERE ID_SESION = ?) ";

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

	public boolean crearReporteValoracion(reporteValoracionTo valoracionTo, Integer idTratamiento, String estado){
		boolean retorno = Boolean.FALSE;
		conexionActual = new ConexionOracle();

		String sqlReporte = "INSERT INTO REPORTE_VALORACION (ID_VALORACION, ID_CITA, MOTIVO, REPORTA, COMPORTAMIENTO, HIPOTESIS, SERVICIO_REMITIDO, ENCUESTADOR) ";
		sqlReporte+= "VALUES(VALORACION_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
		
		String sqlAvance = "UPDATE TRATAMIENTO SET NUM_CITA_ACTUAL = NUM_CITA_ACTUAL + 1, PENDIENTE = 1 WHERE ID_TRATAMIENTO = ? ";

		String sqlEstado = "UPDATE CITA SET ESTADO = ? WHERE ID_CITA = ? ";

		try{
			conexionActual.conectar();
			conexionActual.iniciarTransaccion();

			conexionActual.prepararSentencia(sqlReporte);
			conexionActual.agregarAtributo(1, valoracionTo.getIdCita());
			conexionActual.agregarAtributo(2, valoracionTo.getMotivo());
			conexionActual.agregarAtributo(3, valoracionTo.getPersonaReporta());
			conexionActual.agregarAtributo(4, valoracionTo.getComportamiento());
			conexionActual.agregarAtributo(5, valoracionTo.getHipotesis());
			conexionActual.agregarAtributo(6, valoracionTo.getServicioRemitido());
			conexionActual.agregarAtributo(7, valoracionTo.getEncuestador());
			conexionActual.ejecutarActualizacion();
			
			conexionActual.prepararSentencia(sqlEstado);
			conexionActual.agregarAtributo(1, estado);
			conexionActual.agregarAtributo(2, valoracionTo.getIdCita()); 
			conexionActual.ejecutarActualizacion();

			conexionActual.prepararSentencia(sqlAvance);
			conexionActual.agregarAtributo(1, idTratamiento);
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
		sql+= "WHERE RS.ID_CITA = ? ";
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
				sesionIndividual.setIdCita(idCita);
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

	public ArrayList<SesionIndividualPreviewTo> consultarListaReportesSesionesPorPracticante(Integer idPracticante){
		ResultSet rs =null;
		ArrayList<SesionIndividualPreviewTo> listaSesionesPracticante = new ArrayList<SesionIndividualPreviewTo>();
		conexionActual = new ConexionOracle();

		String sql = "SELECT CT.ID_CITA, CT.SALON, CT.FECHA_CITA, PE.PRIMER_NOMBRE, PE.SEGUNDO_NOMBRE, PE.PRIMER_APELLIDO, PE.SEGUNDO_APELLIDO, CT.ESTADO, CT.ES_VALORACION, RS.ID_SESION ";
		sql+= "FROM CITA CT, PERSONA PE, REPORTE_SESION RS WHERE RS.ID_CITA = CT.ID_CITA AND CT.ID_PACIENTE = PE.ID_PERSONA AND CT.ID_PRACTICANTE = ? ORDER BY CT.ESTADO DESC, CT.FECHA_CITA DESC";

		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idPracticante);

			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){

				SesionIndividualPreviewTo previewSesion = new SesionIndividualPreviewTo(); 
				previewSesion.setIdCita(rs.getInt("ID_CITA"));
				previewSesion.setSalon(rs.getString("SALON"));
				previewSesion.setFecha(rs.getString("FECHA_CITA"));
				previewSesion.setPrimerNombrePaciente(rs.getString("PRIMER_NOMBRE"));
				previewSesion.setSegundoNombrePaciente(rs.getString("SEGUNDO_NOMBRE"));
				previewSesion.setPrimerApellidoPaciente(rs.getString("PRIMER_APELLIDO"));
				previewSesion.setSegundoApellidoPaciente(rs.getString("SEGUNDO_APELLIDO"));
				previewSesion.setEstado(rs.getString("ESTADO"));
				previewSesion.setIdReporte(rs.getInt("ID_SESION") + "");
				listaSesionesPracticante.add(previewSesion);
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
		return listaSesionesPracticante;
	}
	
	public ArrayList<SesionIndividualPreviewTo> consultarListaValoracionesPorPracticante(Integer idPracticante){
		ResultSet rs =null;
		ArrayList<SesionIndividualPreviewTo> listaSesionesPracticante = new ArrayList<SesionIndividualPreviewTo>();
		conexionActual = new ConexionOracle();

		String sql = "SELECT CT.ID_CITA, CT.SALON, CT.FECHA_CITA, PE.PRIMER_NOMBRE, PE.SEGUNDO_NOMBRE, PE.PRIMER_APELLIDO, PE.SEGUNDO_APELLIDO, CT.ESTADO, CT.ES_VALORACION, RV.ID_VALORACION ";
		sql+= "FROM CITA CT, PERSONA PE, REPORTE_VALORACION RV WHERE RV.ID_CITA = CT.ID_CITA AND CT.ID_PACIENTE = PE.ID_PERSONA AND CT.ID_PRACTICANTE = ? ORDER BY CT.ESTADO DESC, CT.FECHA_CITA DESC";

		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idPracticante);

			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){

				SesionIndividualPreviewTo previewSesion = new SesionIndividualPreviewTo(); 
				previewSesion.setIdCita(rs.getInt("ID_CITA"));
				previewSesion.setSalon(rs.getString("SALON"));
				previewSesion.setFecha(rs.getString("FECHA_CITA"));
				previewSesion.setPrimerNombrePaciente(rs.getString("PRIMER_NOMBRE"));
				previewSesion.setSegundoNombrePaciente(rs.getString("SEGUNDO_NOMBRE"));
				previewSesion.setPrimerApellidoPaciente(rs.getString("PRIMER_APELLIDO"));
				previewSesion.setSegundoApellidoPaciente(rs.getString("SEGUNDO_APELLIDO"));
				previewSesion.setEstado(rs.getString("ESTADO"));
				previewSesion.setIdReporte(rs.getInt("ID_VALORACION") + "");
				listaSesionesPracticante.add(previewSesion);
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
		return listaSesionesPracticante;
	}

	public SesionIndividualTo consultarDetalleSesionPorId(Integer idSesion){
		ResultSet rs =null;
		conexionActual = new ConexionOracle();
		String sql = "SELECT CT.ID_CITA, RS.ID_SESION, RS.FECHA, CT.NUM_CITA, RS.RECIBO, RS.NOMBRE_PROFESIONAL, RS.OBJETIVO_SESION, RS.DESCRIPCION_SESION, RS.TAREAS_ASIGNADAS, RS.ACTIVIDADES_PROX_SESION, RS.ES_FALLO FROM CITA CT INNER JOIN REPORTE_SESION RS ON CT.ID_CITA = RS.ID_CITA WHERE RS.ID_SESION = ?";

		SesionIndividualTo sesion = new SesionIndividualTo();
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idSesion);
			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){
				sesion.setIdSesion(rs.getInt("ID_SESION"));
				sesion.setIdCita(rs.getInt("ID_CITA"));
				sesion.setFecha(rs.getString("FECHA"));
				sesion.setNombreProfesional(rs.getString("NOMBRE_PROFESIONAL"));
				sesion.setNumRecibo(Integer.parseInt(rs.getString("RECIBO")));
				sesion.setObjetivo(rs.getString("OBJETIVO_SESION"));
				sesion.setDescripcion(rs.getString("DESCRIPCION_SESION"));
				sesion.setTareasAsignadas(rs.getString("TAREAS_ASIGNADAS"));
				sesion.setActividadesProximaSesion(rs.getString("ACTIVIDADES_PROX_SESION"));
				boolean fallo;
				if (rs.getInt("ES_FALLO")==1) {
					fallo=true;
				}else{
					fallo = false;
				}
				sesion.setFallo(fallo);
				sesion.setNumRecibo(rs.getInt("RECIBO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("falla en busqueda");
			e.getLocalizedMessage();
		}

		return sesion;
	}

	public SesionIndividualTo consultarDetalleComentariosSesionPorIdCita(Integer idCita){

		CitaTo cita = new CitaTo();
		PersonaTo paciente;
		SesionIndividualTo sesionComentada = new SesionIndividualTo();
		ComentariosTo comentario;

		ResultSet rs =null;
		conexionActual = new ConexionOracle();

		String sql = "SELECT CT.ESTADO, CT.NUM_CITA, RS.ID_SESION, RS.ID_CITA, RS.FECHA, RS.NOMBRE_PROFESIONAL, PE.PRIMER_NOMBRE, PE.SEGUNDO_NOMBRE, PE.PRIMER_APELLIDO, PE.SEGUNDO_APELLIDO, ";
			  sql += "(SELECT PEASE.PRIMER_NOMBRE FROM PERSONA PEASE WHERE PEASE.ID_PERSONA = (SELECT PS.PERSONA_ID_SUPERIOR FROM PERSONA PS WHERE PS.ID_PERSONA = PE.PERSONA_ID_SUPERIOR)) AS P_NOM_ASESOR,";
			  sql += "(SELECT PEASE.SEGUNDO_NOMBRE FROM PERSONA PEASE WHERE PEASE.ID_PERSONA = (SELECT PS.PERSONA_ID_SUPERIOR FROM PERSONA PS WHERE PS.ID_PERSONA = PE.PERSONA_ID_SUPERIOR)) AS S_NOM_ASESOR,";
			  sql += "(SELECT PEASE.PRIMER_APELLIDO FROM PERSONA PEASE WHERE PEASE.ID_PERSONA = (SELECT PS.PERSONA_ID_SUPERIOR FROM PERSONA PS WHERE PS.ID_PERSONA = PE.PERSONA_ID_SUPERIOR)) AS P_APE_ASESOR,";
			  sql += "(SELECT PEASE.SEGUNDO_APELLIDO FROM PERSONA PEASE WHERE PEASE.ID_PERSONA = (SELECT PS.PERSONA_ID_SUPERIOR FROM PERSONA PS WHERE PS.ID_PERSONA = PE.PERSONA_ID_SUPERIOR)) AS S_APE_ASESOR,";
			  sql += "RS.OBJETIVO_SESION, CR.COM_OBJETIVO, RS.DESCRIPCION_SESION, CR.COM_DESCRIPCION, RS.TAREAS_ASIGNADAS, CR.COM_TAREAS, RS.ACTIVIDADES_PROX_SESION, ";
			  sql += "CR.COM_ACTIVIDADES, RS.RECIBO, RS.ES_FALLO FROM CITA CT, PERSONA PE, REPORTE_SESION RS, COMENTARIOS_REPORTE CR ";
			  sql += "WHERE RS.ID_CITA = ? AND RS.ID_COMENTARIOS=CR.ID_COMENTARIOS AND CT.ID_PACIENTE=PE.ID_PERSONA AND CT.ID_CITA = ?";
			  
		try{
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idCita);
			conexionActual.agregarAtributo(2, idCita);
			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){
				paciente = new PersonaTo();
				comentario = new ComentariosTo();

				cita.setIdCita(rs.getInt("ID_CITA"));
				cita.setNumCita(rs.getInt("NUM_CITA"));
				cita.setEstado(rs.getString("ESTADO"));

				paciente.setPrimerNombre(rs.getString("PRIMER_NOMBRE"));
				paciente.setSegundoNombre(rs.getString("SEGUNDO_NOMBRE"));
				paciente.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
				paciente.setSegundoNombre(rs.getString("SEGUNDO_APELLIDO"));

				sesionComentada.setIdSesion(rs.getInt("ID_SESION"));
				sesionComentada.setIdCita(rs.getInt("ID_CITA"));
				sesionComentada.setFecha(rs.getString("FECHA"));
				sesionComentada.setNombreProfesional(rs.getString("NOMBRE_PROFESIONAL"));
				sesionComentada.setNombreAsesorProfesional(rs.getString("P_NOM_ASESOR") + " " + rs.getString("S_NOM_ASESOR") + " " + rs.getString("P_APE_ASESOR") + " " + rs.getString("S_APE_ASESOR"));
				sesionComentada.setObjetivo(rs.getString("OBJETIVO_SESION"));
				comentario.setComentariosObjetivo(rs.getString("COM_OBJETIVO"));
				sesionComentada.setDescripcion(rs.getString("DESCRIPCION_SESION"));
				comentario.setComentariosDescripcion(rs.getString("COM_DESCRIPCION"));
				sesionComentada.setTareasAsignadas(rs.getString("TAREAS_ASIGNADAS"));
				comentario.setComentariosTareas(rs.getString("COM_TAREAS"));
				sesionComentada.setActividadesProximaSesion(rs.getString("ACTIVIDADES_PROX_SESION"));
				comentario.setComentariosActividades(rs.getString("COM_ACTIVIDADES"));
				sesionComentada.setNumRecibo(rs.getInt("RECIBO"));
				sesionComentada.setComentarios(comentario);
				boolean fallo;
				if (rs.getInt("ES_FALLO")==1) {
					fallo=true;
				}else{
					fallo = false;
				}
				sesionComentada.setFallo(fallo);
				cita.setPaciente(paciente);
			}

		}catch(Exception e){
			e.printStackTrace();
			e.getLocalizedMessage();
		}

		return sesionComentada;
	}

	public ArrayList<SesionIndividualTo> consultarReporteSesionporPracticante(Integer idPracticante){
		ResultSet rs =null;
		ArrayList<SesionIndividualTo> reportes = new ArrayList<SesionIndividualTo>();
		conexionActual = new ConexionOracle();

		String sql = "SELECT RS.ID_SESION, RS.FECHA, RS.NOMBRE_PROFESIONAL, RS.OBJETIVO_SESION, RS.DESCRIPCION_SESION,";
		sql+= "RS.TAREAS_ASIGNADAS, RS.ACTIVIDADES_PROX_SESION, RS.ES_FALLO, RS.RECIBO, RS.ID_COMENTARIOS ";
		sql+= "FROM REPORTE_SESION RS INNER JOIN CITA CITA ";
		sql+= "ON CITA.ID_PRACTICANTE = ? AND CITA.ID_CITA = RS.ID_CITA ORDER BY CITA.ESTADO DESC, RS.FECHA DESC ";
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
	
	public boolean guardarComentarios(Integer idReporte, ComentariosTo comentarios, String accionAsesor){

		boolean retorno;
		conexionActual = new ConexionOracle();

		String guardar = "INSERT INTO COMENTARIOS_REPORTE (ID_COMENTARIOS,COM_OBJETIVO,COM_DESCRIPCION,COM_TAREAS,COM_ACTIVIDADES) ";
		guardar +="VALUES (COMENTARIOS_REPORTE_SEQ.NEXTVAL,?,?,?,?)";

		String asociar = "UPDATE REPORTE_SESION SET ID_COMENTARIOS = COMENTARIOS_REPORTE_SEQ.CURRVAL WHERE ID_SESION = ? ";

		String actualizar = "UPDATE CITA SET ESTADO = 'revision' WHERE ID_CITA = (SELECT ID_CITA FROM REPORTE_SESION WHERE ID_SESION = ?)";

		
		if (accionAsesor.equals("Aceptado")) {
			actualizar = "UPDATE CITA SET ESTADO = 'Aceptado' WHERE ID_CITA = (SELECT ID_CITA FROM REPORTE_SESION WHERE ID_SESION = ?)";
		}else if (accionAsesor.equals("Rechazado")) {
			actualizar = "UPDATE CITA SET ESTADO = 'Rechazado' WHERE ID_CITA = (SELECT ID_CITA FROM REPORTE_SESION WHERE ID_SESION = ?)";
		}
		

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

		String buscar = "SELECT CT.ID_TRATAMIENTO FROM CITA CT, REPORTE_SESION RS WHERE RS.ID_SESION = ? AND CT.ID_CITA = RS.ID_CITA ";
		String actualizar = "UPDATE CITA SET ESTADO = 'cerrada' WHERE ID_CITA = (SELECT ID_CITA FROM REPORTE_SESION WHERE ID_SESION = ?) ";
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
	
	public boolean actualizarModificacionesReporteSesion(Integer idSesion, Integer numRecibo, String estadoActual, 
														 String objetivo, String descripcion, String tareas, String actividades){
		conexionActual = new ConexionOracle();
		
		String sql = "UPDATE REPORTE_SESION SET RECIBO = ?, OBJETIVO_SESION = ?, DESCRIPCION_SESION = ?, TAREAS_ASIGNADAS = ?, ACTIVIDADES_PROX_SESION = ? WHERE ID_SESION = ?";
		
		String sqlStatusReporte = "UPDATE CITA SET ESTADO = 'Pendiente' WHERE ID_REPORTE = ?";
		
		try{
			conexionActual.conectar();
			conexionActual.iniciarTransaccion();
			
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, numRecibo);
			conexionActual.agregarAtributo(2, objetivo);
			conexionActual.agregarAtributo(3, descripcion);
			conexionActual.agregarAtributo(4, tareas);
			conexionActual.agregarAtributo(5, actividades);
			conexionActual.agregarAtributo(6, idSesion);
			
			conexionActual.ejecutarActualizacion();
			
			if (!estadoActual.equalsIgnoreCase("Aceptado")) {
				conexionActual.prepararSentencia(sqlStatusReporte);
				conexionActual.agregarAtributo(1, idSesion);
				
				conexionActual.ejecutarActualizacion();	
			}
			
			
			conexionActual.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return true;
		
	}
	
}