<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estilo.css"></link>
<script type="text/javascript" src="js/ValidarPassword.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agregar Persona</title>
</head>
		<%
		  if (session.getAttribute("perfil")==null)
		  {
		    String address = "/index.jsp";
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(address);
		    dispatcher.forward(request,response);
		  }
		%>
<body onload="campoOculto()">
	<!--MENU SUPERIOR-->
	<c:choose>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
			<%@include file="./menuNavegacionAdmin.jsp"%>
		</c:when>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
			<%@include file="./menuNavegacionPracticante.jsp"%>
		</c:when>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 2}">
			<%@include file="./menuNavegacionAsesor.jsp" %> 
		</c:when>
	</c:choose>
	
	<!--MEMU LATERAL-->
	<c:choose>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
<%-- 			<%@include file="./menuPersona.jsp"%> --%>
		</c:when>
	</c:choose>
	<div>
		<c:choose>
			<c:when test="${requestScope.pNom != null}">
				<h1 id="titleForm" class="cabin">Editar Persona</h1>
			</c:when>
			<c:otherwise>
				<h1 id="titleForm" class="cabin">Nueva Persona</h1>
			</c:otherwise>
		</c:choose>
		<form id="register-form" action="./ServletPersona" method="POST">
			<input type="hidden" name="operacion" id="operacion" />
			<input type="hidden" name="superior" id="superior" value="${requestScope.sup}" />
			<input type="hidden" name="id" id="id" value="${requestScope.idPersona}" />
	    	<div id="form-content">
	        	<fieldset>
		            <div class="fieldgroup">
		                <label class="cabin">Primer Nombre</label>
		                	<c:choose>
		                		<c:when test="${requestScope.pNom != null}">
		                			<input type="text" id="nombre1" name="nombre1" class="cabin" placeholder="Primer Nombre" value="${requestScope.pNom}" required>
		                		</c:when>
		                		<c:otherwise>
		                			<input type="text" id="nombre1" name="nombre1" placeholder="Primer Nombre" required>
		                		</c:otherwise>
		                	</c:choose>
		            </div>
		
		            <div class="fieldgroup">
		                <label class="cabin">Segundo Nombre</label>
		                	<c:choose>
		                		<c:when test="${requestScope.sNom != null}">
		                			<input type="text" id="nombre2" name="nombre2" placeholder="Segundo Nombre" value="${requestScope.sNom}" required>
		                		</c:when>
		                		<c:otherwise>
		                			<input type="text" id="nombre2" name="nombre2" placeholder="Segundo Nombre" required>
		                		</c:otherwise>
		                	</c:choose>		                
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Primer Apellido</label>
		                <c:choose>
							<c:when test="${requestScope.pApe != null}">
								<input type="text" id="apellido1" name="apellido1" placeholder="Primer Apellido" value="${requestScope.pApe}" required>
					</c:when>
					<c:otherwise>
						<input type="text" id="apellido1" name="apellido1" placeholder="Primer Apellido" required>
					</c:otherwise>
					</c:choose>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Segundo Apellido</label>
						<c:choose>
							<c:when test="${requestScope.sApe != null}">
								<input type="text" id="apellido2" name="apellido2" placeholder="Segundo Apellido" value="${requestScope.sApe}" required>
						</c:when>
						<c:otherwise>
							<input type="text" id="apellido2" name="apellido2" placeholder="Segundo Apellido" required>
					</c:otherwise>
					</c:choose>		                
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Tipo de documento</label>
		                <select id="tipoDocumento" name="tipoDocumento" required>
		                	<option value="">Seleccione</option>
							<c:forEach items="${listaDocumentos}" var="documento">
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
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Número de Documento</label>
		                <c:choose>
							<c:when test="${requestScope.num != null}">
								<input type="text" id="numeroDocumento" name="numeroDocumento" placeholder="Numero Documento" value="${requestScope.num}" required>
						</c:when>
						<c:otherwise>
							<input type="text" id="numeroDocumento" name="numeroDocumento" placeholder="Numero Documento" required>
						</c:otherwise>
						</c:choose>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Dirección</label>
		                <c:choose>
							<c:when test="${requestScope.dir != null}">
								<input type="text" id="direccion" name="direccion"
									placeholder="Dirección Residencia" value="${requestScope.dir}"
									required="">
						</c:when>
						<c:otherwise>
							<input type="text" id="direccion" name="direccion"
								placeholder="Dirección Residencia" required="">
						</c:otherwise>
						</c:choose>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Teléfono</label>
		                <c:choose>
							<c:when test="${requestScope.tel != null}">
								<input type="text" id="telefono" name="telefono"
									placeholder="Teléfono" value="${requestScope.tel}" required="">
						</c:when>
						<c:otherwise>
							<input type="text" id="telefono" name="telefono"
								placeholder="Teléfono" required="">
						</c:otherwise>
						</c:choose>
		            </div>
					<div class="fieldgroup">
		                <label class="cabin">Otro Teléfono</label>
		                <c:choose>
							<c:when test="${requestScope.tel2 != null}">
								<input type="text" id="telefono2" name="telefono2"
									placeholder="Otro Teléfono" value="${requestScope.tel2}">
						</c:when>
						<c:otherwise>
							<input type="text" id="telefono2" name="telefono2"
								placeholder="Otro Teléfono">
							
						</c:otherwise>
						</c:choose>
		            </div>
					<div class="fieldgroup">
		                <label class="cabin">Correo Electrónico</label>
		                <c:choose>
							<c:when test="${requestScope.mail != null}">
								<input type="text" id="correo" name="correo"
									placeholder="Correo Electrónico" value="${requestScope.mail}"
									required>
						</c:when>
						<c:otherwise>
							<input type="text" id="correo" name="correo"
								placeholder="Correo Electrónico" required="">
							
						</c:otherwise>
						</c:choose>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Contraseña</label>
		                <c:choose>
							<c:when test="${requestScope.pass != null}">
								<input type="password" id="password" name="password"
									value="${requestScope.pass}" required="">
							</c:when>
							<c:otherwise>
								<input type="password" id="password" name="password" required>
							</c:otherwise>
							</c:choose>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Confirmar Contraseña</label>
		                <c:choose>
							<c:when test="${requestScope.pass != null}">
								<input type="password" id="password2" name="password2"
									value="${requestScope.pass}" required="">
						</c:when>
						<c:otherwise>
							<input type="password" id="password2" name="password2" required>
						</c:otherwise>
						</c:choose>
		            </div>
		            
					<c:choose>
						<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
							<div class="fieldgroup">
								<label class="cabin">Perfil</label>
								<select id="perfil" name="perfil" onchange="administrarSecciones(this.value)" required>
									<option value="">Seleccione</option>
									<c:forEach items="${listaPerfiles}" var="perfilPersona">
										<c:choose>
											<c:when
												test="${requestScope.perfil == perfilPersona.idPerfil}">
												<option value="${perfilPersona.idPerfil}" selected>${perfilPersona.nombrePerfil}</option>
											</c:when>
											<c:otherwise>
												<option value="${perfilPersona.idPerfil}">${perfilPersona.nombrePerfil}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</c:when>
						<c:otherwise>
							<div class="fieldgroup">
								<% String perfil = "0"; %>
								<label class="cabin">Perfil</label>
								<select id="perfil" name="perfil" disabled>
									<option value="">Seleccione</option>
									<c:forEach items="${listaPerfiles}" var="perfilPersona">
										<c:choose>
											<c:when test="${requestScope.perfil == perfilPersona.idPerfil}">
												<option value="${perfilPersona.idPerfil}" selected>${perfilPersona.nombrePerfil}</option>
											</c:when>
											<c:otherwise>
												<option value="${perfilPersona.idPerfil}">${perfilPersona.nombrePerfil}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
								<input type="hidden" id="perfil" name="perfil" value="${requestScope.perfil}">
							</div>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${requestScope.perfil == 3}">
							<div class="fieldgroup" id="campoCodigo">
		                		<label class="cabin">Código</label>
		                		<input type="text" id="codigo" name="codigo" value="${requestScope.cod}" required>
		            		</div>
						</c:when>
						<c:otherwise>
							<div class="fieldgroup" id="campoCodigo" style="display: none;">
		                		<label class="cabin">Código</label>
		                		<input type="text" id="codigo" name="codigo" value="${requestScope.cod}">
		            		</div>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${requestScope.perfil == 4}">
							<div class="fieldgroup" id="campoEPS">
								<label class="cabin">EPS</label>
								<select id="eps" name="eps" required>
								<option value="">Seleccione</option>
								<c:forEach items="${listaEPS}" var="varEPS">
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
							</div>
						</c:when>
						<c:otherwise>
							<div class="fieldgroup" id="campoEPS" style="display: none;">
								<label class="cabin">EPS</label>
								<select id="eps" name="eps" required>
								<option value="-1">Seleccione</option>
								<c:forEach items="${listaEPS}" var="varEPS">
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
							</div>						
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${requestScope.perfil == 4 || requestScope.perfil == 3}">		
				            <div class="fieldgroup" id="campoJornada">
				                <label class="cabin">Jornada</label>
				                <select id="jornada" name="jornada" required>
					                <c:choose>
										<c:when test="${requestScope.jornada == 'manana'}"><option value="manana" selected>Mañana L-V</option></c:when>
										<c:otherwise><option value="manana">Mañana L-V</option></c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${requestScope.jornada == 'tarde'}"><option value="tarde" selected>Tarde L-V</option></c:when>
										<c:otherwise><option value="tarde">Tarde L-V</option></c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${requestScope.jornada == 'noche'}"><option value="noche" selected>Noche L-S</option></c:when> 
										<c:otherwise><option value="noche">Noche L-S</option></c:otherwise>
									</c:choose>
				                </select>
				            </div>
				            <div class="fieldgroup" id="campoOculto">
		            		</div>
				        </c:when>
				        <c:otherwise>
				            <div class="fieldgroup" id="campoJornada" style="display: none">
				                <label class="cabin">Jornada</label>
				                <select id="jornada" name="jornada">
					                <c:choose>
										<c:when test="${requestScope.jornada == 'manana'}"><option value="manana" selected>Mañana L-V</option></c:when>
										<c:otherwise><option value="manana">Mañana L-V</option></c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${requestScope.jornada == 'tarde'}"><option value="tarde" selected>Tarde L-V</option></c:when>
										<c:otherwise><option value="tarde">Tarde L-V</option></c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${requestScope.jornada == 'noche'}"><option value="noche" selected>Noche L-S</option></c:when> 
										<c:otherwise><option value="noche">Noche L-S</option></c:otherwise>
									</c:choose>
				                </select>
				            </div>
				            <div class="fieldgroup" id="campoOculto" style="display: none">
		            		</div>				        
				        </c:otherwise>
				    </c:choose>
				    <div>
						<a href="./ServletPersona?operacion=detallePersona">Detalle</a>
					</div>
		            <div class="fieldgroup">
		                <input type="button" value="Registrar" onclick="checkTodo(${requestScope.idPersona})" class="submit cabin">
		            </div>
		            <c:choose>
		            	<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
		            		<div class="fieldgroup">
		                		<a href="inicioPersonas.jsp"><input type="button" value="Cancelar" class="submit cabin"></a>
		            		</div>
		            	</c:when>
		            	<c:when test="${sessionScope.personaSession.perfil.idPerfil == 2}">
		            		<div class="fieldgroup">
		                		<a href="VentanaAsesor.jsp"><input type="button" value="Cancelar" class="submit cabin"></a>
		            		</div>
		            	</c:when>		            	
		            	<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
		            		<div class="fieldgroup">
		                		<a href="VentanaPracticante.jsp"><input type="button" value="Cancelar" class="submit cabin"></a>
		            		</div>
		            	</c:when>
		            </c:choose>
	
	        </fieldset>
	    </div>
	</form>
	</div>
</body>
</html>