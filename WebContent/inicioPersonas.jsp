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
            <h1>Personas</h1>
            <div id="gestionContenido">
                <table>
                    <tr>
                    	<td><a href="FormularioEPS.jsp"><img src="resources/expediente.png"/></a></td>  
                    	<td><a href="FormularioPersona.jsp"><img src="resources/man.png"/></a></td>
                    </tr>
                    <tr>
                    	<td>EPS</td> 
                        <td>Persona</td>                                               
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
</html>