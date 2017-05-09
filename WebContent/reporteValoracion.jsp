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
<title>Reporte de Valoración</title>
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
	<h1 class="cabin">Reporte de Valoración</h1>
	<div id="div-form-reporte-cita" class="reporteValoracion">
		
		<form id="reporteSesionForm" action="./ServletSesionIndividual" method="POST">
		<div class="seccionReportePar">
			<label id="hora_label" class="droidSans"><strong>Fecha:</strong></label><input id="fecha" name="fecha" type="text" class="field text fn" size="8" tabindex="1" readonly>
			<label id="hora_label" class="droidSans"><strong>Hora:</strong></label><input id="hora" name="hora" type="text" class="field text fn" size="8" tabindex="1" readonly>
		</div>
		<div class="seccionReporteImpar">
			<label id="entrevistador_label" class="droidSans"><strong>Entrevistador(a):</strong></label><input id="entrevistador" name="entrevistador" type="text" class="field text fn" size="8" tabindex="1">
		</div>
		<div class="seccionReporteImpar">
			<label id="profesional_label" class="droidSans"><strong>Profesional en Formación:</strong></label><input id="profesional" name="profesional" type="text" class="field text fn" size="8" tabindex="1">
		</div>
		
		<div>
			<label id="s1_label" class="droidSans"><strong><h3>1. Información Personal (esta información es obligatoria)</h3></strong></label>
		</div>
		
		<div class="seccionReportePar">
			<label id="nombrePaciente_label" class="droidSans"><strong>Nombre Completo</strong></label>
			<input id="nombre1" name="nombre1" type="text" class="field text fn" value="" size="8" tabindex="1"><input id="nombre2" name="nombre2" type="text" class="field text fn" value="" size="8" tabindex="1">
			<input id="apellido1" name="apellido1" type="text" class="field text fn" value="" size="8" tabindex="1"><input id="apellido2" name="apellido2" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="tipodoc_label" class="droidSans"><strong>Tipo de Documento</strong></label><input id="tipodoc" name="tipodoc" type="text" class="field text fn" size="8" tabindex="1">
			<label id="nun_documento_label" class="droidSans"><strong>Número</strong></label><input id="num_documento" name="num_documento" type="text" class="field text fn" size="8" tabindex="1">
		</div>
		
		<div class="seccionReportePar">
			<label id="sexo" class="droidSans"><strong>Sexo</strong></label><input id="sexo" name="sexo" type="text" class="field text fn" value="" size="8" tabindex="1">
			<label id="estado" class="droidSans"><strong>Estado Civil</strong></label><input id="estado_civil" name="estado_civil" type="text" class="field text fn" value="" size="8" tabindex="1">
			<label id="edad" class="droidSans"><strong>Edad Actual</strong></label><input id="edad" name="edad" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="fecha_nacimiento_label" class="droidSans"><strong>Fecha de Nacimiento:</strong></label><input id="fecha_nacimiento" name="fecha_nacimiento" type="text" class="field text fn" value="" size="8" tabindex="1">
			<label id="lugar_nacimiento_label" class="droidSans"><strong>Lugar de Nacimiento:</strong></label><input id="lugar_nacimiento" name="lugar_nacimiento" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReportePar">
			<label id="escolaridad_label" class="droidSans"><strong>Nivel de Escolaridad:</strong></label><input id="escolaridad" name="escolaridad" type="text" class="field text fn" value="" size="8" tabindex="1">
			<label id="ocupacion_label" class="droidSans"><strong>Ocupación:</label></strong><input id="ocupacion" name="ocupacion" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="dirección_label" class="droidSans"><strong>Dirección de Residencia:</strong></label><input id="dirección" name="dirección" type="text" class="field text fn" value="" size="10" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="localidad_label" class="droidSans"><strong>Localidad:</strong></label><input id="localidad" name="localidad" type="text" class="field text fn" value="" size="8" tabindex="1">
			<label id="barrio_label" class="droidSans"><strong>Barrio:</strong></label><input id="barrio" name="barrio" type="text" class="field text fn" value="" size="8" tabindex="1">
			<label id="estrato_label" class="droidSans"><strong>Estrato:</strong></label><input id="estrato" name="estrato" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReportePar">
			<label id="aseguradora_label" class="droidSans"><strong>Entidad Aseguradora:</strong></label><input id="aseguradora" name="aseguradora" type="text" class="field text fn" value="" size="8" tabindex="1">
			<label id="nombre_aseguradora_label" class="droidSans"><strong>Nombre:</strong></label><input id="nombre_aseguradora" name="nombre_aseguradora" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="emergencia_label" class="droidSans"><strong>En Caso de Emergencia Llamar A:</strong></label><input id="emergencia" name="emergencia" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="telefonos_emergencia_label" class="droidSans"><strong>A los Teléfonos:</strong></label><input id="telefonos_emergencia" name="telefonos_emergencia" type="text" class="field text fn" value="" size="8" tabindex="1">
			<label id="parentesco_label" class="droidSans"><strong>Parentesco:</strong></label><input id="parentesco" name="parentesco" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReportePar">
			<label id="solicitud_label" class="droidSans"><strong>Formato de Solicitud del Servicio:</strong></label><input id="solicitud" name="solicitud" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReportePar">
			<label id="institucion_label" class="droidSans"><strong>Si es Remitido por Cual Institución:</strong></label><input id="institucion" name="institucion" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="acudiente_label" class="droidSans"><strong>Nombre del Acudiente:</strong></label><input id="acudiente" name="acudiente" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="parentesco_acudiente_label" class="droidSans"><strong>Parentesco:</strong></label><input id="parentesco_acudiente" name="parentesco_acudiente" type="text" class="field text fn" value="" size="8" tabindex="1">
			<label id="telefonos_acudiente_label" class="droidSans"><strong>Telefonos:</strong></label><input id="telefonos_acudiente" name="telefonos_acudiente" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReportePar">
			<label id="parientes_label" class="droidSans"><strong>Personas con las que Vive Actualmente el Paciente:</strong></label><br><br>
		</div>
		
		<div class="seccionReportePar">
			<textarea id="parientes" name="parientes" rows="10" cols="90" style=" resize: none;"></textarea><br><br>
		</div>
		
		<div>
			<label id="s2_label" class="droidSans"><strong><h3>2. Motivo de Consulta</h3></strong></label><br><br>
		</div>
		
		<div class="seccionReporteImpar">
			<label id="e1_label" class="droidSans"><strong>Motivo de consulta reportado por el paciente</strong></label>
		</div>
		
		<div class="seccionReporteImpar">
			<label id="persona_reporta_label" class="droidSans">Señale Quien Reporta</label><input id="persona_reporta" name="persona_reporta" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<textarea id="motivo_consulta" name="motivo_consulta" rows="10" cols="90" style=" resize: none;"></textarea><br><br>
		</div>
		
		<div>
			<label id="s3_label" class="droidSans"><strong><h3>3. Diligenciar después de la valoración</h3></strong></label><br><br>
		</div>
		
		<div class="seccionReportePar">
			<label id="aspectos_label" class="droidSans"><strong>Identificar aspectos sobresalientes del comportamiento</strong></label>
		</div>
		
		<div class="seccionReportePar">
			<label id="e2_label" class="droidSans"><em>(Conducta verbal y no verbal), actitud y apariencia del paciente durante la valoración</em></label>
		</div>
		
		<div class="seccionReportePar">
			<textarea id="aspectos" name="aspectos" rows="10" cols="90" style=" resize: none;"></textarea><br><br>
		</div>
		
		<div class="seccionReporteImpar">
			<label id="hipotesis_label" class="droidSans"><strong>Hipotesis Preliminares</strong></label>
		</div>
		
		<div class="seccionReporteImpar">
			<label id="e3_label" class="droidSans"><em>Indicar con base en la información recolectada, las posibles hipótesis preliminares que se recomienda
			explorar en el proceso terapéutico</em></label>
		</div>
		
		<div class="seccionReporteImpar">
			<textarea id="hipotesis" name="hipotesis" rows="10" cols="90" style=" resize: none;"></textarea><br><br>
		</div>
		
		<div class="seccionReportePar">
			<label id="remitido_label" class="droidSans"><strong>En caso de ser remitido el servicio que requiere es:</strong></label><input id="remitido" name="remitido" type="text" class="field text fn" value="" size="8" tabindex="1">
		</div>
		
		<div class="seccionReportePar">
			<label id="otro_label" class="droidSans"><strong>Cual?</strong></label><input id="otro" name="otro" type="text" class="field text fn" value="" size="8" tabindex="1">
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