<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Practicantes</title>
        <link rel="stylesheet" href="estilo.css"></link>
        <link rel="stylesheet" href="estiloAsignaciones.css"></link>
        <script type="text/javascript" src="js/manejarAsignacion.js"></script>
    </head>
		<%
		  if (session.getAttribute("perfil")==null)
		  {
		    String address = "/index.jsp";
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
		    dispatcher.forward(request,response);
		  }
		%>
    <body>
        <%@include file="./menuNavegacionAsesor.jsp" %>
        <div id="gestioncitas" class="">
        	<h1 class="cabin">Practicantes</h1>
			<h2 class="cabin">asignados</h2>        
        	<div id="usuarios">
        		<table id="tablaUsuarios">
        			<thead>
        				<th style="width: 30%;">Nombre</th>
        				<th>Ver detalles de persona</th>
        				<th>Visualizar reportes</th>
        			</thead>
        			<tbody>
        				<c:forEach items="${requestScope.practicantes}" var="practicante">
        				<tr>
        					<td>${practicante.primerNombre} ${practicante.segundoNombre} ${practicante.primerApellido} ${practicante.segundoApellido}</td>
        					<td class="tdBoton"><a href="./ServletPersona?operacion=listarPersonas&id=${practicante.idPersona}"><input type="button" id="btnVer" class="btnVer"></a></td>
        					<td class="tdBoton"><a href="./ServletAsesor?operacion=reportesPreview&idPracticante=${practicante.idPersona}&pNom=${practicante.primerNombre}&sNom=${practicante.segundoNombre}&pApe=${practicante.primerApellido}&sApe=${practicante.segundoApellido}"><input type="button" id="btnReportes" class="btnAsignar"></a></td>
        				</tr>
        				</c:forEach>
        			</tbody>
        		</table>
        	</div>
        
        </div>
        <div class="btnRegresar">
			<a href="VentanaAsesor.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>        
    </body>
</html>
