<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Crear Cita</title>
    </head>
<body>
	<!--MEMU SUPERIOR-->
        <c:choose>
			<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
				<%@include file="./menuNavegacionAdmin.jsp"%>
			</c:when>
			<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
				<%@include file="./menuNavegacionPracticante.jsp"%>
			</c:when>
		</c:choose>	
		<div style="margin-top: 50px;"><%=request.getAttribute("mensajeRespuestaReporte") %></div>
</body>
</html>