<%-- 
    Document   : FormularioPracticante
    Created on : 23/05/2016, 10:18:27 PM
    Author     : HARLIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <script type="text/javascript" src="js/ValidarPassword.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Practicanter</title>
    </head>
    <body>      
        <!--MEMU SUPERIOR--> 
         <%@include file="./menuNavegacion.jsp" %>
        <!--MEMU LATERAL--> 
        <%@include file="./menuLateral.jsp" %>
        <div id="formularioIngreso">
            <h3 id="titleForm">NUEVO PRACTICANTE</h3>
            <form id="formCreatePracticante" action="./ServletPracticantes?opcion=addPracticante" method="POST">
                <table id="tablaFormulario">
                    <tr>
                        <td>Código: </td>
                        <td><input type="text" id="codigo" placeholder="Código" name="codigo" required=""></td>
                    </tr>
                    <tr>
                        <td>Primer Nombre: </td>
                        <td><input type="text" id="nombre1" name="nombre1" placeholder="Primer Nombre" required=""></td>
                    </tr>
                    <tr>
                        <td>Segundo Nombre: </td>
                        <td><input type="text" id="nombre2" name="nombre2" placeholder="Segundo Nombre" required=""></td>
                    </tr>
                    <tr>                        
                        <td>Primer Apellido: </td>
                        <td><input type="text" id="apellido1" name="apellido1" placeholder="Primer Apellido" required=""></td>
                    </tr>
                    <tr>                        
                        <td>Segundo Apellido: </td>
                        <td><input type="text" id="apellido2" name="apellido2" placeholder="Segundo Apellido" required=""></td>
                    </tr>
                    <tr>
                        <td>Dirección: </td>
                        <td><input type="text" id="direccion" name="direccion" placeholder="Dirección Residencia" required=""></td>
                    </tr>
                    <tr>
                        <td>Teléfono: </td>
                        <td><input type="text" id="telefono" name="telefono" placeholder="Teléfono" required=""></td>
                    </tr>
                    <tr>
                        <td>Correo Electrónico: </td>
                        <td><input type="text" id="correo" name="correo" required="" placeholder="e-mail" onkeyup="checkMail(); return false;">
                            <br> <span id="confirmMessagemMail" class="confirmMessage"></span></td>
                    </tr>
                    <tr>
                        <td>Contraseña: </td>
                        <td><input type="password" id="password" placeholder="Contraseña" name="password" required=""></td>
                    </tr>
                    <tr>
                        <td>Confirmar Contraseña: </td>
                        <td><input type="password" id="password2" name="password2" placeholder="Confirmar Contrañea" required="" onkeyup="checkPass(); return false;"><br>                       
                            <span id="confirmMessage" class="confirmMessage"></span></td>
                    </tr>
                    <input type="hidden" name="formName" id="formName" value="formCreatePracticante">
                </table><br>
                <center><a href="GestiondeUsuarios.jsp"><input type="button" id="btnCancelar" value="Cancelar" class="botones"></a>
                    <input type="button" onclick="checkTodo()" id="btnAceptar" value="Aceptar" class="botones"></a></center>
            </form>
        </div>
        
    </body>
</html>
