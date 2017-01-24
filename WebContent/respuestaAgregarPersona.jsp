<%-- 
    Document   : respuestaAgregarPersona
    Created on : 17/11/2016
    Author     : Sebastian Gil
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Persona</title>
    </head>
    <body>
    	<!--MENU SUPERIOR--> 
        <c:choose>
			<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
				<%@include file="./menuNavegacionAdmin.jsp"%>
			</c:when>
			<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
				<%@include file="./menuNavegacionPracticante.jsp"%>
			</c:when>
		</c:choose>
        <!--MEMU LATERAL--> 
        <c:choose>
			<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
				<%@include file="./menuPersona.jsp"%>
			</c:when>
		</c:choose>
	     
        <div id="marcoRespuesta" class="caja">
            &nbsp;&nbsp;&nbsp;
            
            <%String mensaje = "";
            String error = "";
            String respuesta = (String)request.getAttribute("respuesta");
            if(respuesta.equals("1"))
            	mensaje = "Se agregó la nueva persona de forma exitosa";
            else{
            	mensaje = "No fue posible agregar la nueva persona";
            	error = (String)request.getAttribute("error");
            }%>
            
            <h2><%= mensaje %></h2>
            <h4><%= error %></h4>
            <c:choose>
				<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
            		<a href="./ServletPersona?operacion=cargueIncial"><input type="button" id="btnContinuar" value="Crear Otro" class="botones"></a>
            		<a href="./busquedaPersonas"><input type="button" id="btnVer" value="Ver Actuales" class="botones"></a>
            	</c:when>
            	<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
            	    <a href="./ServletPersona?operacion=editarPersona&idPersona=${sessionScope.personaSession.idPersona}"><input type="button" id="btnContinuar" value="Volver" class="botones"></a>
            		<a href="VentanaPracticante.jsp"><input type="button" id="btnVer" value="Salir" class="botones"></a>
            	</c:when>
            </c:choose>
       </div>
    </body>
</html>