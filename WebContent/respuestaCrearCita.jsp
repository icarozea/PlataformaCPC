<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Cita</title>
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
        <c:if test="${sessionScope.personaSession.perfil.idPerfil == 1}" >
        	<%@include file="./menuPersona.jsp" %> 
        </c:if>
             
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
            <c:choose>
            	<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
            		<a href="./ServletCita?operacion=cargueIncial"><input type="button" id="btnContinuar" value="Volver" class="botones"></a>   
            	</c:when>
            	<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
            		<a href="./ServletCita?operacion=crearCita&idPersona=${sessionScope.personaSession.idPersona}"><input type="button" id="btnContinuar" value="Volver" class="botones"></a>  
            	</c:when>
            </c:choose>
       </div>
    </body>
</html>