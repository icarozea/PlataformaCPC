<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" href="estilo.css"></link>
    </head>
    <body>

         <%@include file="./menuNavegacionAdmin.jsp" %> 
        <div id="gestioncitas" class="caja">
            <h1>INICIO</h1>
            <h2>Bienvenido ${sessionScope.personaSession.primerNombre}</h2>
            <div id="gestionContenido">
                <table class="tablaPrincipal">
                    <tr>

                    	<td><a href="inicioPersonas.jsp"><img src="resources/personas.png"/></a></td>                                       
                        <td><a href="./ServletCita?operacion=cargueIncial"><img src="resources/appointment.png"/></a></td>                     
                        <td><a href=""><img src="resources/reporte.png"/></a></td>                      

                    </tr>
                    <tr>
                        <td>Personas</td>
                        <td>Citas</td>
                        <td>Reportes</td>                         
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