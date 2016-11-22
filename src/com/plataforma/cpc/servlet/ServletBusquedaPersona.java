package com.plataforma.cpc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.modelo.PersonaBean;

/**
 * Servlet implementation class ServletBusquedaPersona
 */
@WebServlet(name="/ServletBusquedaPersona", urlPatterns = {"/busquedaPersonas"})
public class ServletBusquedaPersona extends HttpServlet {


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
		RequestDispatcher dispatcher = request.getRequestDispatcher("busquedaPersonas.jsp");
		
		PersonaBean personaBean = new PersonaBean();
		
		try{
			String busqueda = request.getParameter("busqueda");
			
			if(busqueda != null){
				System.out.println("Busqueda, hay parametro: " + busqueda);			
			}
			else
				System.out.println("No hay parámetro");
			
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			System.out.println("Error de formulario: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			dispatcher.forward(request, response);
		}
    }
}