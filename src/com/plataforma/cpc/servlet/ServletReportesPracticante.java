package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.dao.DaoSesionIndividual;
import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.ComentariosTo;
import com.plataforma.cpc.to.SesionIndividualPreviewTo;

/**
 * Servlet implementation class ServletReportesEstudiante
 */
@WebServlet("/ReportesPracticante")
public class ServletReportesPracticante extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportesPracticante() {
        super();
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
			case "guardarMoficiacionesReporteSesion":
				guardarMoficiacionesReporteSesion(request, response);
				break;				
			default:
				System.out.println("Opción no existe");
				break;
		}
    	
    }
    
    public void verReportesPreviewPracticante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();
    	
    	try{
    		Integer idPracticante = Integer.parseInt(request.getParameter("idPersona"));
			ArrayList<SesionIndividualPreviewTo> reportesPreview = daoSesionIndividual.consultarListaReportesSesionesPorPracticante(idPracticante);
			request.setAttribute("reportesPreviewPracticante", reportesPreview);
			RequestDispatcher dispatcher = request.getRequestDispatcher("vistaReportesPracticante.jsp");
	    	request.setAttribute("idPracticante", request.getParameter("idPersona"));
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
    
    public void verComentariosDetalladosReporte(HttpServletRequest request, HttpServletResponse response){
    	
    	DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();
    	
    	try{
    		Integer idCita = Integer.parseInt(request.getParameter("idCita"));
    		CitaTo citaSesionReportePracticante = daoSesionIndividual.consultarDetalleComentariosSesionPorIdCita(idCita);
    		ComentariosTo comentarioReportePracticante = citaSesionReportePracticante.getReporte().getComentarios();
    		request.setAttribute("idCita", idCita);
    		request.setAttribute("citaSesionReportePracticante", citaSesionReportePracticante);
    		request.setAttribute("comentarioReportePracticante", comentarioReportePracticante);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("verComentariosReporteDetallado.jsp");
			dispatcher.forward(request, response);
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		
    	}
    }
    
    public void guardarMoficiacionesReporteSesion(HttpServletRequest request, HttpServletResponse response){
    	
    	DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();
    	daoSesionIndividual.actualizarModificacionesReporteSesion(Integer.valueOf(request.getParameter("idReporte")), Integer.valueOf(request.getParameter("numRecibo")),
    															  request.getParameter("estadoReporte"), request.getParameter("campoObjetivo"),
    															  request.getParameter("campoDescripcion"), request.getParameter("campoTareas"),
    															  request.getParameter("campoActividades"));

		try{
			request.setAttribute("mensajeRespuestaActualizacionReporte", "Se ha actualizado la información del reporte correctamente");
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaActualizarReporteCita.jsp");
			dispatcher.forward(request, response);    				
		}catch(Exception e){
			e.getLocalizedMessage();
		}

    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		responderPeticion(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		responderPeticion(request, response);
	}

}
