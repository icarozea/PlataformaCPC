<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cupos Practicantes</title>
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
        <!--MEMU SUPERIOR--> 
         <%@include file="./menuNavegacionAsesor.jsp" %>
		<div>
        	<h1 class="cabin">Detalle del reporte número ${requestScope.idReporte}</h1>
        </div>
        <br>
        <div id="marcoFormularioCupos">
        	<form align="center" id="register-form" name="" action="" method="POST">
        		<input type="hidden" id="operacion" name="operacion" value="guardarComentarios"/>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Fecha de la cita:</b></label>
        				<label class="cabin">blah</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Cita N°:</b></label>
        				<label class="cabin">blah</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Paciente:</b></label>
        				<label class="cabin">blah</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Recibo N°:</b></label>
        				<label class="cabin">blah</label>
        			</div>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Profesional que atendió la cita:</b></label>
        				<label class="cabin">blah</label>
        			</div>
        		</fieldset>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Objetivo sesión:</b></label>
        				<label class="cabin">${requestScope.objetivoSesion}</label>
						<textarea class="marginTextArea sizeTextArea"></textarea>
        			</div>
        		</fieldset>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Descripción de la sesión:</b></label>
        				<label class="cabin">${requestScope.descripcionSesion}</label>
        				<textarea class="marginTextArea sizeTextArea"></textarea>
        			</div>        		
        		</fieldset>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Tareas asignadas:</b></label>
        				<label class="cabin">${requestScope.tareasAsignadasSesion}</label>
        				<textarea class="marginTextArea sizeTextArea"></textarea>
        			</div>        		
        		</fieldset>
        		<fieldset>
        			<div class="fieldgroupTextArea">
        				<label class="cabin"><b>Actividades para la próxima sesión:</b></label>
        				<label class="cabin">${requestScope.actividadesProxSesion}</label>
        				<textarea class="marginTextArea sizeTextArea"></textarea>
        			</div>
        		</fieldset>	
        		<fieldset>	
        			<div class="fieldgroup">
        				<input type="submit" name="btnCupos" id="btnCupos" value="Aceptar" class="botones" onclick=""/>
        			</div>
        			<div class="fieldgroup">
        				<input type="button" name="btnCupos" id="btnCupos" value="Rechazar" class="botones" onclick=""/>
        			</div>
        		</fieldset>
        		
        	</form>
        </div>
        <div>
			<a href="VentanaAsesor.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
    </body>
</html>