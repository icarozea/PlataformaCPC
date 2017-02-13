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
<%--     <%@include file="./menuPersona.jsp" %> --%>
    
    <div id="detallesPersona">
    	<h1 class="cabin">DATOS PERSONA</h1>
    	<h2>Perfil: ${requestScope.perfil}</h2>
    	<form id="FormDatos" name="FormDatos" action="./ServletPersona" method="POST">
			<input type="hidden" name="operacion" id="operacion" />
			<input type="hidden" name="idPersona" id="idPersona"/>
			<table id="tablaDetallePersona">
				<thead>
        			<tr>
		          		<th scope="col">Nombre</th>
		          		<th scope="col">Tipo de Documento</th>
		          		<th scope="col">N° de Documento</th>
		          		<th scope="col">Dirección</th>
		          		<th scope="col">Teléfono</th>
		          		<th scope="col">Correo</th>
		          		<c:if test="${requestScope.perfil == 'Paciente'}">
							<th>EPS</th>
						</c:if>
						<c:if test="${requestScope.perfil == 'Practicante'}">
							<th>Código</th>
						</c:if>
						<th>Editar</th>
						<th>Eliminar</th>
			         </tr> 		
        		</thead>
        		<tbody>
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
				<td><input type="submit" name="editarPersona" id="editarPersona" value="" class="btnEditar"
							 onclick="enviarFormulario(this.id,${requestScope.idPersona})"/></td>
				<td><input type="submit" name="eliminarPersona" id="eliminarPersona" value="" class="btnEliminar"
						onclick="enviarFormulario(this.id,${requestScope.idPersona})"/></td>
			</tr>
        		</tbody>
			</table>   	
    	</form>
    </div>
    <div id="" class="">
            <a href="busquedaPersonas"><input type="button" id="btnAsignar" value="Volver" class="botones"></a>
     </div>    
</body>
</html>
