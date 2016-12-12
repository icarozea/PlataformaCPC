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
import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.PersonaTo;

/**
 * Servlet implementation class ServletCalendario
 */
@WebServlet(name="/ServletCalendario", urlPatterns = {"/Calendario"})
public class ServletCalendario extends HttpServlet {

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
		
		try{
			DaoCitas daoCitas = new DaoCitas();
			ArrayList<CitaTo> citas = daoCitas.consultarCitasPracticante(Integer.parseInt(request.getParameter("idPersona")));		
			request.setAttribute("citas", parsearCitas(citas));
			RequestDispatcher dispatcher = request.getRequestDispatcher("calendario.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	private String parsearCitas(ArrayList<CitaTo> citas){
		
		String par = "";
		for(int i = 0; i < citas.size(); i++){
			CitaTo actual = citas.get(i);
			LocalDateTime fecha = actual.getFechaCita();
			String paciente = actual.getPaciente().getPrimerNombre();
			String mes = fecha.getMonthValue() > 9? fecha.getMonthValue() + "" : "0" + fecha.getMonthValue();
			String dia = fecha.getDayOfMonth() > 9? fecha.getDayOfMonth() + "" : "0" + fecha.getDayOfMonth();
			par += "{Title: '" + paciente +"', start: '"+ fecha.getYear() +  "-" + mes + "-" + dia + " " + fecha.getHour() + ":" + fecha.getMinute() + "'},";
			System.out.println(par);
		}
		return par;
	}
}
