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
        
        <form id="formReporte" name="formReporte"><table>
        	<tr>
        		<td>Día</td>
        		<td>Mes</td>
        		<td>Año</td>
        		<td>Hora</td>
        	</tr>
        	<tr>
        		<td><input type="text" id="dia" name="dia"></td>
        		<td><input type="text" id="mes" name="mes"></td>
        		<td><input type="text" id="anio" name="anio"></td>
        		<td><input type="text" id="hora" name="hora"></td>
        		<td></td>
        		<td>Sesion No</td>
        		<td><input type="text" id="nSesion" name="nSesion"></td>
        	</tr>
        	<tr><td></td></tr>
        	<tr>
        		<td>Nombre del Paciente</td>
        		<td><input type="text" id="nombrePaciente" name="nombrePaciente"></td>
        		<td>No H.C</td>
        		<td><input type="text" id="nHistoria" name="nHistoria"></td>
        	</tr>
        	<tr><td></td></tr>
        	<tr>
        		<td>Profesional en Formación Área Clinica</td>
        		<td><input type="text" id="nombrePracticante" name="nombrePracticante"></td>
        	</tr>
        	<tr><td></td></tr>
        	<tr><td>Objetivo de la Sesión</td></tr>
        	<tr><td colspan="6"><textarea name="objetivo" id="objetivo" form="formReporte" maxlength="32000"></textarea></td></tr>
        	<tr><td>Descripción de la Sesión</td></tr>
        	<tr><td colspan="6"><textarea name="descripcion" id="descripcion" form="formReporte" maxlength="32000"></textarea></td></tr>
        	<tr><td>Tareas Asignadas</td></tr>
        	<tr><td colspan="6"><textarea name="tareas" id="tareas" form="formReporte" maxlength="32000"></textarea></td></tr>
        	<tr><td>Actividades para la Próxima Sesión</td></tr>
        	<tr><td colspan="6"><textarea name="proximaSesion" id="proximaSesion" form="formReporte" maxlength="32000"></textarea></td></tr>
        </table></form> 
    </body>  
</html>