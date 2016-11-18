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
            <form align="center" id="formCreatePracticante" action="./ServletPersona" method="POST">
                <input type="hidden" name="operacion" />
                <table id="tablaFormulario">
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
						</c:forTokens>
						<!-- <option value="0" selected>(please select:)</option>
						<option value="100M Run">100M Run</option>
						<option value="200M Run">200M Run</option>
						<option value="400M Run">400M Run</option>
						<option value="800M Run">800M Run</option>-->
						</select></td>
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
                </table>
                <br>
                <a href="GestiondeUsuarios.jsp"><input type="button" id="btnCancelar" value="Cancelar" class="botones"></a>
                <input type="button" onclick="checkTodo()" id="btnAceptar" value="Aceptar" class="botones">
            </form>
        </div>
        
    </body>
</html>
