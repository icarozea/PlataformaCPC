<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilo.css"></link>
        <title>Admin/Reportes</title>
    </head>
    <body>
        <%@include file="./menuNavegacionAdmin.jsp" %>
        <div id="gestioncitas" class="caja">
            <h1>REPORTES Y DOCUMENTOS</h1>
            <table>
                <tr>
                    <td><a href=""><img src="resources/bloc.png"/></a></td>
                    <td><a href="sesion.jsp"><img src="resources/expediente.png"/></a></td>
                    <td><a href=""><img src="resources/expediente.png"/></a></td>
                </tr>
                <tr>
                    <td><a href="">Consentimiento</a></td>
                    <td><a href="sesion.jsp">RIPS</a></td>
                    <td><a href="">Estado de Caso</a></td>
                </tr>                
            </table>
            
        </div>
    </body>
</html>
