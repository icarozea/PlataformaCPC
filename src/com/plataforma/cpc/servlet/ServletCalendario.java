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

/**
 * Responde a la petición para mostrar la agenda de un practicante especifico
 */
@WebServlet(name="/ServletCalendario", urlPatterns = {"/Calendario"})
public class ServletCalendario extends HttpServlet {

//-----------------------------------------------------------------------------------------------------
// Proceso de la peticion
//-----------------------------------------------------------------------------------------------------
	
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

//-----------------------------------------------------------------------------------------------------
// Funciones
//-----------------------------------------------------------------------------------------------------
	
	/**
	 * Transforma lo objetos CitaTo en un JSON de la forma {id,title,start,end}
	 * title tiene la forma nom_paciente Salon: num_salon
	 * start y end son fechas que tienen la forma YYYY-MM-DD HH:mm en formato de 24H
	 * @return un string con todos los objetos CitaTo parseados
	 */
	private String parsearCitas(ArrayList<CitaTo> citas){
		PersonaBean personaBean = new PersonaBean();
		String par = "";
		for(int i = 0; i < citas.size(); i++){
			CitaTo actual = citas.get(i);
			LocalDateTime fecha = actual.getFechaCita();
			PersonaTo personaTo = new PersonaTo();
			personaTo.setIdPersona(actual.getPaciente().getIdPersona());
			PersonaTo pacienteTo = personaBean.consultarPersona(personaTo);
			String paciente = pacienteTo.getPrimerNombre() + " " + pacienteTo.getPrimerApellido() + " " + pacienteTo.getSegundoApellido();
			String mes = fecha.getMonthValue() > 9? fecha.getMonthValue() + "" : "0" + fecha.getMonthValue();
			String dia = fecha.getDayOfMonth() > 9? fecha.getDayOfMonth() + "" : "0" + fecha.getDayOfMonth();
			String hora = fecha.getHour() > 9? fecha.getHour() + "" : "0" + fecha.getHour();
			int fin = fecha.getHour() + 1;
			String horaFin = fin > 9? fin + "" : "0" + fin;
			String minutos = fecha.getMinute() > 9? fecha.getMinute() + "" : "0" + fecha.getMinute();
			par += "{id: "+ actual.getIdCita() +", title: '" + paciente + " Salon: " + actual.getSalon() + 
					"', start: '"+ fecha.getYear() +  "-" + mes + "-" + dia + " " + hora + ":" + minutos + "', end: '" + fecha.getYear() +  "-" + mes + "-" + dia + " " + horaFin + ":" + minutos + "'" +
					",estado:'" + actual.getEstado() + "'},";
		}
		return par;
	}
}