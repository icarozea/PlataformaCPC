<%-- 
    Document   : GestiondePacientes
    Created on : 21/05/2016, 04:57:36 PM
    Author     : HARLIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilo.css"></link>
        <title>Admin/GestiondePacientes</title>
    </head>
    <body>
        <%@include file="./menuNavegacion.jsp" %>
       <div id="gestioncitas" class="caja">
                <h1>GESTION DE PACIENTES</h1>
                <table>
                    <tr>
                        <td><a href="FormularioPaciente.jsp"><img src="resources/boy-4.png"/></a></td>
                        <td><a href="./ServletPacientes?opcion=listPaciente"><img src="resources/chica.png"/></a></td>
                    </tr>
                    <tr>
                        <td><a href="FormularioPaciente.jsp">Crear Paciente</a></td>
                        <td><a href="./ServletPacientes?opcion=listPaciente">Ver Paciente</a></td>
                    </tr>                        
                </table>    
            </div>
        <footer>
            <small>Fundación Universitaria Konrad Lorenz</small>
            <address>www.konradlorenz.edu.co</address>
        </footer> 
    </body>
</html>
