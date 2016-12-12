<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			selectable: true,
			selectHelper: true,
			select: function(start, end) {
				//var title = prompt('Event Title:');
				var eventData;
				//if (title) {
					eventData = {
						//title: title,
						start: start,
						end: end
					};
					$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
					$('#fecha').val(eventData.start);
					$('#enviarFecha').submit();
				//}
				$('#calendar').fullCalendar('unselect');
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
		margin: 0 auto;
	}

</style>
</head>
<body>
	<div id='calendar'></div>
	<form id="enviarFecha" name="enviarFecha" action="./ServletCita" method="POST">
		<input type="hidden" name="fecha"  id="fecha"/>
		<input type="hidden" name="idPersona"  id="idPersona" value="<%= request.getParameter("idPersona") %>"/>
		<input type="hidden" name="operacion"  id="operacion" value="crearCita"/>
	</form>
</body>
</html>