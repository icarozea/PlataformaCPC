package com.plataforma.cpc.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.dao.DaoUtilidades;
import com.plataforma.cpc.modelo.HistoriaClinicaBean;

/**
 * Implementación de un servlet para manejar los servicios asociados a reiniciar el consecutivo de las historia clinicas
 */
@WebServlet(name = "ServletConsecutivo", urlPatterns = { "/Consecutivo" })
public class ServletConsecutivo extends HttpServlet {

//----------------------------------------------------------------------------------------------------------------------------------------
// Implementacion del servlet
//----------------------------------------------------------------------------------------------------------------------------------------
	
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
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String operacion = request.getParameter("operacion");
		
		switch (operacion) {
		case "cargar":
			cargar(request, response);
			break;
		case "reiniciar":
			reiniciarConsecutivo(request, response);
			break;
		default:
			System.out.println("Opción no existe");
			break;
		}
	}

//----------------------------------------------------------------------------------------------------------------------------------------
// Metodos
//----------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Carga el formulario de consecutivos con la información actual
	 */
	private void cargar(HttpServletRequest request, HttpServletResponse response){
		try{
			DaoUtilidades utilidades = new DaoUtilidades();
			String consecutivo = utilidades.consultarConsecutivoHistoria(false);
			String[] datos = consecutivo.split("-");
			
			request.setAttribute("ano", datos[0]);
			request.setAttribute("semestre", datos[1]);
			request.setAttribute("consecutivo", datos[2]);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("FormularioConsecutivos.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Operación de reinicio de los consecutivos, dados los parametros capturados en el formulario
	 * Recarga la pagina con el mensaje correspondiente de exito/fracaso de la operación
	 */
	private void reiniciarConsecutivo(HttpServletRequest request, HttpServletResponse response){
		try{
			DaoUtilidades utilidades = new DaoUtilidades();
		
			int ano = Integer.parseInt(request.getParameter("ano"));
			String semestre = request.getParameter("semestre");
			int consecutivo = Integer.parseInt(request.getParameter("consecutivo"));
			
			if(utilidades.reiniciarConsecutivoHistoriaClinica(ano, semestre, consecutivo)){
				request.setAttribute("mensaje", "Reinicio Exitoso");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./Consecutivo?operacion=cargar");
				dispatcher.forward(request, response);
			}
			else{
				request.setAttribute("mensaje", "Reinicio Fallido");
				RequestDispatcher dispatcher = request.getRequestDispatcher("./Consecutivo?operacion=cargar");
				dispatcher.forward(request, response);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}