<%-- 
    Document   : FormularioEPS
    Created on : 07/11/2016
    Author     : Ovidio Zea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <script type="text/javascript" src="js/ValidarPassword.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar EPS</title>
    </head>
    <body>      
        <!--MEMU SUPERIOR--> 
         <%@include file="/menuNavegacionAdmin.jsp" %>
        <!--MEMU LATERAL--> 
<%--         <%@include file="/menuPersona.jsp" %> --%>
        
        <div>
        	<h1 class="cabin">Agregar EPS</h1>
        </div>
        <div id="marcoFormularioEPS">
        	<form id="registerForm" name="registerForm" action="./ServletEPS" method="POST">
        		<input type="hidden" name="operacion"/>
        		<fieldset>
        			<div class="fieldgroup">
        				<label class="cabin">Nombre de la nueva EPS:</label>
        				<input type="text" id="nombre" placeholder="Nombre de la nueva EPS" name="nombre" required>
        			</div>
        			<div class="fieldgroup">
        				<input type="submit" name="btnAgregarEPS" id="btnAgregarEPS" value="Registrar" class="submit cabin" onclick="{document.registerForm.operacion.value=this.id;document.registerForm.submit();}"/>
        			</div>
        		</fieldset>
        	</form>
        </div>
        <div>
			<a href="InicioConfiguracion.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
    </body>
</html>