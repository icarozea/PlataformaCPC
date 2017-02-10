<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estiloAsignaciones.css"></link>
        <script type="text/javascript" src="js/manejarAsignacion.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignar</title>
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
    	<!--MEMU SUPERIOR--> 
         <%@include file="./menuNavegacionAdmin.jsp" %>
         <!--MEMU LATERAL--> 
<%--         <%@include file="./menuLateral.jsp" %> --%>
        <div id="" class="">
            <h1 class="cabin">${requestScope.pNom} ${requestScope.sNom} ${requestScope.pApe} ${requestScope.sApe}</h1>
            <h2 class="cabin">Cupos: ${requestScope.cupos}</h2>
            <a href="busquedaPersonas"><input type="button" id="btnAsignar" value="Volver" class="botones"></a>
            <table>
                <tr>
                    <td><div id="marcoAsignados" class="cajaTablas">
                    	<h1>ASIGNADOS</h1>
                        <table><c:forEach items="${requestScope.asignados}" var="asignado">
                        	<tr>
                            	<td>${asignado.primerNombre} ${asignado.segundoNombre} ${asignado.primerApellido} ${asignado.segundoApellido}</td>
                            	<td><input type="button" id="btnEliminar" value="-"
                            	onclick="manejarAsignacion('eliminar',${asignado.idPersona},${requestScope.id},'${requestScope.pNom}','${requestScope.sNom}', '${requestScope.pApe}', '${requestScope.sApe}', '${requestScope.valor}')"></td>
                        	</tr>
                        </c:forEach>
                        </table></div></td>
                    <td><div id="marcoCandidatos" class="cajaTablas">
                    	<h1>CANDIDATOS</h1>
                        <table><c:forEach items="${requestScope.posibilidades}" var="posible">
                        	<tr>
                            	<td>${posible.primerNombre} ${posible.segundoNombre} ${posible.primerApellido} ${posible.segundoApellido}</td>
                            	<td><input type="button" id="btnAsignar" value="+"
                            	onclick="manejarAsignacion('asignar',${posible.idPersona},${requestScope.id},'${requestScope.pNom}','${requestScope.sNom}', '${requestScope.pApe}', '${requestScope.sApe}', '${requestScope.valor}', ${requestScope.cupos})"></td>
                        	</tr>
                        </c:forEach>
                        </table></div></td>
                </tr>
            </table>          
        </div>
        <form name="data" action="./ServletAsignaciones" method="post">
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