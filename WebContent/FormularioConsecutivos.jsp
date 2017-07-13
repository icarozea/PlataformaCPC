<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reiniciar Consecutivos HC</title>
    </head>
    <body>      
        <!--MEMU SUPERIOR--> 
         <%@include file="/menuNavegacionAdmin.jsp" %>
        
        <div>
        	<h1 class="cabin">Consecutivo Actual</h1>
        </div>
        <div id="marcoFormularioConsecutivos">
        	<form id="registerForm" name="registerForm" action="./Consecutivo" method="POST">
        		<input type="hidden" name="operacion"/>
        		<fieldset>
        			<div class="fieldgroup">
        				<label class="cabin">Año:</label>
        				<input type="text" id="ano" placeholder="2017" name="ano" value='${requestScope.ano}' required>
        			</div>
        			<div class="fieldgroup">
        				<label class="cabin">Semestre:</label>
        				<input type="text" id="semestre" placeholder="I" name="semestre" value='${requestScope.semestre}' required>
        			</div>
        			<div class="fieldgroup">
        				<label class="cabin">Consecutivo:</label>
        				<input type="text" id="consecutivo" name="consecutivo" value='${requestScope.consecutivo}'>
        			</div>
        			<div class="fieldgroup">
        				<input type="submit" name="btnConsecutivo" id="btnConsecutivo" value="Reiniciar Consecutivo" class="submit cabin" onclick="{document.registerForm.operacion.value='reiniciar';document.registerForm.submit();}"/>
        			</div>
        		</fieldset>
        	</form>
        </div>
        <div>
			<br><br><lable>${requestScope.mensaje}</lable><br><br>
		</div>
        <div>
			<a href="InicioConfiguracion.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
    </body>
</html>