<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" href="estilo.css"></link>
    </head>
    <body>

         <%@include file="./menuNavegacionPracticante.jsp" %> 
        <div id="gestioncitas" class="caja">
            <h1 class="droidSans">INICIO</h1>
            <h2 class="droidSans">Bienvenido ${sessionScope.personaSession.primerNombre}</h2>
            <div id="gestionContenido">
                <table class="tablaPrincipal">
                    <tr>
                    	<td><a href="./ServletPersona?operacion=editarPersona&idPersona=${sessionScope.personaSession.idPersona}"><img src="resources/personas.png"/></a></td>                                       
                        <td><a href="inicioCita.jsp"><img src="resources/appointment.png"/></a></td>                     
                        <td><a href=""><img src="resources/reporte.png"/></a></td>                       
                    </tr>
                    <tr>
                        <td class="droidSans">Mi Perfil</td>
                        <td class="droidSans">Citas</td>
                        <td class="droidSans">Reportes</td>                       
                    </tr>
                </table>
            </div>
            <br>
         <div>
			<a href="/PlataformaCPC/Logout"><button id = "logoutBtn" class="btnLogout btnLogout-danger">Cerrar sesión</button></a>
		</div>
        </div>
        
        <footer>
            <small>Fundación Universitaria Konrad Lorenz</small>
            <address>www.konradlorenz.edu.co</address>
        </footer> 
    </body>
</html>