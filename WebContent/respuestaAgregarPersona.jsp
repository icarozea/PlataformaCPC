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
         <%@include file="./menuNavegacion.jsp" %>
        <!--MEMU LATERAL--> 
        <%@include file="./menuPersona.jsp" %>      
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
            <a href="./ServletPersona?operacion=cargueIncial"><input type="button" id="btnContinuar" value="Crear Otro" class="botones"></a>     
       		<a href="./busquedaPersonas"><input type="button" id="btnVer" value="Ver Actuales" class="botones"></a>
       </div>
    </body>
</html>