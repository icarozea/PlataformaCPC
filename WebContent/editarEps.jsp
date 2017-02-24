<%-- 
    Document   : FormularioEPS
    Created on : 07/11/2016
    Author     : Ovidio Zea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.plataforma.cpc.to.EpsTo"%>
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
        	<h1 class="cabin">Editar EPS</h1>
        </div>
        <div id="marcoFormularioEPS">
        	<%
				EpsTo eps = (EpsTo) request.getAttribute("EPS");
			%>
        	<form align="center" id="registerForm" name="registerForm" action="./ServletEPS" method="POST">
        		<input type="hidden" name="operacion" />
                <input type="hidden" name="EpsId" id="EpsId" value="<%=eps.getIdEPS()%>"/>
                <fieldset>
                	<div class="fieldgroup">
                		<label class="cabin">Nombre de la EPS:</label>
                		<input type="text" id="nombre" value="<%=eps.getNombreEPS()%>" placeholder="Nombre" name="nombre" required>
                	</div>
                	<div class="fieldgroup">
                		<input type="submit" name="btnModificarEPS" id="btnModificarEPS" value="Aceptar" class="submit cabin" onclick="{document.registerForm.operacion.value=this.id;document.FormDatos.submit();}"/>
                	</div>
                </fieldset>
        	</form>
        
        </div>
        <div>
			<a href="InicioConfiguracion.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
    </body>
</html>
