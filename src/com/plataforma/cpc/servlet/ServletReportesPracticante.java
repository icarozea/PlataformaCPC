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

import com.plataforma.cpc.dao.DaoCitas;
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
import com.plataforma.cpc.to.reporteValoracionTo;

/**
 * Implementacion del servlet para responder a las peticiones de consulta de los reportes hechos por un practicante especifico
 */
@WebServlet("/ReportesPracticante")
public class ServletReportesPracticante extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//------------------------------------------------------------------------------------------------------
	// Implementacion
	//------------------------------------------------------------------------------------------------------  

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletReportesPracticante() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		responderPeticion(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		responderPeticion(request, response);
	}

	private void responderPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		response.setContentType("text/html;charset=ISO-8859-1");

		String operacion = request.getParameter("operacion");

		switch (operacion) {

		case "visualizarReportes":
			verReportesPreviewPracticante(request, response);
			break;
		case "comentariosReporte":
			verComentariosDetalladosReporte(request, response);
			break;
		case "detallesValoracion":
			verDetallesValoracion(request, response);
			break;
		case "guardarMoficiacionesReporteSesion":
			guardarMoficiacionesReporteSesion(request, response);
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
	 * Redirige a la pagina correspondiente con la información resumida de los reportes filtrados que el practicante ha generado
	 * Redirige a vistaReportesPracticante.jsp
	 */
	public void verReportesPreviewPracticante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();
		PersonaBean personaBean = new PersonaBean();
		HistoriaClinicaBean historiaBean = new HistoriaClinicaBean();

		try{
			//Consigue los reportes del practicante
			Integer idPracticante = Integer.parseInt(request.getParameter("idPersona"));
			ArrayList<SesionIndividualPreviewTo> valoracionesPreview = daoSesionIndividual.consultarListaValoracionesPorPracticante(idPracticante);
			ArrayList<SesionIndividualPreviewTo> reportesPreview = daoSesionIndividual.consultarListaReportesSesionesPorPracticante(idPracticante);

			//Consigue los pacientes asignados al practicante
			ArrayList<PersonaTo> listaPacientes = personaBean.consultarAsignados(idPracticante);
			request.setAttribute("listaPacientes", listaPacientes);

			String pacienteActual = request.getParameter("pacienteActual");
			String tratamientoActual = request.getParameter("tratamientoActual");
			String tipoCambio = request.getParameter("tipoCambio");
			String numHistoria = "";
			int filtroPaciente = 0;
			int filtroTratamiento = 0;			
			ArrayList<TratamientoTo> tratamientos = new ArrayList<TratamientoTo>();

			// Consigue los datos asociados al paciente actual si existe, o al primero encontrado de lo contrario
			if(pacienteActual != null){
				filtroPaciente = Integer.parseInt(pacienteActual);
				if(tratamientoActual != null)
					filtroTratamiento = Integer.parseInt(tratamientoActual);
				tratamientos = historiaBean.consultarTratamientosxPaciente(filtroPaciente);
				numHistoria = historiaBean.consultarHistoriaClinica(Integer.parseInt(pacienteActual)).getCodigo();
			}
			else if(listaPacientes.size() > 0){
				filtroPaciente = listaPacientes.get(0).getIdPersona();
				tratamientos = historiaBean.consultarTratamientosxPaciente(listaPacientes.get(0).getIdPersona());
				if(tratamientos.size() > 0)
					filtroTratamiento = tratamientos.get(0).getIdTratamiento();
				numHistoria = historiaBean.consultarHistoriaClinica(listaPacientes.get(0).getIdPersona()).getCodigo();
				tipoCambio = "vacio";
			}
			else {
				tipoCambio = "vacio";
			}

			request.setAttribute("listaTratamientos", tratamientos);
			request.setAttribute("pacienteActual", pacienteActual);
			request.setAttribute("tratamientoActual", tratamientoActual);
			request.setAttribute("codigo", numHistoria);

			// Se filtran los reportes dependiendo del tipo de cambio (paciente o tratamiento)

			if(tipoCambio.equals("paciente")){
				if(tratamientos.size() > 0)
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
			request.setAttribute("reportesPreviewPracticante", reportesFiltrados);

			// Filtra los reportes de valoracion
			ArrayList<SesionIndividualPreviewTo> valoracionesFiltrados = new ArrayList<SesionIndividualPreviewTo>();
			for(int i = 0; i < valoracionesPreview.size(); i++){
				SesionIndividualPreviewTo actual = valoracionesPreview.get(i);
				CitaTo citaTo = new CitaTo();
				citaTo.setIdCita(actual.getIdCita());
				citaTo = historiaBean.consultarCita(citaTo);
				if(citaTo.getTratamiento().getIdTratamiento() == filtroTratamiento && citaTo.getPaciente().getIdPersona() == filtroPaciente){
					valoracionesFiltrados.add(actual);
				}
			}
			request.setAttribute("valoracionesPreviewPracticante", valoracionesFiltrados);

			RequestDispatcher dispatcher = request.getRequestDispatcher("vistaReportesPracticante.jsp");
			request.setAttribute("idPersona", idPracticante);
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
	 * Redirige a la vista de comentarios de un reporte en particular
	 * redirige a verComentariosReporteDetallado.jsp
	 */
	public void verComentariosDetalladosReporte(HttpServletRequest request, HttpServletResponse response){

		DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();
		DaoCitas daoCitas = new DaoCitas();
		DaoPersona daoPersona = new DaoPersona();

		try{
			Integer idCita = Integer.parseInt(request.getParameter("idCita"));
			SesionIndividualTo citaSesionReportePracticante = daoSesionIndividual.consultarDetalleComentariosSesionPorIdCita(idCita);
			ComentariosTo comentarioReportePracticante = citaSesionReportePracticante.getComentarios();
			CitaTo citaTo = new CitaTo();
			citaTo.setIdCita(idCita);
			citaTo = daoCitas.consultarCita(citaTo);
			PersonaTo paciente = daoPersona.consultarPersona(citaTo.getPaciente());
			request.setAttribute("idCita", idCita);
			request.setAttribute("sesionReportePracticante", citaSesionReportePracticante);
			request.setAttribute("comentarioReportePracticante", comentarioReportePracticante);
			request.setAttribute("paciente", paciente);
			request.setAttribute("cita", citaTo);
			RequestDispatcher dispatcher = request.getRequestDispatcher("verComentariosReporteDetallado.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e){

			e.printStackTrace();

		}
	}

	/**
	 * Redirige a la vista de detalle de un reporte de valoracion particular
	 * Redirige a hcDetalleValoracion.jsp  
	 */
	public void verDetallesValoracion(HttpServletRequest request, HttpServletResponse response){

		HistoriaClinicaBean historiaClinica = new HistoriaClinicaBean();
		DaoCitas daoCitas = new DaoCitas();
		Integer idCita = Integer.parseInt(request.getParameter("idCita"));
		try{
			CitaTo citaTo = new CitaTo();
			citaTo.setIdCita(idCita);
			citaTo = daoCitas.consultarCita(citaTo);
			request.setAttribute("cita", citaTo);
			if(citaTo.getEstado().equals("Aceptado"))
				request.setAttribute("aceptado", 1);
			else
				request.setAttribute("aceptado", 0);
			reporteValoracionTo valoracion = historiaClinica.consultarReportesValoracion(idCita);
			if(valoracion != null)
				request.setAttribute("valoracion", valoracion);
			else
				request.setAttribute("valoracion", null); 

			RequestDispatcher dispatcher = request.getRequestDispatcher("hcDetalleValoracion.jsp");
			dispatcher.forward(request, response);	
		}catch(Exception e){		
			e.printStackTrace();  		
		}
	}

	/**
	 * Guarda las modificaciones que un practicante hace a un reporte de sesión particular que no ha sido aceptado
	 * Redirige a la respuesta con el resultado correspondiente de la operación
	 */
	public void guardarMoficiacionesReporteSesion(HttpServletRequest request, HttpServletResponse response){

		DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();	

		try{
			request.setCharacterEncoding("UTF-8");
			daoSesionIndividual.actualizarModificacionesReporteSesion(Integer.valueOf(request.getParameter("idReporte")), Integer.valueOf(request.getParameter("numRecibo")),
					request.getParameter("estadoReporte"), obtenerParametroCodificado(request, "campoObjetivo"),
					obtenerParametroCodificado(request, "campoDescripcion"), obtenerParametroCodificado(request, "campoTareas"),
					obtenerParametroCodificado(request, "campoActividades"));
			request.setAttribute("mensajeRespuestaActualizacionReporte", "Se ha actualizado la información del reporte correctamente");
			RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaActualizarReporteCita.jsp");
			dispatcher.forward(request, response);    				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private String obtenerParametroCodificado(HttpServletRequest request, String valor) throws UnsupportedEncodingException {
		String cadena = request.getParameter(valor);
		cadena = new String(cadena.getBytes(), request.getCharacterEncoding());
		return cadena;
	}
}