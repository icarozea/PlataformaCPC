package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plataforma.cpc.modelo.EpsBean;
import com.plataforma.cpc.to.EpsTo;

@WebServlet(name = "ServletEPS", urlPatterns = { "/ServletEPS" })
public class ServletEPS extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		EpsBean epsBean;
		EpsTo epsTo;
		String operacion = request.getParameter("operacion");
		System.out.println("Operacion: "+operacion);
		switch (operacion) {
		case "btnAgregarEPS":
			epsBean = new EpsBean();
			if (epsBean.ingresarEPS(request.getParameter("nombre"))) {
				request.setAttribute("mensaje", 1);
				RequestDispatcher dispatcher = request.getRequestDispatcher("verEps.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("mensaje", 2);
				request.getRequestDispatcher("FormularioEPS.jsp").forward(request, response);
			}
			break;
		case "verEPS":
			epsBean = new EpsBean();
			ArrayList<EpsTo> listaEps = new ArrayList<EpsTo>();
			epsTo = new EpsTo();
			listaEps = epsBean.consultarEPS(epsTo);
			if(listaEps.size()>0){
				request.setAttribute("mensaje", 1);
				request.setAttribute("listaEPS", listaEps);
				RequestDispatcher dispatcher = request.getRequestDispatcher("verEps.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("mensaje", 2);
				RequestDispatcher dispatcher = request.getRequestDispatcher("FormularioEPS.jsp");
				dispatcher.forward(request, response);
			}
			break;
		case "btnEditarEPS":
			epsBean = new EpsBean();
			epsTo = new EpsTo();
			epsTo.setIdEPS(Integer.parseInt(request.getParameter("idEPS")));
			System.out.println("idEPS: "+epsTo.getIdEPS().intValue());
			epsTo = epsBean.consultaEPS(epsTo);
			if(epsTo.getIdEPS()!=null){
				request.setAttribute("mensaje", 1);
				request.setAttribute("EPS", epsTo);
				RequestDispatcher dispatcher = request.getRequestDispatcher("editarEps.jsp");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("mensaje", 2);
				RequestDispatcher dispatcher = request.getRequestDispatcher("FormularioEPS.jsp");
				dispatcher.forward(request, response);
			}
			break;
		case "btnModificarEPS":
			epsBean = new EpsBean();
			epsTo = new EpsTo();
			epsTo.setNombreEPS(request.getParameter("nombre"));
			epsTo.setIdEPS(Integer.parseInt(request.getParameter("EpsId")));			
			System.out.println("EpsId: "+epsTo.getIdEPS().intValue());
	
			if(epsBean.modificaEPS(epsTo)){
				request.setAttribute("mensaje", 1);
				RequestDispatcher dispatcher = request.getRequestDispatcher("./ServletEPS?operacion=verEPS");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("mensaje", 2);
				RequestDispatcher dispatcher = request.getRequestDispatcher("editarEps.jsp");
				dispatcher.forward(request, response);
			}
			break;
		case "btnEliminarEPS":
			epsBean = new EpsBean();
			epsTo = new EpsTo();
			epsTo.setIdEPS(Integer.parseInt(request.getParameter("EpsId")));
			System.out.println("EpsId: "+epsTo.getIdEPS().intValue());
			if(epsBean.elminaEPS(epsTo)){
				request.setAttribute("mensaje", 1);
				RequestDispatcher dispatcher = request.getRequestDispatcher("./ServletEPS?operacion=verEPS");
				dispatcher.forward(request, response);
			}else{
				request.setAttribute("mensaje", 2);
				RequestDispatcher dispatcher = request.getRequestDispatcher("FormularioEPS.jsp");
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
