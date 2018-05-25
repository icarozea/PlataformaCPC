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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista Practicantes</title>
</head>
		<%
		  if (session.getAttribute("perfil")==null)
		  {
		    String address = "/index.jsp";
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
		    dispatcher.forward(request,response);
		  }
		%>
<script type="text/javascript">
	function enviarFormulario(operacion, idPersona){
		document.getElementById('operacion').value=operacion;
		document.getElementById('idPracticante').value=idPersona;
		document.getElementById("FormDatos").submit();
	}

</script>
<body>
	<!--MEMU SUPERIOR-->
	<%@include file="/menuNavegacionAdmin.jsp"%>
	
	<h1>LISTA DE PRACTICANTES</h1>
	<form id="FormDatos" name="FormDatos" action="./ServletCita" method="POST">
		<input type="hidden" name="operacion" id="operacion" />
		<input type="hidden" name="idPracticante" id="idPracticante"/>

		<table class="rwd-table">
			<tr>
				<th>Nombre</th>
				<th>Tipo Documento</th>
				<th>Numero Documento </th>
				<th>Dirección</th>
				<th>Telefono</th>
				<th>Ver citas</th>
				<th>Crear cita</th>
			</tr>
			<c:forEach items="${requestScope.listaPracticantes}" var="persona">
				<tr>
					<td>${persona.primerNombre} ${persona.segundoNombre} ${persona.primerApellido} ${persona.segundoApellido}</td>
					<td>${persona.tipoDocumento.sigla}</td>
					<td>${persona.numeroDocumento}</td>
					<td>${persona.direccion}</td>
					<td>${persona.telefono}</td>
					<td><a href="./Calendario?idPracticante=${persona.idPersona}"><input type="button" name="verCita" id="verCita" value="" class="btnVerCita"/></a></td>
					<td><input type="submit" name="crearCita" id="crearCita" value="" class="btnCrearCita"
						onclick="enviarFormulario(this.id,${persona.idPersona})"/>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
