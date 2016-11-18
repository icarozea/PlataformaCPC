<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilo.css"></link>
        <title>Admin/Gestion de Usuarios</title>
    </head>
    <body>
        <%@include file="./menuNavegacion.jsp" %>
       <div id="gestionUsuarios" class="caja">
                <h1>GESTION DE USUARIOS</h1>
                <div id="gestionContenido">
                    <table>
                        <tr>
                            <td><a href="agregarusuarios.jsp"><img src="resources/agregarusuario.png"/></a></td>
                            <td><a href="verUsuarios.jsp"><img src="resources/asignar.png"/></a></td>
                        </tr>
                        <tr>
                            <td><a href="agregarusuarios.jsp">Crear Usuario</a></td>
                            <td><a href="verUsuarios.jsp">Ver Usuarios</a></td>
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