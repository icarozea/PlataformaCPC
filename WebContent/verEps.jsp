<%-- 
    Document   : FormularioEPS
    Created on : 07/11/2016
    Author     : Ovidio Zea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.plataforma.cpc.to.EpsTo"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estilo.css"></link>
<link rel="stylesheet" href="listas.css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista EPS</title>
</head>
<script type="text/javascript">
	function enviarFormulario(operacion, idEps){
		document.getElementById('operacion').value=operacion;
		document.getElementById('idEPS').value=idEps;
		document.getElementById("FormDatos").submit();
	}

</script>
<body>
	<!--MEMU SUPERIOR-->
	<%@include file="/menuNavegacion.jsp"%>
	<h1 id="titulo">LISTA DE EPS</h1>
	<form id="FormDatos" name="FormDatos" action="./ServletEPS" method="POST">
		<input type="hidden" name="operacion" id="operacion" />
		<input type="hidden" name="idEPS" id="idEPS"/>
		<table class="rwd-table">
			<tr>
				<th>Código</th>
				<th>Nombre</th>
				<th>Editar</th>
				<th>Eliminar</th>
			</tr>
			<%
				ArrayList<EpsTo> lista = (ArrayList<EpsTo>) request.getAttribute("listaEPS");
				for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getIdEPS()%></td>
				<td><%=lista.get(i).getNombreEPS()%></td>
				<td><input type="submit" name="btnEditarEPS" id="btnEditarEPS" value="editarEPS" class="btnEditar"
						 onclick="enviarFormulario(this.id,<%=lista.get(i).getIdEPS()%>)"/></td>
				<td><input type="submit" name="btnEliminarEPS" id="btnEliminarEPS" value="eliminarEPS" class="btnEliminar"
					onclick="enviarFormulario(this.id,<%=lista.get(i).getIdEPS()%>)"/>
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</form>
</body>
</html>
