package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.modelo.HistoriaClinicaBean;
import com.plataforma.cpc.modelo.PersonaBean;
import com.plataforma.cpc.to.HistoriaClinicaTo;
import com.plataforma.cpc.to.PersonaTo;

/**
 * Implementación del servlet para los servicios de busqueda de personas para el administrador
 */
@WebServlet(name="/ServletBusquedaPersona", urlPatterns = {"/busquedaPersonas"})
public class ServletBusquedaPersona extends HttpServlet {

//----------------------------------------------------------------------------------------------------------------------------------
// Implementacion del Servlet
//----------------------------------------------------------------------------------------------------------------------------------
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);
	}

//-----------------------------------------------------------------------------------------------------------------------------------
// Metodos
//-----------------------------------------------------------------------------------------------------------------------------------
	
	public void ResponderPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = request.getRequestDispatcher("busquedaPersonas.jsp");

		try{
			String busqueda = request.getParameter("busqueda");
			String jornada = request.getParameter("jornada");

			// La busqueda por defecto es de practicantes de la mañana
			if(busqueda == null)
				busqueda = "Practicante";

			if(jornada == null)
				jornada = "manana";

			request.setAttribute("valor", busqueda);
			request.setAttribute("jornada", jornada);
			// Delega la busqueda dados los parametros recuperados de la peticion
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

	/**
	 * Realiza la busqueda de personas correspondiente dados los parametros recibidos
	 * @param perfil Administrador, Practicante, Supervisor o Paciente
	 * @param jornada Mañana, Tarde o Noche, esto solo afecta la busqueda de Practicantes y pacientes
	 * @return Lista con los resultados especificos
	 */
	public ArrayList<PersonaTo> RealizarBusqueda(String perfil, String jornada){
		PersonaBean persona = new PersonaBean();
		ArrayList<PersonaTo> resultados = new ArrayList<PersonaTo>();
		ArrayList<PersonaTo> retorno = new ArrayList<PersonaTo>();
		boolean buscarJornada = false;
		boolean buscarHistoria = false;

		switch(perfil){
		case "Practicante":
			// Los practicantes se pueden filtrar por Jornada
			resultados = persona.consultarPracticantes();
			buscarJornada = true;
			break;
		case "Supervisor":
			resultados = persona.consultarSupervisores();
			break;
		case "Paciente":
			// Los pacientes se pueden filtrar por Jornada
			// Es necesario consultar la informacion de Hisotria Clinica de los pacientes
			resultados = persona.consultarPacientes();
			buscarJornada = true;
			buscarHistoria = true;
			break;
		case "Administrador":
			resultados = persona.consultarAdministradores();
			break;	
		}

		// Se hace el filtro correspondiente si es necesario
		if(buscarJornada){
			for(int i = 0; i < resultados.size(); i++){
				PersonaTo actual = resultados.get(i);
				if(actual.getJornada().equals(jornada)){
					if(buscarHistoria){
						HistoriaClinicaBean bean = new HistoriaClinicaBean();
						HistoriaClinicaTo historia = bean.consultarHistoriaClinica(actual.getIdPersona());
						actual.setHistoriaClinica(historia);
					}
					retorno.add(actual);
				}
			}

			return retorno;
		}
		
		return resultados;
	}
}