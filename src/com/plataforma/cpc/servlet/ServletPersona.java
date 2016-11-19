package com.plataforma.cpc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.dao.DaoUtilidades;
import com.plataforma.cpc.modelo.PersonaBean;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.TipoDocumentoTo;

/**
 * Servlet implementation class ServletPersona
 */
@WebServlet(name="ServletPersona", urlPatterns = {"/ServletPersona"})
public class ServletPersona extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("do Get");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("do Post");
		
		response.setContentType("text/html;charset=UTF-8");
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
			if(tipoDocumentoTo != null)
				tipoDoc = tipoDocumentoTo.getIdTipoDocumento();
			else
				throw new Exception("El tipo de documento no es valido o no se encontró");

			String numDoc = request.getParameter("numeroDocumento");
			String dir = request.getParameter("direccion");
			Integer tel = Integer.parseInt(request.getParameter("telefono"));
			String correo = request.getParameter("correo");

			Integer idPerfil;
			String nomPerfil = request.getParameter("perfil");
			PerfilTo perfilTo = daoUtilidades.buscarPerfil(nomPerfil);
			if(perfilTo != null)
				idPerfil = perfilTo.getIdPerfil();
			else
				throw new Exception("El perfil seleccionado no es valido o no se encontró");
			
			String password = request.getParameter("password");
			
			
			if(personaBean.ingresarPersona(nom1, nom2, ap1, ap2, tipoDoc, numDoc, dir, tel, correo, idPerfil, password)){
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