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
        <title>Nuevo Administrador</title>
    </head>
    <body>  

         <!--MEMU SUPERIOR--> 
         <%@include file="./menuNavegacion.jsp" %>
        <!--MEMU LATERAL--> 
        <%@include file="./menuLateral.jsp" %>
        
        <div id="formularioIngreso">
            <form id="formCreateAdmin"  name="formCreateAdmin" action="./ServletAdministrador?opcion=addAdmin" method="POST">                
                <table id="tablaFormulario">
                    <h3>Nuevo Administrador</h3>
                    <tr>
                        <td>Código: </td>
                        <td><input type="text" name="codigo" id="codigo" placeholder="Código" required=""></td>
                    </tr>
                    <tr>
                        <td>Primer Nombre: </td>
                        <td><input type="text" name="nombre1" id="nombre1" placeholder="Primer Nombre" required=""></td>
                    </tr>
                    <tr>
                        <td>Segundo Nombre: </td>
                        <td><input type="text" name="nombre2" id="nombre2"  placeholder="Segundo Nombre" required=""></td>
                    </tr>
                    <tr>
                        <td>Primer Apellido: </td>
                        <td><input type="text" name="apellido1" id="apellido1" placeholder="Primer apellido" required=""></td>
                    </tr>
                    <tr>
                        <td>Segundo Apellido: </td>
                        <td><input type="text" name="apellido2" id="apellido2" placeholder="Segundo apellido" required=""></td>
                    </tr>   
                    <tr>
                        <td>Dirección: </td>
                        <td><input type="text" name="direccion" id="direccion" placeholder="Dirección residencia" required=""></td>
                    </tr>
                    <tr>
                        <td>Teléfono: </td>
                        <td><input type="text" name="telefono" id="telefono" placeholder="Teléfono" required=""></td>
                    </tr>
                    <tr>
                        <td>Correo Electrónico: </td>
                        <td><input type="email" id="correo" name="correo" required="" placeholder="e-mail" onkeyup="checkMail(); return false;">
                            <br> <span id="confirmMessage" class="confirmMessage"></span></td>
                    </tr>

                    <td>Permisos</td>
                    <td>                      
                        <br><input type="checkbox" id="permisoSupervisores" name="admin_supervisores" value="1" class="check">
                        <label>Administrar Supervisores</label>
                        <br><input type="checkbox" id="permisoPracticantes" name="admin_practicante" value="1" class="check">
                        <label>Administrar Practicantes</label>
                        <br><input type="checkbox" id="permisoPaciente" name="admin_paciente" value="1" class="check">
                        <label>Administrar Pacientes</label>
                        <br><input type="checkbox" id="permisoCitas" name="admin_citas" value="1" class="check">
                        <label>Administrar Citas</label>                        
                        <br><input type="checkbox" id="permisoEstadodeCaso" name="casos_estado" value="1" class="check">
                        <label>Permitir Creación de estados de Caso</label>
                    </td>
                    </tr>
                    <tr>
                        <td>Contraseña: </td>
                        <td><input type="password" id="password" name="pass1" placeholder="Contraseña" required=""></td>
                    </tr>
                    <tr>
                        <td>Confirmar Contraseña: </td>
                        <td><input type="password" id="password2" name="pass2" placeholder="Cofirmar contraseña"required="" onkeyup="checkPass(); return false;"><br>                        
                            <span id="confirmMessage" class="confirmMessage"></span></td>
                    </tr>
                    <input type="hidden" name="formName" id="formName" value="formCreateAdmin">
                </table>
                <center><a href="./ServletAdministrador?opcion=listAdmin"><input type="button" id="btnCancelar" value="Cancelar" class="botones"></a>
                    <input type="button" onclick="checkTodo()" id="btnAceptar" value="Aceptar" class="botones"></a></center>
            </form>
        </div>
    </body>
</html>
