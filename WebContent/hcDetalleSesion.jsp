<%-- 
    Document   : citaPracticantes
    Created on : 04/02/2017
    Author     : Ovidio Zea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.plataforma.cpc.to.CitaTo" %>
<%@ page import="com.plataforma.cpc.to.PersonaTo" %>
<%@page import="com.plataforma.cpc.dao.DaoPersona"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estilo.css"></link>
<link rel="stylesheet" href="listas.css"></link>
<script type="text/javascript" src="js/validarCita.js"></script>
<script src="https://unpkg.com/jspdf@latest/dist/jspdf.min.js"></script>
<script type="text/javascript" src="js/toPDF.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Historia Clínica - Sesión</title>
</head>
<% CitaTo citaRecibida = (CitaTo) request.getAttribute("cita"); %>
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
	
	<div style="height: 50px;" id="ignorePDF"></div>
	<h1 class="droidSans">Reporte de Sesión</h1>
		<div id="div-form-reporte-cita">
			
			<form id="reporteSesionForm" action="./ServletHistoriaClinica" method="POST">				
			<br><br>
				<label class="droidSans">Fecha:</label>
				<p class="droidSans"><b id="h_Fecha">${requestScope.sesion.fecha}</b></p>
			<br><br>
				<label class="droidSans">Cita No:</label>
				<p class="droidSans"><b id="h_Numero"><%=citaRecibida.getNumCita()%></b></p>
			<br><br>
			<div>
				<label id="nombrePaciente_label" class="droidSans">Nombre del paciente:</label>
				<p class="droidSans"><b id="h_NomPaciente"><%=paciente.getPrimerNombre()+" "+(paciente.getSegundoNombre()==null?"":paciente.getSegundoNombre())+" "+paciente.getPrimerApellido()+" "+paciente.getSegundoApellido()%></b></p>
				<br><br>
				<label id="numeroRecibo_label" class="droidSans">Recibo No:</label>
				<p class="droidSans"><b>${requestScope.sesion.numRecibo}</b></p>
			</div>
			<br><br>
			<div>
				<label id="profesional_label" class="droidSans">Profesional en formación del área clínica:</label>
				<p class="droidSans"><b id="h_Profesional">${requestScope.sesion.nombreProfesional}</b></p>
			</div>
			<br><br>
			<div>
				<label id="diagnostico_label" class="droidSans">Código del diagnóstico:</label><input id="diagnostico" name="diagnostico" type="text" class="field text fn" size="8" tabindex="2" value="${requestScope.diagnostico}" disabled>
			</div>
			<br><br>
			<c:choose>
				<c:when test="${requestScope.sesion.fallo == true}">
					<div><p class="droidSans"><h2><b>Sesión reportada como falla</b></h2></p></div>
				</c:when>
			</c:choose>	
			<br><br>
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
			<input type="button" id="btnImprimir" value="Imprimir" class="botones" onclick="toPDF(1)">
			<c:choose>
				<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1 }">
					<a href="./ServletHistoriaClinica?operacion=detalleCitas&idPaciente=<%= paciente.getIdPersona()%>&grupoTratamiento=${requestScope.cita.tratamiento.idTratamiento}"><input type="button" id="btnVolver" value="Volver" class="botones"/></a>
				</c:when>
				<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
					<a href="./ReportesPracticante?operacion=visualizarReportes&idPersona=${sessionScope.personaSession.idPersona}"><input type="button" id="btnVolver" value="Volver" class="botones"/></a>
				</c:when>
			</c:choose>
		 </form>		
	</div>
</body>
</html>