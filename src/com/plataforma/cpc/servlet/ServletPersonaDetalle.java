package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.dao.DaoUtilidades;
import com.plataforma.cpc.modelo.EpsBean;
import com.plataforma.cpc.modelo.PersonaBean;
import com.plataforma.cpc.modelo.PersonaDetalleBean;
import com.plataforma.cpc.modelo.UtilBean;
import com.plataforma.cpc.to.EpsTo;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.PersonaDetalleTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.TipoDocumentoTo;

/**
 * Servlet que se ocupa de la creación y edición de personas mediante el formulario correspondiente
 */
@WebServlet(name="ServletPersonaDetalle", urlPatterns = {"/ServletPersonaDetalle"})
public class ServletPersonaDetalle extends HttpServlet {

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
		
		case "guardarPersona":
			guardarPersonaDetalle(request,response);
			break;
		default:
			System.out.println("Opción no existe");
			break;
		
		}
	}
	
	
	/**
	 * Responde a la peticion de guardar una nueva persona en la base de datos con la informacion capturada en el formulario
	 * Redirige a la pagina de respuestaAgregarPersona
	 */
	private void guardarPersonaDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaAgregarPersona.jsp");
		
		PersonaDetalleBean personaBean = new PersonaDetalleBean();
		PersonaDetalleTo personaTo = new PersonaDetalleTo();
		try{
			personaTo.setPersonaId(Integer.parseInt(request.getParameter("idPersona")));
			personaTo.setSexo(request.getParameter("sexo")); 
			personaTo.setEdad( request.getParameter("edad"));
			personaTo.setAcudiente(request.getParameter("acudiente"));
			personaTo.setProceso(request.getParameter("proceso"));
			personaTo.setPerteneceU(request.getParameter("perteneceU"));
			personaTo.setFacultad(request.getParameter("facultad"));
			personaTo.setSemestre(request.getParameter("semestre"));
			personaTo.setProblematica(request.getParameter("nombre"));
			personaTo.setObservación(request.getParameter("problematica"));
			personaTo.setNombreModifica(request.getParameter("observaciones"));
			
			if(personaBean.ingresarDetallePersona(personaTo)){
				request.setAttribute("respuesta", "1");
				request.setAttribute("error", "");
				dispatcher.forward(request, response);
			}
			else{
				throw new Exception("Hubo un error al intentar guardar la información");
			}
		}
		catch(Exception e){
			System.out.println("Error de formulario: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			dispatcher.forward(request, response);
		}		
	}

}