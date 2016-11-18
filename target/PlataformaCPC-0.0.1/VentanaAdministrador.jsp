<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="estilo.css"></link>
    </head>
    <body>

         <%@include file="./menuNavegacion.jsp" %> 
        <div id="gestioncitas" class="caja">
            <h1>INICIO</h1>
            <div id="gestionContenido">
                <table>
                    <tr>
                        <td><a href="GestiondeUsuarios.jsp"><img src="resources/empresario.png"/></a></td>
                        <td><a href="GestiondePacientes.jsp"><img src="resources/boy-4.png"/></a></td>
                        <td><a href="inicio_calendario.jsp"><img src="resources/agenda.png"/></a></td>
                        <td><a href="reportes.jsp"><img src="resources/expediente.png"/></a></td>                        
                    </tr>
                    <tr>
                        <td>Usuarios</td>
                        <td>Pacientes</td>
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