<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personas</title>
        <link rel="stylesheet" href="estilo.css"></link>
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
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

         <%@include file="./menuNavegacionAdmin.jsp" %>
		<div id="gestionContenido">
		<h1 class="cabin">Administración</h1>
		<h2 class="cabin">de personas</h2>
		<div id="gestionContenido">
			<table class="tablaPrincipal">
				<tr>
					<td>
						<div>
							<a href="./ServletPersona?operacion=cargueIncial"><button class="btn-xlarge"><i class="fa fa-plus fa-4x" ></i></button></a>
						</div>
					</td>
					<td>
						<div>
							<a href="busquedaPersonas"><button class="btn-xlarge"><i class="fa fa-search fa-4x" ></i></button></a>
						</div>
					</td>
				</tr>
				<tr>
					<td class="cabin"><a href="./ServletPersona?operacion=cargueIncial">CREAR</a></td>
					<td class="cabin"><a href="busquedaPersonas">BUSCAR</a><br></td>
				</tr>
			</table>
			
			<table class="tablaPrincipal">
				<tr>
					<td>
						<div>
							<a href="InicioConfiguracion.jsp"><button class="btn-xlarge"><i class="fa fa-cogs fa-4x" ></i></button></a>
						</div>
					</td>
				</tr>
				<tr>
					<td class="cabin"><a href="InicioConfiguracion.jsp">CONFIGURACIÓN</a><br></td>
				</tr>
			</table>
			
		</div>
		<br>
		<div>
			<a href="VentanaAdministrador.jsp"><button id="logoutBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>
		<br>
	</div>
        <footer>
            <small>Fundación Universitaria Konrad Lorenz</small>
            <address>www.konradlorenz.edu.co</address>
        </footer> 
    </body>
</html>