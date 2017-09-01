package com.plataforma.cpc.dao;

import java.sql.Clob;
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
import com.plataforma.cpc.utils.TextAdmin;

/**
 * DAO para las operaciones relacionadas con crear y manejar los reportes de sesion y los de valoracion
 */
public class DaoSesionIndividual extends ConexionOracle{

	//----------------------------------------------------------------------------------------------------------------------
	// Atributos
	//----------------------------------------------------------------------------------------------------------------------
	
	public Conexion conexionActual;  //Interfaz de conexion con la base de datos

	public DaoSesionIndividual(){

	}

	//----------------------------------------------------------------------------------------------------------------------
	// Operaciones
	//----------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Transaccion que crea un nuevo reporte de sesion y actualiza el estado de la cita relacionada
	 * @param sesion Datos del reporte de sesion
	 * @param idTratamiento Tratamiento relacionado a la cita
	 * @param idCita Cita relacionada al reporte
	 * @param estado Estado que se asigna a la cita una vez se complete la creacion
	 * @param fallo Determina si una cita fue una inasistencia o no
	 * @return Verdadero o Falso si el reporte puede crearse efectivamente
	 */
	public boolean crearReporteSesionIndividual(SesionIndividualTo sesion, Integer idTratamiento, Integer idCita, String estado, boolean fallo){
		boolean retorno;
		conexionActual = new ConexionOracle();

		
		String sqlReporte = "INSERT INTO REPORTE_SESION (ID_SESION,ID_CITA,FECHA,NOMBRE_PROFESIONAL,OBJETIVO_SESION,DESCRIPCION_SESION,TAREAS_ASIGNADAS,ACTIVIDADES_PROX_SESION,ES_FALLO,RECIBO) ";
		sqlReporte +="VALUES (REPORTE_SESION_SEQ.NEXTVAL,?,TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI'),?,?,?,?,?,?,?)";

		String sqlEstado = "UPDATE CITA SET ESTADO = ? WHERE ID_CITA = ? ";

		try {
			conexionActual.conectar();
			conexionActual.iniciarTransaccion();

			conexionActual.prepararSentencia(sqlReporte);
			conexionActual.agregarAtributo(1, idCita);
			conexionActual.agregarAtributo(2, sesion.getFecha());
			conexionActual.agregarAtributo(3, sesion.getNombreProfesional());
			
			Clob objetivo = TextAdmin.generarClob(sesion.getObjetivo(), conexionActual);
			conexionActual.agregarAtributo(4, objetivo);
			
			Clob descripcion = TextAdmin.generarClob(sesion.getDescripcion(), conexionActual);
			conexionActual.agregarAtributo(5, descripcion);

			Clob tareas = TextAdmin.generarClob(sesion.getTareasAsignadas(), conexionActual);
			conexionActual.agregarAtributo(6, tareas);
			
			Clob actividades = TextAdmin.generarClob(sesion.getActividadesProximaSesion(), conexionActual);
			conexionActual.agregarAtributo(7, actividades);
			
			conexionActual.agregarAtributo(8,sesion.isFallo()?1:0);
			conexionActual.agregarAtributo(9,sesion.getNumRecibo());
			conexionActual.ejecutarActualizacion();

			conexionActual.prepararSentencia(sqlEstado);
			conexionActual.agregarAtributo(1, estado);
			conexionActual.agregarAtributo(2, idCita); 
			conexionActual.ejecutarActualizacion();

			conexionActual.commit();
			
			objetivo.free();
			descripcion.free();
			tareas.free();
			actividades.free();
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

	/**
	 * Actualiza la información de un reporte de sesión
	 * @param sesion Datos actializados del reporte
	 * @return Verdadero o Falso dependiendo del resultado de la operacion
	 */
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
			Clob objetivo = TextAdmin.generarClob(sesion.getObjetivo(), conexionActual);
			conexionActual.agregarAtributo(1, objetivo);
			
			Clob descripcion = TextAdmin.generarClob(sesion.getDescripcion(), conexionActual);
			conexionActual.agregarAtributo(2, descripcion);

			Clob tareas = TextAdmin.generarClob(sesion.getTareasAsignadas(), conexionActual);
			conexionActual.agregarAtributo(3, tareas);
			
			Clob actividades = TextAdmin.generarClob(sesion.getActividadesProximaSesion(), conexionActual);
			conexionActual.agregarAtributo(4, actividades);
			
			conexionActual.agregarAtributo(5, idSesion);
			conexionActual.ejecutarActualizacion();

			conexionActual.prepararSentencia(sqlEstado);
			conexionActual.agregarAtributo(1, idSesion); 
			conexionActual.ejecutarActualizacion();

			conexionActual.commit();
			
			objetivo.free();
			descripcion.free();
			tareas.free();
			actividades.free();
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

	/**
	 * Crea un nuevo reporte de valoracion y actualiza el estado de la cita asociada
	 * @param valoracionTo Datos del reporte de valoracion
	 * @param idTratamiento Tratamiento asociado a la cita
	 * @param estado Estado que se asigna a la cita una vez se complete la creacion
	 * @return Verdadero o Falso dependiendo del resultado de la operacion
	 */
	public boolean crearReporteValoracion(reporteValoracionTo valoracionTo, Integer idTratamiento, String estado){
		boolean retorno = Boolean.FALSE;
		conexionActual = new ConexionOracle();

		String sqlReporte = "INSERT INTO REPORTE_VALORACION (ID_VALORACION, ID_CITA, MOTIVO, REPORTA, COMPORTAMIENTO, HIPOTESIS, SERVICIO_REMITIDO, ENCUESTADOR) ";
		sqlReporte+= "VALUES(VALORACION_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
		
		String sqlAvance = "UPDATE TRATAMIENTO SET PENDIENTE = 1 WHERE ID_TRATAMIENTO = ? ";

		String sqlEstado = "UPDATE CITA SET ESTADO = ? WHERE ID_CITA = ? ";

		
		try{
			conexionActual.conectar();	
			conexionActual.iniciarTransaccion();

			conexionActual.prepararSentencia(sqlReporte);
			conexionActual.agregarAtributo(1, valoracionTo.getIdCita());
			
			Clob motivo = TextAdmin.generarClob(valoracionTo.getMotivo(), conexionActual);
			conexionActual.agregarAtributo(2, motivo);
			
			conexionActual.agregarAtributo(3, valoracionTo.getPersonaReporta());
			
			Clob comportamiento = TextAdmin.generarClob(valoracionTo.getComportamiento(), conexionActual);
			conexionActual.agregarAtributo(4, comportamiento);
			
			Clob hipotesis = TextAdmin.generarClob(valoracionTo.getHipotesis(), conexionActual);
			conexionActual.agregarAtributo(5, hipotesis);
		
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
			
			motivo.free();
			comportamiento.free();
			hipotesis.free();
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

	/**
	 * Retorna el reporte de sesión de una cita en particular
	 * @param idCita Cita a consultar
	 * @return SesionIndividualTo con los datos del reporte si existe, SesionIndividualTo vacio si el reporte no existe
	 */
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
				
				String objetivo = TextAdmin.getTexto(rs.getClob("OBJETIVO_SESION"));
				sesionIndividual.setObjetivo(objetivo);
				
				String descripcion = TextAdmin.getTexto(rs.getClob("DESCRIPCION_SESION"));
				sesionIndividual.setDescripcion(descripcion);
				
				String tareas = TextAdmin.getTexto(rs.getClob("TAREAS_ASIGNADAS"));
				sesionIndividual.setTareasAsignadas(tareas);
				
				String actividades = TextAdmin.getTexto(rs.getClob("ACTIVIDADES_PROX_SESION"));
				sesionIndividual.setActividadesProximaSesion(actividades);
				
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
	
	/**
	 * Consulta el reporte de valoracion de una cita particualar
	 * @param idCita Cita a consultar
	 * @return ReporteValoracionTo con los datos del reporte si existe, ReporteValoracionTo vacio si el reporte no existe
	 */
	public reporteValoracionTo consultarValoracionporCita(Integer idCita){
		ResultSet rs =null;
		conexionActual = new ConexionOracle();
		reporteValoracionTo valoracion = new reporteValoracionTo();
		String sql = "SELECT RV.ID_VALORACION, RV.MOTIVO, RV.REPORTA, RV.COMPORTAMIENTO, RV.HIPOTESIS, ";
		sql+= "RV.SERVICIO_REMITIDO, RV.ENCUESTADOR ";
		sql+= "FROM REPORTE_VALORACION RV ";
		sql+= "WHERE RV.ID_CITA = ? ";
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idCita);

			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){			
				valoracion.setIdValoracion(rs.getInt("ID_VALORACION"));
				
				String motivo = TextAdmin.getTexto(rs.getClob("MOTIVO"));
				valoracion.setMotivo(motivo);
				
				valoracion.setPersonaReporta(rs.getString("REPORTA"));
				
				String comportamiento = TextAdmin.getTexto(rs.getClob("COMPORTAMIENTO"));
				valoracion.setComportamiento(comportamiento);

				String hipotesis = TextAdmin.getTexto(rs.getClob("HIPOTESIS"));
				valoracion.setHipotesis(hipotesis);

				valoracion.setServicioRemitido(rs.getString("SERVICIO_REMITIDO"));
				valoracion.setEncuestador(rs.getString("ENCUESTADOR"));
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
		return valoracion;
	}

	/**
	 * Consulta todos los reportes de sesión asociados a un practicante en particular
	 * @param idPracticante Practicante a consultar
	 * @return Lista de SesionIndividualPreviewTo con solo los datos basicos de cada reporte, es necesario consultar el reporte individual para obtener todos los datos
	 */
	public ArrayList<SesionIndividualPreviewTo> consultarListaReportesSesionesPorPracticante(Integer idPracticante){
		ResultSet rs =null;
		ArrayList<SesionIndividualPreviewTo> listaSesionesPracticante = new ArrayList<SesionIndividualPreviewTo>();
		conexionActual = new ConexionOracle();

		String sql = "SELECT CT.ID_CITA, CT.SALON, CT.FECHA_CITA, PE.PRIMER_NOMBRE, PE.SEGUNDO_NOMBRE, PE.PRIMER_APELLIDO, PE.SEGUNDO_APELLIDO, CT.ESTADO, CT.ES_VALORACION, CT.NUM_CITA, RS.ID_SESION ";
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
				previewSesion.setNumCita(rs.getInt("NUM_CITA"));
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
	
	/**
	 * Consulta todos los reportes de valoracion asociados a un practicante en particular
	 * @param idPracticante Practicante a consultar
	 * @return Lista de SesionIndividualPreviewTo con solo los datos basicos de cada reporte, es necesario consultar el reporte individual para obtener todos los datos
	 */
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

	/**
	 * Consulta la informacion de un reporte en particualr dado su ID
	 * @param idSesion Reporte a consultar
	 * @return SesionIndividualTo con los datos del reporte. Objeto vacio si no exite
	 */
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
				
				String objetivo = TextAdmin.getTexto(rs.getClob("OBJETIVO_SESION"));
				sesion.setObjetivo(objetivo);
				
				String descripcion = TextAdmin.getTexto(rs.getClob("DESCRIPCION_SESION"));
				sesion.setDescripcion(descripcion);
				
				String tareas = TextAdmin.getTexto(rs.getClob("TAREAS_ASIGNADAS"));
				sesion.setTareasAsignadas(tareas);
				
				String actividades = TextAdmin.getTexto(rs.getClob("ACTIVIDADES_PROX_SESION"));
				sesion.setActividadesProximaSesion(actividades);
				
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

	/**
	 * Consulta los comentario asociados a un reporte de sesion en particular
	 * @param idCita Cita a la que esta asociado el reporte
	 * @return SesionIndividualTo con los datos del reporte, incluyendo los comentarios si existen. Objeto vacio si no exite el reporte
	 */
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
				
				String objetivo = TextAdmin.getTexto(rs.getClob("OBJETIVO_SESION"));
				sesionComentada.setObjetivo(objetivo);
				
				String descripcion = TextAdmin.getTexto(rs.getClob("DESCRIPCION_SESION"));
				sesionComentada.setDescripcion(descripcion);
				
				String tareas = TextAdmin.getTexto(rs.getClob("TAREAS_ASIGNADAS"));
				sesionComentada.setTareasAsignadas(tareas);
				
				String actividades = TextAdmin.getTexto(rs.getClob("ACTIVIDADES_PROX_SESION"));
				sesionComentada.setActividadesProximaSesion(actividades);
				
				String com_objetivo = TextAdmin.getTexto(rs.getClob("COM_OBJETIVO"));
				comentario.setComentariosObjetivo(com_objetivo);
				
				String com_descripcion = TextAdmin.getTexto(rs.getClob("COM_DESCRIPCION"));
				comentario.setComentariosDescripcion(com_descripcion);

				String com_tareas = TextAdmin.getTexto(rs.getClob("COM_TAREAS"));
				comentario.setComentariosTareas(com_tareas);
				
				String com_actividades = TextAdmin.getTexto(rs.getClob("COM_ACTIVIDADES"));
				comentario.setComentariosActividades(com_actividades);
				
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

	/**
	 * Esto es un duplicado de otro metodo. Tiene jerarquia de llamados, por tanto esta pendiente de correcion y depuracion
	 * @param idPracticante
	 * @return
	 */
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
				
				String objetivo = TextAdmin.getTexto(rs.getClob("OBJETIVO_SESION"));
				sesionIndividual.setObjetivo(objetivo);
				
				String descripcion = TextAdmin.getTexto(rs.getClob("DESCRIPCION_SESION"));
				sesionIndividual.setDescripcion(descripcion);
				
				String tareas = TextAdmin.getTexto(rs.getClob("TAREAS_ASIGNADAS"));
				sesionIndividual.setTareasAsignadas(tareas);
				
				String actividades = TextAdmin.getTexto(rs.getClob("ACTIVIDADES_PROX_SESION"));
				sesionIndividual.setActividadesProximaSesion(actividades);
				
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
	
	/**
	 * Guarda la información de comentarios asociados a un reporte particular
	 * @param idReporte Reporte asociado
	 * @param comentarios Informacion de los comentarios
	 * @param accionAsesor Determina si el asesor a aceptado o rechazado el reporte
	 * @return Vewrdadero o Falso dependiendo del exito de la operacion
	 */
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
			
			Clob objetivo = TextAdmin.generarClob(comentarios.getComentariosObjetivo(), conexionActual);
			conexionActual.agregarAtributo(1, objetivo);
			
			Clob descripcion = TextAdmin.generarClob(comentarios.getComentariosDescripcion(), conexionActual);
			conexionActual.agregarAtributo(2, descripcion);
		
			Clob tareas = TextAdmin.generarClob(comentarios.getComentariosTareas(), conexionActual);
			conexionActual.agregarAtributo(3, tareas);
			
			Clob actividades = TextAdmin.generarClob(comentarios.getComentariosActividades(), conexionActual);
			conexionActual.agregarAtributo(4, actividades);

			conexionActual.ejecutarActualizacion();

			conexionActual.prepararSentencia(asociar);
			conexionActual.agregarAtributo(1, idReporte);

			conexionActual.ejecutarActualizacion();

			conexionActual.prepararSentencia(actualizar);
			conexionActual.agregarAtributo(1, idReporte);

			conexionActual.ejecutarActualizacion();

			conexionActual.commit();
			
			objetivo.free();
			descripcion.free();
			tareas.free();
			actividades.free();
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

	/**
	 * Actualiza la informacion de comentarios de un reporte particular
	 * @param comentarios Datos de lso comentarios
	 * @return Verdadero o Falso dependiendo del exito de la operacion
	 */
	public boolean actualizarComentarios(ComentariosTo comentarios){
		boolean retorno;
		conexionActual = new ConexionOracle();

		String actualizar = "UPDATE COMENTARIOS_REPORTE SET COM_OBJETIVO = ?, COM_DESCRIPCION = ?, COM_TAREAS = ?, COM_ACTIVIDADES = ? ";
		actualizar +="WHERE ID_COMENTARIOS = ?";

		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(actualizar);	
			Clob objetivo = TextAdmin.generarClob(comentarios.getComentariosObjetivo(), conexionActual);
			conexionActual.agregarAtributo(1, objetivo);
			
			Clob descripcion = TextAdmin.generarClob(comentarios.getComentariosDescripcion(), conexionActual);
			conexionActual.agregarAtributo(2, descripcion);
		
			Clob tareas = TextAdmin.generarClob(comentarios.getComentariosTareas(), conexionActual);
			conexionActual.agregarAtributo(3, tareas);
			
			Clob actividades = TextAdmin.generarClob(comentarios.getComentariosActividades(), conexionActual);
			conexionActual.agregarAtributo(4, actividades);
			
			conexionActual.agregarAtributo(5, comentarios.getIdComentarios());

			conexionActual.ejecutarActualizacion();
			
			objetivo.free();
			descripcion.free();
			tareas.free();
			actividades.free();
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

	/**
	 * Consulta un conjunto de comentarios dado su identificador
	 * @param idComentarios Identificador de los comentarios
	 * @return ComentariosTo con la infromación recuperada, objeto vacio de lo contario
	 */
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
				
				String objetivo = TextAdmin.getTexto(rs.getClob("COM_OBJETIVO"));
				comentarios.setComentariosObjetivo(objetivo);
				
				String descripcion = TextAdmin.getTexto(rs.getClob("COM_DESCRIPCION"));
				comentarios.setComentariosDescripcion(descripcion);

				String tareas = TextAdmin.getTexto(rs.getClob("COM_TAREAS"));
				comentarios.setComentariosTareas(tareas);
				
				String actividades = TextAdmin.getTexto(rs.getClob("COM_ACTIVIDADES"));
				comentarios.setComentariosActividades(actividades);
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

	/**
	 * Hace las actualizaciones necesarias para dar un reporte como aceptado
	 * @param idReporte Reporte a aceptar
	 * @return Verdadero o Falso segun el exito de la operacion
	 */
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
	
	/**
	 * Actualiza la informacion de un reporte que previamente ha sido rechazado
	 * @param idSesion Identificador del reporte
	 * @param numRecibo Numero de recibo reportado
	 * @param estadoActual estado actual del reporte
	 * @param objetivo Datos del reporte
	 * @param descripcion Datos del reporte
	 * @param tareas Datos del reporte
	 * @param actividades Datos del reporte
	 * @return Verdadero o Falso segun el exito de la operacion
	 */
	public boolean actualizarModificacionesReporteSesion(Integer idSesion, Integer numRecibo, String estadoActual, 
														 String objetivo, String descripcion, String tareas, String actividades){
		conexionActual = new ConexionOracle();
		
		String sql = "UPDATE REPORTE_SESION SET RECIBO = ?, OBJETIVO_SESION = ?, DESCRIPCION_SESION = ?, TAREAS_ASIGNADAS = ?, ACTIVIDADES_PROX_SESION = ? WHERE ID_SESION = ?";
		
		String cita = "SELECT ID_CITA FROM REPORTE_SESION WHERE ID_SESION = ?";
		
		String sqlStatusReporte = "UPDATE CITA SET ESTADO = 'Pendiente' WHERE ID_CITA = ?";
		
		try{
			conexionActual.conectar();
			conexionActual.iniciarTransaccion();
			
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, numRecibo);
			
			Clob clob_objetivo = TextAdmin.generarClob(objetivo, conexionActual);
			conexionActual.agregarAtributo(2, clob_objetivo);
			
			Clob clob_descripcion = TextAdmin.generarClob(descripcion, conexionActual);
			conexionActual.agregarAtributo(3, clob_descripcion);

			Clob clob_tareas = TextAdmin.generarClob(tareas, conexionActual);
			conexionActual.agregarAtributo(4, clob_tareas);
			
			Clob clob_actividades = TextAdmin.generarClob(actividades, conexionActual);
			conexionActual.agregarAtributo(5, clob_actividades);
			
			conexionActual.agregarAtributo(6, idSesion);
			
			conexionActual.ejecutarActualizacion();
			
			conexionActual.prepararSentencia(cita);
			conexionActual.agregarAtributo(1, idSesion);
			ResultSet rs = conexionActual.ejecutarSentencia();
			while(rs.next()) {
				int idCita = rs.getInt("ID_CITA");
				
				if (!estadoActual.equalsIgnoreCase("Aceptado")) {
					conexionActual.prepararSentencia(sqlStatusReporte);
					conexionActual.agregarAtributo(1, idCita);
					
					conexionActual.ejecutarActualizacion();	
				}
			}
			
			conexionActual.commit();
			
			clob_objetivo.free();
			clob_descripcion.free();
			clob_tareas.free();
			clob_actividades.free();
			
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