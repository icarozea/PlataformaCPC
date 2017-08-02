package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.security.Permissions;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.dao.DaoUtilidades;
import com.plataforma.cpc.modelo.PersonaBean;
import com.plataforma.cpc.to.PersonaTo;

/**
 * Implementacion de Servlet para manejar las peticiones de asignacion de personas a Practicantes y Asesores
 */
@WebServlet(name="/ServletAsignaciones", urlPatterns = {"/asignaciones","/ServletAsignaciones"})
public class ServletAsignaciones extends HttpServlet {

	//----------------------------------------------------------------------------------------------------------------------------
	// Implementacion
	//----------------------------------------------------------------------------------------------------------------------------
	
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
			DaoUtilidades utils = new DaoUtilidades();
			String operacion = request.getParameter("operacion");
			Integer idActual = Integer.parseInt(request.getParameter("id"));
			Integer idAsignado = Integer.parseInt(request.getParameter("asignado"));

			if(operacion.equals("asignar"))
				asignarPersona(idActual, idAsignado);
			else if(operacion.equals("eliminar"))
				asignarPersona(0, idAsignado);

			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("pNom", request.getParameter("pNom"));
			request.setAttribute("sNom", request.getParameter("sNom"));
			request.setAttribute("pApe", request.getParameter("pApe"));
			request.setAttribute("sApe", request.getParameter("sApe"));
			request.setAttribute("sApe", request.getParameter("sApe"));
			request.setAttribute("valor", request.getParameter("rol"));

			String jornada = request.getParameter("jornada");
			request.setAttribute("posibilidades", CargarPosibilidades(request.getParameter("rol"),jornada));
			request.setAttribute("jornada", jornada);
			ArrayList<PersonaTo> asignados = CargarAsignados(idActual);
			request.setAttribute("asignados", asignados);
			
			if(request.getParameter("rol").equals("Practicante")){
				int cupos = utils.buscarCuposActuales() - asignados.size();
				request.setAttribute("cupos", cupos);
			}
			else
				request.setAttribute("cupos", 999);
			

			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<PersonaTo> CargarAsignados(Integer id){
		PersonaBean personaBean = new PersonaBean();
		return personaBean.consultarAsignados(id);
	}

	public ArrayList<PersonaTo> CargarPosibilidades(String rol, String jornada) throws Exception {

		PersonaBean personaBean = new PersonaBean();
		ArrayList<PersonaTo> resultados = new ArrayList<PersonaTo>();

		switch (rol){
		case "Practicante":
			resultados = personaBean.consultarPacientes();
			break;
		case "Supervisor":
			resultados = personaBean.consultarPracticantes();
			break;
		default:
			throw new Exception("No es posible realizar asignaciones a ese perfil: " + rol);
		}
		
		ArrayList<PersonaTo> aEntregar = new ArrayList<PersonaTo>();
		for(int i = 0; i < resultados.size(); i++){
			PersonaTo actual = resultados.get(i);
			if(actual.getSuperior() == 0 && actual.getJornada().equals(jornada)){
				aEntregar.add(actual);
			}
		}

		return aEntregar;
	}

	public void asignarPersona(Integer idActual, Integer idAsignado){
		try{
			PersonaBean personaBean = new PersonaBean();
			PersonaTo consultaAsignado = new PersonaTo();
			consultaAsignado.setIdPersona(idAsignado);
			PersonaTo asignado = personaBean.consultarPersona(consultaAsignado);
			asignado.setSuperior(idActual);
			
			if(!personaBean.modificarPersona(asignado))
				throw new Exception("No fue posible realizar la operación");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}