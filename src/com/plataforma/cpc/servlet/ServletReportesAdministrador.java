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
@WebServlet("/ReportesAdministrador")
public class ServletReportesAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportesAdministrador() {
        super();
    }
    
    private void responderPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	response.setContentType("text/html;charset=ISO-8859-1");

		String operacion = request.getParameter("operacion");
		
		switch (operacion) {
			
			case "visualizarReportes":
				verReportesPreviewPracticante(request, response);
				break;			
			default:
				System.out.println("Opción no existe");
				break;
		}
    	
    }
    
    public void verReportesPreviewPracticante(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  	
    	
    	try{
			RequestDispatcher dispatcher = request.getRequestDispatcher("vistaReportesAdministrador.jsp");
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
