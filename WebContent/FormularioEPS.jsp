<%-- 
    Document   : FormularioEPS
    Created on : 07/11/2016
    Author     : Ovidio Zea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <script type="text/javascript" src="js/ValidarPassword.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar EPS</title>
    </head>
    
    <script>
    	function redireccionar(){
    		document.registerForm.action = "./ServletPersona?operacion=volverEPS";
    		document.registerForm.submit();
    	}
    </script>
    <body>      
        <!--MEMU SUPERIOR--> 
         <%@include file="/menuNavegacionAdmin.jsp" %>
        
        <div>
        	<h1 class="cabin">Agregar EPS</h1>
        </div>
        
        <c:choose>
        		<c:when test="${requestScope.mensaje == '1' }">
        			<h2>EPS agregada exitosamente</h2>
        		</c:when>
        		<c:when test="${requestScope.mensaje == '2' }">
        			<h2>No se pudo agregar la nueva EPS</h2>
        		</c:when>
		</c:choose>
		
        <div id="marcoFormularioEPS">
        	<form id="registerForm" name="registerForm" action="./ServletEPS" method="POST">
        		<input type="hidden" name="operacion" value="btnAgregarEPS"/>
        		<input type="hidden" name="formulario" value="${requestScope.formulario}"/>
        		<input type="hidden" id="operacion"  name="operacion" value="volverEPS">
				<input type="hidden" id="nombre1"  name="nombre1" value="${requestScope.pNom}">
				<input type="hidden" id="nombre2"  name="nombre2" value="${requestScope.sNom}">
				<input type="hidden" id="apellido1"  name="apellido1" value="${requestScope.pApe}">
				<input type="hidden" id="apellido2"  name="apellido2" value="${requestScope.sApe}">
				<input type="hidden" id="tipoDocumento"  name="tipoDocumento" value="${requestScope.doc}">
				<input type="hidden" id="numeroDocumento"  name="numeroDocumento" value="${requestScope.num}">
				<input type="hidden" id="direccion"  name="direccion" value="${requestScope.dir}">
				<input type="hidden" id="telefono"  name="telefono" value="${requestScope.tel}">
				<input type="hidden" id="telefono2"  name="telefono2" value="${requestScope.tel2}">
				<input type="hidden" id="correo"  name="correo" value="${requestScope.mail}">
				<input type="hidden" id="perfil"  name="perfil" value="${requestScope.perfil}">
				<input type="hidden" id="password"  name="password" value="${requestScope.pass}">
				<input type="hidden" id="jornada"  name="jornada" value="${requestScope.jornada}">
				<input type="hidden" id="codigo"  name="codigo" value="${requestScope.cod}">
				<input type="hidden" id="eps"  name="eps" value="${requestScope.eps}">
				
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
        	<c:choose>
        		<c:when test="${requestScope.formulario != null || requestScope.formulario != '' }">
        			<button id="logoutBtn" class="btnReturn btnReturn-warning" onClick="redireccionar()">Regresar</button>
        		</c:when>
        		<c:otherwise>
					<a href="InicioConfiguracion.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
				</c:otherwise>
			</c:choose>
		</div>
    </body>
</html>