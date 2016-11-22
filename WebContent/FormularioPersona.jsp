<%-- 
    Document   : FormularioPersona
    Created on : 07/11/2016
    Author     : Ovidio Zea
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>
        <script type="text/javascript" src="js/ValidarPassword.js"></script>
        <script type="text/javascript" src="js/mostrarEPS.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Persona</title>
    </head>
    <body>      
        <!--MENU SUPERIOR--> 
         <%@include file="./menuNavegacion.jsp" %>
        <!--MEMU LATERAL--> 
        <%@include file="./menuPersona.jsp" %>
        <div id="formularioIngreso">
            <h3 id="titleForm">Nueva Persona</h3>
            <form align="center" id="formCreatePersona" name="formCreatePersona" value="formCreatePersona" action="./ServletPersona" method="POST">
                <input type="hidden" name="operacion" />
                <table id="tablaFormulario" class="tablaPrincipal">
                    <tr>
                        <td>Primer Nombre: </td>
                        <td><input type="text" id="nombre1" name="nombre1" placeholder="Primer Nombre" required></td>
                    </tr>
                    <tr>
                        <td>Segundo Nombre: </td>
                        <td><input type="text" id="nombre2" name="nombre2" placeholder="Segundo Nombre"></td>
                    </tr>
                    <tr>                        
                        <td>Primer Apellido: </td>
                        <td><input type="text" id="apellido1" name="apellido1" placeholder="Primer Apellido" required></td>
                    </tr>
                    <tr>                        
                        <td>Segundo Apellido: </td>
                        <td><input type="text" id="apellido2" name="apellido2" placeholder="Segundo Apellido"></td>
                    </tr>
                    <tr>
                    	<td>Tipo Documento: </td>
                    	<td><select id ="tipoDocumento" name="tipoDocumento">
                    	<c:forTokens items="Zara,nuha,roshy" delims="," var="name">
						   <option value="${name}">${name}</option>
						</c:forTokens></select></td>
                    </tr>
                    <tr>                        
                        <td>Numero Documento: </td>
                        <td><input type="text" id="numeroDocumento" name="numeroDocumento" placeholder="Numero Documento"></td>
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
                        <td><input type="text" id="correo" name="correo" placeholder="Correo Electrónico" required=""></td>
                    </tr>
                    <tr>
                        <td>Contraseña: </td>
                        <td><input type="password" id="password" name="password"  required=""></td>
                    </tr>
                    <tr>
                        <td>Confirmar Contraseña: </td>
                        <td><input type="password" id="password2" name="password2"  required=""></td>
                    </tr>
                    <tr>
                        <td>Defina un perfil: </td>
                    </tr>
                    <tr>
                    	<td>&nbsp</td>
                        <td>Administrador</td>
                        <td><input type="radio" id="perfil" name="perfil" value="admin" required="" onclick="ocultarEPS()"></td>
                    </tr>
                    <tr>
                    	<td>&nbsp</td>
                        <td>Supervisor</td>
                        <td><input type="radio" id="perfil" name="perfil" value="supervisor" required="" onclick="ocultarEPS()"></td>
                    </tr>
                    <tr>
                        <td>&nbsp</td>
                        <td>Practicante</td>
                        <td><input type="radio" id="perfil" name="perfil" value="practicante" required="" onclick="ocultarEPS()"></td>
                    </tr>
                    <tr>
                    	<td>&nbsp</td>
                        <td>Paciente</td>
                        <td><input type="radio" id="perfil" name="perfil" value="paciente" required="" onclick="mostrarEPS()"></td>
                    </tr>
                    <tr>
                        <td><p id="txtEps" style="display:none;">EPS</p></td>
                        <td><select id ="eps" name="eps" style="display:none;">
                        <c:forTokens items="No aplica,Colsanitas,Compensar,Nueva EPS" delims="," var="name">
						   <option value="${name}">${name}</option>
						</c:forTokens></select></td>
                    </tr>
                </table>
                <br>
                <input type="button" onclick="checkTodo()" id="btnAceptar" value="Aceptar" class="botones">
                <a href="inicioPersonas.jsp"><input type="button" id="btnCancelar" value="Cancelar" class="botones"></a>
            </form>
        </div>
        
    </body>
</html>
