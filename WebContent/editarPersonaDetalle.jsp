<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estilo.css"></link>
<script type="text/javascript" src="js/ValidarPassword.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agregar Persona</title>
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
	<c:choose>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
<%-- 			<%@include file="./menuPersona.jsp"%> --%>
		</c:when>
	</c:choose>
	<div>
		<form id="register-form" action="./ServletPersonaDetalle" method="POST">
			<input type="hidden" name="operacion" id="operacion" />
			<input type="hidden" name="idPersona" id="idPersona" value="${requestScope.personaDetalle.personaId}"/>
	    	<div id="form-content">
	        	<fieldset>
		            <div class="fieldgroup">
		                <label class="cabin">Sexo</label>
		                <input type="text" id="sexo" name="sexo" class="cabin" value="${requestScope.personaDetalle.sexo}" placeholder="sexo"/>
		            </div>	
		            <div class="fieldgroup">
		                <label class="cabin">Edad</label>
		                <input type="text" id="edad" name="edad" value="${requestScope.personaDetalle.edad}" placeholder="Edad">            
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Acudiente</label>
						<input type="text" id="acudiente" name="acudiente" value="${requestScope.personaDetalle.acudiente}" placeholder="Acudiente"  />
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Proceso</label>
						<input type="text" id="proceso" name="proceso" value="${requestScope.personaDetalle.proceso}" placeholder="Proceso" />                
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Pertenece a la universidad</label>
						<input type="text" id="perteneceU" name="perteneceU" value="${requestScope.personaDetalle.perteneceU}" placeholder="Si/No" />
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Facultad</label>
						<input type="text" id="facultad" name="facultad" value="${requestScope.personaDetalle.facultad}" placeholder="Facultad" />
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Semestre</label>
						<input type="text" id="semestre" name="semestre" value="${requestScope.personaDetalle.semestre}" placeholder="Semestre" />
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Persona modifica datos</label>
						<input type="text" id="nombre" name="nombre" value="${requestScope.personaDetalle.nombreModifica}" placeholder="Nombre" />
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Problematica</label>
						<textarea id="problematica" name="problematica" rows="10" value="${requestScope.personaDetalle.problematica}"  cols="46" style=" resize: none;">
						</textarea>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Observaciones</label>
						<textarea id=observaciones" name="observaciones" rows="10" value="${requestScope.personaDetalle.observación}" cols="46" style=" resize: none;">
						</textarea>
		            </div>
		            <div class="fieldgroup">
		                <input type="button" value="Registrar" onclick="modificarDetalle()" class="submit cabin"/>
		            </div>
		            <c:choose>
		            	<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
		            		<div class="fieldgroup">
		                		<a href="inicioPersonas.jsp"><input type="button" value="Cancelar" class="submit cabin"></a>
		            		</div>
		            	</c:when>
		            	<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
		            		<div class="fieldgroup">
		                		<a href="VentanaPracticante"><input type="button" value="Cancelar" class="submit cabin"></a>
		            		</div>
		            	</c:when>
		            </c:choose>	
	        </fieldset>
	    </div>
	</form>
	</div>
</body>
</html>