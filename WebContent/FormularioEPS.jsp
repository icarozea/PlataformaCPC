<%-- 
    Document   : FormularioEPS
    Created on : 07/11/2016
    Author     : Ovidio Zea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <script type="text/javascript" src="js/ValidarPassword.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar EPS</title>
    </head>
    <body>      
        <!--MEMU SUPERIOR--> 
         <%@include file="/menuNavegacionAdmin.jsp" %>
        <!--MEMU LATERAL--> 
        <%@include file="/menuPersona.jsp" %>
        <div id="formularioIngreso" >
            <h3 id="titleForm">NUEVA EPS</h3>
            <form align="center" id="FormDatos" name="FormDatos" action="./ServletEPS" method="POST">
                <input type="hidden" name="operacion" />
                <table id="tablaFormulario">
                    <tr>
                        <td>Nombre: </td>
                        <td><input type="text" id="nombre" placeholder="Nombre" name="nombre" required></td>
                    </tr>
                </table>
                <br>
                <input type="submit" name="btnAgregarEPS" id="btnAgregarEPS" value="Aceptar" class="botones" onclick="{document.FormDatos.operacion.value=this.id;document.FormDatos.submit();}"/>
            </form>
        </div>
        
    </body>
</html>