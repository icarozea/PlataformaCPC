<%@page import="com.plataforma.cpc.modelo.PersonaDetalleBean"%>
<%@page import="com.plataforma.cpc.to.PersonaDetalleTo"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page import="java.util.ArrayList" %>
<%@page import="com.plataforma.cpc.dao.DaoPersona"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.plataforma.cpc.to.CitaTo" %>
<%@ page import="com.plataforma.cpc.to.PersonaTo" %>
<%@ page import="com.plataforma.cpc.to.EpsTo" %>
<%@ page import="com.plataforma.cpc.to.TipoDocumentoTo" %>
<%@ page import="com.plataforma.cpc.modelo.UtilBean" %>
<%@ page import="com.plataforma.cpc.modelo.EpsBean" %>
<%@ page import="com.plataforma.cpc.to.MunicipioTo" %>
<%@ page import="com.plataforma.cpc.to.LocalidadTo" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estiloAsignaciones.css"></link>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="js/manejarAsignacion.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="js/datepick/css/jquery.datepick.css"> 
<script type="text/javascript" src="js/datepick/js/jquery.plugin.js"></script> 
<script type="text/javascript" src="js/datepick/js/jquery.datepick.js"></script>
<meta charset="UTF-8">
<script>
  $( function() {
    $( "#fecha_nacimiento" ).datepick({dateFormat: 'yyyy-mm-dd'});
  } );
  </script>
<title>Reporte de Valoración</title>
</head>
<% CitaTo citaRecibida = (CitaTo) request.getAttribute("cita"); %>
<% PersonaTo practicante = new DaoPersona().consultarPersona(citaRecibida.getPracticante()); %>
<% PersonaTo paciente =  new DaoPersona().consultarPersona(citaRecibida.getPaciente());%>
<% PersonaDetalleTo detallePaciente = new PersonaDetalleTo(); %>
<% detallePaciente.setPersonaId(paciente.getIdPersona()); %>
<% detallePaciente =  new PersonaDetalleBean().consultarPersonaDetalle(detallePaciente);%>
<% LocalDateTime hoy = LocalDateTime.now(); %>
<% String dia = hoy.getDayOfMonth() > 9? hoy.getDayOfMonth() + "" : "0" + hoy.getDayOfMonth(); %>
<% String mes = hoy.getMonthValue() > 9? hoy.getMonthValue() + "" : "0" + hoy.getMonthValue(); %>
<% String ano = hoy.getYear() + ""; %>
<% String horas = hoy.getHour() > 9? hoy.getHour() + "": "0" + hoy.getHour(); %>
<% String minutos = hoy.getMinute() > 9?  hoy.getMinute() + "": "0" + hoy.getMinute(); %>
<% UtilBean util = new UtilBean(); %>
<% ArrayList<TipoDocumentoTo> listaDocumentos = util.consultarTiposDocumento(); %>
<% ArrayList<EpsTo> listaEps = new EpsBean().consultarEPS(new EpsTo()); %>
<% ArrayList<MunicipioTo> municipios = util.consultarMunicipios(); %>
<% ArrayList<LocalidadTo> localidades = util.consultarLocalidades(); %>
<% pageContext.setAttribute("listaDocumentos", listaDocumentos); %>
<% pageContext.setAttribute("listaEps", listaEps); %>
<% pageContext.setAttribute("practicante", practicante); %>
<% pageContext.setAttribute("paciente", paciente); %>
<% pageContext.setAttribute("detallePaciente", detallePaciente); %>
<% pageContext.setAttribute("municipios", municipios); %>
<% pageContext.setAttribute("localidades", localidades); %>
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
	<h1 class="cabin">Reporte de Valoración</h1><br><br>
	<div id="div-form-reporte-cita" class="reporteValoracion">
		
		<form id="reporteSesionForm" action="./ServletSesionIndividual" method="POST">
			<input type="hidden" name="operacion" value="valoracion"/>
			<input type="hidden" name="idPaciente" value="<%=paciente.getIdPersona() %>"/>
			<input type="hidden" name="idCita" value="<%=citaRecibida.getIdCita() %>"/>
			<input type="hidden" name="idTratamiento" value="<%=citaRecibida.getTratamiento().getIdTratamiento() %>"/>
		<div class="seccionReportePar">
			<label id="hora_label" class="droidSans"><strong>Fecha:</strong></label><input id="fecha" name="fecha" type="text" class="field text fn" size="8" tabindex="1" value="<%=ano + "-" + mes + "-" + dia%>">
			<label id="hora_label" class="droidSans"><strong>Hora:</strong></label><input id="hora" name="hora" type="text" class="field text fn" size="8" tabindex="1" value="<%=horas + ":" + minutos%>">
		</div>
		<div class="seccionReporteImpar">
			<label id="entrevistador_label" class="droidSans"><strong>Entrevistador(a):</strong></label><input id="entrevistador" name="entrevistador" type="text" class="field text fn" size="8" tabindex="1">
		</div>
		<div class="seccionReporteImpar">
			<label id="profesional_label" class="droidSans"><strong>Profesional en Formación:</strong></label>
			<input id="profesional" name="profesional" type="text" class="field text fn" size="8" tabindex="1" value="<%= practicante.getPrimerNombre() + " " + practicante.getSegundoNombre() + " " + practicante.getPrimerApellido() + " " + practicante.getSegundoApellido()%>">
		</div>
		
		<div>
			<label id="s1_label" class="droidSans"><strong><h3>1. Información Personal (esta información es obligatoria)</h3></strong></label><br><br>
		</div>
		
		<div class="seccionReportePar">
			<label id="nombrePaciente_label" class="droidSans"><strong>Nombre Completo</strong></label>
			<input id="nombre1" name="nombre1" type="text" class="field text fn" value="<%= paciente.getPrimerNombre() %>" size="8" tabindex="1"><input id="nombre2" name="nombre2" type="text" class="field text fn" value="<%= paciente.getSegundoNombre() %>" size="8" tabindex="1">
			<input id="apellido1" name="apellido1" type="text" class="field text fn" value="<%= paciente.getPrimerApellido() %>" size="8" tabindex="1"><input id="apellido2" name="apellido2" type="text" class="field text fn" value="<%= paciente.getSegundoApellido() %>" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="tipodoc_label" class="droidSans"><strong>Tipo de Documento</strong></label>
			<select id="tipoDocumento" name="tipoDocumento" class="droidSans">
				<c:forEach items="${pageScope.listaDocumentos}" var="documento">
					<c:choose>
					<c:when test="${pageScope.paciente.tipoDocumento.sigla == documento.sigla}">
						<option value="${documento.sigla}" selected>${documento.nombreDocumento}</option>
					</c:when>
					<c:otherwise>	
						<option value="${documento.sigla}">${documento.nombreDocumento}</option>
					</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<label id="nun_documento_label" class="droidSans"><strong>Número</strong></label><input id="num_documento" name="num_documento" type="text" class="field text fn" size="8" tabindex="1" value="<%= paciente.getNumeroDocumento()%>">
		</div>
		
		<div class="seccionReportePar">
			<label id="sexo_label" class="droidSans"><strong>Sexo</strong></label>
			<select id="sexo" name="sexo" class="droidSans">
				<c:choose>
					<c:when test="${pageScope.detallePaciente.sexo == 'M'}"><option value="M" selected>Masculino</option></c:when>
					<c:otherwise><option value="M">Masculino</option></c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageScope.detallePaciente.sexo == 'F'}"><option value="F" selected>Femenino</option></c:when>
					<c:otherwise><option value="F">Femenino</option></c:otherwise>
				</c:choose>
			</select>
			<label id="estado_civil_label" class="droidSans"><strong>Estado Civil</strong></label>
			<select id="estado_civil" name="estado_civil" class="droidSans">
				<c:forTokens items="Soltero,Casado,Divorciado,Viudo" delims="," var="name">
					<c:choose>
				    	<c:when test="${pageScope.detallePaciente.estadoCivil == name}">
				        	<option value="${name}" selected>${name}(a)</option>
				         </c:when>
				         <c:otherwise>
				            <option value="${name}">${name}(a)</option>
				         </c:otherwise>
					</c:choose>
				</c:forTokens>
				<c:choose>
					<c:when test="${pageScope.detallePaciente.estadoCivil == 'Union Libre'}">
						<option value="Union Libre" selected>Union Libre</option>
					</c:when>
				    <c:otherwise>
				    	<option value="Union Libre">Union Libre</option>
				    </c:otherwise>
				</c:choose>
			</select>
			<label id="edad_label" class="droidSans"><strong>Edad Actual</strong></label><input id="edad" name="edad" type="text" class="field text fn" value="${pageScope.detallePaciente.edad}" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="fecha_nacimiento_label" class="droidSans"><strong>Fecha de Nacimiento:</strong></label>
			<input id="fecha_nacimiento" name="fecha_nacimiento" type="text" class="field text fn" value="${pageScope.detallePaciente.fechaNacimiento}" size="8" tabindex="1">
			<label id="lugar_nacimiento_label" class="droidSans"><strong>Lugar de Nacimiento:</strong></label>
			<select id="lugar_nacimiento" name="lugar_nacimiento">
		    	<option value="-1">Seleccione</option>
		        <c:forEach items="${municipios}" var="municipio">
					<c:choose>
				    	<c:when test="${pageScope.detallePaciente.lugarNacimiento == municipio.codigo}">
				        	<option value="${municipio.codigo}" selected>${municipio.nombre}</option>
				        </c:when>
				        <c:otherwise>
				        	<option value="${municipio.codigo}">${municipio.nombre}</option>
				        </c:otherwise>
					</c:choose>		                	
				</c:forEach>
			</select>
		</div>
		
		<div class="seccionReportePar">
			<label id="escolaridad_label" class="droidSans"><strong>Nivel de Escolaridad:</strong></label>
			<select id="escolaridad" name="escolaridad" class="droidSans">
				<c:forTokens items="Ninguna,Primaria,Bachillerato,Tecnica,Profesional,Especializacion,Maestria,Doctorado,Post-Doctorado" delims="," var="name">
					<c:choose>
				    	<c:when test="${pageScope.detallePaciente.escolaridad == name}">
				        	<option value="${name}" selected>${name}</option>
				        </c:when>
				        <c:otherwise>
				        	<option value="${name}">${name}</option>
				        </c:otherwise>
					</c:choose>
				</c:forTokens>
			</select>
			<label id="ocupacion_label" class="droidSans"><strong>Ocupación:</label></strong><input id="ocupacion" name="ocupacion" type="text" class="field text fn" value="${pageScope.detallePaciente.ocupacion}" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="dirección_label" class="droidSans"><strong>Dirección de Residencia:</strong></label><input id="direccion" name="direccion" type="text" class="field text fn" tabindex="1" value="<%=paciente.getDireccion() %>">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="localidad_label" class="droidSans"><strong>Localidad:</strong></label>
			<select id="localidad" name="localidad">
		    	<option value="-1">Seleccione</option>
		        <c:forEach items="${localidades}" var="localidad">
					<c:choose>
						<c:when test="${pageScope.detallePaciente.localidad == localidad.codigo}">
					    	<option value="${localidad.codigo}" selected>${localidad.nombre}</option>
					    </c:when>
					    <c:otherwise>
					    	<option value="${localidad.codigo}">${localidad.nombre}</option>
					    </c:otherwise>
					</c:choose>		                	
				</c:forEach>
			</select>
			<label id="barrio_label" class="droidSans"><strong>Barrio:</strong></label><input id="barrio" name="barrio" type="text" class="field text fn" value="${pageScope.detallePaciente.barrio}" size="8" tabindex="1">
			<label id="estrato_label" class="droidSans"><strong>Estrato:</strong></label>
			<select id="estrato" name="estrato" class="droidSans">
				<c:forTokens items="1,2,3,4,5,6" delims="," var="name">
					<c:choose>
				    	<c:when test="${pageScope.detallePaciente.estrato == name}">
				        	<option value="${name}" selected>${name}</option>
				        </c:when>
				        <c:otherwise>
				        	<option value="${name}">${name}</option>
				      	</c:otherwise>
					</c:choose>
				</c:forTokens>
			</select>
		</div>
		
		<div class="seccionReportePar">
			<label id="aseguradora_label" class="droidSans"><strong>Entidad Aseguradora:</strong></label>
			<select id="aseguradora" name="aseguradora" class="droidSans">
				<c:forEach items="${pageScope.listaEps}" var="eps">
					<c:choose>
					<c:when test="${pageScope.paciente.eps.nombreEPS == eps.nombreEPS}">
						<option value="${eps.idEPS}" selected>${eps.nombreEPS}</option>
					</c:when>
					<c:otherwise>	
						<option value="${eps.idEPS}">${eps.nombreEPS}</option>
					</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
		
		<div class="seccionReporteImpar">
			<label id="emergencia_label" class="droidSans"><strong>En Caso de Emergencia Llamar A:</strong></label><input id="emergencia" name="emergencia" type="text" class="field text fn" value="${pageScope.detallePaciente.personaEmergencia}" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="telefonos_emergencia_label" class="droidSans"><strong>A los Teléfonos:</strong></label><input id="telefonos_emergencia" name="telefonos_emergencia" type="text" class="field text fn" value="${pageScope.detallePaciente.telefonoEmergencia}" size="8" tabindex="1">
			<label id="parentesco_label" class="droidSans"><strong>Parentesco:</strong></label><input id="parentesco" name="parentesco" type="text" class="field text fn" value="${pageScope.detallePaciente.parentescoEmergencia}" size="8" tabindex="1">
		</div>
		
		<div class="seccionReportePar">
			<label id="solicitud_label" class="droidSans"><strong>Formato de Solicitud del Servicio:</strong></label>
			<select id="solicitud" name="solicitud" class="droidSans">
				<c:forTokens items="Voluntaria,Remision" delims="," var="name">
					<c:choose>
				    	<c:when test="${pageScope.detallePaciente.formatoSolicitud == name}">
				        	<option value="${name}" selected>${name}</option>
				        </c:when>
				        <c:otherwise>
				        	<option value="${name}">${name}</option>
				        </c:otherwise>
					</c:choose>
				</c:forTokens>
			</select>
		</div>
		
		<div class="seccionReportePar">
			<label id="institucion_label" class="droidSans"><strong>Si es Remisión por Cual Institución:</strong></label><input id="institucion" name="institucion" type="text" class="field text fn" value="${pageScope.detallePaciente.institucionRemision}" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="acudiente_label" class="droidSans"><strong>Nombre del Acudiente</strong> <em>(si es menor de edad):</em></label><input id="acudiente" name="acudiente" type="text" class="field text fn" value="${pageScope.detallePaciente.acudiente}" size="8" tabindex="1">
		</div>
		
		<div class="seccionReporteImpar">
			<label id="parentesco_acudiente_label" class="droidSans"><strong>Parentesco:</strong></label><input id="parentesco_acudiente" name="parentesco_acudiente" type="text" class="field text fn" value="${pageScope.detallePaciente.parentescoAcudiente}" size="8" tabindex="1">
			<label id="telefonos_acudiente_label" class="droidSans"><strong>Telefonos:</strong></label><input id="telefonos_acudiente" name="telefonos_acudiente" type="text" class="field text fn" value="${pageScope.detallePaciente.telefonoAcudiente}" size="8" tabindex="1">
		</div>
		
		<div class="seccionReportePar">
			<label id="parientes_label" class="droidSans"><strong>Personas con las que Vive Actualmente el Paciente:</strong></label><br><br>
		</div>
		
		<div class="seccionReportePar">
			<label id="parientes_indicacion_label" class="droidSans">(Reportar Nombre, Edad, Parentezco y Ocupación)</label><br><br>
		</div>
		
		<div class="seccionReportePar">
			<textarea id="parientes" name="parientes" rows="10" cols="90" style=" resize: none;">${pageScope.detallePaciente.personasReside}</textarea><br><br>
		</div>
		
		<div>
			<label id="s2_label" class="droidSans"><strong><h3>2. Motivo de Consulta y Descripción de la Valoración</h3></strong></label><br><br>
		</div>
		
		<div class="seccionReporteImpar">
			<label id="e1_label" class="droidSans"><strong>Motivo de consulta reportado por el paciente</strong></label>
		</div>
		
		<div class="seccionReporteImpar">
			<label id="persona_reporta_label" class="droidSans">Señale Quien Reporta</label>
			<select id="persona_reporta" name="persona_reporta" class="droidSans">
				<option value="paciente">Paciente</option>
				<option value="acudiente">Acudiente</option>
				<option value="adjunto">Acompañante</option>
				<option value="remision">Remisión</option>
			</select>
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
			<label id="hipotesis_label" class="droidSans"><strong>Hipótesis Preliminares</strong></label>
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
			<label id="diagnostico_label" class="droidSans"><strong>Código del diagnóstico:</strong></label><input id="diagnostico" name="diagnostico" type="text" class="field text fn" value="" size="8" tabindex="2">
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