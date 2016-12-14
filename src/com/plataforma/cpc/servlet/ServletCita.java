package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.plataforma.cpc.dao.DaoCitas;
import com.plataforma.cpc.modelo.PersonaBean;
import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.PersonaTo;


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
		case "eliminarCita":
			eliminarCita(request, response);
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
		
		String fecha = parsearFecha(request.getParameter("fecha"));
		
		personaFiltro.setIdPersona(idPersona);			
		ArrayList<PersonaTo> listaPacientes = new ArrayList<PersonaTo>();
		
		try {
			practicante = personaBean.consultarPersona(personaFiltro);
			listaPacientes = personaBean.consultarAsignados(idPersona);
			
			request.setAttribute("practicante", practicante);
			request.setAttribute("listaPacientes", listaPacientes);
			request.setAttribute("fecha", fecha);
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
	
	public void guardarCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaCrearCita.jsp");
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
			
			if(dao.crearCita(citaTo))
				request.setAttribute("respuesta", "1");
			else{
				request.setAttribute("respuesta", "2");
				request.setAttribute("error", "No fue posible crear una nueva cita");
			}
			
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			dispatcher.forward(request, response);
		}
	}
	
	public void eliminarCita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		DaoCitas daoCitas = new DaoCitas();
		CitaTo citaTo = new CitaTo();
		
		try{
			Integer idCita = Integer.parseInt(request.getParameter("idCita"));
			citaTo.setIdCita(idCita);
			
			if(daoCitas.eliminarCita(citaTo)){
				RequestDispatcher dispatcher = request.getRequestDispatcher("./Calendario");
				dispatcher.forward(request, response);
			}
			else
				throw new Exception("No fue posible eliminar la cita");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private String parsearFecha(String fecha){
		if(fecha != null){
			String[] partes = fecha.split(" ");
			String retorno = partes[3] + "-" + parsearMes(partes[1]) + "-" + partes[2] + "T" + partes[4];
			return retorno;
		}
		
		return "";
	}
	
	private String parsearMes(String mes){
		String par = "err";
		
		switch (mes){
		case "Jan":
			par = "01";
			break;
		case "Feb":
			par = "02";
			break;
		case "Mar":
			par = "03";
			break;
		case "Apr":
			par = "04";
			break;
		case "May":
			par = "05";
			break;
		case "Jun":
			par = "06";
			break;
		case "Jul":
			par = "07";
			break;
		case "Aug":
			par = "08";
			break;
		case "Sep":
			par = "09";
			break;
		case "Oct":
			par = "10";
			break;
		case "Nov":
			par = "11";
			break;
		case "Dec":
			par = "12";
			break;
		default:
			break;
		}
		
		return par;
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