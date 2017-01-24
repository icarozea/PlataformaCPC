<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cupos Practicantes</title>
    </head>
    <body>      
        <!--MEMU SUPERIOR--> 
         <%@include file="/menuNavegacionAdmin.jsp" %>
        <!--MEMU LATERAL--> 
        <%@include file="/menuPersona.jsp" %>
        <div id="formularioCupos" class="cajaformulario" style="margin-top: 15%;">
            <h3 id="titleForm">Cupos</h3>
            <form align="center" id="FormDatos" name="FormDatos" action="./ServletCupos" method="POST">
                <input type="hidden" id="operacion" name="operacion" value="Actualizar"/>
                <table id="tablaFormulario">
                    <tr>
                        <td>Cupos por Practicante: </td>
                        <td><input type="text" id="cupos" placeholder="Cupos" value="${requestScope.cupos}" name="cupos" required></td>
                    </tr>
                    <tr>
                        <td>${requestScope.mensaje}</td>
                    </tr>
                </table>
                <br>
                <input type="submit" name="btnCupos" id="btnCupos" value="Aceptar" class="botones" onclick="{document.FormDatos.operacion.value=Actualizar;document.FormDatos.submit();}"/>
            </form>
        </div>
        
    </body>
</html>