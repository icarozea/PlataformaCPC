<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" href="estilo.css"></link>
    </head>
    <body>

         <%@include file="./menuNavegacion.jsp" %> 
        <div id="gestioncitas" class="caja">
            <h1>INICIO</h1>
            <div id="gestionContenido">
                <table class="tablaPrincipal">
                    <tr>
                    	<td><a href="./ServletPersona?operacion=editarPersona&idPersona=30"><img src="resources/personas.png"/></a></td>
                        <td><a href="GestiondeUsuarios.jsp"><img src="resources/appointment.png"/></a></td>                     
                        <td><a href="inicio_calendario.jsp"><img src="resources/reporte.png"/></a></td>                      
                    </tr>
                    <tr>
                        <td>Mi Perfil</td>
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