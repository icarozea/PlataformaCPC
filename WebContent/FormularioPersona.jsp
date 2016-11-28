<%-- 
    Document   : FormularioPersona
    Created on : 07/11/2016
    Author     : Ovidio Zea
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="estilo.css"></link>

        <script type="text/javascript" src="js/ValidarPassword.js"></script>
        <script type="text/javascript" src="js/mostrarEPS.js"></script>
        <script type="text/javascript" src="js/util.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Persona</title>
    </head>

    <body onload="cargueInicial()">      
        <!--MENU SUPERIOR--> 
         <%@include file="./menuNavegacion.jsp" %>
        <!--MEMU LATERAL--> 
        <%@include file="./menuPersona.jsp" %>
        <div id="formularioIngreso">
        	<c:choose>
            	<c:when test="${requestScope.pNom != null}">
            		<h3 id="titleForm">Editar Persona</h3>
            	</c:when>
                <c:otherwise>
                	<h3 id="titleForm">Nueva Persona</h3>
                </c:otherwise>
            </c:choose>
            <form align="center" id="formCreatePersona" name="formCreatePersona" value="formCreatePersona" action="./ServletPersona" method="POST">
                <input type="hidden" name="operacion"  id="operacion"/>
				<input type="hidden" name="superior"  id="superior" value="${requestScope.sup}"/>
				<input type="hidden" name="id"  id="id" value="${requestScope.idPersona}"/>
                <table id="tablaFormulario" class="tablaPrincipal">
                    <tr>
                        <td>Primer Nombre: </td>
                        <td><c:choose>
                        <c:when test="${requestScope.pNom != null}">
                        	<input type="text" id="nombre1" name="nombre1" placeholder="Primer Nombre" value="${requestScope.pNom}" required></td>
                        </c:when>
                        <c:otherwise>
                        	<input type="text" id="nombre1" name="nombre1" placeholder="Primer Nombre" required></td>
                        </c:otherwise>
                    	</c:choose>
                    </tr>
                    <tr>
                        <td>Segundo Nombre: </td>
                        <td><c:choose>
                        <c:when test="${requestScope.sNom != null}">
                        	<input type="text" id="nombre2" name="nombre2" placeholder="Segundo Nombre" value="${requestScope.sNom}"></td>
                        </c:when>
                        <c:otherwise>
                        	<input type="text" id="nombre2" name="nombre2" placeholder="Segundo Nombre"></td>
                        </c:otherwise>
                    	</c:choose>
                    </tr>
                    <tr>                        
                        <td>Primer Apellido: </td>
                        <td><c:choose>
                        <c:when test="${requestScope.pApe != null}">
                        	<input type="text" id="apellido1" name="apellido1" placeholder="Primer Apellido" value="${requestScope.pApe}" required></td>
                        </c:when>
                        <c:otherwise>
                        	<input type="text" id="apellido1" name="apellido1" placeholder="Primer Apellido" required></td>
                        </c:otherwise>
                    	</c:choose>
                    </tr>
                    <tr>                        
                        <td>Segundo Apellido: </td>
                        <td><c:choose>
                        <c:when test="${requestScope.sApe != null}">
                        	<input type="text" id="apellido2" name="apellido2" placeholder="Segundo Apellido" value="${requestScope.sApe}"></td>
                        </c:when>
                        <c:otherwise>
                        	<input type="text" id="apellido2" name="apellido2" placeholder="Segundo Apellido"></td>
                        </c:otherwise>
                    	</c:choose>
                    </tr>
                    <tr>
                    	<td>Tipo Documento: </td>
                    	<td>
                    		<select id ="tipoDocumento" name="tipoDocumento">
                    				<option value="-1"> Seleccione </option>                 		
                    			<c:forEach items="${listaDocumentos}"  var="documento">
                    				<c:choose>
                    				<c:when test="${requestScope.doc == documento.sigla}">
						   				<option value="${documento.sigla}" selected>${documento.nombreDocumento}</option>
						   			</c:when>
						   			<c:otherwise>
						   				<option value="${documento.sigla}">${documento.nombreDocumento}</option>
						   			</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
                    </tr>
                    <tr>                        
                        <td>Número Documento: </td>
                        <td><c:choose>
                        <c:when test="${requestScope.num != null}">
                        	<input type="text" id="numeroDocumento" name="numeroDocumento" placeholder="Numero Documento" value="${requestScope.num}"></td>
                         </c:when>
                        <c:otherwise>
                        	<input type="text" id="numeroDocumento" name="numeroDocumento" placeholder="Numero Documento"></td>
                        </c:otherwise>
                    	</c:choose>
                    </tr>
                    <tr>
                        <td>Dirección: </td>
                        <td><c:choose>
                        <c:when test="${requestScope.dir != null}">
                        	<input type="text" id="direccion" name="direccion" placeholder="Dirección Residencia" value="${requestScope.dir}" required=""></td>
                         </c:when>
                        <c:otherwise>
                        	<input type="text" id="direccion" name="direccion" placeholder="Dirección Residencia" required=""></td>
                        </c:otherwise>
                    	</c:choose>
                    </tr>
                    <tr>
                        <td>Teléfono: </td>
                        <td><c:choose>
                        <c:when test="${requestScope.tel != null}">
                        	<input type="text" id="telefono" name="telefono" placeholder="Teléfono" value="${requestScope.tel}" required=""></td>
                        </c:when>
                        <c:otherwise>
                        	<input type="text" id="telefono" name="telefono" placeholder="Teléfono" required=""></td>
                        </c:otherwise>
                    	</c:choose>
                    </tr>
                    <tr>
                        <td>Correo Electrónico: </td>
                        <td><c:choose>
                        <c:when test="${requestScope.mail != null}">
                        	<input type="text" id="correo" name="correo" placeholder="Correo Electrónico" value="${requestScope.mail}" required=""></td>
                        </c:when>
                        <c:otherwise>
                        	<input type="text" id="correo" name="correo" placeholder="Correo Electrónico" required=""></td>
                        </c:otherwise>
                    	</c:choose>
                    </tr>
                    <tr>
                        <td>Contraseña: </td>
                        <td><c:choose>
                        <c:when test="${requestScope.pass != null}">
                        	<input type="password" id="password" name="password" value="${requestScope.pass}" required=""></td>
                        </c:when>
                        <c:otherwise>
                        	<input type="password" id="password" name="password" required=""></td>
                        </c:otherwise>
                    	</c:choose>
                    </tr>
                    <tr>
                        <td>Confirmar Contraseña: </td>
                        <td><c:choose>
                        <c:when test="${requestScope.pass != null}">
                        	<input type="password" id="password2" name="password2" value="${requestScope.pass}" required=""></td>
                        </c:when>
                        <c:otherwise>
                        	<input type="password" id="password2" name="password2" required=""></td>
                        </c:otherwise>
                    	</c:choose>
                    </tr>
					<tr>
                    	<td>Perfil: </td>
                    	<td>
                    		<select id ="perfil" name="perfil" onchange="mostrarEPS(this.value)">
                    			<option value="-1"> Seleccione </option>                 		
                    			<c:forEach items="${listaPerfiles}"  var="perfilPersona">
                    				<c:choose>
                    				<c:when test="${requestScope.perfil == perfilPersona.nombrePerfil}">
						   				<option value="${perfilPersona.idPerfil}" selected>${perfilPersona.nombrePerfil}</option>
						   			</c:when>
						   			<c:otherwise>
						   				<option value="${perfilPersona.idPerfil}">${perfilPersona.nombrePerfil}</option>
						   			</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
                    </tr>
	                    <tr>
	                    	<c:choose>
                    		<c:when test="${requestScope.perfil == 'Paciente'}">
	                    		<td id="seccionEPS">EPS: </td>
	                    	</c:when>
						   	<c:otherwise>
						   		<td id="seccionEPS" style="display:none;">EPS: </td>
						   	</c:otherwise>
							</c:choose>
	                    	<td>
	                    		<c:choose>
                    			<c:when test="${requestScope.perfil == 'Paciente'}">
	                    			<select id ="eps" name="eps">
	                    		</c:when>
						   		<c:otherwise>
						   			<select id ="eps" name="eps" style="display:none;">
						   		</c:otherwise>
								</c:choose>
	                    			<option value="-1"> Seleccione </option>                 		
	                    			<c:forEach items="${listaEPS}"  var="varEPS">
	                    				<c:choose>
                    					<c:when test="${requestScope.eps == varEPS.nombreEPS}">
							   				<option value="${varEPS.idEPS}" selected>${varEPS.nombreEPS}</option>
							   			</c:when>
						   				<c:otherwise>
						   					<option value="${varEPS.idEPS}">${varEPS.nombreEPS}</option>
						   				</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
	                    </tr>
                </table>
                <br>
                <input type="button" onclick="checkTodo(${requestScope.idPersona})" id="btnAceptar" value="Aceptar" class="botones">
                <a href="inicioPersonas.jsp"><input type="button" id="btnCancelar" value="Cancelar" class="botones"></a>
            </form>
        </div>
        
    </body>
</html>
