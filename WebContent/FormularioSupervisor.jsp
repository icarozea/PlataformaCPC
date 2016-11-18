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
        <title>Nuevo Supervisor</title>
    </head>
    <body> 
        <!--MEMU SUPERIOR--> 
         <%@include file="./menuNavegacion.jsp" %>
        <!--MEMU LATERAL--> 
        <%@include file="./menuLateral.jsp" %>
        <div id="formularioIngreso">
            <form id="formCreateSupervisor" action="./ServletSupervisores?opcion=addSupervisor" method="POST">
                <table id="tablaFormulario">
                    <h3>Nuevo Supervisor</h3>
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
                        <td><input type="text" id="correo" name="correo" placeholder="e-mail" required="" onkeyup="checkMail(); return false;">
                            <br> <span id="confirmMessage" class="confirmMessage"></span></td>
                    </tr>
                    <tr>
                        <td>Contraseña: </td>
                        <td><input type="password" name="pass1" id="password" placeholder="Contraseña" required=""></td>                        
                    </tr>
                    <tr>
                        <td>Confirmar Contraseña: </td>
                        <td><input type="password" name="pass2" id="password2" required="" placeholder="Confirmar contraseña"onkeyup="checkPass(); return false;"><br>
                            <span id="confirmMessage" class="confirmMessage"></span></td>
                    </tr>
                    <input type="hidden" name="formName" id="formName" value="formCreateSupervisor">
                </table>
                <center><a href="GestiondeUsuarios.jsp"><input type="button" id="btnCancelar" value="Cancelar" class="botones"></a>
                    <input type="button" onclick="checkTodo()" id="btnAceptar" value="Aceptar" class="botones"></a></center>
            </form>
        </div>
        <footer>
            <small>Fundación Universitaria Konrad Lorenz</small>
            <address>www.konradlorenz.edu.co</address>
        </footer> 
    </body>
</html>
