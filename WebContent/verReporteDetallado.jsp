<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <title>Detalle Reporte</title>
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
			
			$('#btnAceptar').click(function() {
				$('#register-form').attr('action', 'ServletAsesor');
				$('<input />').attr('type', 'hidden')
	              .attr('name', 'accionAsesor')
	              .attr('value', 'Aceptado')
	              .appendTo('#register-form');
				$('#register-form').submit();
			});

			$('#btnRechazar').click(function() {
				$('#register-form').attr('action', 'ServletAsesor');
				$('<input />').attr('type', 'hidden')
	              .attr('name', 'accionAsesor')
	              .attr('value', 'Rechazado')
	              .appendTo('#register-form');
				$('#register-form').submit();
			});
	        
		});
	</script>		    
    <body>      
        <!--MEMU SUPERIOR--> 
         <%@include file="./menuNavegacionAsesor.jsp" %>
		<div>
        	<h1 class="cabin">Detalle de reporte</h1>
        	<h2 class="cabin">Cita número ${requestScope.cita.numCita}</h2>
        </div>
        <br>
        <div id="marcoFormularioCupos">
        	<form align="center" id="register-form" name="formularioComentariosAsesor" action="" method="POST">
        		<input type="hidden" id="operacion" name="operacion" value="guardarComentarios"/>
        		<input type="hidden" id="idReporte" name="idReporte" value="${requestScope.idReporte}"/>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Fecha de la cita:</b></label>
        				<label class="cabin">${requestScope.fechaCita}</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Paciente:</b></label>
        				<label class="cabin">${requestScope.nomPaciente}</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Recibo N°:</b></label>
        				<label class="cabin">${requestScope.reciboNum}</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Profesional que atendió la cita:</b></label>
        				<label class="cabin">${requestScope.profesionalNom}</label>
        			</div>
        		</fieldset>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Objetivo de la sesión:</b></label><br>
        				<textarea id="txtObjetivo" name="txtObjetivo" class="marginTextArea sizeTextArea" disabled>${requestScope.objetivoSesion}</textarea>
						<textarea id="campoObjetivo" name="campoObjetivo" class="marginTextArea sizeTextArea">${requestScope.comentarioReportePracticante.comentariosObjetivo}</textarea>
        			</div>
        		</fieldset>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Descripción de la sesión:</b></label><br>
        				<textarea id="txtDescripcion" name="txtDescripcion" class="marginTextArea sizeTextArea" disabled>${requestScope.descripcionSesion}</textarea>
        				<textarea id="campoDesc" name="campoDesc" class="marginTextArea sizeTextArea">${requestScope.comentarioReportePracticante.comentariosDescripcion}</textarea>
        			</div>        		
        		</fieldset>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Tareas asignadas:</b></label><br>
        				<textarea id="txtTareas" name="txtTareas" class="marginTextArea sizeTextArea" disabled>${requestScope.tareasAsignadasSesion}</textarea>
        				<textarea id="campoTareasAsig" name="campoTareasAsig" class="marginTextArea sizeTextArea">${requestScope.comentarioReportePracticante.comentariosTareas}</textarea>
        			</div>        		
        		</fieldset>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Actividades para la próxima sesión:</b></label><br>
        				<textarea id="txtActividades" name="txtActividades" class="marginTextArea sizeTextArea" disabled>${requestScope.actividadesProxSesion}</textarea>
        				<textarea id="campoActividades" name="campoActividades" class="marginTextArea sizeTextArea">${requestScope.comentarioReportePracticante.comentariosActividades}</textarea>
        			</div>
        		</fieldset>	
        		<fieldset>	
        			<div class="fieldgroup">
        				<input type="button" name="btnAceptar" id="btnAceptar" value="Aprobar Reporte" class="botones"/>
        			</div>
        			<div class="fieldgroup">
        				<input type="button" name="btnRechazar" id="btnRechazar" value="Reporte por Ajustar" class="botones"/>
        			</div>
        		</fieldset>
        		
        	</form>
        </div>
        <div>
			<a href="VentanaAsesor.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
    </body>
</html>