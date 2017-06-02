package com.plataforma.cpc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.plataforma.cpc.modelo.UsuarioBean;
import com.plataforma.cpc.to.PersonaTo;

/**
 * Servlet que maneja el inicio de sesión para entrar al portal web
 */
@WebServlet(name = "ServletUsuario", urlPatterns = { "/ServletUsuario" })
public class ServletUsuario extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String operacion = request.getParameter("operacion");
		PersonaTo personaSesion = new PersonaTo();
		UsuarioBean usuarioBean = new UsuarioBean();
		HttpSession session = request.getSession(true);

		switch (operacion) {
		case "btnIngresar":
			personaSesion = usuarioBean.validarUsuario(request.getParameter("user"), request.getParameter("password"));

			if (personaSesion.getIdPersona() != null) {

				if(personaSesion.getNumeroDocumento().equals("000")){
					request.setAttribute("mensaje", "3");
					System.out.println("Clave incorrecta");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else {
					session.setAttribute("personaSession", personaSesion);
					request.setAttribute("mensaje", "1");
					session.setAttribute("perfil", personaSesion.getPerfil().getNombrePerfil());
					if(personaSesion.getPerfil().getNombrePerfil().equals("Administrador")){
						RequestDispatcher dispatcher = request.getRequestDispatcher("VentanaAdministrador.jsp");
						dispatcher.forward(request, response);
					}
						else if(personaSesion.getPerfil().getNombrePerfil().equals("Practicante")){
							RequestDispatcher dispatcher = request.getRequestDispatcher("VentanaPracticante.jsp");
							dispatcher.forward(request, response);
						}
						else{
							RequestDispatcher dispatcher = request.getRequestDispatcher("VentanaAsesor.jsp");
							dispatcher.forward(request, response);
						}
					}		
				} 
				else {
					request.setAttribute("mensaje", "2");
					System.out.println("usuario no existe");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				break;
			default:
				System.out.println("Opción no existe");
				break;
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