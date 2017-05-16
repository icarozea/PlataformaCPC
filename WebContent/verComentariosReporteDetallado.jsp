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

		$('#btnDescripcion').click(function() {
			$('#btnDescripcionCancel').show();
			$('#campoDescripcion').show();
			$('#btnDescripcion').hide();
		});
	
		$('#btnDescripcionCancel').click(function() {
			$('#btnDescripcionCancel').hide();
			$('#campoDescripcion').hide();
			$('#btnDescripcion').show();
			});

		$('#btnTareas').click(function() {
			$('#btnTareasCancel').show();
			$('#campoTareas').show();
			$('#btnTareas').hide();
		});
	
		$('#btnTareasCancel').click(function() {
			$('#btnTareasCancel').hide();
			$('#campoTareas').hide();
			$('#btnTareas').show();
			});

		$('#btnActividadesProx').click(function() {
			$('#btnActividadesProxCancel').show();
			$('#campoActividades').show();
			$('#btnActividadesProx').hide();
		});
	
		$('#btnActividadesProxCancel').click(function() {
			$('#btnActividadesProxCancel').hide();
			$('#campoActividades').hide();
			$('#btnActividadesProx').show();
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
        		<input type="hidden" id="idReporte" name="idReporte" value="${requestScope.citaSesionReportePracticante.idSesion}"/>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Fecha de la cita:</b></label>
        				<label class="cabin">${requestScope.citaSesionReportePracticante.fecha}</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Paciente:</b></label>
        				<label class="cabin">${requestScope.paciente.primerNombre}
        									 ${requestScope.paciente.segundoNombre}
        									 ${requestScope.paciente.primerApellido}
        									 ${requestScope.paciente.segundoApellido}</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Recibo N°:</b></label>
        				<label class="cabin">${requestScope.citaSesionReportePracticante.numRecibo}</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Profesional que atendió la cita:</b></label>
        				<label class="cabin">${requestScope.citaSesionReportePracticante.nombreProfesional}</label>
        			</div>        		
        		</fieldset>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Objetivo sesión:</b></label>
        				<label class="cabin">${requestScope.citaSesionReportePracticante.objetivo}</label>
        				<input type="button" name="btnObjetivo" id="btnObjetivo" value="Actualizar" class="botones"/>
        				<input type="button" name="btnObjetivoCancel" id="btnObjetivoCancel" value="Cancelar" class="botones" hidden="true"/>
						<textarea id="campoObjetivo" name="campoObjetivo" class="marginTextArea sizeTextArea" hidden="true">${requestScope.citaSesionReportePracticante.objetivo}</textarea>
        			</div>
        		</fieldset>
        		<fieldset>
        				<label class="cabin"><b>Comentario sobre objetivo:</b></label>
        				<label class="cabin">${requestScope.citaSesionReportePracticante.comentarios.comentariosObjetivo}</label>        		
        		</fieldset>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Descripción de sesión:</b></label>
        				<label class="cabin">${requestScope.citaSesionReportePracticante.descripcion}</label>
        				<input type="button" name="btnDescripcion" id="btnDescripcion" value="Actualizar" class="botones"/>
        				<input type="button" name="btnDescripcionCancel" id="btnDescripcionCancel" value="Cancelar" class="botones" hidden="true"/>
						<textarea id="campoDescripcion" name="campoDescripcion" class="marginTextArea sizeTextArea" hidden="true">${requestScope.citaSesionReportePracticante.descripcion}</textarea>
        			</div>
        		</fieldset>
        		<fieldset>
        				<label class="cabin"><b>Comentario sobre descripción:</b></label>
        				<label class="cabin">${requestScope.citaSesionReportePracticante.comentarios.comentariosDescripcion}</label>        		
        		</fieldset>        		
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Tareas asignadas:</b></label>
        				<label class="cabin">${requestScope.citaSesionReportePracticante.tareasAsignadas}</label>
        				<input type="button" name="btnTareas" id="btnTareas" value="Actualizar" class="botones"/>
        				<input type="button" name="btnTareasCancel" id="btnTareasCancel" value="Cancelar" class="botones" hidden="true"/>
						<textarea id="campoTareas" name="campoTareas" class="marginTextArea sizeTextArea" hidden="true">${requestScope.citaSesionReportePracticante.tareasAsignadas}</textarea>
        			</div>
        		</fieldset>
        		<fieldset>
        				<label class="cabin"><b>Comentario sobre tareas asignadas:</b></label>
        				<label class="cabin">${requestScope.citaSesionReportePracticante.comentarios.comentariosTareas}</label>        		
        		</fieldset>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Actividades próxima sesión:</b></label>
        				<label class="cabin">${requestScope.citaSesionReportePracticante.actividadesProximaSesion}</label>
        				<input type="button" name="btnActividadesProx" id="btnActividadesProx" value="Actualizar" class="botones"/>
        				<input type="button" name="btnActividadesProxCancel" id="btnActividadesProxCancel" value="Cancelar" class="botones" hidden="true"/>
						<textarea id="campoActividades" name="campoActividades" class="marginTextArea sizeTextArea" hidden="true">${requestScope.citaSesionReportePracticante.actividadesProximaSesion}</textarea>
        			</div>
        		</fieldset>        		
        		<fieldset>
        				<label class="cabin"><b>Comentario sobre actividades próxima sesión:</b></label>
        				<label class="cabin">${requestScope.citaSesionReportePracticante.comentarios.comentariosActividades}</label>        		
        		</fieldset>
        		<fieldset>	
        			<div class="fieldgroup">
        				<input type="submit" name="btnAceptar" id="btnAceptar" value="Guardar" class="botones"/>
        			</div>
        		</fieldset>        		        		
        	</form>
        </div>
        <div>
			<a href="VentanaPracticante.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
    </body>
</html>