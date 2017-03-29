package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.modelo.PersonaBean;
import com.plataforma.cpc.to.PersonaTo;

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

		try{
			String busqueda = request.getParameter("busqueda");
			String jornada = request.getParameter("jornada");

			if(busqueda == null)
				busqueda = "Practicante";

			if(jornada == null)
				jornada = "manana";

			request.setAttribute("valor", busqueda);
			request.setAttribute("jornada", jornada);
			request.setAttribute("listaPersonas", RealizarBusqueda(busqueda, jornada));

			dispatcher.forward(request, response);
		}
		catch(Exception e){
			System.out.println("Error de Busqueda: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("valor", "Practicante");
			dispatcher.forward(request, response);
		}
	}

	public ArrayList<PersonaTo> RealizarBusqueda(String perfil, String jornada){
//		System.out.println("Jornada halada: " + jornada);
		PersonaBean persona = new PersonaBean();
		ArrayList<PersonaTo> resultados = new ArrayList<PersonaTo>();
		ArrayList<PersonaTo> retorno = new ArrayList<PersonaTo>();
		boolean buscarJornada = false;

		switch(perfil){
		case "Practicante":
			resultados = persona.consultarPracticantes();
			buscarJornada = true;
			break;
		case "Supervisor":
			resultados = persona.consultarSupervisores();
			break;
		case "Paciente":
			resultados = persona.consultarPacientes();
			buscarJornada = true;
			break;
		case "Administrador":
			resultados = persona.consultarAdministradores();
			break;	
		}

		if(buscarJornada){
			for(int i = 0; i < resultados.size(); i++){
				PersonaTo actual = resultados.get(i);
				if(actual.getJornada().equals(jornada))
					retorno.add(actual);
			}

			return retorno;
		}
		
		return resultados;
	}
}