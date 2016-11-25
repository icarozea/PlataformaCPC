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

			if(busqueda == null)
				busqueda = "Practicante";

			request.setAttribute("valor", busqueda);

			request.setAttribute("listaPersonas", RealizarBusqueda(busqueda));

			dispatcher.forward(request, response);
		}
		catch(Exception e){
			System.out.println("Error de Busqueda: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("valor", "Practicante");
			dispatcher.forward(request, response);
		}
	}

	public ArrayList<PersonaTo> RealizarBusqueda(String perfil){
		PersonaBean persona = new PersonaBean();
		ArrayList<PersonaTo> resultados = new ArrayList<PersonaTo>();

//		if(perfil.equals("Practicante")){
//			PersonaTo pedro = new PersonaTo();
//			pedro.setPrimerNombre("Pedro");
//			//pedro.setSegundoNombre("Pontificio");
//			pedro.setPrimerApellido("Puentes");
//			pedro.setSegundoApellido("Paredes");
//
//			PersonaTo juan = new PersonaTo();
//			juan.setPrimerNombre("Juan");
//			juan.setSegundoNombre("Jacinto");
//			juan.setPrimerApellido("Jimenez");
//			juan.setSegundoApellido("Juliao");
//
//			PersonaTo carlos = new PersonaTo();
//			carlos.setPrimerNombre("Carlos");
//			carlos.setSegundoNombre("Camilo");
//			carlos.setPrimerApellido("Casas");
//			carlos.setSegundoApellido("Cadenas");
//
//
//			resultados.add(pedro);
//			resultados.add(juan);
//			resultados.add(carlos);
//		}
//		else{
//			PersonaTo maria = new PersonaTo();
//			maria.setPrimerNombre("Maria");
//			maria.setPrimerApellido("Martinez");
//			maria.setSegundoApellido("Mendoza");
//
//			PersonaTo federico = new PersonaTo();
//			federico.setPrimerNombre("Federico");
//			federico.setSegundoNombre("Fabian");
//			federico.setPrimerApellido("Fernandez");
//			federico.setSegundoApellido("Folleto");
//			
//			resultados.add(maria);
//			resultados.add(federico);
//		}

		switch(perfil){
		case "Practicante":
			resultados = persona.consultarPracticantes();
			break;
		case "Supervisor":
			resultados = persona.consultarSupervisores();
			break;
		case "Paciente":
			resultados = persona.consultarPacientes();
			break;
		case "Administrador":
			resultados = persona.consultarAdministradores();
			break;	
		}
		
		return resultados;
	}
}