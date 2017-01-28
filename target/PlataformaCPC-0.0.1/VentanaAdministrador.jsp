<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inicio</title>
<link rel="stylesheet" href="estilo.css"></link>
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"></link> -->
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
	<%@include file="./menuNavegacionAdmin.jsp"%>
	<div id="gestioncitas" class="caja">
		<h1 class="droidSans">INICIO</h1>
		<h2 class="droidSans">Bienvenido ${sessionScope.personaSession.primerNombre}</h2>
		<div id="gestionContenido">
			<table class="tablaPrincipal">
				<tr>

					<td><a href="inicioPersonas.jsp"><img
							src="resources/personas.png" /></a></td>
					<td><a href="./ServletCita?operacion=cargueIncial"><img
							src="resources/appointment.png" /></a></td>
					<td><a href=""><img src="resources/reporte.png" /></a></td>

				</tr>
				<tr>
					<td class="droidSans">Personas</td>
					<td class="droidSans">Citas</td>
					<td class="droidSans">Reportes</td>
				</tr>
			</table>
		</div>
		<br>
		<div>
			<button id = "logoutBtn" class="btnLogout btnLogout-danger">Cerrar sesión</button>
		</div>
	</div>

	<footer>
		<small>Fundación Universitaria Konrad Lorenz</small>
		<address>www.konradlorenz.edu.co</address>
	</footer>
</body>
</html>