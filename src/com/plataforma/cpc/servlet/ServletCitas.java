package com.plataforma.cpc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCitas
 */
@WebServlet(name="/ServletCitas", urlPatterns = {"/Calendario"})
public class ServletCitas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ResponderPeticion(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ResponderPeticion(request, response);
	}
    
    public void ResponderPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	System.out.println("Llego el evento: " + request.getParameter("fecha"));
    }

}
