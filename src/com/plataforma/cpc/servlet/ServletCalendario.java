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
 * Responde a la petici�n para mostrar la agenda de un practicante especifico
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
		request.setAttribute("paciente", request.getParameter("paciente"));
		request.setAttribute("salon", request.getParameter("salon"));
		request.setAttribute("valoracion", request.getParameter("valoracion"));
		request.setAttribute("tipo", request.getParameter("tipo"));
		try{
			DaoCitas daoCitas = new DaoCitas();
			ArrayList<CitaTo> citas = daoCitas.consultarCitasPracticante(Integer.parseInt(request.getParameter("idPracticante")));		
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
			String paciente = "";
			String mes = "";
			String dia = "";
			String hora = "";
			String horaFin = "";
			if(actual.getPaciente().getIdPersona() != null) {
				personaTo.setIdPersona(actual.getPaciente().getIdPersona());
				PersonaTo pacienteTo = personaBean.consultarPersona(personaTo);
				paciente = pacienteTo.getPrimerNombre() + " " + pacienteTo.getPrimerApellido() + " " + pacienteTo.getSegundoApellido();
				mes = fecha.getMonthValue() > 9? fecha.getMonthValue() + "" : "0" + fecha.getMonthValue();
				dia = fecha.getDayOfMonth() > 9? fecha.getDayOfMonth() + "" : "0" + fecha.getDayOfMonth();
				hora = fecha.getHour() > 9? fecha.getHour() + "" : "0" + fecha.getHour();			
			}
			
			if(actual.isValoracion()){
				if(fecha.getMinute() > 0){
					int h = fecha.getHour() +1;
					horaFin = h > 9? h + ":00" : "0" + h + ":00";
				}
				else
					horaFin = hora + ":30";
			}
			else{
				int fin = fecha.getHour() + 1;
				horaFin = fin > 9? fin + "" : "0" + fin;
				String minutos = fecha.getMinute() > 9? fecha.getMinute() + "" : "0" + fecha.getMinute();
				horaFin += ":" + minutos;	
			}
			
			String minutos = fecha.getMinute() > 9? fecha.getMinute() + "" : "0" + fecha.getMinute();
			par += "{id: "+ actual.getIdCita() +", title: '" + paciente + " Salon: " + actual.getSalon() + 
					"', start: '"+ fecha.getYear() +  "-" + mes + "-" + dia + " " + hora + ":" + minutos + "', end: '" + fecha.getYear() +  "-" + mes + "-" + dia + " " + horaFin + "'" +
					",estado:'" + actual.getEstado() + "'},";
		}
		return par;
	}
}