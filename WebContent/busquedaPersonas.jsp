<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estiloAsignaciones.css"></link>
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
                            	<c:forTokens items="Practicante,Supervisor,Paciente, Administrador" delims="," var="name">
				                	<option value="${name}">${name}</option>
				            	</c:forTokens></select>
                    	</td></tr>
            	</table></form></div>  
        <div id="marcoTabla" class="cajaAsignacion">
            <table class="tablaAsignacion">
                <tr>
                    <td class="tdNombre"><h2>Pedro Paredes Puentes</h2></td>
                    <td class="tdBoton"><input type="button" id="btnVer" class="btnVer"></td>
                    <td class="tdBoton"><input type="button" id="btnAsignar" class="btnAsignar"></td>
                </tr>
                <tr>
                    <td class="tdNombre"><h2>Juan Jose Jaramillo Jimenez</h2></td>
                    <td class="tdBoton"><input type="button" id="btnVer" class="btnVer"></td>
                    <td class="tdBoton"><input type="button" id="btnAsignar" class="btnAsignar"></td>
                </tr>
            </table>   
       </div>
    </body>
</html>