<%-- 
    Document   : citaPracticantes
    Created on : 04/02/2017
    Author     : Ovidio Zea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estilo.css"></link>
<link rel="stylesheet" href="listas.css"></link>
<script type="text/javascript" src="js/validarCita.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reporte de Sesión</title>
</head>
<body>
	<!--MEMU SUPERIOR-->
	<c:choose>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
			<%@include file="./menuNavegacionAdmin.jsp"%>
		</c:when>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
			<%@include file="./menuNavegacionPracticante.jsp"%>
		</c:when>
	</c:choose>
	
	<div style="height: 50px;"></div>
	<h1 class="droidSans">Reporte de Sesión Individual</h1>
		<div id="div-form-reporte-cita">
			
			<form id="reporteSesionForm" action="./ServletHistoriaClinica" method="POST">				
				<label class="droidSans">Fecha:</label>
				<label id="hora_label" class="droidSans">${requestScope.sesion.fecha}</label>
			
			<div>
				<label id="profesional_label" class="droidSans">Profesional en formación del área clínica:</label>
				<input id="profesional" name="profesional" type="text" class="field text fn" value="${requestScope.sesion.nombreProfesional}" size="8" tabindex="1" disabled="disabled" >
			</div>
			
			<div>
				<label id="objetivo_label" class="droidSans">Objetivo de la sesión:</label>
				<br><br>
				<textarea id="objetivoSesion" name="objetivoSesion" rows="10" cols="90"  style=" resize: none;" disabled="disabled">${requestScope.sesion.objetivo}</textarea>
			</div>
			
			<div>
				<label id="descripcion_label" class="droidSans">Descripción de la sesión:</label>
				<br><br>
				<textarea id="descripcionSesion" name="descripcionSesion" rows="10" cols="90" style=" resize: none;" disabled="disabled">${requestScope.sesion.descripcion}</textarea>
			</div>
			
			<div>
				<label id="tareas_label" class="droidSans">Tareas asignadas:</label>
				<br><br>
				<textarea id="tareasSesion" name="tareasSesion" rows="10" cols="90" style=" resize: none;" disabled="disabled">${requestScope.sesion.tareasAsignadas}</textarea>
			</div>
			
			<div>
				<label id="actividades_label" class="droidSans">Actividades para la próxima sesión:</label>
				<br><br>
				<textarea id="actividadesProxSesion" name="actividadesProxSesion" rows="10" cols="90" style=" resize: none;" disabled="disabled">${requestScope.sesion.actividadesProximaSesion}</textarea>
			</div>
		   </form>		
		</div>
</body>
</html>