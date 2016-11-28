package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.modelo.UsuarioBean;
import com.plataforma.cpc.to.UsuarioTo;

@WebServlet(name = "ServletUsuario", urlPatterns = { "/ServletUsuario" })
public class ServletUsuario extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String operacion = request.getParameter("operacion");
		switch (operacion) {
		case "btnIngresar":
//			usuario = usuarioBean.validarUsuario(request.getParameter("txtname"), request.getParameter("password"));
//			System.out.println(usuario.toString());
//			if (usuario.getIdUsuario() != null) {
//				request.setAttribute("mensaje", 1);
//				RequestDispatcher dispatcher = request.getRequestDispatcher("VentanaAdministrador.jsp");
//				dispatcher.forward(request, response);
//			} else {
//				request.setAttribute("mensaje", 2);
//				System.out.println("usuario no existe");
//				request.getRequestDispatcher("index.jsp").forward(request, response);
//			}
			
			if(request.getParameter("user").equals("admin")){
				request.setAttribute("mensaje", 1);
				RequestDispatcher dispatcher = request.getRequestDispatcher("VentanaAdministrador.jsp");
				dispatcher.forward(request, response);
			}
			else{
				request.setAttribute("mensaje", 1);
				RequestDispatcher dispatcher = request.getRequestDispatcher("VentanaPracticante.jsp");
				dispatcher.forward(request, response);
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
