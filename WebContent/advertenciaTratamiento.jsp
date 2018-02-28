<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Advertencia</title>
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
             
        <div id="marcoRespuesta" class="caja">
            <br>
            <h2>El paciente ya tiene un tratamiento activo de este tipo</h2>
            <h3>¿Desea continuar con la creación de la cita?</h3>
            <form id="FormDatos" name="FormDatos" action="./ServletCita" method="POST">
            	<input type="hidden" name="operacion" id="operacion" value="guardarCita"/>
				<input type="hidden" name="idPracticante" id="idPracticante" value="${requestScope.idPracticante}"/>
				<input type="hidden" name="grupoPaciente" id="grupoPaciente" value="${requestScope.idPaciente}"/>
				<input type="hidden" name="salon" id="salon" value="${requestScope.salon}"/>
				<input type="hidden" name="valoracion" id="valoracion" value="${requestScope.valoracion}"/>
				<input type="hidden" name="fecha" id="fecha" value="${requestScope.fecha}"/>
				<input type="hidden" name="tipoTratamiento" id="tipoTratamiento" value="${requestScope.tipoTratamiento}"/>
				<input type="hidden" name="flagValoracion" id="flagValoracion" value="aceptado"/>
            	<input type="submit" id="btnContinuar" value="Continuar" class="botones">
            </form>
            <c:choose>
            	<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
            		<a href="./ServletCita?operacion=cargueIncial"><input type="button" id="btnVolver" value="Volver" class="botones"></a>   
            	</c:when>
            	<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
            		<a href="./ServletCita?operacion=crearCita&idPracticante=${sessionScope.personaSession.idPersona}"><input type="button" id="btnVolver" value="Volver" class="botones"></a>  
            	</c:when>
            </c:choose>
       </div>
    </body>
</html>