<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estiloAsignaciones.css"></link>
        <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="js/manejarAsignacion.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Personas</title>
    </head>
    <body>
  		<!--MEMU SUPERIOR--> 
         <%@include file="./menuNavegacion.jsp" %>
         <!--MEMU LATERAL--> 
        <%@include file="./menuLateral.jsp" %>
        <div id="marcoBusqueda" class="cajaBusqueda">
            <form id="buscarPersona" name="buscarPersona" value="buscarPersona" action="./busquedaPersonas" method="GET">
            	<table>
                	<tr><td><h2><b>Seleccionar</b></h2></td>
                    	<td>
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
                    	</td></tr>
            	</table></form></div>  
        <div id="marcoTabla" class="cajaAsignacion">
            <table class="tablaAsignacion">
                <c:forEach items="${requestScope.listaPersonas}" var="persona">
                	<tr>
                    <td class="tdNombre"><h2>${persona.primerNombre} ${persona.segundoNombre} ${persona.primerApellido} ${persona.segundoApellido}</h2></td>
                    <td class="tdBoton"><input type="button" id="btnVer" class="btnVer"></td>
                    <td class="tdBoton"><input type="button" id="btnAsignar" class="btnAsignar" onclick="manejarAsignacion(${persona.idPersona},'${persona.primerNombre}','${persona.segundoNombre}', '${persona.primerApellido}', '${persona.segundoApellido}')"></td>
                </tr> 
                </c:forEach>
            </table>   
       </div>
       <form name="data" action="./ServletAsignaciones" method="get">
    		<input type="hidden" name="id">
    		<input type="hidden" name="pNom">
    		<input type="hidden" name="sNom">
    		<input type="hidden" name="pApe">
    		<input type="hidden" name="sApe">
	   </form>
    </body>
</html>