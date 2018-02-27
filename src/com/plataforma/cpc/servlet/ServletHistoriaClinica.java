package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.modelo.EpsBean;
import com.plataforma.cpc.modelo.HistoriaClinicaBean;
import com.plataforma.cpc.modelo.PersonaBean;
import com.plataforma.cpc.modelo.UtilBean;
import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.EpsTo;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.SesionIndividualTo;
import com.plataforma.cpc.to.TipoDocumentoTo;
import com.plataforma.cpc.to.TratamientoTo;
import com.plataforma.cpc.to.reporteValoracionTo;

/**
 * Servlet que se ocupa de la creación y edición de personas mediante el formulario correspondiente
 */
@WebServlet(name="ServletHistoriaClinica", urlPatterns = {"/ServletHistoriaClinica"})
public class ServletHistoriaClinica extends HttpServlet {

//-----------------------------------------------------------------------------------------------------
// Proceso de la peticion
//-----------------------------------------------------------------------------------------------------
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		String operacion = request.getParameter("operacion");
		switch (operacion) {
		
		case "detalleTratamiento":
			detalleTratamiento(request,response);
			break;
		case "detalleCitas":
			detalleCitas(request,response);
			break;
		case "detalleSesion":
			detalleSesion(request,response);
			break;
		case "detalleSesion2":
			detalleSesion(request,response);
			break;	
		default:
			System.out.println("Opción no existe");
			break;
		
		}
	}
	
	private void detalleTratamiento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HistoriaClinicaBean historiaClinica = new HistoriaClinicaBean();
		PersonaBean personaBean = new PersonaBean();
		ArrayList<TratamientoTo> tratamientos = new ArrayList<TratamientoTo>();
		PersonaTo personaFiltro = new PersonaTo();
		PersonaTo paciente = new PersonaTo();

		Integer idPaciente = new Integer(request.getParameter("idPersona"));
		personaFiltro.setIdPersona(idPaciente);

		try {
			paciente = personaBean.consultarPersona(personaFiltro);
			tratamientos = historiaClinica.consultarTratamientosxPaciente(idPaciente);
			
			request.setAttribute("paciente", paciente);
			request.setAttribute("tratamientos", tratamientos);
			RequestDispatcher dispatcher = request.getRequestDispatcher("hcDetallePaciente.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			System.out.println("Error de formulario: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}	
	}
	
	@SuppressWarnings("unused")
	private void detalleCitas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HistoriaClinicaBean historiaClinica = new HistoriaClinicaBean();
		ArrayList<CitaTo> citas = new ArrayList<CitaTo>();
		TratamientoTo tratamiento = new TratamientoTo();
		
		Integer idPaciente = new Integer(request.getParameter("idPaciente"));
		Integer idTratamiento = Integer.parseInt(request.getParameter("grupoTratamiento"));

		try {
			tratamiento = historiaClinica.consultarTratamiento(idTratamiento);
			citas = historiaClinica.consultarCitasxTratamiento(idPaciente, idTratamiento);
			
			request.setAttribute("tratamiento", tratamiento);
			request.setAttribute("listaCitas", citas);
			request.setAttribute("idPaciente", idPaciente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("hcDetalleTratamiento.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			System.out.println("Error de formulario: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}	
	}
	
	@SuppressWarnings("unused")
	private void detalleSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HistoriaClinicaBean historiaClinica = new HistoriaClinicaBean();
		ArrayList<SesionIndividualTo> sesiones = new ArrayList<SesionIndividualTo>();
		
		Integer idCita = Integer.parseInt(request.getParameter("grupoCita"));
		CitaTo citaFiltro = new CitaTo();
		CitaTo cita = new CitaTo();
		citaFiltro.setIdCita(idCita);
		try {			
			cita = historiaClinica.consultarCita(citaFiltro);
			request.setAttribute("cita", cita);
			if(cita.isValoracion()){
				reporteValoracionTo valoracion = historiaClinica.consultarReportesValoracion(idCita);
				if(valoracion != null)
					request.setAttribute("valoracion", valoracion);
				else
					request.setAttribute("valoracion", null); 
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("hcDetalleValoracion.jsp");
				dispatcher.forward(request, response);		
			}
			else{
				SesionIndividualTo sesion = historiaClinica.consultarReportesSesion(idCita);
				if (sesion != null)
					request.setAttribute("sesion", sesion);
				else 
					request.setAttribute("sesion", null);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("hcDetalleSesion.jsp");
				dispatcher.forward(request, response);	
			}
		} catch (Exception e) {
			System.out.println("Error de formulario: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("");
			dispatcher.forward(request, response);
		}	
	}
}