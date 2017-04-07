<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inicio</title>
<link rel="stylesheet" href="estilo.css"></link>
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
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

	<%@include file="./menuNavegacionPracticante.jsp"%>
	<div id="gestioncitas">
		<h1 class="droidSans">INICIO</h1>
		<h2 class="droidSans">Bienvenido
			${sessionScope.personaSession.primerNombre}</h2>
		<div id="gestionContenido">
			<table class="tablaPrincipal">
				<tr>
					<td>
						<div>
							<a
								href="./ServletPersona?operacion=editarPersona&idPersona=${sessionScope.personaSession.idPersona}"><button
									class="btn-xlarge">
									<i class="fa fa-users fa-4x"></i>
								</button></a>
						</div>
					</td>
					<td>
						<div>
							<a href="inicioCita.jsp"><button class="btn-xlarge">
									<i class="fa fa-calendar fa-4x"></i>
								</button></a>
						</div>
					</td>
				</tr>
				<tr>
					<td class="cabin"><a
						href="./ServletPersona?operacion=editarPersona&idPersona=${sessionScope.personaSession.idPersona}">MI
							PERFIL</a></td>
					<td class="cabin"><a href="inicioCita.jsp">CITAS</a><br></td>
				</tr>
			</table>

			<table class="tablaPrincipal">
				<tr>
					<td>
						<div>
							<a href="./ReportesPracticante?operacion=visualizarReportes"><button class="btn-xlarge">
									<i class="fa fa-file-text fa-4x"></i>
								</button></a>
						</div>
					</td>
				</tr>
				<tr>
					<td class="cabin"><a href="./ReportesPracticante?operacion=visualizarReportes">REPORTES</a><br></td>
				</tr>
			</table>
		</div>
		<br>
		<div>
			<a href="/PlataformaCPC/Logout"><button id="logoutBtn"
					class="btnLogout btnLogout-danger">Cerrar sesión</button></a>
		</div>
	</div>
</body>
</html>