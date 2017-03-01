package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.dao.DaoPersona;
import com.plataforma.cpc.dao.DaoSesionIndividual;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.SesionIndividualTo;

/**
 * Servlet implementation class ServletAsesor
 */
@WebServlet(name="/ServletAsesor", urlPatterns = {"/ServletAsesor"})
public class ServletAsesor extends HttpServlet {

	public void ResponderPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");

		String operacion = request.getParameter("operacion");

		switch (operacion) {

		case "practicantes":
			verPracticantes(request,response);
			break;
		case "reportes":
			verReportes(request,response);
			break;
		case "comentarios":
			guardarComentarios(request,response);
			break;
		default:
			System.out.println("Opción no existe");
			break;
		}
	}

	private void verPracticantes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoPersona daoPersona = new DaoPersona();
		Integer idAsesor = Integer.parseInt(request.getParameter("idAsesor"));

		try{
			ArrayList<PersonaTo> practicantes = daoPersona.consultarAsignados(idAsesor);
			request.setAttribute("practicantes", practicantes);
			System.out.println(practicantes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			System.out.println("Busqueda fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}

	private void verReportes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();
		Integer idPracticante = Integer.parseInt(request.getParameter("idPracticante"));

		try{
			ArrayList<SesionIndividualTo> reportes = daoSesionIndividual.consultarReporteSesionporPracticante(idPracticante);
			request.setAttribute("reportes", reportes);
			System.out.println(reportes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			System.out.println("Busqueda fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}
	
	private void guardarComentarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);
	}
}