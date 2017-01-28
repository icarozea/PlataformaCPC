<%-- 
    Document   : verUsuarios
    Created on : 30/05/2016, 11:53:33 PM
    Author     : HARLIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Usuarios</title>
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
            <h1>VER USUARIO</h1>
            <div id="gestionContenido">
                <table>
                    <tr>
                        <td><a href="./ServletAdministrador?opcion=listAdmin"><img src="resources/empresario.png"/></a></td>
                        <td><a href="./ServletSupervisores?opcion=listSupervisores"><img src="resources/hombre-de-negocios.png"/></a></td>
                        <td><a href="./ServletPracticantes?opcion=listPracticantes"><img src="resources/man.png"/></a></td>
                    </tr>
                    <tr>
                        <td><a href="./ServletAdministrador?opcion=listAdmin">Administrador</a></td>
                        <td><a href="./ServletSupervisores?opcion=listSupervisores">Supervisor</a></td>
                        <td><a href="./ServletPracticantes?opcion=listPracticantes">Practicante</a></td>
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
