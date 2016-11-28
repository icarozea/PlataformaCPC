<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estilo.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<nav id="menu">
		<%                 
                if (request.getAttribute("usuario") != null) {                    
            %>
		<div class="NombreUsuario">Hola</div>
		<%}%>
		<ul>
			<a href="VentanaAdministrador.jsp"><li>Inicio</li></a>
			<a href="inicioPersonas.jsp"><li>Personas</li></a>
			<a href="inicio_calendario.jsp"><li>Citas</li></a>
			<a href="GestiondeUsuarios.jsp"><li>Reportes</li></a>
		</ul>
	</nav>
	<br>
</body>
</html>