<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Configuracion</title>
        <link rel="stylesheet" href="estilo.css"></link>
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    </head>
    <body>
         <%@include file="./menuNavegacionAdmin.jsp" %>
         
         <div id="configuracion">
         <h1 class="cabin padding100">CONFIGURACI�N</h1>
         	<table class="tablaPrincipal">
         		<tr>
         			<td>
         				<div>
							<a href="FormularioEPS.jsp"><button class="btn-xlarge"><i class="fa fa-users fa-4x" ></i></button></a>
						</div>
					</td>
					<td>
						<div>
							<a href="./ServletCupos?operacion=Cargar"><button class="btn-xlarge"><i class="fa fa-users fa-4x" ></i></button></a>
						</div>
					</td>	
         		</tr>
         			<td class="cabin"><a href="FormularioEPS.jsp">EPS</a></td>
					<td class="cabin"><a href="./ServletCupos?operacion=Cargar">Cupos</a><br></td>
         		<tr>
         			
         		</tr>
         	</table>
         </div>
        <footer>
            <small>Fundaci�n Universitaria Konrad Lorenz</small>
            <address>www.konradlorenz.edu.co</address>
        </footer> 
    </body>
</html>