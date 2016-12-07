<%-- 
    Document   : citaPracticantes
    Created on : 26/11/2016
    Author     : Ovidio Zea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estilo.css"></link>
<link rel="stylesheet" href="listas.css"></link>
<link type="text/css" href="jquery/css/custom-theme/jquery-ui-1.8.13.custom.css" rel="stylesheet">
<script type="text/javascript" src="jquery/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="jquery/js/jquery-ui-1.8.13.custom.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-timepicker-addon.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crear citas</title>
</head>
<script type="text/javascript">
	$( function() {
		$('#fecha').datetimepicker();
	} );
	
	function enviarFormulario(operacion, idPersona){
		document.getElementById('operacion').value=operacion;
		document.getElementById('idPersona').value=idPersona;
		document.getElementById("FormDatos").submit();
	}

</script>
<body>
	<!--MEMU SUPERIOR-->
	<%@include file="/menuNavegacion.jsp"%>
	
	<h1 id="titulo">Cita</h1>
	<form id="FormDatos" name="FormDatos" action="./ServletCita" method="POST">
		<input type="hidden" name="operacion" id="operacion" />
		<input type="hidden" name="idPracticante" id="idPracticante" value="${requestScope.practicante.idPersona}"/>
		<input type="hidden" name="idPaciente" id="idPaciente"/>
		<table class="rwd-table-noBorder">
			<tr>
				<th>Practicante: </th>
				<td>${requestScope.practicante.primerNombre} ${requestScope.practicante.segundoNombre} ${requestScope.practicante.primerApellido} ${requestScope.practicante.segundoApellido}</td>
				<th>Tipo Documento: </th>
				<td>${requestScope.practicante.tipoDocumento.idTipoDocumento}</td>
				<th>Numero Documento: </th>
				<td>${requestScope.practicante.numeroDocumento}</td>
			</tr>
			<tr>
				<td><br><br></td>
			</tr>
			<tr>			
				<td colspan="6">
					<table class="rwd-table">
						<tr>
							<th>Seleccionar</th>
							<th>Paciente</th>
							<th>Tipo Documento</th>
							<th>Número Documento </th>
							<th>Dirección</th>
							<th>Teléfono</th>
						</tr>
					<c:forEach items="${requestScope.listaPacientes}" var="paciente">
						<tr>
							<td><input type="radio" id="grupoPaciente" name="grupoPaciente" value="${paciente.idPersona}"></td>
							<td>${paciente.primerNombre} ${paciente.segundoNombre} ${paciente.primerApellido} ${paciente.segundoApellido}</td>
							<td>${paciente.tipoDocumento.idTipoDocumento}</td>
							<td>${paciente.numeroDocumento}</td>
							<td>${paciente.direccion}</td>
							<td>${paciente.telefono}</td>
						</tr>
					</c:forEach>
					</table>				
				</td>
			</tr>
			<tr>
				<td></td>
				<td>Salón: </td>
				<td><input type="text" id="salon" name="salon" required></td>
				<td>Fecha y Hora: </td>
				<td><input type="text" id="fecha" name="fecha" required></td>
				<td>
				</td>
			</tr>
		</table>
		<br>
        <input type="button" onclick="{document.FormDatos.operacion.value='guardarCita'; document.FormDatos.submit();}" id="btnAceptar" value="Aceptar" class="botones">		
	</form>
</body>
</html>