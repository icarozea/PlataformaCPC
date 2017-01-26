package com.plataforma.cpc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletReporteSesion
 */
@WebServlet(name="ServletReporteSesion", urlPatterns = {"/ReporteCita"})
public class ServletReporteSesion extends HttpServlet {
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String operacion = request.getParameter("operacion");

		switch (operacion) {

		case "cargueIncial":
			cargueInicial(request, response);
			break;
		case "crearCita":
			break;
		case "guardarCita":
			break;
		case "eliminarCita":
			break;
		case "ejecutarCita":
			break;
		default:
			System.out.println("Opción no existe");
			break;	
		}
	}
	
	public void cargueInicial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			RequestDispatcher dispatcher = request.getRequestDispatcher("reporteCita.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}