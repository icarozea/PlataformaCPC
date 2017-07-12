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
@WebServlet(name="ServletPersona", urlPatterns = {"/ServletPersona"})
public class ServletPersona extends HttpServlet {

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
		
		case "cargueIncial":
			cargueInicial(request,response);
			break;
		case "guardarPersona":
			guardarPersona(request,response);
			break;
		case "listarPersonas":
			listarPersonas(request,response);
			break;
		case "listarPacientes":
			listarPacientes(request,response);
			break;
		case "editarPersona":
			editarPersonas(request,response);
			break;
		case "actualizarDatos":
			actualizarDatos(request,response);
			break;
		case "eliminarPersona":
			eliminarPersonas(request,response);
			break;
		case "detallePersona":
			detallePersona(request,response);
			break;
		case "irEPS":
			irEPS(request,response);
			break;
		default:
			System.out.println("Opción no existe");
			break;
		
		}
	}
	
	private void irEPS(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		  RequestDispatcher dispatcher = request.getRequestDispatcher("FormularioEPS.jsp");		  
		  request.setAttribute("idPersona", 101);
		  dispatcher.forward(request, response);
	}
	
	/**
	 * Responde a una petición de eliminar una persona con el id presente en el request
	 * Redirige a la pagina de respuestaEliminarPersona con el mensaje apropiado
	 */
	private void detallePersona(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		  RequestDispatcher dispatcher = request.getRequestDispatcher("editarPersonaDetalle.jsp");		  
		  request.setAttribute("idPersona", 0);
		  request.setAttribute("personaDetalle", new PersonaDetalleTo());
		  dispatcher.forward(request, response);
	}
//-----------------------------------------------------------------------------------------------------
// Funciones
//-----------------------------------------------------------------------------------------------------
	
	/**
	 * Responde a una petición de eliminar una persona con el id presente en el request
	 * Redirige a la pagina de respuestaEliminarPersona con el mensaje apropiado
	 */
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
	
	/**
	 * Responde a una petición de ediciones de persona y puebla el formulario de personas con la informacion
	 * de la persona cuyo ID está presente en el request.
	 * Redirige al formulario de personas
	 */
	private void editarPersonas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PersonaBean personaBean = new PersonaBean();
		PersonaTo personaTo = new PersonaTo();
		personaTo.setIdPersona(new Integer(request.getParameter("idPersona")));
		
		PersonaTo persona = new PersonaTo();
		persona = personaBean.consultarPersona(personaTo);
		
		request.setAttribute("idPersona", persona.getIdPersona());
		request.setAttribute("pNom", persona.getPrimerNombre());
		request.setAttribute("sNom", persona.getSegundoNombre());
		request.setAttribute("pApe", persona.getPrimerApellido());
		request.setAttribute("sApe", persona.getSegundoApellido());
		request.setAttribute("doc", persona.getTipoDocumento().getSigla());
		request.setAttribute("num", persona.getNumeroDocumento());
		request.setAttribute("dir", persona.getDireccion());
		request.setAttribute("tel", persona.getTelefono());
		request.setAttribute("tel2", persona.getOtroTelefono());
		request.setAttribute("mail", persona.getCorreo());
		request.setAttribute("perfil", persona.getPerfil().getIdPerfil());
		request.setAttribute("idPersona", persona.getIdPersona());
		request.setAttribute("pass", persona.getPassword());
		request.setAttribute("jornada", persona.getJornada());
		request.setAttribute("sup", persona.getSuperior());
		
		if (persona.getPerfil().getIdPerfil().equals("Practicante")) {
			request.setAttribute("cod", persona.getCodigoEstudiante());
		}else if(persona.getPerfil().getIdPerfil().equals("Paciente")){
			request.setAttribute("eps", persona.getEps().getNombreEPS());
		}
		
		cargueInicial(request,response);
	}
	
	/**
	 * Responde a la petición de ver el detalle de una persona
	 * Redirige a la pagina verPersonas  que consecuentemente ofrece la opción de editar o eliminar una persona
	 */
	private void listarPersonas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			PersonaBean personaBean = new PersonaBean();
			PersonaTo consulta = new PersonaTo();
			consulta.setIdPersona(Integer.parseInt(request.getParameter("id")));
			PersonaTo persona = personaBean.consultarPersona(consulta);
			
			request.setAttribute("pNom", persona.getPrimerNombre());
			request.setAttribute("sNom", persona.getSegundoNombre());
			request.setAttribute("pApe", persona.getPrimerApellido());
			request.setAttribute("sApe", persona.getSegundoApellido());
			request.setAttribute("doc", persona.getTipoDocumento().getSigla());
			request.setAttribute("num", persona.getNumeroDocumento());
			request.setAttribute("dir", persona.getDireccion());
			request.setAttribute("tel", persona.getTelefono());
			request.setAttribute("tel2", persona.getOtroTelefono());
			request.setAttribute("mail", persona.getCorreo());
			request.setAttribute("perfil", persona.getPerfil().getNombrePerfil());
			request.setAttribute("idPersona", persona.getIdPersona());
			request.setAttribute("jornada", persona.getJornada());
			
			if (persona.getPerfil().getNombrePerfil().equals("Practicante")) {
				request.setAttribute("cod", persona.getCodigoEstudiante());
			}else if(persona.getPerfil().getNombrePerfil().equals("Paciente")){
				request.setAttribute("eps", persona.getEps().getNombreEPS());
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("verPersonas2.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			System.out.println("Error de Busqueda: " + e.getMessage());
			e.printStackTrace();
			request.setAttribute("valor", "Practicante");
			RequestDispatcher dispatcher = request.getRequestDispatcher("verPersonas2.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	/**
	 * Responde a la peticion de listar todos los pacientes de un practicante especifico, cuyo id esta en el request
	 * Redirige a la pagina de verPacientes, que consecuentemente redirige a la creación de citas
	 */
	private void listarPacientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{		
			PersonaBean personaBean = new PersonaBean();
			ArrayList<PersonaTo> listaPacientes = new ArrayList<PersonaTo>();
			
			PersonaTo practicante = (PersonaTo)request.getSession().getAttribute("personaSession");
			listaPacientes = personaBean.consultarAsignados(practicante.getIdPersona());
			
			request.setAttribute("listaPacientes", listaPacientes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("verPacientes.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			System.out.println("Error de Busqueda: " + e.getMessage());
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("verPacientes.jsp");
			dispatcher.forward(request, response);
		}	
	}
	
	/**
	 * Responde a la peticion de guardar una nueva persona en la base de datos con la informacion capturada en el formulario
	 * Redirige a la pagina de respuestaAgregarPersona
	 */
	private void guardarPersona(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("editarPersonaDetalle.jsp");
		
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
			
			Long tel = (!request.getParameter("telefono").equals(""))? new Long(request.getParameter("telefono")) : 0;
			String otroTel = request.getParameter("telefono2");
			Long tel2 = !otroTel.equals("")? new Long(otroTel) : 0;
			String correo = request.getParameter("correo");

			Integer idPerfil = (!request.getParameter("perfil").equals(""))? new Integer(request.getParameter("perfil")) : 0;
			PerfilTo perfilTo = daoUtilidades.buscarPerfil(idPerfil);
			if(idPerfil > 0 || perfilTo != null)
				idPerfil = perfilTo.getIdPerfil();
			else{
				System.out.println("Texto: " + idPerfil + " Busqueda: " + perfilTo);
				throw new Exception("El perfil seleccionado no es valido o no se encontró");
			}
			
			String password = request.getParameter("password");
			String jornada = request.getParameter("jornada");
			String cod = request.getParameter("codigo");
			Integer codigo = !cod.equals("")? Integer.parseInt(cod) : 0;
			
			Integer eps;
			Integer idEPS = Integer.parseInt(request.getParameter("eps"));
			if(idEPS < 0)
				eps = 0;
			else{
				EpsTo epsTo = daoUtilidades.buscarEps(idEPS);
				if(epsTo != null)
					eps = epsTo.getIdEPS();
				else
					throw new Exception("La EPS no es válida o no se encontró");
			}
			
			int idPersona = personaBean.ingresarPersona(nom1, nom2, ap1, ap2, tipoDoc, numDoc, dir, tel, tel2, correo, idPerfil, password, eps, jornada, codigo);
			
			if(idPersona != -1){
				PersonaDetalleTo detalleTo = new PersonaDetalleTo();
				detalleTo.setPersonaId(idPersona);
				PersonaDetalleBean detalleBean = new PersonaDetalleBean();
				if(detalleBean.ingresarDetallePersona(detalleTo)){
					request.setAttribute("respuesta", "1");
					request.setAttribute("idPersona", idPersona);
					request.setAttribute("error", "");
					dispatcher.forward(request, response);
				}
				else{
					throw new Exception("Hubo un error al intentar guardar la información");
				}
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
			dispatcher = request.getRequestDispatcher("respuestaAgregarPersona.jsp");
			dispatcher.forward(request, response);
		}		
	}
	
	/**
	 * Responde a una funcion de actualizar los datos de una persona ya existente en la base de datos
	 * Redirige a la pagina de respuestaAgregarPersona
	 */
	private void actualizarDatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("editarPersonaDetalle.jsp");
		
		PersonaBean personaBean = new PersonaBean();
		try{
			DaoUtilidades daoUtilidades = new DaoUtilidades();
			
			Integer id = Integer.parseInt(request.getParameter("id"));
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
	
			Long tel = (!request.getParameter("telefono").equals(""))? new Long(request.getParameter("telefono")) : 0;

			Long tel2 = (!request.getParameter("telefono2").equals(""))? new Long(request.getParameter("telefono2")) : 0;
			String correo = request.getParameter("correo");

			Integer idPerfil = (!request.getParameter("perfil").equals(""))? new Integer(request.getParameter("perfil")) : 0;
			PerfilTo perfilTo = daoUtilidades.buscarPerfil(idPerfil);
			if(idPerfil > 0 || perfilTo != null)
				idPerfil = perfilTo.getIdPerfil();
			else{
				System.out.println("Texto: " + idPerfil + " Busqueda: " + perfilTo);
				throw new Exception("El perfil seleccionado no es valido o no se encontró");
			}
			
			String password = request.getParameter("password");
			String jornada = request.getParameter("jornada");
			
			Integer codigo = 0;
			if(!request.getParameter("codigo").equals("")){
				codigo = new Integer(request.getParameter("codigo"));
			}
			Integer eps;
			Integer idEPS = (!request.getParameter("eps").equals(""))? new Integer(request.getParameter("eps")) : 0;
			if(idEPS < 0)
				eps = 1;
			else{
				EpsTo epsTo = daoUtilidades.buscarEps(idEPS);
				if(epsTo != null)
					eps = epsTo.getIdEPS();
				else
					throw new Exception("La EPS no es válida o no se encontró");
			}
			
			String superior = request.getParameter("superior");
			Integer idSuperior = superior.equals("") ? 0 : Integer.parseInt(superior); 
			if(personaBean.modificarPersona(id, nom1, nom2, ap1, ap2, tipoDoc, numDoc, dir, tel, tel2, correo, idPerfil, password, eps, idSuperior, jornada, codigo)){
				PersonaDetalleBean personaDetalle = new PersonaDetalleBean();
				PersonaDetalleTo personaDetalleTo = new PersonaDetalleTo();
				personaDetalleTo.setPersonaId(id);
				personaDetalleTo = personaDetalle.consultarPersonaDetalle(personaDetalleTo);
				personaDetalleTo.setPersonaId(id);
				request.setAttribute("personaDetalle", personaDetalleTo);
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

	/**
	 * Carga la información de los campos desplegables del formulario (eps, tipos de documentos y perfiles)
	 * Redirige al formulario de personas
	 */
	private void cargueInicial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilBean util = new UtilBean();
		EpsBean  epsBean = new EpsBean();
		ArrayList<PerfilTo> perfiles = new ArrayList<PerfilTo>();
		ArrayList<TipoDocumentoTo> listaDocumentos = new ArrayList<TipoDocumentoTo>();
		ArrayList<EpsTo> epss = new ArrayList<EpsTo>();
		try {
			perfiles = util.consultarPerfiles();
			
			if (perfiles != null && perfiles.size() > 0){
				request.setAttribute("listaPerfiles", perfiles);
			}
			
			listaDocumentos = util.consultarTiposDocumento();
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
}