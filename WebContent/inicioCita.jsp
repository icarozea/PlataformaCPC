<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Citas - Practicante</title>
        <link rel="stylesheet" href="estilo.css"></link>
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    </head>
    <body>

        <%@include file="./menuNavegacionPracticante.jsp" %> 
        <div id="gestioncitas">
        	<h1 class="cabin">Mis Citas</h1>
        	<div id="gestionContenido">
	        	<table class="tablaPrincipal">
					<tr>
						<td>
							<div>
								<a href="./ServletPersona?operacion=listarPacientes"><button class="btn-xlarge"><i class="fa fa-users fa-4x" ></i></button></a>
							</div>
						</td>
						<td>
							<div>
								<a href="./Calendario?idPersona=${sessionScope.personaSession.idPersona}"><button class="btn-xlarge"><i class="fa fa-calendar fa-4x" ></i></button></a>
							</div>
						</td>
					</tr>
					<tr>
						<td class="cabin"><a href="./ServletPersona?operacion=listarPacientes">MIS PACIENTES</a></td>
						<td class="cabin"><a href="./Calendario?idPersona=${sessionScope.personaSession.idPersona}">MI AGENDA</a><br></td>
					</tr>						        	
	        	</table>
				<table class="tablaPrincipal">
					<tr>
						<td>
							<div>
								<a href="./ServletCita?operacion=crearCita&idPersona=${sessionScope.personaSession.idPersona}"><button class="btn-xlarge"><i class="fa fa-file-text fa-4x" ></i></button></a>
							</div>
						</td>
					</tr>
					<tr>
						<td class="cabin"><a href="./ServletCita?operacion=crearCita&idPersona=${sessionScope.personaSession.idPersona}">CREAR CITA</a><br></td>
					</tr>
				</table>	        	
        	</div>
        </div>
		<div>
			<a href="VentanaPracticante.jsp"><button id="returnBtn" class="btnReturn btnReturn-warning">Regresar</button></a>
		</div>        
    </body>
</html>