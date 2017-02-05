<%@page import="com.plataforma.cpc.dao.DaoPersona"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.plataforma.cpc.to.CitaTo" %>
<%@ page import="com.plataforma.cpc.to.PersonaTo" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estiloAsignaciones.css"></link>
<script type="text/javascript" src="js/manejarAsignacion.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reporte Sesión</title>
</head>
<% CitaTo citaRecibida = (CitaTo) request.getAttribute("cita"); %>
<% PersonaTo practicante = new DaoPersona().consultarPersona(citaRecibida.getPracticante()); %>
<% PersonaTo paciente =  new DaoPersona().consultarPersona(citaRecibida.getPaciente());%>
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
			
			<form id="reporteSesionForm" action="./ServletSesionIndividual" method="POST">
				<input type="hidden" name="operacion" value="guardarSesionIndividual"/>
				<input type="hidden" name="idCita" value="<%=citaRecibida.getIdCita() %>"/>
				<input type="hidden" name="citaFull" value="<%=citaRecibida %>"/>
				<label id="hora_label" class="droidSans">Fecha:</label><input id="fecha" name="fecha" type="date" class="field text fn" value="" size="8" tabindex="1">
				<label id="hora_label" class="droidSans">Hora:</label><input id="hora" name="hora" type="time" class="field text fn" value="" size="8" tabindex="1">
		
			<div>
				<label id="nombrePaciente_label" class="droidSans">Nombre del paciente:</label><p class="droidSans"><b><%=paciente.getPrimerNombre()+" "+paciente.getPrimerApellido() %></b></p>
			</div>
			
			<div>
				<label id="profesional_label" class="droidSans">Profesional en formación del área clínica:</label><input id="profesional" name="profesional" type="text" class="field text fn" value="" size="8" tabindex="1">
			</div>
			
			<div>
				<label id="objetivo_label" class="droidSans">Objetivo de la sesión:</label><br><br><textarea id="objetivoSesion" name="objetivoSesion" rows="10" cols="90" style=" resize: none;"></textarea>
			</div>
			
			<div>
				<label id="descripcion_label" class="droidSans">Descripción de la sesión:</label><br><br><textarea id="descripcionSesion" name="descripcionSesion" rows="10" cols="90" style=" resize: none;"></textarea>
			</div>
			
			<div>
				<label id="tareas_label" class="droidSans">Tareas asignadas:</label><br><br><textarea id="tareasSesion" name="tareasSesion" rows="10" cols="90" style=" resize: none;"></textarea>
			</div>
			
			<div>
				<label id="actividades_label" class="droidSans">Actividades para la próxima sesión:</label><br><br><textarea id="actividadesProxSesion" name="actividadesProxSesion" rows="10" cols="90" style=" resize: none;"></textarea>
			</div>
			<div>
				<div>
					<input id="saveForm" name="saveForm" type="submit" value="Guardar">
				</div>
			</div>
		   </form>		
		</div>

			
</body>
</html>