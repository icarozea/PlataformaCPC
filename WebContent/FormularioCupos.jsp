<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cupos Practicantes</title>
    </head>
    <body>      
        <!--MEMU SUPERIOR--> 
         <%@include file="/menuNavegacionAdmin.jsp" %>
        <!--MEMU LATERAL--> 
<%--         <%@include file="/menuPersona.jsp" %> --%>

		<div>
        	<h1 class="cabin">Configuración de cupos por usuario</h1>
        </div>
        <div id="marcoFormularioCupos">
        	<form align="center" id="registerForm" name="registerForm" action="./ServletCupos" method="POST">
        		<input type="hidden" id="operacion" name="operacion" value="Actualizar"/>
        		<fieldset>
        			<div class="fieldgroup">
        				<label class="cabin">Cupos por usuario:</label>
        				<input type="text" id="cupos" placeholder="Cupos" value="${requestScope.cupos}" name="cupos" required>
        			</div>
        			<div class="fieldgroup">
        				<input type="submit" name="btnCupos" id="btnCupos" value="Aceptar" class="botones" onclick="{document.registerForm.operacion.value=Actualizar;document.registerForm.submit();}"/>
        			</div>
        		</fieldset>
        	</form>
        </div>
        <div>
			<a href="InicioConfiguracion.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
    </body>
</html>