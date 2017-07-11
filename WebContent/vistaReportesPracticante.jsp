<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="estilo.css"></link>
<link rel="stylesheet" href="estiloAsignaciones.css"></link>
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>Reportes Practicante</title>
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
		<br>
		<h2 class="droidSans">Verificación de reportes de Sesión Individual emitidos por el practicante</h2>
		<div id="gestionContenido">
			<table id="tablaUsuarios">
				<thead>
					<th>Cita número</th>
					<th>Salón</th>
					<th>Nombre Paciente</th>
					<th>Estado</th>
					<th>Fecha</th>
					<th>Revisar Evaluación</th>
				</thead>
				<tbody>
						<c:forEach items="${requestScope.valoracionesPreviewPracticante}" var="valoraciones">
							<tr>
								<td>0</td>
								<td>${valoraciones.salon}</td>
								<td>${valoraciones.primerNombrePaciente} ${valoraciones.segundoNombrePaciente} ${valoraciones.primerApellidoPaciente} ${valoraciones.segundoApellidoPaciente}</td>
								<td>${valoraciones.estado}</td>
								<td>${valoraciones.fecha}</td>
								<td><a href="ReportesPracticante?operacion=detallesValoracion&idCita=${valoraciones.idCita}"><input type="button" id="btnComentarios" class="btnAsignar"></a></td>
							</tr>
						</c:forEach>
						<c:forEach items="${requestScope.reportesPreviewPracticante}" var="reportePreviewPracticante">
							<tr>
								<td>${reportePreviewPracticante.numCita}</td>
								<td>${reportePreviewPracticante.salon}</td>
								<td>${reportePreviewPracticante.primerNombrePaciente} ${reportePreviewPracticante.segundoNombrePaciente} ${reportePreviewPracticante.primerApellidoPaciente} ${reportePreviewPracticante.segundoApellidoPaciente}</td>
								<td>${reportePreviewPracticante.estado}</td>
								<td>${reportePreviewPracticante.fecha}</td>
								<td><a href="ReportesPracticante?operacion=comentariosReporte&idCita=${reportePreviewPracticante.idCita}"><input type="button" id="btnComentarios" class="btnAsignar"></a></td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>	
		<br>
		<div>
			<a href="inicioReporte.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
</body>
</html>