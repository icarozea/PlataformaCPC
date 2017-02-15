<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="estilo.css"></link>
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<title>Inicio</title>
</head>

<body>
	<%@include file="./menuNavegacionAdmin.jsp"%>
	<div id="gestioncitas">
		<h1 class="cabin">INICIO</h1>
		<h2 class="cabin">Bienvenido ${sessionScope.personaSession.primerNombre}</h2>
		<div id="gestionContenido">
			<table class="tablaPrincipal">
				<tr>
					<td>
						<div>
							<a href="inicioPersonas.jsp"><button class="btn-xlarge"><i class="fa fa-users fa-4x" ></i></button></a>
						</div>
					</td>
					<td>
						<div>
							<a href="./ServletCita?operacion=cargueIncial"><button class="btn-xlarge"><i class="fa fa-calendar fa-4x" ></i></button></a>
						</div>
					</td>
				</tr>
				<tr>
					<td class="cabin"><a href="inicioPersonas.jsp">PERSONAS</a></td>
					<td class="cabin"><a href="./ServletCita?operacion=cargueIncial">CITAS</a><br></td>
				</tr>
			</table>
			
			<table class="tablaPrincipal">
				<tr>
					<td>
						<div>
							<a href="#"><button class="btn-xlarge"><i class="fa fa-file-text fa-4x" ></i></button></a>
						</div>
					</td>
				</tr>
				<tr>
					<td class="cabin">REPORTES<br></td>
				</tr>
			</table>
			
		</div>
		<br>
		<div>
			<a href="/PlataformaCPC/Logout"><button id="logoutBtn" class="btnLogout btnLogout-danger">Cerrar sesión</button></a>
		</div>
		<br>
	</div>
	<footer>
		<small>Fundación Universitaria Konrad Lorenz</small>
		<address>www.konradlorenz.edu.co</address>
	</footer>
</body>
</html>