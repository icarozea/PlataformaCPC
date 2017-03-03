package com.plataforma.cpc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.plataforma.cpc.dao.DaoSesionIndividual;
import com.plataforma.cpc.to.SesionIndividualTo;

/**
 * Servlet implementation class ServletSesionIndividual
 */
@WebServlet("/ServletSesionIndividual")
public class ServletSesionIndividual extends HttpServlet {
	
	public void ResponderPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html;charset=UTF-8");

		String operacion = request.getParameter("operacion");

		switch (operacion) {

		case "crear":
			crearReporte(request,response);
			break;
		case "actualizar":
			actualizarReporte(request, response);
			break;
		default:
			System.out.println("Opción no existe");
			break;
		}
	}
       
	public void crearReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DaoSesionIndividual dao = new DaoSesionIndividual();
		SesionIndividualTo sesion = new SesionIndividualTo();
		sesion.setFecha(request.getParameter("fecha") +" "+ request.getParameter("hora"));
		sesion.setNombreProfesional(request.getParameter("profesional"));
		sesion.setObjetivo(request.getParameter("objetivoSesion"));
		sesion.setDescripcion(request.getParameter("descripcionSesion"));
		sesion.setTareasAsignadas(request.getParameter("tareasSesion"));
		sesion.setActividadesProximaSesion(request.getParameter("actividadesProxSesion"));
		String fallo = request.getParameter("fallo");
		sesion.setFallo(fallo != null?true:false);
		sesion.setNumRecibo(Integer.parseInt(request.getParameter("numeroRecibo")));
		Integer idTratamiento = Integer.parseInt(request.getParameter("idTratamiento"));
		Integer idCita = Integer.parseInt(request.getParameter("idCita"));
		String estado = sesion.isFallo()? "cancelada" : "pendiente";
		boolean resultado = dao.crearReporteSesionIndividual(sesion, idTratamiento, idCita, estado, sesion.isFallo());
		if (resultado) 	
			request.setAttribute("mensajeRespuestaReporte", "Se ha creado exitosamente el reporte de la sesión.");
		else
			request.setAttribute("mensajeRespuestaReporte", "Ha ocurrido un error durante la creación del reporte.");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");
		dispatcher.forward(request, response);
	}
	
	public void actualizarReporte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DaoSesionIndividual dao = new DaoSesionIndividual();
		SesionIndividualTo sesion = new SesionIndividualTo();
		sesion.setIdSesion(Integer.parseInt(request.getParameter("idSesion")));
		sesion.setObjetivo(request.getParameter("objetivoSesion"));
		sesion.setDescripcion(request.getParameter("descripcionSesion"));
		sesion.setTareasAsignadas(request.getParameter("tareasSesion"));
		sesion.setActividadesProximaSesion(request.getParameter("actividadesProxSesion"));
		boolean resultado = dao.actualizarReporteSesionIndividual(sesion);
		if (resultado) 	
			request.setAttribute("mensajeRespuestaReporte", "Se ha actualizado exitosamente el reporte de la sesión.");
		else
			request.setAttribute("mensajeRespuestaReporte", "Ha ocurrido un error durante la actualizacion del reporte.");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);	
	}
}