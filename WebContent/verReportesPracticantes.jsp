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
        <script>
        	function filtrar(cambio){
        		document.filtro.tipoCambio.value = cambio;
        		document.filtro.submit();
        	}
        </script>
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
			<h2 class="cabin">${requestScope.pNom} ${requestScope.sNom} ${requestScope.pApe} ${requestScope.sApe}</h2>
			<br>
			<div id="marcoSeleccionPersona">
        	<form action="" name="filtro" id="filtro" method="post">
        		<input type="hidden" name="operacion" id="operacion" value="reportesPreview">
        		<input type="hidden" name="idPracticante" id="idPracticante" value="${requestScope.idPracticante}">
        		<input type="hidden" name="tipoCambio" id="tipoCambio">
        		
        		<div class="fieldgroup">
        			<label class="cabin">Paciente</label>
        			<select id ="pacienteActual" name="pacienteActual" onchange="filtrar('paciente')">
                    	<c:forEach items="${requestScope.listaPacientes}" var="paciente">
                    		<c:choose>
                    			<c:when test="${requestScope.pacienteActual == paciente.idPersona}">
				        			<option value="${paciente.idPersona}" selected>${paciente.primerNombre} ${paciente.segundoNombre} ${paciente.primerApellido} ${paciente.segundoApellido}</option>
				        		</c:when>
				        		<c:otherwise>
				        			<option value="${paciente.idPersona}">${paciente.primerNombre} ${paciente.segundoNombre} ${paciente.primerApellido} ${paciente.segundoApellido}</option>
				        		</c:otherwise>
				        	</c:choose>
				        </c:forEach>
				    </select>
        		</div>
        		<div class="fieldgroup">
        			<label class="cabin">Tratamiento</label>
        			<select id ="tratamientoActual" name="tratamientoActual" onchange="filtrar('tratamiento')">
                    	<c:forEach items="${requestScope.listaTratamientos}" var="tratamiento">
                    		<c:choose>
                    			<c:when test="${requestScope.tratamientoActual == tratamiento.idTratamiento}">
				        			<option value="${tratamiento.idTratamiento}" selected>${tratamiento.idTratamiento}. ${tratamiento.tipo} - ${tratamiento.fechaInicio.year}</option>
				        		</c:when>
				        		<c:otherwise>
				        			<option value="${tratamiento.idTratamiento}">${tratamiento.idTratamiento}. ${tratamiento.tipo} - ${tratamiento.fechaInicio.year}</option>
				        		</c:otherwise>
				        	</c:choose>
				        </c:forEach>
				    </select>
        		</div>
        	</form>
        	</div>        
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
	        					<td>${reportePreview.numCita}</td>
	        					<td>${reportePreview.salon}</td>
	        					<td>${reportePreview.primerNombrePaciente} ${reportePreview.segundoNombrePaciente}
	        						${reportePreview.primerApellidoPaciente} ${reportePreview.segundoApellidoPaciente}</td>
	        					
	        					<td>${reportePreview.estado}</td>
	        					
	        					<td>${reportePreview.fecha}</td>
	        					<td class="tdBoton"><a href="./ServletAsesor?operacion=reporteDetallado&idReporte=${reportePreview.idReporte}&pNomPaciente=${reportePreview.primerNombrePaciente}&sNomPaciente=${reportePreview.segundoNombrePaciente}&pApePaciente=${reportePreview.primerApellidoPaciente}&sApePaciente=${reportePreview.segundoApellidoPaciente}"><input type="button" id="btnReportes" class="btnAsignar"></a></td>
        					</tr>
        				</c:forEach>
        				
        				<c:forEach items="${requestScope.valoracionPreview}" var="valoracionPreview">
        					<tr>
	        					<td>0</td>
	        					<td>${valoracionPreview.salon}</td>
	        					<td>${valoracionPreview.primerNombrePaciente} ${valoracionPreview.segundoNombrePaciente}
	        						${valoracionPreview.primerApellidoPaciente} ${valoracionPreview.segundoApellidoPaciente}</td>
	        					
	        					<td>${valoracionPreview.estado}</td>
	        					
	        					<td>${valoracionPreview.fecha}</td>
	        					<td class="tdBoton"><a href="ReportesPracticante?operacion=detallesValoracion&idCita=${valoracionPreview.idCita}"><input type="button" id="btnReportes" class="btnAsignar"></a></td>
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
