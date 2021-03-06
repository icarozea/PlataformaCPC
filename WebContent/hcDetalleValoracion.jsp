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
<script>
	function aprobar(){
		console.log(aprobar);
		document.getElementById("aceptarForm").submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Historia Clínica - Valoración</title>
</head>
<% CitaTo citaRecibida = (CitaTo) request.getAttribute("cita"); %>
<% PersonaTo paciente =  new DaoPersona().consultarPersona(citaRecibida.getPaciente());%>
<% PersonaTo practicante = new DaoPersona().consultarPersona(citaRecibida.getPracticante()); %>
<% String nomPracticante =  practicante.getPrimerNombre() + " " + (practicante.getSegundoNombre()==null?"":practicante.getSegundoNombre())+" "+practicante.getPrimerApellido()+" "+practicante.getSegundoApellido();%>
<body>
	<!--MEMU SUPERIOR-->
	<c:choose>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
			<%@include file="./menuNavegacionAdmin.jsp"%>
		</c:when>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 2}">
			<%@include file="./menuNavegacionAsesor.jsp"%>
		</c:when>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
			<%@include file="./menuNavegacionPracticante.jsp"%>
		</c:when>
	</c:choose>
	
	<div style="height: 50px;"></div>
	<h1 class="droidSans">Reporte de Valoración</h1>
		<div id="div-form-reporte-cita">
			
			<form id="reporteValoracionForm" action="./ServletSesionIndividual" method="POST">
			<input type="hidden" id="operacion" name="operacion" value="actualizarValoracion">	
			<input type="hidden" id="id" name="id" value="${requestScope.valoracion.idValoracion}">				
			<br><br>
				<label class="droidSans" >Fecha:</label>
				<p class="droidSans"><b id="h_Fecha"><%=citaRecibida.getFechaCita()%></b></p>
			<br><br>
				<label class="droidSans">Cita No:</label>
				<p class="droidSans"><b>0</b></p>
			<br><br>
			<div>
				<label id="nombrePaciente_label" class="droidSans">Nombre del paciente:</label>
				<p class="droidSans"><b id="h_NomPaciente"><%=paciente.getPrimerNombre()+" "+(paciente.getSegundoNombre()==null?"":paciente.getSegundoNombre())+" "+paciente.getPrimerApellido()+" "+paciente.getSegundoApellido()%></b></p>
			</div>
			<br><br>
			<div>
				<label id="entrevistador_label" class="droidSans"><strong>Entrevistador(a):</strong></label><input id="entrevistador" name="entrevistador" type="text" class="field text fn" size="8" tabindex="1" value="${requestScope.valoracion.encuestador}">
			</div>
			<div>
				<label id="practicante_label" class="droidSans"><strong>Practicante:</strong></label><input id="practicante" name="practicante" type="text" class="field text fn" size="8" tabindex="1" value="<%= nomPracticante %>">
			</div>
			<br><br>
			<div>
				<label id="persona_reporta_label" class="droidSans">Persona que reporta:</label>
			</div>
			<div>
				<input id="persona_reporta" name="persona_reporta" type="text" class="field text fn" size="8" tabindex="1" value="${requestScope.valoracion.personaReporta}">
			</div>
			<br><br>
			<div>
				<label id="motivo_consulta_label" class="droidSans">Motivo de la consulta</label>
			</div>
			<br><br>
			<div>
				<textarea id="motivo_consulta" name="motivo_consulta" rows="10" cols="90" style=" resize: none;">${requestScope.valoracion.motivo}</textarea>
			</div>
			<br><br>
			<div>
				<label id="aspectos_label" class="droidSans">Aspectos sobresalientes del comportamiento</label>
			</div>
			<br><br>
			<div>
				<textarea id="aspectos" name="aspectos" rows="10" cols="90" style=" resize: none;">${requestScope.valoracion.comportamiento}</textarea>
			</div>
			<br><br>
			<div>
				<label id="hipotesis_label" class="droidSans">Hipótesis Preliminares</label>
			</div>
			<br><br>
			<div>
				<textarea id="hipotesis" name="hipotesis" rows="10" cols="90" style=" resize: none;">${requestScope.valoracion.hipotesis}</textarea><br><br>
			</div>
			<br><br>
			<div>
				<label id="remitido_label" class="droidSans">Servicio remitido</label>
				
			</div>
			<div>
				<input id="remitido" name="remitido" type="text" class="field text fn" size="8" tabindex="1" value="${requestScope.valoracion.servicioRemitido}">
			</div>
			<br><br>
			<div>
				<label id="diagnostico_label" class="droidSans">Código del diagnóstico</label>
			</div>
			<div>
				<input id="diagnostico" name="diagnostico" type="text" class="field text fn" size="8" tabindex="2" value="${requestScope.diagnostico}">
			</div>
			<br><br>
			<div>
			<input type="button" id="btnImprimir" value="Imprimir" class="botones" onclick="toPDF(2)">
			<c:if test="${sessionScope.personaSession.perfil.idPerfil == 2 || sessionScope.personaSession.perfil.idPerfil == 3}">
				<c:if test="${requestScope.aceptado == 0}">		
					<input type="submit" id="btnGuardar" value="Guardar" class="botones">
				</c:if>
			</c:if>
			<c:if test="${sessionScope.personaSession.perfil.idPerfil == 2}">
				<input type="button" id="btnAprobar" value="Aprobar" class="botones" onClick="aprobar()">
			</c:if>
			<c:choose>
				<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1 }">
					<a href="./ServletHistoriaClinica?operacion=detalleCitas&idPaciente=<%= paciente.getIdPersona()%>&grupoTratamiento=${requestScope.cita.tratamiento.idTratamiento}"><input type="button" id="btnVolver" value="Volver" class="botones"/></a>
				</c:when>
				<c:when test="${sessionScope.personaSession.perfil.idPerfil == 2}">
					<a href="./ServletAsesor?operacion=reportesPreview&idPracticante=<%= practicante.getIdPersona() %>&pNom=<%= practicante.getPrimerNombre()%>&sNom=<%= (practicante.getSegundoNombre()==null?"":practicante.getSegundoNombre()) %>&pApe=<%= practicante.getPrimerApellido() %>&sApe=<%= practicante.getSegundoApellido()%>"><input type="button" id="btnVolver" value="Volver" class="botones"/></a>
				</c:when>
				<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
					<a href="./ReportesPracticante?operacion=visualizarReportes&idPersona=${sessionScope.personaSession.idPersona}"><input type="button" id="btnVolver" value="Volver" class="botones"/></a>
				</c:when>
			</c:choose>
			</div>	
		 </form>		
	</div>
	
	<form id="aceptarForm" action="./ServletAsesor" method="POST">
			<input type="hidden" id="operacion" name="operacion" value="aceptarValoracion">	
			<input type="hidden" id="idValoracion" name="idValoracion" value="${requestScope.valoracion.idValoracion}">	
	</form>	
</body>
</html>