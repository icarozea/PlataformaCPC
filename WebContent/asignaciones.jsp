<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estiloAsignaciones.css"></link>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignar</title>
    </head>
    <body>
    	<!--MEMU SUPERIOR--> 
         <%@include file="./menuNavegacion.jsp" %>
         <!--MEMU LATERAL--> 
        <%@include file="./menuLateral.jsp" %>
        <div id="marcoGeneral" class="cajaVentanaAsignacion">
            <p>Pedro Perez----------Cupos: 5</p>
            <table>
                <tr>
                    <td><div id="marcoAsignados" class="cajaTablas">
                        <table><tr>
                            <td>Juan Jose Jimenez</td>
                            <td><input type="button" id="btnAsignar" value="-"></td>
                        </tr>
                        <tr>
                            <td>Maria Mercedes Moreno</td>
                            <td><input type="button" id="btnAsignar" value="-"></td>
                        </tr>
                        </table></div></td>
                    <td><div id="marcoCandidatos" class="cajaTablas">
                        <table><tr>
                            <td>Opcion 1</td>
                            <td><input type="button" id="btnAsignar" value="+"></td>
                        </tr>
                        <tr>
                            <td>Opcion 2</td>
                            <td><input type="button" id="btnAsignar" value="+"></td>
                        </tr>
                        <tr>
                            <td>Opcion 3</td>
                            <td><input type="button" id="btnAsignar" value="+"></td>
                        </tr>
                        <tr>
                            <td>Opcion 4</td>
                            <td><input type="button" id="btnAsignar" value="+"></td>
                        </tr>
                        <tr>
                            <td>Opcion 5</td>
                            <td><input type="button" id="btnAsignar" value="+"></td>
                        </tr>
                        <tr>
                            <td>Opcion 6</td>
                            <td><input type="button" id="btnAsignar" value="+"></td>
                        </tr>
                        <tr>
                            <td>Opcion 7</td>
                            <td><input type="button" id="btnAsignar" value="+"></td>
                        </tr>
                        <tr>
                            <td>Opcion 8</td>
                            <td><input type="button" id="btnAsignar" value="+"></td>
                        </tr>
                        <tr>
                            <td>Opcion 9</td>
                            <td><input type="button" id="btnAsignar" value="+"></td>
                        </tr>
                        <tr>
                            <td>Opcion 10</td>
                            <td><input type="button" id="btnAsignar" value="+"></td>
                        </tr>
                        </table></div></td>
                </tr>
            </table>          
        </div>  
</html>