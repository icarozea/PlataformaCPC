<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Configuracion</title>
        <link rel="stylesheet" href="estilo.css"></link>
    </head>
    <body>

         <%@include file="./menuNavegacionAdmin.jsp" %> 
        <div id="configuracion" class="caja">
            <h1>Configuración</h1>
            <div id="contenido">
                <table class="tablaPrincipal">
                    <tr>                  	 
                    	<td><a href="FormularioEPS.jsp"><img src="resources/eps.png"/></a></td>
                    	<td><a href="./ServletCupos?operacion=Cargar"><img src="resources/cupos.png"/></a></td>  
                    </tr>
                    <tr>             	
                        <td>EPS</td>
                        <td>Cupos</td>                                                  
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