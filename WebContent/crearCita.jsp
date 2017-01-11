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
<script type="text/javascript" src="js/validarCita.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crear citas</title>
</head>
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
				<td>${requestScope.practicante.tipoDocumento.sigla}</td>
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
							<td>${paciente.tipoDocumento.sigla}</td>
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
				<td><input type="text" id="fecha" name="fecha" value="${requestScope.fecha}" required></td>
				<td><input type="button" id="btnFecha" value="Buscar" class="botones" onclick="{document.FormCalendario.submit();}"></td>
			</tr>
		</table>
		<br>
        <input type="button" onclick="validarCita()" id="btnAceptar" value="Aceptar" class="botones">		
	</form>
	<form id="FormCalendario" name="FormCalendario" action="./Calendario"  method="GET">
		<input type="hidden" id="idPersona" name="idPersona" value="${requestScope.practicante.idPersona}">
	</form>
</body>
</html>