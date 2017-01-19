<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estiloAsignaciones.css"></link>
        <script type="text/javascript" src="js/manejarAsignacion.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte Sesión</title>
    </head>
    <body>
    	<!--MEMU SUPERIOR--> 
    	<%@include file="./menuNavegacionPracticante.jsp" %>
    	<h1>Reporte de Sesión Individual</h1>
        <table>
        	<tr>
        		<td>Día</td>
        		<td>Mes</td>
        		<td>Año</td>
        		<td>Hora</td>
        	</tr>
        	<tr>
        		<td></td>
        		<td></td>
        		<td></td>
        		<td></td>
        	</tr>
        </table> 
    	<form>
    		<table>
    		</table>
    	</form>
    </body>  
</html>