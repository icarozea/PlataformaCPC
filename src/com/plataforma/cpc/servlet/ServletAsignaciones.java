package com.plataforma.cpc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletAsignaciones
 */
@WebServlet(name="/ServletAsignaciones", urlPatterns = {"/asignaciones","/ServletAsignaciones"})
public class ServletAsignaciones extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);
	}
	
	public void ResponderPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("asignaciones.jsp");
		
		try{
			request.setAttribute("pNom", request.getParameter("pNom"));
			request.setAttribute("sNom", request.getParameter("sNom"));
			request.setAttribute("pApe", request.getParameter("pApe"));
			request.setAttribute("sApe", request.getParameter("sApe"));
			
			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void CargarAsignados(){
		
	}
	
	public void CargarPosibilidades(){
		
	}
}