<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estilo.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
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
	<nav id="menu">
		<ul>
			<a href="VentanaAdministrador.jsp"><li>Inicio</li></a>
			<a href="inicioPersonas.jsp"><li>Personas</li></a>
			<a href="./ServletCita?operacion=cargueIncial"><li>Citas</li></a>
			<a href=""><li>Reportes</li></a>
		</ul>
	</nav>
	<br>
</body>
</html>