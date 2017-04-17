<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="estilo.css"></link>
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<title>Reportes practicante</title>
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
		<h1 class="droidSans">Gestión de Reportes</h1>
		<h2 class="droidSans">Verificación de reportes de Sesión Individual emitidos por el practicante</h2>
		<div id="gestionContenido">
			<table class="tablaPrincipal">
				<tr>
					<td>
						<div>
							<a
								href="./ReportesPracticante?operacion=reportesAprobados&idPracticante=${requestScope.idPracticante}"><button class="btn-xlarge">
									<i class="fa fa-check-square-o fa-4x"></i>
								</button></a>
						</div>
					</td>
					<td>
						<div>
							<a href="./ReportesPracticante?operacion=reportesPendientes&idPracticante=${requestScope.idPracticante}"><button class="btn-xlarge">
									<i class="fa fa-exclamation-triangle fa-4x"></i>
								</button></a>
						</div>
					</td>
				</tr>
				<tr>
					<td class="cabin"><a href="./ReportesPracticante?operacion=reportesAprobados&idPracticante=${requestScope.idPracticante}">MIS REPORTES APROBADOS</a></td>
					<td class="cabin"><a href="./ReportesPracticante?operacion=reportesPendientes&idPracticante=${requestScope.idPracticante}">MIS REPORTES PENDIENTES</a><br></td>
				</tr>
			</table>
		</div>
	</div>
			<br>
		<div>
			<a href="VentanaPracticante.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
</body>
</html>