<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Precio Consulta</title>
    </head>
    <body>      
        <!--MEMU SUPERIOR--> 
         <%@include file="/menuNavegacionAdmin.jsp" %>
        <!--MEMU LATERAL--> 
<%--         <%@include file="/menuPersona.jsp" %> --%>

		<div>
        	<h1 class="cabin">Configuración del precio por consulta</h1>
        	<h2 class="cabin">${requestScope.mensaje}</h2>
        </div>
        <div id="marcoFormularioCupos">
        	<form align="center" id="registerForm" name="registerForm" action="./ServletCosto" method="POST">
        		<input type="hidden" id="operacion" name="operacion" value="Actualizar"/>
        		<fieldset>
        			<div class="fieldgroup">
        				<label class="cabin">Costo por consulta:</label>
        				<input type="text" id="costo" placeholder="Costo" value="${requestScope.precio}" name="costo" required>
        			</div>
        			<div class="fieldgroup">
        				<input type="submit" name="btnCostos" id="btnCostos" value="Aceptar" class="botones" onclick="{document.registerForm.operacion.value=Actualizar;document.registerForm.submit();}"/>
        			</div>
        		</fieldset>
        	</form>
        </div>
        <div>
			<a href="InicioConfiguracion.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
    </body>
</html>