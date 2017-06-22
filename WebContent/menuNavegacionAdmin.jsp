<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estilo.css"/>
<link rel="stylesheet" href="estiloMenu.css" />
<link href="/css/bootstrap.css" rel="stylesheet" type="text/css"/>
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
	<div class="top-header">	
	 <div class="container" style="margin-bottom: 8%;">
	 	
		 <div class="top-menu">
		 	<span class="menu"> </span>
			 <ul>
				 <li><a href="VentanaAdministrador.jsp">INICIO</a></li>
				 <li><a href="inicioPersonas.jsp">PERSONAS</a></li>
				 <li><a href="./ServletCita?operacion=cargueIncial">CITAS</a></li>
				 <li><a href="./ReportesAdministrador?operacion=visualizarReportes">REPORTES</a></li>
				 <div class="clearfix"></div>
			 </ul>
			<!--  <script>
					$("span.menu").click(function(){
						$(".top-menu ul").slideToggle(200);
					});
			</script> -->
		</div>
		<div class="logo">
	 		<img id="logoK" alt="Logo Konrad" class="logoKonrad" src="resources/LOGO.jpg"/>
	 	</div>
	 </div>	
	</div>
</body>
</html>