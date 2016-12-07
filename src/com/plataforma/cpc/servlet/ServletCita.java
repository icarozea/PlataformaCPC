package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.dao.DaoCitas;
import com.plataforma.cpc.modelo.EpsBean;
import com.plataforma.cpc.modelo.PersonaBean;
import com.plataforma.cpc.modelo.UtilBean;
import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.EpsTo;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.TipoDocumentoTo;

@WebServlet(name="ServletCita", urlPatterns = {"/ServletCita"})
public class ServletCita extends HttpServlet{
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		
		String operacion = request.getParameter("operacion");
		System.out.println("Operacion: "+operacion);
		switch (operacion) {
		
		case "cargueIncial":
			cargueInicial(request,response);
			break;
		case "crearCita":
			crearCita(request,response);
			break;
		case "guardarCita":
			guardarCita(request, response);
			break;
		case "editarPersona":
			//editarPersonas(request,response);
			break;
		case "eliminarPersona":
			//eliminarPersonas(request,response);
			break;
		default:
			System.out.println("Opción no existe");
			break;
		
		}
	}
	
	private void crearCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PersonaBean personaBean = new PersonaBean();
		PersonaTo personaFiltro = new PersonaTo();
		PersonaTo practicante = new PersonaTo();
		
		Integer idPersona = new Integer(request.getParameter("idPersona"));
		
		personaFiltro.setIdPersona(idPersona);			
		ArrayList<PersonaTo> listaPacientes = new ArrayList<PersonaTo>();
		
		try {
			practicante = personaBean.consultarPersona(personaFiltro);
			listaPacientes = personaBean.consultarAsignados(idPersona);
			
			request.setAttribute("practicante", practicante);
			request.setAttribute("listaPacientes", listaPacientes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("crearCita.jsp");
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
	
	private void cargueInicial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PersonaBean persona = new PersonaBean();
		ArrayList<PersonaTo> practicantes = new ArrayList<PersonaTo>();
		
		try {
			practicantes = persona.consultarPracticantes();
			
			request.setAttribute("listaPracticantes", practicantes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("citaPracticantes.jsp");
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
	
	public void guardarCita(HttpServletRequest request, HttpServletResponse response){
		DaoCitas dao = new DaoCitas();
		CitaTo citaTo = new CitaTo();
		PersonaTo paciente = new PersonaTo();
		PersonaTo practicante = new PersonaTo();
		
		try{
			practicante.setIdPersona(Integer.parseInt(request.getParameter("idPracticante")));
			citaTo.setPracticante(practicante);
			paciente.setIdPersona(Integer.parseInt(request.getParameter("grupoPaciente")));
			citaTo.setPaciente(paciente);
			citaTo.setSalon(request.getParameter("salon"));
			LocalDateTime date = LocalDateTime.parse(request.getParameter("fecha"));
			citaTo.setFechaCita(date);
			
			dao.crearCita(citaTo);
			System.out.println("Cita creada");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
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

}
