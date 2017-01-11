package com.plataforma.cpc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCalendario
 */
@WebServlet(name="/ServletCalendario", urlPatterns = {"/Calendario"})
public class ServletCalendario extends HttpServlet {

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
		//request.setAttribute("idPracticante", request.getParameter("id"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("calendario.jsp");
		
		dispatcher.forward(request, response);
	}
}
