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
import com.plataforma.cpc.modelo.UtilBean;
import com.plataforma.cpc.to.EpsTo;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.TipoDocumentoTo;

/**
 * Servlet implementation class ServletPersona
 */
@WebServlet(name="ServletPersona", urlPatterns = {"/ServletPersona"})
public class ServletPersona extends HttpServlet {
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		
		String operacion = request.getParameter("operacion");
		System.out.println("Operacion: "+operacion);
		switch (operacion) {
		
		case "cargueIncial":
			cargueInicial(request,response);
			break;
		case "guardarPersona":
			guardarPersona(request,response);
			break;
		case "listarPersonas":
			listarPersonas(request,response);
			break;
		case "editarPersona":
			editarPersonas(request,response);
			break;
		case "eliminarPersona":
			eliminarPersonas(request,response);
			break;
		default:
			System.out.println("Opción no existe");
			break;
		
		}
	}
	
	private void eliminarPersonas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PersonaBean personaBean = new PersonaBean();
		PersonaTo persona = new PersonaTo();
		persona.setIdPersona(new Integer(request.getParameter("idPersona")));
		
		if(personaBean.elminarPersona(persona)){
			request.setAttribute("respuesta", "1");
			request.setAttribute("error", "");
			RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaEliminarPersona.jsp");
			dispatcher.forward(request, response);
		}else{
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", "");
			RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaEliminarPersona.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	private void editarPersonas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersonaBean personaBean = new PersonaBean();
		PersonaTo personaTo = new PersonaTo();
		personaTo.setIdPersona(new Integer(request.getParameter("idPersona")));
		
		PersonaTo persona = new PersonaTo();
		persona = personaBean.consultarPersona(personaTo);
		System.out.println(persona);
	}
	
	private void listarPersonas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			UtilBean util = new UtilBean();
			ArrayList<PerfilTo> perfiles = new ArrayList<PerfilTo>();
			perfiles = util.consultarPerfil();
						
			if (perfiles != null && perfiles.size() > 0)
				request.setAttribute("listaPerfiles", perfiles);
			
			String perfil = request.getParameter("perfil");

			if(perfil == null)
				perfil = "3";//Practicante

			request.setAttribute("valor", perfil);
			PersonaBean persona = new PersonaBean();
			ArrayList<PersonaTo> resultados = new ArrayList<PersonaTo>();
			
			switch(perfil){
			case "1":
				resultados = persona.consultarAdministradores();
				break;
			case "2":
				resultados = persona.consultarSupervisores();
				break;
			case "3":
				resultados = persona.consultarPracticantes();
				break;
			case "4":
				resultados = persona.consultarPacientes();
				break;	
			}

			request.setAttribute("listaPersonas", resultados);

			RequestDispatcher dispatcher = request.getRequestDispatcher("verPersonas.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			System.out.println("Error de Busqueda: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("valor", "Practicante");
			RequestDispatcher dispatcher = request.getRequestDispatcher("verPersonas.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	private void guardarPersona(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaAgregarPersona.jsp");
		
		PersonaBean personaBean = new PersonaBean();
		try{
			DaoUtilidades daoUtilidades = new DaoUtilidades();
			
			String nom1 = request.getParameter("nombre1");
			String nom2 = request.getParameter("nombre2");
			String ap1 = request.getParameter("apellido1");
			String ap2 = request.getParameter("apellido2");

			Integer tipoDoc;
			String sigla = request.getParameter("tipoDocumento");
			TipoDocumentoTo tipoDocumentoTo = daoUtilidades.buscarTipoDocumento(sigla);
			if(!sigla.equals("-1") || tipoDocumentoTo != null)
				tipoDoc = tipoDocumentoTo.getIdTipoDocumento();
			else
				throw new Exception("El tipo de documento no es valido o no se encontró");

			String numDoc = request.getParameter("numeroDocumento");
			String dir = request.getParameter("direccion");
			Long tel = new Long(request.getParameter("telefono"));
			String correo = request.getParameter("correo");

			Integer idPerfil = Integer.parseInt(request.getParameter("perfil"));
			PerfilTo perfilTo = daoUtilidades.buscarPerfil(idPerfil);
			if(idPerfil > 0 || perfilTo != null)
				idPerfil = perfilTo.getIdPerfil();
			else{
				System.out.println("Texto: " + idPerfil + " Busqueda: " + perfilTo);
				throw new Exception("El perfil seleccionado no es valido o no se encontró");
			}
			
			String password = request.getParameter("password");
			
			Integer eps;
			Integer idEPS = Integer.parseInt(request.getParameter("eps"));
			if(idEPS < 0)
				eps = 1;
			else{
				EpsTo epsTo = daoUtilidades.buscarEps(idEPS);
				if(epsTo != null)
					eps = epsTo.getIdEPS();
				else
					throw new Exception("La EPS no es válida o no se encontró");
			}
			
			if(personaBean.ingresarPersona(nom1, nom2, ap1, ap2, tipoDoc, numDoc, dir, tel, correo, idPerfil, password, eps)){
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

	private void cargueInicial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilBean util = new UtilBean();
		EpsBean  epsBean = new EpsBean();
		ArrayList<PerfilTo> perfiles = new ArrayList<PerfilTo>();
		ArrayList<TipoDocumentoTo> listaDocumentos = new ArrayList<TipoDocumentoTo>();
		ArrayList<EpsTo> epss = new ArrayList<EpsTo>();
		try {
			perfiles = util.consultarPerfil();
			
			if (perfiles != null && perfiles.size() > 0){
				request.setAttribute("listaPerfiles", perfiles);
			}
			
			listaDocumentos = util.consultarTipoDocumento();
			if (listaDocumentos != null && listaDocumentos.size() > 0){
				request.setAttribute("listaDocumentos", listaDocumentos);
			}
			EpsTo epsTo = new EpsTo();
			epss = epsBean.consultarEPS(epsTo);
			if (epss != null && epss.size() > 0){
				request.setAttribute("listaEPS", epss);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("FormularioPersona.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			System.out.println("Error de formulario: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("respuesta", "2");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("respuestaAgregarPersona.jsp");
			dispatcher.forward(request, response);
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