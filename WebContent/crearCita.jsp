<%-- 
    Document   : citaPracticantes
    Created on : 26/11/2016
    Author     : Ovidio Zea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estilo.css"></link>
<link rel="stylesheet" href="listas.css"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crear citas</title>
<style type="text/css">
			body,img,p,h1,h2,h3,h4,h5,h6,form,table,td,ul,ol,li,dl,dt,dd,pre,blockquote,fieldset,label{
				margin:0;
				padding:0;
				border:0;
			}
			#ui-datepicker-div, .ui-datepicker{ font-size: 85%; }
</style>
<link rel="stylesheet" media="all" type="text/css" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css" />
		<link rel="stylesheet" media="all" type="text/css" href="js/datePicker/jquery-ui-timepicker-addon.css" />
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="http://code.jquery.com/ui/1.11.0/jquery-ui.min.js"></script>
		<script type="text/javascript" src="js/datePicker/jquery-ui-timepicker-addon.js"></script>
		<script type="text/javascript" src="js/datePicker/jquery-ui-timepicker-addon-i18n.min.js"></script>
</head>
<script type="text/javascript">
	$( function() {
		$('#fecha').datetimepicker({
			timeFormat: "hh:mm tt",
			dateFormat: "dd/mm/yy"
		});
	} );
	
	function enviarFormulario(operacion){
		document.getElementById('operacion').value=operacion;
		document.getElementById("FormDatos").submit();
	}
	
	function radioSelect(idPaciente){
		document.getElementById('idPaciente').value=idPaciente;
	}

</script>
<body>
	<!--MEMU SUPERIOR-->
	<%@include file="/menuNavegacion.jsp"%>
	
	<h1 id="titulo">Cita</h1>
	<form id="FormDatos" name="FormDatos" action="./ServletCita" method="POST">
		<input type="hidden" name="operacion" id="operacion" />
		<input type="hidden" name="idPracticante" id="idPracticante" value="${requestScope.practicante.idPersona}"/>
		<input type="hidden" name="idPaciente" id="idPaciente"/>
		
		<table class="rwd-table-noBorder">
			<tr>
				<th>Practicante: </th>
				<td>${requestScope.practicante.primerNombre} ${requestScope.practicante.segundoNombre} ${requestScope.practicante.primerApellido} ${requestScope.practicante.segundoApellido}</td>
				<th>Tipo Documento: </th>
				<td>${requestScope.practicante.tipoDocumento.idTipoDocumento}</td>
				<th>Numero Documento: </th>
				<td>${requestScope.practicante.numeroDocumento}</td>
			</tr>
			<tr>
				<td><br><br></td>
			</tr>
			<tr>			
				<td colspan="6">
					<table class="rwd-table">
						<tr>
							<th>Seleccionar</th>
							<th>Paciente</th>
							<th>Tipo Documento</th>
							<th>Numero Documento </th>
							<th>Dirección</th>
							<th>Telefono</th>
						</tr>
					<c:forEach items="${requestScope.listaPacientes}" var="paciente">
						<tr>
							<td><input type="radio" id="${paciente.idPersona}" name="grupoPaciente" onclick="radioSelect(this.id);"></td>
							<td>${paciente.primerNombre} ${paciente.segundoNombre} ${paciente.primerApellido} ${paciente.segundoApellido}</td>
							<td>${paciente.tipoDocumento.idTipoDocumento}</td>
							<td>${paciente.numeroDocumento}</td>
							<td>${paciente.direccion}</td>
							<td>${paciente.telefono}</td>
						</tr>
					</c:forEach>
					</table>				
				</td>
			</tr>
			<tr>
				<td></td>
				<td>Salon: </td>
				<td><input type="text" id="salon" name="salon" required></td>
				<td>Fecha y Hora: </td>
				<td><input type="text" id="fecha" name="fecha" required></td>
				<td>
				</td>
			</tr>
		</table>
		<br>
        <input type="button" onclick="enviarFormulario(this.id)" id="crearCita" value="Aceptar" class="botones">		
	</form>
</body>
</html>
