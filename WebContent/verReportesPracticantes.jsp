<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilo.css"></link>
        <link rel="stylesheet" href="estiloAsignaciones.css"></link>
        <script type="text/javascript" src="js/manejarAsignacion.js"></script>
        <title>Ver Practicantes</title>
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
        	<h1 class="cabin">Reportes del practicante</h1>
			<h2 class="cabin">${requestScope.nomPracticante}</h2>
			<br>        
        	<div id="reportes">
        		<table id="tablaUsuarios">
        			<thead>
        				<th>Cita número</th>
        				<th>Salón</th>
        				<th>Nombre Paciente</th>
        				<th>Estado</th>
        				<th>Fecha</th>
        				<th>Evaluar</th>
        			</thead>
        			<tbody>
        				<c:forEach items="${requestScope.reportesPreview}" var="reportePreview">
        					<tr>
	        					<td>${reportePreview.idCita}</td>
	        					<td>${reportePreview.salon}</td>
	        					<td>${reportePreview.primerNombrePaciente} ${reportePreview.segundoNombrePaciente}
	        						${reportePreview.primerApellidoPaciente} ${reportePreview.segundoApellidoPaciente}</td>
	        					
	        					<td>${reportePreview.estado}</td>
	        					
	        					<td>${reportePreview.fecha}</td>
	        					<td class="tdBoton"><a href="./ServletAsesor?operacion=reporteDetallado&idReporte=${reportePreview.idReporte}&pNomPaciente=${reportePreview.primerNombrePaciente}&sNomPaciente=${reportePreview.segundoNombrePaciente}&pApePaciente=${reportePreview.primerApellidoPaciente}&sApePaciente=${reportePreview.segundoApellidoPaciente}"><input type="button" id="btnReportes" class="btnAsignar"></a></td>
        					</tr>
        				</c:forEach>
        			</tbody>
        		</table>
        	</div>
        
        </div>
    		<div class="btnRegresar">
				<a href="./ServletAsesor?operacion=practicantes&idAsesor=${sessionScope.personaSession.idPersona}"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
			</div>        
    </body>
</html>
