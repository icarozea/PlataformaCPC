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
	<%@include file="/menuNavegacionAdmin.jsp"%>
    <div>
    	<h1 class="cabin">Ver lista de EPS</h1>
    </div>
    <div id="EPSs">
    	<form id="FormDatos" name="FormDatos" action="./ServletEPS" method="POST">
	    	<input type="hidden" name="operacion" id="operacion" />
			<input type="hidden" name="idEPS" id="idEPS"/>
	    	<table id="tablaListaEPS">
	    		<thead>
	    			<th>Código</th>
	    			<th>Nombre</th>
	    			<th>Editar</th>
	    			<th>Eliminar</th>
	    		</thead>
	    		<tbody>
		    		<%
						ArrayList<EpsTo> lista = (ArrayList<EpsTo>) request.getAttribute("listaEPS");
						for (int i = 0; i < lista.size(); i++) {
					%>
		    		<tr>
		    			<td><%=lista.get(i).getIdEPS()%></td>
		    			<td><%=lista.get(i).getNombreEPS()%></td>
		    			<td><input type="submit" name="btnEditarEPS" id="btnEditarEPS" value="" class="btnEditar" onclick="enviarFormulario(this.id,<%=lista.get(i).getIdEPS()%>)"/></td>
						<td><input type="submit" name="btnEliminarEPS" id="btnEliminarEPS" value="" class="btnEliminar" onclick="enviarFormulario(this.id,<%=lista.get(i).getIdEPS()%>)"/>
						</td>	 
		    		</tr>
		    		<% } %>
	    		</tbody>
	    	</table>
    	</form>
    </div>
    <div>
		<a href="InicioConfiguracion.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
	</div>
	
</body>
</html>
