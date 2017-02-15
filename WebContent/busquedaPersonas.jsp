<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estiloAsignaciones.css"></link>
        <script type="text/javascript" src="js/manejarAsignacion.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Personas</title>
    </head>
    <body>
  		<!--MEMU SUPERIOR--> 
         <%@include file="./menuNavegacionAdmin.jsp" %>
         <!--MEMU LATERAL--> 
<%--         <%@include file="./menuLateral.jsp" %> --%>
        <div>
        	<h1 class="cabin">Búsqueda de personas</h1>
        </div>
        <div id="marcoSeleccionPersona">
        	<form action="">
        		<div class="fieldgroup">
        			<label class="cabin">Rol</label>
        			<select id ="busqueda" name="busqueda" onchange="this.form.submit()">
                            	<c:forTokens items="Practicante,Supervisor,Paciente,Administrador" delims="," var="name">
				                	<c:choose>
				                		<c:when test="${requestScope.valor == name}">
				                			<option value="${name}" selected>${name}</option>
				                		</c:when>
				                		<c:otherwise>
				                			<option value="${name}">${name}</option>
				                		</c:otherwise>
									</c:choose>
				            	</c:forTokens></select>
        		</div>
        		<div class="fieldgroup">
        			<label class="cabin">Jornada</label>
        			<select id ="jornada" name="jornada" onchange="this.form.submit()">
                    		<c:forTokens items="manana,tarde,noche" delims="," var="name">
				                	<c:choose>
				                		<c:when test="${requestScope.jornada == name}">
				                			<option value="${name}" selected>${name}</option>
				                		</c:when>
				                		<c:otherwise>
				                			<option value="${name}">${name}</option>
				                		</c:otherwise>
									</c:choose>
                    		</c:forTokens></select>
        		</div>
        	</form>
        </div>
        <div id="usuarios">
        	<table id="tablaUsuarios">
        		<thead>
        			<tr>
		          		<th scope="col">Nombre</th>
		          		<th scope="col">Visualizar</th>
	          			<c:forEach items="${requestScope.listaPersonas}" var="persona">
	          				<c:if test="${requestScope.valor == 'Paciente'}">
	          					<th scope="col">Historia clínica</th>
	          				</c:if>
	          				<c:if test="${requestScope.valor == 'Practicante' || requestScope.valor == 'Supervisor'}">
	          					<th scope="col">Asignación de usuarios</th>
	          				</c:if>
	          			</c:forEach>
			         </tr> 		
        		</thead>
        		<tbody>
	        		<c:forEach items="${requestScope.listaPersonas}" var="persona">
	                	<tr>
	                    <td class="tdNombre"><label class="cabin">${persona.primerNombre} ${persona.segundoNombre} ${persona.primerApellido} ${persona.segundoApellido}</label></td>
	                    <td class="tdBoton"><a href="./ServletPersona?operacion=listarPersonas&id=${persona.idPersona}"><input type="button" id="btnVer" class="btnVer"></a></td>
	                    <c:choose>
						    <c:when test="${requestScope.valor == 'Practicante' || requestScope.valor == 'Supervisor'}">
		                    	<td class="tdBoton"><input type="button" id="btnAsignar" class="btnAsignar" onclick="manejarAsignacion('consultar',-1,${persona.idPersona},'${persona.primerNombre}','${persona.segundoNombre}', '${persona.primerApellido}', '${persona.segundoApellido}', '${requestScope.valor}')"></td>
		                    </c:when>
	                    	<c:when test="${requestScope.valor == 'Paciente'}">
	                    		<td class="tdBoton"><a href="./ServletHistoriaClinica?operacion=detalleTratamiento&idPersona=${persona.idPersona}"><input type="button" id="btnHistoria" class="btnHistoria"></a></td>
	                    	</c:when>
	                    </c:choose>
	                </tr> 
	                </c:forEach>
                </tbody>
        	</table>
        </div>
        <div>
			<a href="inicioPersonas.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
       <form name="data" action="./ServletAsignaciones" method="get">
       		<input type="hidden" name="operacion">
       		<input type="hidden" name="asignado">
    		<input type="hidden" name="id">
    		<input type="hidden" name="pNom">
    		<input type="hidden" name="sNom">
    		<input type="hidden" name="pApe">
    		<input type="hidden" name="sApe">
    		<input type="hidden" name="rol">
	   </form>
    </body>
</html>