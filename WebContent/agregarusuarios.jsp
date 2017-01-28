<%-- 
    Document   : agregarusuarios
    Created on : 21/05/2016, 06:58:38 PM
    Author     : HARLIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AgregarUsuario</title>
        <link rel="stylesheet" href="estilo.css"></link>
    </head>
		<%
		  if (session.getAttribute("perfil")==null)
		  {
		    String address = "/index.jsp";
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
		    dispatcher.forward(request,response);
		  }
		%>    
    <body>
         <%@include file="./menuNavegacionAdmin.jsp" %>
        <div id="gestioncitas" class="caja">
            <h1>AGREGAR USUARIO</h1>
            <div id="gestionContenido">
                <table>
                    <tr>
                        <td><a href="FormularioAdministrador.jsp"><img src="resources/empresario.png"/></a></td>
                        <td><a href="FormularioSupervisor.jsp"><img src="resources/hombre-de-negocios.png"/></a></td>
                        <td><a href="FormularioPracticante.jsp"><img src="resources/man.png"/></a></td>
                    </tr>
                    <tr>
                        <td><a href="FormularioAdministrador.jsp">Administrador</a></td>
                        <td><a href="FormularioSupervisor.jsp">Supervisor</a></td>
                        <td><a href="FormularioPracticante.jsp">Practicante</a></td>
                    </tr>
                </table>
            </div>
        </div>
        <footer>
            <small>Fundación Universitaria Konrad Lorenz</small>
            <address>www.konradlorenz.edu.co</address>
        </footer> 
    </body>
</html>
