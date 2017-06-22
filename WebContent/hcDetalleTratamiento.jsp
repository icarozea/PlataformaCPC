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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Historia Clínica - Tratamiento</title>
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
	
	<h1 id="titulo">Citas</h1>
	<form id="FormDatos" name="FormDatos" action="./ServletHistoriaClinica" method="POST">
		<input type="hidden" name="operacion" id="operacion" value="detalleSesion"/>
		<table class="rwd-table-noBorder">
			<tr>
				<th>Tipo: </th>
				<td>${requestScope.tratamiento.tipo}</td>
				<th>Estado: </th>
				<td>${requestScope.tratamiento.estado}</td>
				<th>Fecha: </th>
				<td>${requestScope.tratamiento.fechaInicio}</td>
			</tr>
			<tr>
				<td><br><br></td>
			</tr>
			<tr>			
				<td colspan="6">
					<table class="rwd-table">
						<tr>
							<th>Seleccionar</th>
							<th>Salon</th>
							<th>Fecha</th>
							<th>Practicante</th>
						</tr>
					<c:forEach items="${requestScope.listaCitas}" var="cita">
						<tr>
							<td><input type="radio" id="grupoCita" name="grupoCita" value="${cita.idCita}"></td>
							<td>${cita.salon}</td>
							<td>${cita.fechaSolicitud}</td>
							<td>${cita.practicante.primerNombre} ${cita.practicante.segundoNombre} ${cita.practicante.primerApellido} ${cita.practicante.segundoApellido}</td>
						</tr>
					</c:forEach>
					</table>				
				</td>
			</tr>
		</table>
		<br>
        <input type="button" onclick="enviarCita()" id="btnAceptar" value="Aceptar" class="botones"/>
        <a href="./ServletHistoriaClinica?operacion=detalleTratamiento&idPersona=${requestScope.idPaciente}"><input type="button" id="btnVolver" value="Volver" class="botones"/></a>			
	</form>
</body>
</html>