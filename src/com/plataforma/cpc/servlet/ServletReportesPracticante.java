package com.plataforma.cpc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				opcionesReportes(request, response);
				break;
			case "reportesPendientes":
				visualizarReportesPendientes(request, response);
				System.out.println();
				break;
			case "reportesAprobados":
				visualizarReportesAprobados(request, response);
				System.out.println();
				break;	
			default:
				System.out.println("Opción no existe");
				break;
		}
    	
    }
    
    public void opcionesReportes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	RequestDispatcher dispatcher = request.getRequestDispatcher("vistaReportesPracticante.jsp");
    	request.setAttribute("idPracticante", request.getParameter("idPersona"));
		dispatcher.forward(request, response);
    }
    
    public void visualizarReportesPendientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	//TODO	traer reportes pendientes por practicante
    	RequestDispatcher dispatcher = request.getRequestDispatcher("vistaReportesPendientesPracticante.jsp");
    	request.setAttribute("idPracticante", request.getParameter("idPracticante"));
		dispatcher.forward(request, response);
    }
    
    public void visualizarReportesAprobados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	//TODO	traer reportes aprobados por practicante    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("vistaReportesAprobadosPracticante.jsp");
    	request.setAttribute("idPracticante", request.getParameter("idPracticante"));
		dispatcher.forward(request, response);
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
