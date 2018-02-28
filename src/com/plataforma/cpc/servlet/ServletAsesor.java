package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.dao.DaoPersona;
import com.plataforma.cpc.dao.DaoSesionIndividual;
import com.plataforma.cpc.modelo.HistoriaClinicaBean;
import com.plataforma.cpc.modelo.PersonaBean;
import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.ComentariosTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.SesionIndividualPreviewTo;
import com.plataforma.cpc.to.SesionIndividualTo;
import com.plataforma.cpc.to.TratamientoTo;

/**
 * Implementacion de Servlet para las peticiones relacionadas con funciones del Asesor
 */
@WebServlet(name="/ServletAsesor", urlPatterns = {"/ServletAsesor"})
public class ServletAsesor extends HttpServlet {

	//------------------------------------------------------------------------------------------------------
	// Implementacion
	//------------------------------------------------------------------------------------------------------

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);
	}

	public void ResponderPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=ISO-8859-1");

		String operacion = request.getParameter("operacion");

		switch (operacion) {

		case "practicantes":
			verPracticantes(request,response);
			break;
		case "reporte":
			verReporte(request,response);
			break;
		case "reporteDetallado":
			verReporteDetallado(request,response);
			break;	
		case "reportesPreview":
			verPreviewReportes(request,response);
		case "comentarios":
			//			verComentarios(request,response);
			break;
		case "guardarComentarios":
			guardarComentarios(request,response);
			break;
		case "actualizarComentarios":
			actualizarComentarios(request,response);
			break;
		case "aceptar":
			aceptarReporte(request,response);
			break;
		case "aceptarValoracion":
			aceptarValoracion(request,response);
			break;
		default:
			System.out.println("Opción no existe");
			break;
		}
	}

	//--------------------------------------------------------------------------------------------------
	// Funciones
	//--------------------------------------------------------------------------------------------------


	/**
	 * Dirige a la vista de practicantes asignados a un asesor particular
	 * Redirige a la pagina verPracticantes.jsp
	 */
	private void verPracticantes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoPersona daoPersona = new DaoPersona();

		try{
			Integer idAsesor = Integer.parseInt(request.getParameter("idAsesor"));
			ArrayList<PersonaTo> practicantes = daoPersona.consultarAsignados(idAsesor);
			request.setAttribute("practicantes", practicantes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("verPracticantes.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			System.out.println("Busqueda fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * Dirige a la vista de evaluacion de un reporteParticular 
	 * Redirige a la pagina verReporteDetallado.jsp
	 */
	private void verReporteDetallado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();

		try{
			Integer idReporte = Integer.parseInt(request.getParameter("idReporte"));
			SesionIndividualTo sesionIndividual = daoSesionIndividual.consultarDetalleSesionPorId(idReporte);
			request.setAttribute("idReporte", idReporte);
			request.setAttribute("idCita", sesionIndividual.getIdCita());
			request.setAttribute("fechaCita", sesionIndividual.getFecha());
			request.setAttribute("nomPaciente", request.getParameter("pNomPaciente")+" "+request.getParameter("sNomPaciente")+" "+request.getParameter("pApePaciente")+" "+request.getParameter("sApePaciente"));
			request.setAttribute("reciboNum", sesionIndividual.getNumRecibo());
			request.setAttribute("profesionalNom", sesionIndividual.getNombreProfesional());
			request.setAttribute("objetivoSesion", sesionIndividual.getObjetivo());
			request.setAttribute("descripcionSesion", sesionIndividual.getDescripcion());
			request.setAttribute("tareasAsignadasSesion",sesionIndividual.getTareasAsignadas());
			request.setAttribute("actividadesProxSesion",sesionIndividual.getActividadesProximaSesion());
			RequestDispatcher dispatcher = request.getRequestDispatcher("verReporteDetallado.jsp");
			dispatcher.forward(request, response);

		}catch(Exception e){
			System.out.println("Busqueda de reporte individual fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * Funcion deprecada
	 */
	private void verReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();		

		try{
			Integer idPracticante = Integer.parseInt(request.getParameter("idPracticante"));
			ArrayList<SesionIndividualTo> reportes = daoSesionIndividual.consultarReporteSesionporPracticante(idPracticante);
			request.setAttribute("reportes", reportes);
			request.setAttribute("idPracticante", idPracticante);

			RequestDispatcher dispatcher = request.getRequestDispatcher("verReportesPracticantes.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			System.out.println("Busqueda fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * Dirige a la vista de reportes de un practicante en específico, mostrando un resumen de los mismos y filtrandolos por paciente y tratamiento
	 * Redirige a la pagina verReportesPracticante.jsp
	 */
	private void verPreviewReportes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();
		PersonaBean personaBean = new PersonaBean();
		HistoriaClinicaBean historiaBean = new HistoriaClinicaBean();

		try{
			// Consigue los reportes del practicante
			Integer idPracticante = Integer.parseInt(request.getParameter("idPracticante"));
			ArrayList<SesionIndividualPreviewTo> valoracionPreview = daoSesionIndividual.consultarListaValoracionesPorPracticante(idPracticante);
			ArrayList<SesionIndividualPreviewTo> reportesPreview = daoSesionIndividual.consultarListaReportesSesionesPorPracticante(idPracticante);
			
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("idPracticante", idPracticante);
			request.setAttribute("pNom", obtenerParametroCodificado(request, "pNom"));
			request.setAttribute("sNom", obtenerParametroCodificado(request, "sNom"));		
			request.setAttribute("pApe", obtenerParametroCodificado(request, "pApe"));		
			request.setAttribute("sApe", obtenerParametroCodificado(request, "sApe"));

			//Consigue los pacientes asignados al practicante
			ArrayList<PersonaTo> listaPacientes = personaBean.consultarAsignados(idPracticante);
			request.setAttribute("listaPacientes", listaPacientes);

			String pacienteActual = request.getParameter("pacienteActual");
			String tratamientoActual = request.getParameter("tratamientoActual");
			String tipoCambio = request.getParameter("tipoCambio");
			int filtroPaciente = 0;
			int filtroTratamiento = 0;			
			ArrayList<TratamientoTo> tratamientos = new ArrayList<TratamientoTo>();

			// Consigue los datos asociados al paciente actual si existe, o al primero encontrado de lo contrario
			if(pacienteActual != null){
				filtroPaciente = Integer.parseInt(pacienteActual);
				filtroTratamiento = Integer.parseInt(tratamientoActual);
				tratamientos = historiaBean.consultarTratamientosxPaciente(filtroPaciente);
			}
			else{
				filtroPaciente = listaPacientes.get(0).getIdPersona();
				tratamientos = historiaBean.consultarTratamientosxPaciente(listaPacientes.get(0).getIdPersona());
				filtroTratamiento = tratamientos.get(0).getIdTratamiento();
				tipoCambio = "vacio";
			}

			request.setAttribute("listaTratamientos", tratamientos);
			request.setAttribute("pacienteActual", pacienteActual);
			request.setAttribute("tratamientoActual", tratamientoActual);

			// Se filtran los reportes dependiendo del tipo de cambio (paciente o tratamiento)

			if(tipoCambio.equals("paciente")){
				filtroTratamiento = tratamientos.get(0).getIdTratamiento();
			}

			// Filtra los reportes de sesion
			ArrayList<SesionIndividualPreviewTo> reportesFiltrados = new ArrayList<SesionIndividualPreviewTo>();
			for(int i = 0; i < reportesPreview.size(); i++){
				SesionIndividualPreviewTo actual = reportesPreview.get(i);
				CitaTo citaTo = new CitaTo();
				citaTo.setIdCita(actual.getIdCita());
				citaTo = historiaBean.consultarCita(citaTo);
				if(citaTo.getTratamiento().getIdTratamiento() == filtroTratamiento && citaTo.getPaciente().getIdPersona() == filtroPaciente){
					reportesFiltrados.add(actual);
				}
			}
			request.setAttribute("reportesPreview", reportesFiltrados);
			
			
			// Filtra los reportes de valoracion
			ArrayList<SesionIndividualPreviewTo> valoracionesFiltrados = new ArrayList<SesionIndividualPreviewTo>();
			for(int i = 0; i < valoracionPreview.size(); i++){
				SesionIndividualPreviewTo actual = valoracionPreview.get(i);
				CitaTo citaTo = new CitaTo();
				citaTo.setIdCita(actual.getIdCita());
				citaTo = historiaBean.consultarCita(citaTo);
				if(citaTo.getTratamiento().getIdTratamiento() == filtroTratamiento && citaTo.getPaciente().getIdPersona() == filtroPaciente){
					valoracionesFiltrados.add(actual);
				}
			}
			request.setAttribute("valoracionPreview", valoracionesFiltrados);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("verReportesPracticantes.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e){
			System.out.println("Busqueda fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);			
		}
	}

	/**
	 * Funcion deprecada
	 */
	private void verComentarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();		

		try{
			Integer idComentarios = Integer.parseInt(request.getParameter("idComentarios"));
			ComentariosTo comentarios = daoSesionIndividual.consultarComentarios(idComentarios);
			request.setAttribute("comentarios", comentarios);
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			System.out.println("Busqueda fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * Permite guardar comentarios a un reporte
	 * Redirige al mismo reporte previo(verReporteDetallado.jsp)
	 */
	private void guardarComentarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoSesionIndividual daoSesion = new DaoSesionIndividual();
		ComentariosTo comentarios = new ComentariosTo();
		String accionAsesor = request.getParameter("accionAsesor");

		try{

			Integer idReporte = Integer.parseInt(request.getParameter("idReporte"));
			request.setCharacterEncoding("UTF-8");
			comentarios.setComentariosObjetivo(obtenerParametroCodificado(request, "campoObjetivo"));			
			comentarios.setComentariosDescripcion(obtenerParametroCodificado(request, "campoDesc"));		
			comentarios.setComentariosTareas(obtenerParametroCodificado(request, "campoTareasAsig"));		
			comentarios.setComentariosActividades(obtenerParametroCodificado(request, "campoActividades"));
			System.out.println("RESULTADO: " + accionAsesor);

			if(daoSesion.guardarComentarios(idReporte, comentarios, accionAsesor)){
				request.setAttribute("respuesta", "1");
				request.setAttribute("mensajeRespuestaReporte", "Los comentarios se han guardado exitosamente");
				RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");
				dispatcher.forward(request, response);
			}	
			else{
				throw new Exception("Error al guardar los comentarios");
			}
		}
		catch(Exception e){
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * Actualiza los comentarios de un reporte particular
	 * Redirige al mismo reporte previo(verReporteDetallado.jsp)
	 */
	private void actualizarComentarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoSesionIndividual daoSesion = new DaoSesionIndividual();
		ComentariosTo comentarios = new ComentariosTo();

		try{
			request.setCharacterEncoding("UTF-8");
			Integer idComentarios = Integer.parseInt(request.getParameter("idComentarios"));
			comentarios.setIdComentarios(idComentarios);
			comentarios.setComentariosObjetivo(obtenerParametroCodificado(request, "comObjetivo"));		
			comentarios.setComentariosDescripcion(obtenerParametroCodificado(request, "comDescripcion"));		
			comentarios.setComentariosTareas(obtenerParametroCodificado(request, "comTareas"));	
			comentarios.setComentariosActividades(obtenerParametroCodificado(request, "comActividades"));

			if(daoSesion.actualizarComentarios(comentarios)){
				System.out.println("Actualizacion exitosa");
				request.setAttribute("respuesta", "1");
				RequestDispatcher dispatcher = request.getRequestDispatcher("");
				dispatcher.forward(request, response);
			}	
			else{
				throw new Exception("Error al actualizar los comentarios");
			}
		}
		catch(Exception e){
			System.out.println("Actualización fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * Actualiza un reporte y lo da por aceptado
	 * Redirige al mismo reporte previo (verReporteDetallado.jsp)
	 */
	private void aceptarReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DaoSesionIndividual daoSesion = new DaoSesionIndividual();

		try{
			Integer idReporte = Integer.parseInt(request.getParameter("idReporte"));
			if(daoSesion.aceptarReporte(idReporte)){
				request.setAttribute("respuesta", "1");
				RequestDispatcher dispatcher = request.getRequestDispatcher("");
				dispatcher.forward(request, response);
			}
		}
		catch(Exception e){
			System.out.println("Operacion fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}
	
	private void aceptarValoracion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DaoSesionIndividual daoSesion = new DaoSesionIndividual();

		try{
			Integer idValoracion = Integer.parseInt(request.getParameter("idValoracion"));
			if(daoSesion.aceptarValoracion(idValoracion)){
				request.setAttribute("respuesta", "1");
				request.setAttribute("mensajeRespuestaReporte", "Reporte actualizado correctamente");
				RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");
				dispatcher.forward(request, response);
			}
		}
		catch(Exception e){
			System.out.println("Operacion fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private String obtenerParametroCodificado(HttpServletRequest request, String valor) throws UnsupportedEncodingException {
		String cadena = request.getParameter(valor);
		cadena = new String(cadena.getBytes(), request.getCharacterEncoding());
		return cadena;
	}
}