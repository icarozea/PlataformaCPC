<%-- 
    Document   : FormularioEPS
    Created on : 07/11/2016
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
<title>Lista Personas</title>
</head>
<script type="text/javascript">
	function enviarFormulario(operacion, idPersona){
		document.getElementById('operacion').value=operacion;
		document.getElementById('idPersona').value=idPersona;
		document.getElementById("FormDatos").submit();
	}
	
	function cargarPersona(operacion){
		document.getElementById('operacion').value=operacion;
		document.getElementById("FormDatos").submit();
	}

</script>
<body>
	<!--MEMU SUPERIOR-->
	<%@include file="/menuNavegacion.jsp"%>
	<!--MEMU LATERAL--> 
        <%@include file="./menuPersona.jsp" %>
	<h1 id="titulo">LISTA DE PERSONAS</h1>
	<form id="FormDatos" name="FormDatos" action="./ServletPersona" method="POST">
		<input type="hidden" name="operacion" id="operacion" />
		<input type="hidden" name="idPersona" id="idPersona"/>
		<table class="rwd-table-noBorder">
			<tr>
				<td>
					Perfil
				</td>
				<td>
					<select id ="perfil" name="perfil" onchange="cargarPersona('listarPersonas')">
			        	<option value="-1"> Seleccione </option> 
			        	<c:forEach items="${listaPerfiles}"  var="perfilPersona">
                  			<c:choose>
		                		<c:when test="${requestScope.valor == perfilPersona.idPerfil}">
		                			<option value="${perfilPersona.idPerfil}" selected>${perfilPersona.nombrePerfil}</option>
		                		</c:when>
		                		<c:otherwise>
		                			<option value="${perfilPersona.idPerfil}">${perfilPersona.nombrePerfil}</option>
		                		</c:otherwise>
							</c:choose>						   			
						</c:forEach>               		
					</select>
				</td>
			</tr>
		</table>
		<table class="rwd-table">
			<tr>
				<th>Nombre</th>
				<th>Tipo Documento</th>
				<th>Numero Documento </th>
				<th>Dirección</th>
				<th>Telefono</th>
				<th>Editar</th>
				<th>Eliminar</th>
			</tr>
			<c:forEach items="${requestScope.listaPersonas}" var="persona">
				<tr>
					<td>${persona.primerNombre} ${persona.segundoNombre} ${persona.primerApellido} ${persona.segundoApellido}</td>
					<td>${persona.tipoDocumento.idTipoDocumento}</td>
					<td>${persona.numeroDocumento}</td>
					<td>${persona.direccion}</td>
					<td>${persona.telefono}</td>
					<td><input type="submit" name="editarPersona" id="editarPersona" value="editar" class="btnEditar"
							 onclick="enviarFormulario(this.id,${persona.idPersona})"/></td>
					<td><input type="submit" name="eliminarPersona" id="eliminarPersona" value="eliminar" class="btnEliminar"
						onclick="enviarFormulario(this.id,${persona.idPersona})"/>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
