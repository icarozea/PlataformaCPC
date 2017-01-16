<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Cita</title>
    </head>
    <body>
    	<!--MENU SUPERIOR--> 
         <%@include file="./menuNavegacionAdmin.jsp" %>
        <!--MEMU LATERAL--> 
        <%@include file="./menuPersona.jsp" %>      
        <div id="marcoRespuesta" class="caja">
            &nbsp;&nbsp;&nbsp;
            
            <%String mensaje = "";
            String error = "";
            String respuesta = (String)request.getAttribute("respuesta");
            if(respuesta.equals("1"))
            	mensaje = "La nueva cita se creó exitosamente";
            else{
            	mensaje = "No fue posible crear la nueva cita";
            	error = (String)request.getAttribute("error");
            }%>
            
            <h2><%= mensaje %></h2>
            <h4><%= error %></h4>
            <a href="./ServletCita?operacion=cargueIncial"><input type="button" id="btnContinuar" value="Volver" class="botones"></a>     
       </div>
    </body>
</html>