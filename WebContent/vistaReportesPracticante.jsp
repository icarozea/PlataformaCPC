<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="estilo.css"></link>
<link rel="stylesheet" href="estiloAsignaciones.css"></link>
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
		<h1 class="droidSans">Gesti�n de Reportes</h1>
		<br>
		<h2 class="droidSans">Verificaci�n de reportes de Sesi�n Individual emitidos por el practicante</h2>
		<div id="gestionContenido">
			<table id="tablaUsuarios">
				<thead>
					<th>Cita n�mero</th>
					<th>Sal�n</th>
					<th>Nombre Paciente</th>
					<th>Estado</th>
					<th>Fecha</th>
					<th>Revisar Evaluaci�n</th>
				</thead>
				<tbody>
						<c:forEach items="${requestScope.reportesPreviewPracticante}" var="reportePreviewPracticante">
							<tr>
								<td>${reportePreviewPracticante.idCita}</td>
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
		<h2 class="droidSans">Generaci�n mensual de reportes</h2>
		<div id="generacionReportes">
			<table class="tablaPrincipal">
				<tr>
					<td>
						<div>
							<a href="./ServletReporte?operacion=usuarios"><button class="btn-xlarge"><i class="fa fa-file-text fa-4x" ></i></button></a>
						</div>
					</td>
					<td>
						<div>
							<a href="./ServletReporte?operacion=transacciones"><button class="btn-xlarge"><i class="fa fa-file-text fa-4x" ></i></button></a>
						</div>
					</td>
				</tr>
				<tr>
					<td class="cabin"><a href="./ServletReporte?operacion=usuarios">USUARIOS</a></td>
					<td class="cabin"><a href="./ServletReporte?operacion=transacciones">TRANSACCIONES</a><br></td>
				</tr>
			</table>
			
			<table class="tablaPrincipal">
				<tr>
					<td>
						<div>
							<a href="./ServletReporte?operacion=consulta"><button class="btn-xlarge"><i class="fa fa-file-text fa-4x" ></i></button></a>
						</div>
					</td>
					<td>
						<div>
							<a href="./ServletReporte?operacion=procedimiento"><button class="btn-xlarge"><i class="fa fa-file-text fa-4x" ></i></button></a>
						</div>
					</td>
				</tr>
				<tr>
					<td class="cabin"><a href="./ServletReporte?operacion=consulta">CONSULTA</a></td>
					<td class="cabin"><a href="./ServletReporte?operacion=procedimiento">PROCEDIMIENTO</a><br></td>
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