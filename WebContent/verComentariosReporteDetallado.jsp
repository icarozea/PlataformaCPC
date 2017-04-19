<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <title>Comentarios de reporte</title>
    </head>
		<%
		  if (session.getAttribute("perfil")==null)
		  {
		    String address = "/index.jsp";
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
		    dispatcher.forward(request,response);
		  }
		%>
	<script>
	$(document).ready(function() {
		
		$('#btnObjetivo').click(function() {
				$('#btnObjetivoCancel').show();
				$('#campoObjetivo').show();
				$('#btnObjetivo').hide();
			});
		
		$('#btnObjetivoCancel').click(function() {
			$('#btnObjetivoCancel').hide();
			$('#campoObjetivo').hide();
			$('#btnObjetivo').show();
			});		
		});
	</script>
		
    <body>      
        <!--MEMU SUPERIOR--> 
         <%@include file="./menuNavegacionAsesor.jsp" %>
		<div>
        	<h1 class="cabin">Comentarios de reporte</h1>
        	<h2 class="cabin">Cita número ${requestScope.idCita}</h2>
        </div>
        <br>
        <div id="marcoFormularioCupos">
        	<form align="center" id="register-form" name="formularioComentariosAsesor" action="ReportesPracticante" method="POST">
        		<input type="hidden" id="operacion" name="operacion" value="guardarMoficiacionesReporteSesion"/>
        		<input type="hidden" id="idReporte" name="idReporte" value="${requestScope.sesionReportePracticante.idSesion}"/>
<%--         		Cita: ${requestScope.sesionReportePracticante.idCita}<br> --%>
<%--         		Fecha: ${requestScope.sesionReportePracticante.fecha}<br> --%>
<%--         		Nombre profesional: ${requestScope.sesionReportePracticante.nombreProfesional}<br> --%>
<%--         		objetivo sesion: ${requestScope.sesionReportePracticante.objetivo}<br> --%>
<%--         		comentario objetivo: ${requestScope.comentarioReportePracticante.comentariosObjetivo}<br> --%>
<%--         		descripcion sesión: ${requestScope.sesionReportePracticante.descripcion}<br> --%>
<%--         		comentario descripción: ${requestScope.comentarioReportePracticante.comentariosDescripcion}<br> --%>
<%--         		tareas sesión: ${requestScope.sesionReportePracticante.tareasAsignadas}<br> --%>
<%--         		comentario tareas: ${requestScope.comentarioReportePracticante.comentariosTareas}<br> --%>
<%--         		actividades sesión: ${requestScope.sesionReportePracticante.actividadesProximaSesion}<br> --%>
<%--         		comentario actividades: ${requestScope.comentarioReportePracticante.comentariosActividades}<br> --%>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Fecha de la cita:</b></label>
        				<label class="cabin">${requestScope.sesionReportePracticante.fecha}</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Paciente:</b></label>
        				<label class="cabin">pepito perez</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Recibo N°:</b></label>
        				<label class="cabin">num recibo</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Profesional que atendió la cita:</b></label>
        				<label class="cabin">${requestScope.sesionReportePracticante.nombreProfesional}</label>
        			</div>        		
        		</fieldset>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Objetivo sesión:</b></label>
        				<label class="cabin">${requestScope.sesionReportePracticante.objetivo}</label>
        				<input type="button" name="btnObjetivo" id="btnObjetivo" value="Modificar objetivo" class="botones"/>
        				<input type="button" name="btnObjetivoCancel" id="btnObjetivoCancel" value="Cancelar" class="botones" hidden="true"/>
						<textarea id="campoObjetivo" name="campoObjetivo" class="marginTextArea sizeTextArea" hidden="true">${requestScope.sesionReportePracticante.objetivo}</textarea>
        			</div>
        		</fieldset>
        		<fieldset>	
        			<div class="fieldgroup">
        				<input type="button" name="btnAceptar" id="btnAceptar" value="Guardar" class="botones"/>
        			</div>
        		</fieldset>        		        		
        	</form>
        </div>
        <div>
			<a href="VentanaPracticante.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
    </body>
</html>