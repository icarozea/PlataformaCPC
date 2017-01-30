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
<title>Datos Persona</title>
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
	<%@include file="/menuNavegacionAdmin.jsp"%>
	<!--MEMU LATERAL--> 
        <%@include file="./menuPersona.jsp" %>
	<h1 id="titulo">DATOS PERSONA</h1>
	<form id="FormDatos" name="FormDatos" action="./ServletPersona" method="POST">
		<input type="hidden" name="operacion" id="operacion" />
		<input type="hidden" name="idPersona" id="idPersona"/>
		<table class="rwd-table-noBorder">
			<tr>
				<td>
					<h2>Perfil: ${requestScope.perfil}</h2>
				</td>
			</tr>
		</table>
		<table class="rwd-table" style="font-size: 20px">
		<tr>
				<th>Nombre</th>
				<th>Tipo Documento</th>
				<th>No. Documento</th>
				<th>Dirección</th>
				<th>Telefono</th>
				<th>Correo</th>
				<c:if test="${requestScope.perfil == 'Paciente'}">
					<th>EPS</th>
				</c:if>
				<c:if test="${requestScope.perfil == 'Practicante'}">
					<th>Código</th>
				</c:if>
				<th></th>
				<th></th>
			</tr>
			<tr>
				<td>${requestScope.pNom} ${requestScope.sNom} ${requestScope.pApe} ${requestScope.sApe}</td>
				<td>${requestScope.doc}</td>
				<td>${requestScope.num} </td>
				<td>${requestScope.dir}</td>
				<td>${requestScope.tel}</td>
				<td>${requestScope.mail}</td>
				<c:if test="${requestScope.perfil == 'Paciente'}">
					<td>${requestScope.eps}</td>
				</c:if>
				<c:if test="${requestScope.perfil == 'Practicante'}">
					<td>${requestScope.cod}</td>
				</c:if>
				<td><input type="submit" name="editarPersona" id="editarPersona" value="editar" class="btnEditar"
							 onclick="enviarFormulario(this.id,${requestScope.idPersona})"/></td>
				<td><input type="submit" name="eliminarPersona" id="eliminarPersona" value="eliminar" class="btnEliminar"
						onclick="enviarFormulario(this.id,${requestScope.idPersona})"/></td>
			</tr>
			<c:forEach items="${requestScope.listaPersonas}" var="persona">
				<tr>
					<td>${persona.primerNombre} ${persona.segundoNombre} ${persona.primerApellido} ${persona.segundoApellido}</td>
					<td>${persona.tipoDocumento.idTipoDocumento}</td>
					<td>${persona.numeroDocumento}</td>
					<td>${persona.direccion}</td>
					<td>${persona.telefono}</td>
					<td><input type="submit" name="editarPersona" id="editarPersona" value="" class="btnEditarPersona"
							 onclick="enviarFormulario(this.id,${persona.idPersona})"/></td>
					<td><input type="submit" name="eliminarPersona" id="eliminarPersona" value="" class="btnEliminarPersona"
						onclick="enviarFormulario(this.id,${persona.idPersona})"/>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
