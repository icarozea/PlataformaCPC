<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personas</title>
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
            <h1>Personas</h1>
            <div id="gestionContenido">
                <table class="tablaPrincipal">
                    <tr>                  	 
                    	<!-- <td><a href="FormularioPersona.jsp"><img src="resources/man.png"/></a></td>-->
                    	<td><a href="./ServletPersona?operacion=cargueIncial"><img src="resources/agregarPersona.png"/></a></td>
                    	<td><a href="busquedaPersonas"><img src="resources/buscar.png"/></a></td>
                    	<td><a href="InicioConfiguracion.jsp"><img src="resources/config.png"/></a></td>  
                    </tr>
                    <tr>             	
                        <td>Crear</td>
                        <td>Buscar</td>
                        <td>Configuración</td>                                                  
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