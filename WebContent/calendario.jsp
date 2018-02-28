<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Calendario</title>
<meta charset='utf-8' />
<link href='fullcalendar.css' rel='stylesheet' />
<link href='fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='js/moment.min.js'></script>
<script src='js/jquery-3.1.1.min.js'></script>
<script src='js/fullcalendar.min.js'></script>
<script src='js/locale-all.js'></script>
<% String eventos = (String)request.getAttribute("citas"); %>
<script>
	$(document).ready(function() {
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'agendaWeek,agendaDay'
			},
			locale: 'es',
			navLinks: true, // can click day/week names to navigate views
			defaultView: 'agendaWeek',
			lazyFetching: true,
			selectable: true,
			selectHelper: true,
			select: function(start, end) {
				var eventData;
					eventData = {
						start: start,
						end: end
					};
					$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
					$('#fecha').val(eventData.start);
					$('#operacion').val('crearCita');
					$('#formDatos').submit();

				$('#calendar').fullCalendar('unselect');
			},
			eventClick: function(calEvent, jsEvent, view) {
				
				if($('#ejecutar').is(':checked')){
					if(calEvent.estado === 'creada'){
						var ask=confirm("¿Desea ejecutar esta cita?");
				    	if(ask){
				    		$('#idCita').val(calEvent.id);
							$('#operacion').val('ejecutarCita');
							$('#formDatos').submit();
				    	}
					}
					else{
						var ask = confirm("Ya existe un reporte de esta cita ¿Desea ver el reporte?");
						if(ask){
							$('#idCita').val(calEvent.id);
							$('#operacion').val('verCita');
							$('#formDatos').submit();
						}
					}
				}
				else{
					var ask=confirm("¿Desea eliminar la cita?");
				    if(ask){
				    	$('#idCita').val(calEvent.id);
						$('#operacion').val('eliminarCita');
						$('#formDatos').submit();
				    }
				}
		    },
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events: [
				<%= eventos %>
			]
		});
	});

</script>
<style>

	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}

	#calendar {
		max-width: 900px;
		margin: 2% auto;
		background-color: #ffffff;
	}
	
	.divCentro{
		margin-left: 40%;
	}

</style>
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
	<c:choose>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
			<%@include file="./menuNavegacionAdmin.jsp"%>
		</c:when>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
			<%@include file="./menuNavegacionPracticante.jsp"%>
		</c:when>
	</c:choose>
	
	<div>
    	<h2 class="cabin">Al seleccionar</h2>
    	<div class="divCentro">
	    	<table>
	    		<tr>
	    			<td><input type="radio" id="ejecutar" name="grupoMenu" value="ejecutar" checked></td>
	    			<td>Ejecutar</td>
	    			<td><input type="radio" id="eliminar" name="grupoMenu" value="eliminar"></td>
	    			<td>Eliminar</td>
	    		</tr>
	    	</table>
    	</div>
    </div>
    
	<div id='calendar'></div>
	
	<form id="formDatos" name="formDatos" action="./ServletCita" method="POST">
		<input type="hidden" name="fecha"  id="fecha"/>
		<input type="hidden" name="idPracticante"  id="idPracticante" value="<%= request.getParameter("idPracticante") %>"/>
		<input type="hidden" name="operacion"  id="operacion"/>
		<input type="hidden" name="idCita"  id="idCita"/>
		
		<input type="hidden" id="paciente" name="paciente" value="${requestScope.paciente}">
		<input type="hidden" id="salon" name="salon" value="${requestScope.salon}">
		<input type="hidden" id="valoracion" name="valoracion" value="${requestScope.valoracion}">
		<input type="hidden" id="tipo" name="tipo" value="${requestScope.tipo}">
	</form>
</body>
</html>