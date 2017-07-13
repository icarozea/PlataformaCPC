package com.plataforma.cpc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
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
			request.setAttribute("formulario", request.getParameter("formulario"));
			request.setAttribute("pNom", request.getParameter("nombre1"));
			request.setAttribute("sNom", request.getParameter("nombre2"));
			request.setAttribute("pApe", request.getParameter("apellido1"));
			request.setAttribute("sApe", request.getParameter("apellido2"));
			request.setAttribute("doc", request.getParameter("tipoDocumento"));
			request.setAttribute("num", request.getParameter("numeroDocumento"));
			request.setAttribute("dir", request.getParameter("direccion"));
			request.setAttribute("tel", request.getParameter("telefono"));
			request.setAttribute("tel2", request.getParameter("telefono2"));
			request.setAttribute("mail", request.getParameter("correo"));
			request.setAttribute("perfil", request.getParameter("perfil"));
			request.setAttribute("pass", request.getParameter("password"));
			request.setAttribute("jornada", request.getParameter("jornada"));
			request.setAttribute("cod", request.getParameter("codigo"));
			request.setAttribute("eps", request.getParameter("eps"));
			
			epsBean = new EpsBean();
			if (epsBean.ingresarEPS(request.getParameter("nombre"))) {
				request.setAttribute("mensaje", 1);
				RequestDispatcher dispatcher = request.getRequestDispatcher("FormularioEPS.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("mensaje", 2);
				request.getRequestDispatcher("FormularioEPS.jsp").forward(request, response);
			}
			break;
		case "verEPS":
			ArrayList<EpsTo> listaEps = buscarEPS();
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
			epsTo.setIdEPS(Integer.parseInt(request.getParameter("idEPS")));
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
	
	public ArrayList<EpsTo> buscarEPS(){
		EpsBean epsBean = new EpsBean();
		ArrayList<EpsTo> listaEps = new ArrayList<EpsTo>();
		EpsTo epsTo = new EpsTo();
		return epsBean.consultarEPS(epsTo);
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