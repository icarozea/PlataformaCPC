<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="estilo.css"></link>
<link rel="stylesheet" href="estiloAsignaciones.css"></link>
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script type='text/javascript'>
function generarReporteAdmin(operacion){
	idPerfil = 4;
	document.getElementById('reporteForm').action = "./ServletReporte?operacion="+operacion+"&idPerfil="+idPerfil;
	document.getElementById('reporteForm').submit();
}
</script>
<title>Reportes Administrador</title>
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
	<%@include file="./menuNavegacionAdmin.jsp"%>
	<div id="gestioncitas">
		<h1 class="droidSans">Gestión de Reportes</h1>
		<form id="reporteForm" action="" method="POST">
		<div id="generacionReportes">
			<table class="tablaPrincipal">
				<tr>
					<td>
						<div>
							<a href="#" onclick="generarReporteAdmin('pacientes'); return false"><button class="btn-xlarge"><i class="fa fa-file-text fa-4x" ></i></button></a>
						</div>
					</td>
				</tr>
				<tr>
					<td class="cabin"><a href="#" onclick="generarReporteAdmin('pacientes'); return false">PACIENTES</a></td>
				</tr>
			</table>
		</div>
		<input type="hidden" id="fechaReporte" name="fechaReporte" value="01/01/2000">
		</form>
	</div>
			<br>
		<div>
			<a href="VentanaAdministrador.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
</body>
</html>