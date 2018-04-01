<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="estilo.css"></link>
		<link rel="stylesheet" href="listas.css"></link>
		<script>
			function iniciarSesion(id){
				document.getElementById("idSesion").value = id;
				document.getElementById("FormDatos").submit();
			}
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Seleccionar Perfil</title>
	</head>
	<body>
		<h1 id="titulo">Seleccione el perfil con el que desea iniciar sesión</h1>
		<form id="FormDatos" name="FormDatos" action="./ServletUsuario" method="POST">
			<input type="hidden" name="operacion" id="operacion" value="iniciarSesion"/>
			<input type="hidden" name="idSesion" id="idSesion"/>
			<table class="rwd-table-noBorder">
				<c:forEach items="${requestScope.perfiles}" var="perfil">
					<tr>
						<td>${perfil.perfil.nombrePerfil}</td>
						<c:if test="${perfil.perfil.idPerfil != 4}">
							<td><input type="button" class="btnLogout" value="Entrar" onClick="iniciarSesion(${perfil.idPersona})"/></td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</form>
		<br>
		<a href="./"><button id="btnVolver" class="btnLogout btnLogout-danger">Volver</button></a> 
	</body>
</html>