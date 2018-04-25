package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.dao.DaoCitas;
import com.plataforma.cpc.dao.DaoSesionIndividual;
import com.plataforma.cpc.dao.DaoUtilidades;
import com.plataforma.cpc.modelo.HistoriaClinicaBean;
import com.plataforma.cpc.modelo.PersonaBean;
import com.plataforma.cpc.modelo.PersonaDetalleBean;
import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.EpsTo;
import com.plataforma.cpc.to.HistoriaClinicaTo;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.PersonaDetalleTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.SesionIndividualTo;
import com.plataforma.cpc.to.TipoDocumentoTo;
import com.plataforma.cpc.to.TratamientoTo;
import com.plataforma.cpc.to.reporteValoracionTo;

/**
 * Servlet implementation class ServletSesionIndividual
 */
@WebServlet("/ServletSesionIndividual")
public class ServletSesionIndividual extends HttpServlet {

	public void ResponderPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");

		String operacion = request.getParameter("operacion");

		switch (operacion) {

		case "crear":
			crearReporte(request,response);
			break;
		case "actualizar":
			actualizarReporte(request, response);
			break;
		case "actualizarValoracion":
			actualizarValoracion(request, response);
			break;
		case "valoracion":
			reporteValoracion(request, response);
			break;
		default:
			System.out.println("Opción no existe");
			break;
		}
	}

	public void crearReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DaoSesionIndividual dao = new DaoSesionIndividual();
		SesionIndividualTo sesion = new SesionIndividualTo();
		request.setCharacterEncoding("UTF-8");
		sesion.setFecha(request.getParameter("fecha") +" "+ request.getParameter("hora"));
		sesion.setNombreProfesional(obtenerParametroCodificado(request, "profesional"));
		String fallo = request.getParameter("fallo");

		if(fallo != null) {
			sesion.setFallo(true);	
			sesion.setObjetivo("");		
			sesion.setTareasAsignadas("");	
			sesion.setActividadesProximaSesion("");
		}
		else {
			sesion.setFallo(false);
			sesion.setNombreProfesional(obtenerParametroCodificado(request, "profesional"));	
			sesion.setObjetivo(obtenerParametroCodificado(request, "objetivoSesion"));		
			sesion.setTareasAsignadas(obtenerParametroCodificado(request, "tareasSesion"));	
			sesion.setActividadesProximaSesion(obtenerParametroCodificado(request, "actividadesProxSesion"));
		}

		sesion.setDescripcion(obtenerParametroCodificado(request, "descripcionSesion"));
		sesion.setNumRecibo(Integer.parseInt(request.getParameter("numeroRecibo")));
		Integer idTratamiento = Integer.parseInt(request.getParameter("idTratamiento"));
		Integer idCita = Integer.parseInt(request.getParameter("idCita"));
		String estado = sesion.isFallo()? "cancelada" : "pendiente";
		boolean resultado = dao.crearReporteSesionIndividual(sesion, idTratamiento, idCita, estado, sesion.isFallo());
		if (resultado) {
			DaoCitas actualizacion = new DaoCitas();
			if(actualizacion.actualizarDiagnostico(idTratamiento, request.getParameter("diagnostico"))){
				String precio = request.getParameter("precio");
				if(actualizacion.actualizarPrecioCita(idCita, Integer.parseInt(precio != null? precio:"0"))) {
					request.setAttribute("mensajeRespuestaReporte", "Se ha creado exitosamente el reporte de la sesión.");
				}
				else
					request.setAttribute("mensajeRespuestaReporte", "Hubo un error al guardar el valor de la cita");
			}
			else
				request.setAttribute("mensajeRespuestaReporte", "Hubo un error al guardar el diagnostico");		
		}
		else
			request.setAttribute("mensajeRespuestaReporte", "Ha ocurrido un error durante la creación del reporte.");

		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");
		dispatcher.forward(request, response);
	}

	public void actualizarReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DaoSesionIndividual dao = new DaoSesionIndividual();
		SesionIndividualTo sesion = new SesionIndividualTo();
		request.setCharacterEncoding("UTF-8");
		sesion.setIdSesion(Integer.parseInt(request.getParameter("idSesion")));
		sesion.setObjetivo(obtenerParametroCodificado(request, "objetivoSesion"));	
		sesion.setDescripcion(obtenerParametroCodificado(request, "descripcionSesion"));	
		sesion.setTareasAsignadas(obtenerParametroCodificado(request, "tareasSesion"));	
		sesion.setActividadesProximaSesion(obtenerParametroCodificado(request, "actividadesProxSesion"));
		boolean resultado = dao.actualizarReporteSesionIndividual(sesion);
		if (resultado) 	
			request.setAttribute("mensajeRespuestaReporte", "Se ha actualizado exitosamente el reporte de la sesión.");
		else
			request.setAttribute("mensajeRespuestaReporte", "Ha ocurrido un error durante la actualizacion del reporte.");

		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");
		dispatcher.forward(request, response);
	}

	public void actualizarValoracion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DaoSesionIndividual dao = new DaoSesionIndividual();
		reporteValoracionTo valoracionTo = new reporteValoracionTo();
		request.setCharacterEncoding("UTF-8");
		valoracionTo.setIdValoracion(Integer.parseInt(request.getParameter("id")));
		valoracionTo.setEncuestador(obtenerParametroCodificado(request, "entrevistador"));
		valoracionTo.setPersonaReporta(obtenerParametroCodificado(request, "persona_reporta"));	
		valoracionTo.setMotivo(obtenerParametroCodificado(request, "motivo_consulta"));
		valoracionTo.setComportamiento(obtenerParametroCodificado(request, "aspectos"));
		valoracionTo.setHipotesis(obtenerParametroCodificado(request, "hipotesis"));
		valoracionTo.setServicioRemitido(obtenerParametroCodificado(request, "remitido"));

		boolean resultado = dao.actualizarReporteValoracion(valoracionTo);
		if (resultado){
			DaoCitas daoCita = new DaoCitas();
			valoracionTo = dao.consultarValoracion(Integer.parseInt(request.getParameter("id")));
			CitaTo cita = new CitaTo();
			cita.setIdCita(valoracionTo.getIdCita());
			cita = daoCita.consultarCita(cita);
			if(daoCita.actualizarDiagnostico(cita.getTratamiento().getIdTratamiento(), obtenerParametroCodificado(request, "diagnostico")))
				request.setAttribute("mensajeRespuestaReporte", "Se ha actualizado exitosamente el reporte.");
			else
				request.setAttribute("mensajeRespuestaReporte", "Ha ocurrido un error actualizando el diagnostico.");
		}
		else
			request.setAttribute("mensajeRespuestaReporte", "Ha ocurrido un error durante la actualizacion del reporte.");

		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");
		dispatcher.forward(request, response);
	}

	public void reporteValoracion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");

		PersonaBean personaBean = new PersonaBean();
		PersonaDetalleBean detalleBean = new PersonaDetalleBean();
		DaoSesionIndividual dao = new DaoSesionIndividual();
		PersonaTo personaTo = new PersonaTo();
		PersonaDetalleTo detalleTo = new PersonaDetalleTo();
		reporteValoracionTo valoracionTo = new reporteValoracionTo();

		try{
			// Actualizacion datos persona
			DaoUtilidades daoUtilidades = new DaoUtilidades();
			Integer idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
			personaTo.setIdPersona(idPaciente);
			personaTo = personaBean.consultarPersona(personaTo);
			request.setCharacterEncoding("UTF-8");
			personaTo.setPrimerNombre(obtenerParametroCodificado(request, "nombre1"));
			personaTo.setSegundoNombre(obtenerParametroCodificado(request, "nombre2"));
			personaTo.setPrimerApellido(obtenerParametroCodificado(request, "apellido1"));
			personaTo.setSegundoApellido(obtenerParametroCodificado(request, "apellido2"));
			String sigla = request.getParameter("tipoDocumento");
			TipoDocumentoTo tipoDocumentoTo = daoUtilidades.buscarTipoDocumento(sigla);
			if(!sigla.equals("-1") || tipoDocumentoTo != null)
				personaTo.setTipoDocumento(tipoDocumentoTo);
			else
				throw new Exception("El tipo de documento no es valido o no se encontró");
			personaTo.setNumeroDocumento(request.getParameter("num_documento"));
			personaTo.setDireccion(request.getParameter("direccion"));
			Integer idEPS = Integer.parseInt(request.getParameter("aseguradora"));
			EpsTo epsTo = daoUtilidades.buscarEps(idEPS);
			PerfilTo perfilTo = new PerfilTo();
			perfilTo.setIdPerfil(4);
			personaTo.setPerfil(perfilTo);
			if(epsTo != null)
				personaTo.setEps(epsTo);
			else
				throw new Exception("La EPS no es válida o no se encontró");

			if(personaBean.modificarPersona(personaTo)){
				// Actualiza datos detalle
				detalleTo.setPersonaId(idPaciente);
				detalleTo.setSexo(request.getParameter("sexo"));
				detalleTo.setEstadoCivil(request.getParameter("estado_civil"));
				detalleTo.setEdad( request.getParameter("edad"));
				detalleTo.setFechaNacimiento(request.getParameter("fecha_nacimiento"));
				detalleTo.setLugarNacimiento(request.getParameter("lugar_nacimiento"));
				detalleTo.setEscolaridad(request.getParameter("escolaridad"));
				detalleTo.setOcupacion(request.getParameter("ocupacion"));
				detalleTo.setLocalidad(request.getParameter("localidad"));
				detalleTo.setBarrio(obtenerParametroCodificado(request, "barrio"));
				detalleTo.setEstrato(request.getParameter("estrato"));
				detalleTo.setPersonaEmergencia(request.getParameter("emergencia"));
				detalleTo.setParentescoEmergencia(obtenerParametroCodificado(request, "parentesco"));
				detalleTo.setTelefonoEmergencia(request.getParameter("telefonos_emergencia"));
				detalleTo.setFormatoSolicitud(request.getParameter("solicitud"));
				detalleTo.setInstitucionRemision(obtenerParametroCodificado(request, "institucion"));
				detalleTo.setAcudiente(obtenerParametroCodificado(request, "acudiente"));
				detalleTo.setParentescoAcudiente(obtenerParametroCodificado(request, "parentesco_acudiente"));
				detalleTo.setTelefonoAcudiente(request.getParameter("telefonos_acudiente"));

				if(detalleBean.modificarPersonaDetalle(detalleTo)){
					//Verificacion de la historia clinica. La crea si no existe
					HistoriaClinicaBean bean = new HistoriaClinicaBean();
					HistoriaClinicaTo historiaClinica = bean.consultarHistoriaClinica(Integer.parseInt(request.getParameter("idPaciente")));
					if(historiaClinica.getCodigo() == null){
						DaoUtilidades utilidades = new DaoUtilidades();
						String codigo = utilidades.consultarConsecutivoHistoria(true);
						if(codigo != null){
							historiaClinica.setIdPaciente(Integer.parseInt(request.getParameter("idPaciente")));
							historiaClinica.setCodigo(codigo);
							if(!bean.crearHistoriaClinica(historiaClinica)){
								throw new Exception("Hubo un error asignar una nueva Historia Clinica");
							}
						}
						else
							throw new Exception("Hubo un error asignar una nueva Historia Clinica");				
					}

					//Creacion del reporte
					valoracionTo.setIdCita(Integer.parseInt(request.getParameter("idCita")));		
					valoracionTo.setMotivo(obtenerParametroCodificado(request, "motivo_consulta"));
					valoracionTo.setPersonaReporta(request.getParameter("persona_reporta"));
					valoracionTo.setComportamiento(obtenerParametroCodificado(request, "aspectos"));		
					valoracionTo.setHipotesis(obtenerParametroCodificado(request, "hipotesis"));

					valoracionTo.setServicioRemitido(obtenerParametroCodificado(request, "remitido"));
					valoracionTo.setEncuestador(obtenerParametroCodificado(request, "entrevistador"));

					if(dao.crearReporteValoracion(valoracionTo, Integer.parseInt(request.getParameter("idTratamiento")), "pendiente")){
						DaoCitas actualizacion = new DaoCitas();
						if(actualizacion.actualizarDiagnostico(Integer.parseInt(request.getParameter("idTratamiento")), request.getParameter("diagnostico"))){
							request.setAttribute("mensajeRespuestaReporte", "Reporte guardado exitosamente");
							dispatcher.forward(request, response);
						}
						else{
							throw new Exception("Hubo un error al guardar el diagnostico");
						}
					}
					else{
						throw new Exception("Hubo un error al intentar guardar el reporte");
					}
				}
				else{
					throw new Exception("Hubo un error al intentar guardar el reporte");
				}
			}
			else{
				throw new Exception("Hubo un error al intentar guardar el reporte");
			}
		}
		catch(Exception e){
			System.out.println("Error de formulario: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("mensajeRespuestaReporte", "Error de formulario: " + e.getMessage());
			dispatcher.forward(request, response);
		}
	}

	private String obtenerParametroCodificado(HttpServletRequest request, String valor) throws UnsupportedEncodingException {
		String cadena = request.getParameter(valor);
		cadena = new String(cadena.getBytes("ISO-8859-1"), request.getCharacterEncoding());
		return cadena;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);	
	}
}