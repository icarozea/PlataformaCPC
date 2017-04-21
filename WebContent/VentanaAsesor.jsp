<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" href="estilo.css"></link>
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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

        <%@include file="./menuNavegacionAsesor.jsp" %>
        <div id="gestioncitas">
        	<h1 class="cabin">INICIO</h1>
			<h2 class="cabin">Bienvenido ${sessionScope.personaSession.primerNombre}</h2>
			<div id="gestionContenido">
				<table class="tablaPrincipal">
					<tr>
						<td>
							<div>
								<a href="./ServletAsesor?operacion=practicantes&idAsesor=${sessionScope.personaSession.idPersona}"><button class="btn-xlarge"><i class="fa fa-users fa-4x" ></i></button></a>
							</div>
						</td>
						<td>
							<div>
								<a href="./ServletPersona?operacion=editarPersona&idPersona=${sessionScope.personaSession.idPersona}"><button class="btn-xlarge"><i class="fa fa-user-md fa-4x" ></i></button></a>
							</div>
						</td>
					</tr>
					<tr>
						<td class="cabin"><a href="./ServletAsesor?operacion=practicantes&idAsesor=${sessionScope.personaSession.idPersona}">MIS PRACTICANTES</a></td>
						<td class="cabin"><a href="./ServletPersona?operacion=editarPersona&idPersona=${sessionScope.personaSession.idPersona}">MI PERFIL</a><br></td>
					</tr>
				</table>
			</div>
			<br>
			<div>
				<a href="/PlataformaCPC/Logout"><button id="logoutBtn" class="btnLogout btnLogout-danger">Cerrar sesión</button></a>
			</div>
			<br>
        </div>
        
        <footer>
            <small>Fundación Universitaria Konrad Lorenz</small>
            <address>www.konradlorenz.edu.co</address>
        </footer> 
    </body>
</html>