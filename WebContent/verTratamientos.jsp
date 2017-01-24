<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estilo.css"></link>
<link rel="stylesheet" href="listas.css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tratamientos</title>
</head>
<body>
	<!--MENU SUPERIOR-->
	<c:choose>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
			<%@include file="./menuNavegacionAdmin.jsp"%>
		</c:when>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
			<%@include file="./menuNavegacionPracticante.jsp"%>
		</c:when>
	</c:choose>
	
	<!--MEMU LATERAL-->
	<c:choose>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
			<%@include file="./menuPersona.jsp"%>
		</c:when>
	</c:choose>
	<div class="caja">
		<h2>Seleccione un Tratamiento</h2>
		<form id="FormTratamiento" name="FormTratamiento" action="./ServletCita" method="POST">
			<input type="hidden" name="operacion" id="operacion" value="guardarCita" />
			<input type="hidden" name="salon" id="salon" value="${requestScope.cita.salon}" />
			<input type="hidden" name="fecha" id="fecha" value="${requestScope.cita.fechaCita}" />
			<input type="hidden" name="idPracticante" id="idPracticante" value="${requestScope.cita.practicante.idPersona}" />
			<input type="hidden" name="grupoPaciente" id="grupoPaciente" value="${requestScope.cita.paciente.idPersona}" />
			<input type="hidden" name="flag" id="flag" value="flag"/>
			<table class="rwd-table-noBorder" style="font-size: 20px">
				<tr>
					<th></th>
					<th>Tipo</th>
					<th>Estado</th>
					<th>Inicio</th>
					<th>Cierre</th>
				</tr>
				<tr>
				<c:forEach items="${requestScope.tratamientos}" var="tratamiento">
					<td><input type="radio" id="grupoTratamiento" name="grupoTratamiento" value="${tratamiento.idTratamiento}"></td>
					<td>${tratamiento.tipo}</td>
					<td>${tratamiento.estado}</td>
					<td>${tratamiento.fechaInicio}</td>
					<td>${tratamiento.fechaCierre}</td>
				</c:forEach>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><input type="button" onclick="{document.FormTratamiento.submit();}" id="btnAceptar" value="Aceptar" class="botones"></td>
					<td><a href="./ServletCita?operacion=crearCita&idPersona=${sessionScope.personaSession.idPersona}"><input type="button" id="btnCancelar" value="Cancelar" class="botones"></a></td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>