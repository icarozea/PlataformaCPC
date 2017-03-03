<%@page import="java.time.LocalDateTime"%>
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
<script type="text/javascript">
	function modCamposTexto(){
		var estado = document.getElementById('objetivoSesion').disabled;
		document.getElementById('objetivoSesion').disabled = !estado;
		document.getElementById('descripcionSesion').disabled = !estado;
		document.getElementById('tareasSesion').disabled = !estado;
		document.getElementById('actividadesProxSesion').disabled = !estado;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reporte Sesión</title>
</head>
<% CitaTo citaRecibida = (CitaTo) request.getAttribute("cita"); %>
<% PersonaTo practicante = new DaoPersona().consultarPersona(citaRecibida.getPracticante()); %>
<% PersonaTo paciente =  new DaoPersona().consultarPersona(citaRecibida.getPaciente());%>
<% LocalDateTime hoy = LocalDateTime.now(); %>
<% String dia = hoy.getDayOfMonth() > 9? hoy.getDayOfMonth() + "" : "0" + hoy.getDayOfMonth(); %>
<% String mes = hoy.getMonthValue() > 9? hoy.getMonthValue() + "" : "0" + hoy.getMonthValue(); %>
<% String ano = hoy.getYear() + ""; %>
<% String horas = hoy.getHour() > 9? hoy.getHour() + "": "0" + hoy.getHour(); %>
<% String minutos = hoy.getMinute() > 9?  hoy.getMinute() + "": "0" + hoy.getMinute(); %>
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
	<h1 class="cabin">Reporte de Sesión</h1>
	<div id="div-form-reporte-cita">
		
		<form id="reporteSesionForm" action="./ServletSesionIndividual" method="POST">
			<input type="hidden" name="operacion" value="crear"/>
			<input type="hidden" name="idCita" value="<%=citaRecibida.getIdCita() %>"/>
			<input type="hidden" name="idTratamiento" value="<%=citaRecibida.getTratamiento().getIdTratamiento() %>"/>
			<input type="hidden" name="citaFull" value="<%=citaRecibida %>"/>
			<label id="hora_label" class="droidSans">Fecha:</label><input id="fecha" name="fecha" type="text" class="field text fn" value="<%=ano + "-" + mes + "-" + dia%>" size="8" tabindex="1" readonly>
			<label id="hora_label" class="droidSans">Hora:</label><input id="hora" name="hora" type="text" class="field text fn" value="<%=horas + ":" + minutos%>" size="8" tabindex="1" readonly>
			<label id="num_cita_label" class="droidSans">Cita No:</label><input id="num_cita" name="num_cita" type="text" class="field text fn" value="<%=citaRecibida.getNumCita()%>" size="8" tabindex="1" readonly>
	
		<div>
			<label id="nombrePaciente_label" class="droidSans">Nombre del paciente:</label><p class="droidSans"><b><%=paciente.getPrimerNombre()+" "+(paciente.getSegundoNombre()==null?"":paciente.getSegundoNombre())+" "+paciente.getPrimerApellido()+" "+paciente.getSegundoApellido()%></b></p>
			<label id="numeroRecibo_label" class="droidSans">Recibo No:</label><input id="numeroRecibo" name="numeroRecibo" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div>
			<label id="profesional_label" class="droidSans">Profesional en formación del área clínica:</label>
			<input id="profesional" name="profesional" type="text" class="field text fn" value="<%=practicante.getPrimerNombre()+" "+(practicante.getSegundoNombre()==null?"":practicante.getSegundoNombre())+" "+practicante.getPrimerApellido()+" "+practicante.getSegundoApellido()%>" size="8" tabindex="1" readonly>
		</div>
		
		<div>
			<label id="fallo_label" class="droidSans">¿Reportar la cita como una falla?</label><input id="fallo" name="fallo" type="checkbox" class="field text fn" value="fallo" size="8" tabindex="1" onChange="modCamposTexto()">
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