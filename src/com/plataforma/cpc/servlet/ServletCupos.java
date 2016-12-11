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
import com.plataforma.cpc.to.EpsTo;

@WebServlet(name = "ServletCupos", urlPatterns = { "/ServletCupos" })
public class ServletCupos extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String operacion = request.getParameter("operacion");
		
		switch (operacion) {
		case "Cargar":
			try{
				DaoUtilidades utils = new DaoUtilidades();
				request.setAttribute("cupos", utils.buscarCuposActuales());
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("FormularioCupos.jsp");
				dispatcher.forward(request, response);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			break;
		case "Actualizar":
			try{
				DaoUtilidades utils = new DaoUtilidades();
				
				if(utils.actualizarCupos(Integer.parseInt(request.getParameter("cupos")))){
					request.setAttribute("cupos", utils.buscarCuposActuales());
					request.setAttribute("mensaje", "Cupos Actualizados");
					RequestDispatcher dispatcher = request.getRequestDispatcher("./ServletCupos?operacion=Cargar");
					dispatcher.forward(request, response);
				}
				else{
					request.setAttribute("cupos", utils.buscarCuposActuales());
					request.setAttribute("mensaje", "No fue posible realizar la operación");
					RequestDispatcher dispatcher = request.getRequestDispatcher("./ServletCupos?operacion=Cargar");
					dispatcher.forward(request, response);
				}
			}
			catch(Exception e){
				e.printStackTrace();
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