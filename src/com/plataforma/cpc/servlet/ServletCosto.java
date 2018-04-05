package com.plataforma.cpc.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.dao.DaoUtilidades;

/**
 * Servlet implementation class ServletCosto
 */
@WebServlet(name = "ServletCosto", urlPatterns = { "/ServletCosto" })
public class ServletCosto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
		String operacion = request.getParameter("operacion");
		
		switch (operacion) {
		case "Cargar":
			try{
				DaoUtilidades utils = new DaoUtilidades();
				request.setAttribute("precio", utils.consultarPrecioActual());
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("FormularioPrecio.jsp");
				dispatcher.forward(request, response);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			break;
		case "Actualizar":
			try{
				DaoUtilidades utils = new DaoUtilidades();
				
				if(utils.actualizarPrecioActual(Integer.parseInt(request.getParameter("costo")))){
					request.setAttribute("precio", utils.consultarPrecioActual());
					request.setAttribute("mensaje", "Precio Actualizado");
					RequestDispatcher dispatcher = request.getRequestDispatcher("./ServletCosto?operacion=Cargar");
					dispatcher.forward(request, response);
				}
				else{
					request.setAttribute("precio", utils.consultarPrecioActual());
					request.setAttribute("mensaje", "No fue posible realizar la operación");
					RequestDispatcher dispatcher = request.getRequestDispatcher("./ServletCosto?operacion=Cargar");
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}