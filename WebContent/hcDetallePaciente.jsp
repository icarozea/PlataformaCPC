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
<script>
	function cerrarTratamiento(idTratamiento){
		document.getElementById("idTratamiento").value = idTratamiento;
		document.getElementById("FormCerrar").submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Historia Clínica - Paciente</title>
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
	
	<h1 id="titulo">Tratamientos</h1>
	<form id="FormDatos" name="FormDatos" action="./ServletHistoriaClinica" method="POST">
		<input type="hidden" name="operacion" id="operacion" value="detalleCitas"/>
		<input type="hidden" name="idPaciente" id="idPaciente" value="${requestScope.paciente.idPersona}"/>
		<table class="rwd-table-noBorder">
			<tr>
				<th>Paciente: </th>
				<td colspan="3">${requestScope.paciente.primerNombre} ${requestScope.paciente.segundoNombre} ${requestScope.paciente.primerApellido} ${requestScope.paciente.segundoApellido}</td>
				<th>Tipo Documento: </th>
				<td>${requestScope.paciente.tipoDocumento.sigla}</td>
				<th>Numero Documento: </th>
				<td>${requestScope.paciente.numeroDocumento}</td>
			</tr>
			<tr>
				<th>Direccion: </th>
				<td>${requestScope.paciente.direccion}</td>
				<th>Telefono: </th>
				<td>${requestScope.paciente.telefono}</td>
				<th>Correo: </th>
				<td>${requestScope.paciente.correo}</td>
				<th>EPS: </th>
				<td>${requestScope.paciente.eps.nombreEPS}</td>
			</tr>
			<tr>
				<th>Jornada: </th>
				<td>${requestScope.paciente.jornada}</td>
			</tr>
			<tr>
				<td><br><br></td>
			</tr>
			<tr>			
				<td colspan="6">
					<table class="rwd-table">
						<tr>
							<th>Seleccionar</th>
							<th>Estado</th>
							<th>Fecha Inicio</th>
							<th>Fecha cierre</th>
							<th>Tipo</th>
							<th></th>
						</tr>
					<c:forEach items="${requestScope.tratamientos}" var="tratamiento">
						<tr>
							<td><input type="radio" id="grupoTratamiento" name="grupoTratamiento" value="${tratamiento.idTratamiento}"></td>
							<td>${tratamiento.estado}</td>
							<td>${tratamiento.fechaInicio}</td>
							<td>${tratamiento.fechaCierre}</td>
							<td>${tratamiento.tipo}</td>
							<td><input type=button value="Cerrar" class="btnLogout" onClick="cerrarTratamiento(${tratamiento.idTratamiento})"></td>
						</tr>
					</c:forEach>
					</table>				
				</td>
			</tr>
		</table>
		<br>
        <input type="button" onclick="validarTratamiento()" id="btnAceptar" value="Aceptar" class="botones"/>
        <a href="busquedaPersonas"><input type="button" id="btnVolver" value="Volver" class="botones"/></a>		
	</form>
	
	<form id="FormCerrar" name="FormCerrar" action="./ServletCita" method="POST">
		<input type="hidden" name="operacion" id="operacion" value="cerrarTratamiento"/>
		<input type="hidden" name="idPaciente" id="idPaciente" value="${requestScope.paciente.idPersona}"/>
		<input type="hidden" name="idTratamiento" id="idTratamiento"/>
	</form>
</body>
</html>