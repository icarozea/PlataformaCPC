<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="estilo.css"></link>
<link rel="stylesheet" href="estiloAsignaciones.css"></link>
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>Historico Reportes</title>
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
	<%@include file="./menuNavegacionPracticante.jsp"%>
	<div id="gestioncitas">
		<h1 class="droidSans">Histórico de Reportes</h1>
		<br>
		<div id="marcoSeleccionPersona">
        	<form action="ReportesPracticante" name="filtroForm" id="filtroForm" method="post">
        		<input type="hidden" name="operacion" id="operacion" value="historicoReportes">
        		<input type="hidden" name="idPracticante" id="idPracticante" value="${sessionScope.personaSession.idPersona}">
        		
        		<div class="fieldgroup">
        			<label class="cabin">Tipo de Reporte</label>
        			<select id ="filtro" name="filtro" onchange="submit()">
        				<c:choose>
        					<c:when test="${requestScope.tipo == 0 }">
        						<option value="valoracion" selected>Valoraciones</option>
				        		<option value="sesion">Sesiones</option>
        					</c:when>
        					<c:otherwise>
        						<option value="valoracion">Valoraciones</option>
				        		<option value="sesion" selected>Sesiones</option>
        					</c:otherwise>
        				</c:choose>
				    </select>
        		</div>
        	</form>
        </div>  
        	
		<div id="gestionContenido">
			<table id="tablaUsuarios">
				<thead>
					<th>Paciente</th>
					<th>No Cita</th>
					<th>Fecha</th>
					<th>Ver Reporte</th>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.datos}" var="dato">
						<tr>
								<td>${dato.primerNombrePaciente} ${dato.segundoNombrePaciente} ${dato.primerApellidoPaciente} ${dato.segundoApellidoPaciente}</td>
								<td>${dato.numCita}</td>
								<td>${dato.fecha}</td>
								<c:choose>
        							<c:when test="${requestScope.tipo == 0 }">
										<td><a href="ReportesPracticante?operacion=detallesValoracion&idCita=${dato.idCita}"><input type="button" id="btnComentarios" class="btnAsignar"></a></td>
									</c:when>
									<c:otherwise>
										<td><a href="ReportesPracticante?operacion=comentariosReporte&idCita=${dato.idCita}"><input type="button" id="btnComentarios" class="btnAsignar"></a></td>
									</c:otherwise>
								</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
		<br>
		<div>
			<a href="inicioReporte.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
</body>
</html>