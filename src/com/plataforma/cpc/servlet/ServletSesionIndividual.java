package com.plataforma.cpc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.dao.DaoCitas;
import com.plataforma.cpc.dao.DaoSesionIndividual;
import com.plataforma.cpc.to.SesionIndividualTo;

/**
 * Servlet implementation class ServletSesionIndividual
 */
@WebServlet("/ServletSesionIndividual")
public class ServletSesionIndividual extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResponderPeticion(request, response);
	}
	
	public void ResponderPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		DaoSesionIndividual dao = new DaoSesionIndividual();
		SesionIndividualTo sesion = new SesionIndividualTo();
		sesion.setCitaId(request.getParameter("idCita"));
		sesion.setFecha(request.getParameter("fecha") +" "+ request.getParameter("hora"));
		sesion.setNombreProfesional(request.getParameter("profesional"));
		sesion.setObjetivo(request.getParameter("objetivoSesion"));
		sesion.setDescripcion(request.getParameter("descripcionSesion"));
		sesion.setTareasAsignadas(request.getParameter("tareasSesion"));
		sesion.setActividadesProximaSesion(request.getParameter("actividadesProxSesion"));

		try{
			boolean resultado = dao.crearReporteSesionIndividual(sesion);
			if (resultado) {
				DaoCitas daoCitas = new DaoCitas();
				if(daoCitas.actualizarEstadoCita(Integer.parseInt(request.getParameter("idCita")), "pendiente"))
					request.setAttribute("respuesta", "1");
				else{
					request.setAttribute("respuesta", "2");
					request.setAttribute("error", "No fue posible cambiar el estado de la cita");
				}
			}else{
				request.setAttribute("respuesta", "2");
				request.setAttribute("error", "Error en la creación del reporte");
			}
		}
		catch (Exception e){
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");
		dispatcher.forward(request, response);
	}
}