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
import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.SesionIndividualTo;

/**
 * Servlet implementation class ServletSesionIndividual
 */
@WebServlet("/ServletSesionIndividual")
public class ServletSesionIndividual extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSesionIndividual() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		boolean resultado = dao.crearReporteSesionIndividual(sesion);
		if (resultado) {
			
			if(!sesion.isFallo()){
				DaoCitas daoCitas = new DaoCitas();
				Integer idTratamiento = Integer.parseInt(request.getParameter("idTratamiento"));
				if(daoCitas.avanzarCitaActual(idTratamiento))
					request.setAttribute("mensajeRespuestaReporte", "Se ha creado exitosamente el reporte de la sesión.");
				else
					request.setAttribute("mensajeRespuestaReporte", "Ha ocurrido un error durante la creación del reporte: Error en el consecutivo de la cita.");
			}
			else{
				request.setAttribute("mensajeRespuestaReporte", "Se ha creado exitosamente el reporte de la sesión.");
			}
		}else{
			request.setAttribute("mensajeRespuestaReporte", "Ha ocurrido un error durante la creación del reporte.");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaReporteCita.jsp");
		dispatcher.forward(request, response);
	}
}