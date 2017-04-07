<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <div id="aside">
            <h1>EPS</h1>
            <ul>
                <li><a href="FormularioEPS.jsp">Agregar EPS</a></li>
                <li><a href="./ServletEPS?operacion=verEPS">Ver EPS</a></li>                
            </ul>
            <h1>PERSONA</h1>
            <ul>
                <li><a href="./ServletPersona?operacion=cargueIncial">Agregar Persona</a></li>
                <li><a href="./ServletPersona?operacion=listarPersonas">Ver Personas</a></li>
            </ul>
        </div>
     
    </body>
</html>