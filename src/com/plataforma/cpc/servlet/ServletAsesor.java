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
import com.plataforma.cpc.to.ComentariosTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.SesionIndividualPreviewTo;
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
		case "reporte":
			verReporte(request,response);
			break;
		case "reporteDetallado":
			verReporteDetallado(request,response);
			break;	
		case "reportesPreview":
			verPreviewReportes(request,response);
		case "comentarios":
//			verComentarios(request,response);
			break;
		case "guardarComentarios":
			guardarComentarios(request,response);
			break;
		case "actualizarComentarios":
			actualizarComentarios(request,response);
			break;
		case "aceptar":
			aceptarReporte(request,response);
			break;
		default:
			System.out.println("Opción no existe");
			break;
		}
	}

	private void verPracticantes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoPersona daoPersona = new DaoPersona();

		try{
			Integer idAsesor = Integer.parseInt(request.getParameter("idAsesor"));
			ArrayList<PersonaTo> practicantes = daoPersona.consultarAsignados(idAsesor);
			request.setAttribute("practicantes", practicantes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("verPracticantes.jsp");
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
	
	private void verReporteDetallado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();
		
		try{
			Integer idReporte = Integer.parseInt(request.getParameter("idReporte"));
			SesionIndividualTo sesionIndividual = daoSesionIndividual.consultarSesionPorId(idReporte);
			request.setAttribute("idReporte", idReporte);
			request.setAttribute("objetivoSesion", sesionIndividual.getObjetivo());
			request.setAttribute("descripcionSesion", sesionIndividual.getDescripcion());
			request.setAttribute("tareasAsignadasSesion",sesionIndividual.getTareasAsignadas());
			request.setAttribute("actividadesProxSesion",sesionIndividual.getActividadesProximaSesion());
			RequestDispatcher dispatcher = request.getRequestDispatcher("verReporteDetallado.jsp");
			dispatcher.forward(request, response);
			
		}catch(Exception e){
			System.out.println("Busqueda de reporte individual fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}

	private void verReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();		

		try{
			Integer idPracticante = Integer.parseInt(request.getParameter("idPracticante"));
			ArrayList<SesionIndividualTo> reportes = daoSesionIndividual.consultarReporteSesionporPracticante(idPracticante);
			request.setAttribute("reportes", reportes);
			request.setAttribute("idPracticante", idPracticante);
			
			System.out.println(reportes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("verReportesPracticantes.jsp");
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
	
	private void verPreviewReportes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();
		
		try{
			Integer idPracticante = Integer.parseInt(request.getParameter("idPracticante"));
			ArrayList<SesionIndividualPreviewTo> reportesPreview = daoSesionIndividual.consultarListaReportesSesionesPorPracticante(idPracticante);
			request.setAttribute("reportesPreview", reportesPreview);
			request.setAttribute("idPracticante", idPracticante);
			request.setAttribute("nomPracticante", request.getParameter("pNom")+" "+request.getParameter("sNom")+" "
					+request.getParameter("pApe")+" "+request.getParameter("sApe"));
			System.out.println(reportesPreview);
			RequestDispatcher dispatcher = request.getRequestDispatcher("verReportesPracticantes.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e){
			System.out.println("Busqueda fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);			
		}
	}
	
	private void verComentarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoSesionIndividual daoSesionIndividual = new DaoSesionIndividual();		

		try{
			Integer idComentarios = Integer.parseInt(request.getParameter("idComentarios"));
			ComentariosTo comentarios = daoSesionIndividual.consultarComentarios(idComentarios);
			request.setAttribute("comentarios", comentarios);
			System.out.println(comentarios.getComentariosObjetivo());
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

		DaoSesionIndividual daoSesion = new DaoSesionIndividual();
		ComentariosTo comentarios = new ComentariosTo();

		try{
			Integer idReporte = Integer.parseInt(request.getParameter("idReporte"));
			comentarios.setComentariosObjetivo(request.getParameter("comObjetivo"));
			comentarios.setComentariosDescripcion(request.getParameter("comDescripcion"));
			comentarios.setComentariosTareas(request.getParameter("comTareas"));
			comentarios.setComentariosActividades(request.getParameter("comActividades"));

			if(daoSesion.guardarComentarios(idReporte, comentarios)){
				System.out.println("Creacion exitosa");
				request.setAttribute("respuesta", "1");
				RequestDispatcher dispatcher = request.getRequestDispatcher("");
				dispatcher.forward(request, response);
			}	
			else{
				throw new Exception("Error al guardar los comentarios");
			}
		}
		catch(Exception e){
			System.out.println("Creación fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}

	private void actualizarComentarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoSesionIndividual daoSesion = new DaoSesionIndividual();
		ComentariosTo comentarios = new ComentariosTo();

		try{
			Integer idComentarios = Integer.parseInt(request.getParameter("idComentarios"));
			comentarios.setIdComentarios(idComentarios);
			comentarios.setComentariosObjetivo(request.getParameter("comObjetivo"));
			comentarios.setComentariosDescripcion(request.getParameter("comDescripcion"));
			comentarios.setComentariosTareas(request.getParameter("comTareas"));
			comentarios.setComentariosActividades(request.getParameter("comActividades"));

			if(daoSesion.actualizarComentarios(comentarios)){
				System.out.println("Actualizacion exitosa");
				request.setAttribute("respuesta", "1");
				RequestDispatcher dispatcher = request.getRequestDispatcher("");
				dispatcher.forward(request, response);
			}	
			else{
				throw new Exception("Error al actualizar los comentarios");
			}
		}
		catch(Exception e){
			System.out.println("Actualización fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
	}
	
	private void aceptarReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DaoSesionIndividual daoSesion = new DaoSesionIndividual();
		
		try{
			Integer idReporte = Integer.parseInt(request.getParameter("idReporte"));
			if(daoSesion.aceptarReporte(idReporte)){
				request.setAttribute("respuesta", "1");
				RequestDispatcher dispatcher = request.getRequestDispatcher("");
				dispatcher.forward(request, response);
			}
		}
		catch(Exception e){
			System.out.println("Operacion fallida: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}
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