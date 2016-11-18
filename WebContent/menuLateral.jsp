<%-- 
    Document   : menuLateral
    Created on : 26/06/2016, 10:42:10 PM
    Author     : HARLIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    	
                <div id="aside">
            <h1>ADMINISTRADORES</h1>
            <ul>
                <li><a href="FormularioAdministrador.jsp">Agregar administrador</a></li>
                <li><a href="./ServletAdministrador?opcion=listAdmin">Ver administradores</a></li>                
            </ul>
            <h1>SUPERVISORES</h1>
            <ul>
                <li><a href="FormularioSupervisor.jsp">Agregar Supervisor</a></li>
                <li><a href="./ServletSupervisores?opcion=listSupervisores">Ver supervisores</a></li>
            </ul>
            <h1>PRACTICANTES</h1>
            <ul>
                <li><a href="FormularioPracticante.jsp">Agregar Practicante</a></li>
                <li><a href="./ServletPracticantes?opcion=listPracticantes">Ver practicantes</a></li>
            </ul>
            <h1>PACIENTES</h1>
            <ul>
                <li><a href="FormularioPaciente.jsp">Agregar Paciente</a></li>
                <li><a href="./ServletPacientes?opcion=listPaciente">Ver pacientes</a></li>
            </ul>
        </div>
    </body>
</html>
