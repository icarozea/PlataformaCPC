<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="estilo.css"></link>
    </head>
    <body>

         <%@include file="./menuNavegacionPracticante.jsp" %> 
        <div id="gestioncitas" class="caja">
            <h1>Mis Citas</h1>
            <div id="gestionContenido">
                <table class="tablaPrincipal">
                    <tr>                  	 
                    	<td><a href="FormularioPersona.jsp"><img src="resources/paciente.png"/></a></td>
                    	<td><a href="./ServletCita?operacion=cargueIncial"><img src="resources/agendaCitas.png"/></a></td>
                    	<td><a href="./ServletCita?operacion=cargueIncial"><img src="resources/newCita.png"/></a></td>
                    </tr>
                    <tr>             	
                        <td>Mis Pacientes</td>
                        <td>Agenda</td>
                        <td>Crear Cita</td>                                                  
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