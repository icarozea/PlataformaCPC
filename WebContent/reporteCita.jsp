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
	<div>
		<h1 class="cabin">Reporte de Sesión Individual</h1>
	</div>
	<div>
		<form id="register-form" action="./ServletSesionIndividual" method="POST">
			<input type="hidden" name="operacion" value="guardarSesionIndividual"/>
			<input type="hidden" name="idCita" value="<%=citaRecibida.getIdCita() %>"/>
			<input type="hidden" name="citaFull" value="<%=citaRecibida %>"/>
			<div id="form-content">
				<fieldset>
					<div class="fieldgroup">
						<label class="cabin">Fecha</label>
						<input id="fecha" name="fecha" type="date" class="field text fn" value="" size="8" tabindex="1">
					</div>
					<div class="fieldgroup">
						<label class="cabin">Hora</label>
						<input id="hora" name="hora" type="time" class="field text fn" value="" size="8" tabindex="1">
					</div>
					<div class="fieldgroup">
						<label class="cabin">Nombre del paciente</label>
						<label class="cabin"><b><%=paciente.getPrimerNombre()+" "+paciente.getPrimerApellido() %></b></label>
					</div>
					<div class="fieldgroup">
						<label class="cabin">Profesional en formación del área clínica</label>
						<input id="profesional" name="profesional" type="text" class="field text fn" value="<%=practicante.getPrimerNombre()+" "+practicante.getSegundoNombre()+" "+practicante.getPrimerApellido()+" "+practicante.getSegundoApellido()%>" size="8" tabindex="1">
					</div>
					<div class="fieldgroupTextArea">
						<label class="cabin">Objetivo de la sesión</label>
						<textarea id="objetivoSesion" name="objetivoSesion" rows="10" cols="90" style=" resize: none;"></textarea>
					</div>
					<div class="fieldgroupTextArea">
						<label class="cabin">Descripción de la sesión</label>
						<textarea id="descripcionSesion" name="descripcionSesion" rows="10" cols="90" style=" resize: none;"></textarea>
					</div>
					<div class="fieldgroupTextArea">
						<label class="cabin">Tareas asignadas</label>
						<textarea id="tareasSesion" name="tareasSesion" rows="10" cols="90" style=" resize: none;"></textarea>
					</div>
					<div class="fieldgroupTextArea">
						<label class="cabin">Actividades para la próxima sesión</label>
						<textarea id="actividadesProxSesion" name="actividadesProxSesion" rows="10" cols="90" style=" resize: none;"></textarea>
					</div>
					<div class="fieldgroup">
						<input id="saveForm" class="submit cabin" name="saveForm" type="submit" value="Guardar">
					</div>
					<div class="fieldgroup">
						<a href="Calendario?idPersona=<%=practicante.getIdPersona()%>"><input type="button" value="Cancelar" class="submit cabin"></a>
					</div>
				</fieldset>
			</div>
		</form>
	</div>
</body>
</html>