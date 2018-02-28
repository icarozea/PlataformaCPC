<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    	<link rel="stylesheet" href="estilo.css"></link>
    	<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <title>Respuesta Cierre</title>
    </head>
<body>
	<!--MEMU SUPERIOR-->
        <c:choose>
			<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
				<%@include file="./menuNavegacionAdmin.jsp"%>
			</c:when>
			<c:when test="${sessionScope.personaSession.perfil.idPerfil == 2}">
				<%@include file="./menuNavegacionAsesor.jsp"%>
			</c:when>			
			<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
				<%@include file="./menuNavegacionPracticante.jsp"%>
			</c:when>
		</c:choose>
		<div>
			<table class="tablaPrincipal">
				<tr>
					<td>
						<div>
							<button class="respuestaCheck">
									<i class="fa fa-check fa-4x"></i>
								</button>
						</div>
					</td>
				</tr>
			</table>		
		</div>	
		<div class="respuestaMsg cabin"><%=request.getAttribute("mensaje") %></div>
		<br>
		<br>
        <div>
			<a href="./ServletHistoriaClinica?operacion=detalleTratamiento&idPersona=${requestScope.idPaciente}"><input type="button" id="btnVolver" value="Volver" class="botones"/></a>
		</div>		
</body>
</html>