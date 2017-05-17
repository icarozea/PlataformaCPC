package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.plataforma.cpc.dao.DaoSesionIndividual;
import com.plataforma.cpc.dao.DaoUtilidades;
import com.plataforma.cpc.modelo.PersonaBean;
import com.plataforma.cpc.modelo.PersonaDetalleBean;
import com.plataforma.cpc.to.EpsTo;
import com.plataforma.cpc.to.PersonaDetalleTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.SesionIndividualTo;
import com.plataforma.cpc.to.TipoDocumentoTo;
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
		sesion.setFecha(request.getParameter("fecha") +" "+ request.getParameter("hora"));
		sesion.setNombreProfesional(request.getParameter("profesional"));
		sesion.setObjetivo(request.getParameter("objetivoSesion"));
		sesion.setDescripcion(request.getParameter("descripcionSesion"));
		sesion.setTareasAsignadas(request.getParameter("tareasSesion"));
		sesion.setActividadesProximaSesion(request.getParameter("actividadesProxSesion"));
		String fallo = request.getParameter("fallo");
		sesion.setFallo(fallo != null?true:false);
		sesion.setNumRecibo(Integer.parseInt(request.getParameter("numeroRecibo")));
		Integer idTratamiento = Integer.parseInt(request.getParameter("idTratamiento"));
		Integer idCita = Integer.parseInt(request.getParameter("idCita"));
		String estado = sesion.isFallo()? "cancelada" : "pendiente";
		boolean resultado = dao.crearReporteSesionIndividual(sesion, idTratamiento, idCita, estado, sesion.isFallo());
		if (resultado) 	
			request.setAttribute("mensajeRespuestaReporte", "Se ha creado exitosamente el reporte de la sesión.");
		else
			request.setAttribute("mensajeRespuestaReporte", "Ha ocurrido un error durante la creación del reporte.");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");
		dispatcher.forward(request, response);
	}
	
	public void actualizarReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DaoSesionIndividual dao = new DaoSesionIndividual();
		SesionIndividualTo sesion = new SesionIndividualTo();
		sesion.setIdSesion(Integer.parseInt(request.getParameter("idSesion")));
		sesion.setObjetivo(request.getParameter("objetivoSesion"));
		sesion.setDescripcion(request.getParameter("descripcionSesion"));
		sesion.setTareasAsignadas(request.getParameter("tareasSesion"));
		sesion.setActividadesProximaSesion(request.getParameter("actividadesProxSesion"));
		boolean resultado = dao.actualizarReporteSesionIndividual(sesion);
		if (resultado) 	
			request.setAttribute("mensajeRespuestaReporte", "Se ha actualizado exitosamente el reporte de la sesión.");
		else
			request.setAttribute("mensajeRespuestaReporte", "Ha ocurrido un error durante la actualizacion del reporte.");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");
		dispatcher.forward(request, response);
	}
	
	public void reporteValoracion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaAgregarPersona.jsp");
		
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
			personaTo.setPrimerNombre(request.getParameter("nombre1"));
			personaTo.setSegundoNombre(request.getParameter("nombre2"));
			personaTo.setPrimerApellido(request.getParameter("apellido1"));
			personaTo.setSegundoApellido(request.getParameter("apellido2"));
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
				detalleTo.setBarrio(request.getParameter("barrio"));
				detalleTo.setEstrato(request.getParameter("estrato"));
				detalleTo.setPersonaEmergencia(request.getParameter("emergencia"));
				detalleTo.setParentescoEmergencia(request.getParameter("parentesco"));
				detalleTo.setTelefonoEmergencia(request.getParameter("telefonos_emergencia"));
				detalleTo.setFormatoSolicitud(request.getParameter("solicitud"));
				detalleTo.setInstitucionRemision(request.getParameter("institucion"));
				detalleTo.setAcudiente(request.getParameter("acudiente"));
				detalleTo.setParentescoAcudiente(request.getParameter("parentesco_acudiente"));
				detalleTo.setTelefonoAcudiente(request.getParameter("telefonos_acudiente"));
				
				if(detalleBean.modificarPersonaDetalle(detalleTo)){
					//Creacion del reporte
					valoracionTo.setIdCita(Integer.parseInt(request.getParameter("idCita")));
					valoracionTo.setMotivo(request.getParameter("motivo_consulta"));
					valoracionTo.setPersonaReporta(request.getParameter("persona_reporta"));
					valoracionTo.setComportamiento(request.getParameter("aspectos"));
					valoracionTo.setHipotesis(request.getParameter("hipotesis"));
					valoracionTo.setServicioRemitido(request.getParameter("remitido"));
					
					if(dao.crearReporteValoracion(valoracionTo, Integer.parseInt(request.getParameter("idTratamiento")), "pendiente")){
						request.setAttribute("respuesta", "1");
						request.setAttribute("error", "");
						dispatcher.forward(request, response);
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
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			dispatcher.forward(request, response);
		}
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