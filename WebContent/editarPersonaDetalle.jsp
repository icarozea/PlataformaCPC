<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="estilo.css"></link>
<script type="text/javascript" src="js/ValidarPassword.js"></script>
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
<body>
	<!--MENU SUPERIOR-->
	<c:choose>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
			<%@include file="./menuNavegacionAdmin.jsp"%>
		</c:when>
		<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
			<%@include file="./menuNavegacionPracticante.jsp"%>
		</c:when>
	</c:choose>
	
	<div>
		<form id="register-form" action="./ServletPersonaDetalle" method="POST">
			<input type="hidden" name="operacion" id="operacion" />
			<input type="hidden" name="idPersona" id="idPersona" value="${requestScope.personaDetalle.personaId}"/>
	    	<div id="form-content"> 
	        	<fieldset>
		            <div class="fieldgroup">
		                <label class="cabin">Sexo</label>
		                <select id="sexo" name="sexo">
					    	<c:choose>
								<c:when test="${requestScope.personaDetalle.sexo == 'M'}"><option value="M" selected>Masculino</option></c:when>
								<c:otherwise><option value="M">Masculino</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${requestScope.personaDetalle.sexo == 'F'}"><option value="F" selected>Femenino</option></c:when>
								<c:otherwise><option value="F">Femenino</option></c:otherwise>
							</c:choose>
				        </select>
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Estado Civil</label>
						<select id="estado_civil" name="estado_civil">
							<c:forTokens items="Soltero,Casado,Divorciado,Viudo" delims="," var="name">
				            	<c:choose>
				                	<c:when test="${requestScope.personaDetalle.estadoCivil == name}">
				                		<option value="${name}" selected>${name}(a)</option>
				                	</c:when>
				                	<c:otherwise>
				                		<option value="${name}">${name}(a)</option>
				                	</c:otherwise>
								</c:choose>
				            </c:forTokens>
				            <c:choose>
				            	<c:when test="${requestScope.personaDetalle.estadoCivil == 'Union Libre'}">
									<option value="Union Libre" selected>Union Libre</option>
								</c:when>
				                <c:otherwise>
				                	<option value="Union Libre">Union Libre</option>
				                </c:otherwise>
				            </c:choose>
						</select>
		            </div>	
		            <div class="fieldgroup">
		                <label class="cabin">Edad</label>
		                <input type="text" id="edad" name="edad" value="${requestScope.personaDetalle.edad}" placeholder="Edad">            
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Fecha de Nacimiento</label>
						<input id="fecha_nacimiento" name="fecha_nacimiento" type="text"  value="${requestScope.personaDetalle.fechaNacimiento}" placeholder="Fecha de Nacimiento">
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Lugar de Nacimiento</label>
		            	<input id="lugar_nacimiento" name="lugar_nacimiento" type="text"  value="${requestScope.personaDetalle.lugarNacimiento}" placeholder="Lugar de Nacimiento">
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Nivel de Escolaridad</label>
						<select id="escolaridad" name="escolaridad">
							<c:forTokens items="Ninguna,Primaria,Bachillerato,Tecnica,Profesional,Especializacion,Maestria,Doctorado,Post-Doctorado" delims="," var="name">
				            	<c:choose>
				                	<c:when test="${requestScope.personaDetalle.escolaridad == name}">
				                		<option value="${name}" selected>${name}</option>
				                	</c:when>
				                	<c:otherwise>
				                		<option value="${name}">${name}</option>
				                	</c:otherwise>
								</c:choose>
				            </c:forTokens>
						</select>
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Ocupación</label>
		            	<input id="ocupacion" name="ocupacion" type="text"  value="${requestScope.personaDetalle.ocupacion}" placeholder="Ocupación">
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Localidad</label>
		            	<input id="localidad" name="localidad" type="text" value="${requestScope.personaDetalle.localidad}" placeholder="Localidad">
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Barrio</label>
		            	<input id="barrio" name="barrio" type="text" value="${requestScope.personaDetalle.barrio}" placeholder="Barrio">
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Estrato</label>
						<select id="estrato" name="estrato">
							<c:forTokens items="1,2,3,4,5,6" delims="," var="name">
				            	<c:choose>
				                	<c:when test="${requestScope.personaDetalle.estrato == name}">
				                		<option value="${name}" selected>${name}</option>
				                	</c:when>
				                	<c:otherwise>
				                		<option value="${name}">${name}</option>
				                	</c:otherwise>
								</c:choose>
				            </c:forTokens>
						</select>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">En Caso de Emergencia Llamar A</label>
		                <input id="emergencia" name="emergencia" type="text" value="${requestScope.personaDetalle.personaEmergencia}" placeholder="Emergencia">
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">A los Teléfonos</label>
		                <input id="telefonos_emergencia" name="telefonos_emergencia" type="text" value="${requestScope.personaDetalle.telefonoEmergencia}" placeholder="Telefonos Emergencia">
		            </div>
		             <div class="fieldgroup">
		                <label class="cabin">Parentesco</label>
		                <input id="parentesco" name="parentesco" type="text" value="${requestScope.personaDetalle.parentescoEmergencia}" placeholder="Parentesco">
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Formato de Solicitud del Servicio</label>
						<select id="solicitud" name="solicitud" >
							<c:forTokens items="Voluntaria,Remision" delims="," var="name">
				            	<c:choose>
				                	<c:when test="${requestScope.personaDetalle.formatoSolicitud == name}">
				                		<option value="${name}" selected>${name}</option>
				                	</c:when>
				                	<c:otherwise>
				                		<option value="${name}">${name}</option>
				                	</c:otherwise>
								</c:choose>
				            </c:forTokens>
						</select>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Institución que Remite</strong></label>
		                <input id="institucion" name="institucion" type="text" value="${requestScope.personaDetalle.institucionRemision}" placeholder="Institución">
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Acudiente</label>
						<input type="text" id="acudiente" name="acudiente" value="${requestScope.personaDetalle.acudiente}" placeholder="Acudiente"  />
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Parentesco del Acudiente</label>
						<input id="parentesco_acudiente" name="parentesco_acudiente" type="text" value="${requestScope.personaDetalle.parentescoAcudiente}" placeholder="Parentesco Acudiente">
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Telefonos del Acudiente</label>
						<input id="telefonos_acudiente" name="telefonos_acudiente" type="text" value="${requestScope.personaDetalle.telefonoAcudiente}" placeholder="Telefonos Acudiente">
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Personas con las que vive actualmente</label>
						<textarea id="parientes" name="parientes" rows="10" cols="46" style="resize: none;">${requestScope.personaDetalle.personasReside}</textarea>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Problematica</label>
						<textarea id="problematica" name="problematica" rows="10" cols="46" style=" resize: none;">${requestScope.personaDetalle.problematica}</textarea>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Proceso</label>
						<input type="text" id="proceso" name="proceso" value="${requestScope.personaDetalle.proceso}" placeholder="Proceso" />                
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Pertenece a la universidad</label>
						<select id="perteneceU" name="perteneceU">
							<c:choose>
								<c:when test="${requestScope.personaDetalle.perteneceU == 'si'}"><option value="si" selected>Si</option></c:when>
								<c:otherwise><option value="si">Si</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${requestScope.personaDetalle.perteneceU == 'no'}"><option value="no" selected>No</option></c:when>
								<c:otherwise><option value="no">No</option></c:otherwise>
							</c:choose>
						</select>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Facultad</label>
						<input type="text" id="facultad" name="facultad" value="${requestScope.personaDetalle.facultad}" placeholder="Facultad" />
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Semestre</label>
						<input type="text" id="semestre" name="semestre" value="${requestScope.personaDetalle.semestre}" placeholder="Semestre" />
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Observaciones</label>
						<textarea id="observaciones" name="observaciones" rows="10" cols="46" style=" resize: none;">${requestScope.personaDetalle.observación}</textarea>
		            </div>
		            <div class="fieldgroup">
		            </div>
		            <div class="fieldgroup">
		            <c:choose>
		            	<c:when test="${requestScope.personaDetalle.personaId != null}">
		                	<input type="button" value="Registrar" onclick="modificarDetalle()" class="submit cabin"/>
		                </c:when>
		                <c:otherwise>
		                	<input type="button" value="Registrar" onclick="guardarDetalle()" class="submit cabin"/>
		                </c:otherwise>
		            </c:choose>
		            </div>
		            <c:choose>
		            	<c:when test="${sessionScope.personaSession.perfil.idPerfil == 1}">
		            		<div class="fieldgroup">
		                		<a href="inicioPersonas.jsp"><input type="button" value="Cancelar" class="submit cabin"></a>
		            		</div>
		            	</c:when>
		            	<c:when test="${sessionScope.personaSession.perfil.idPerfil == 3}">
		            		<div class="fieldgroup">
		                		<a href="VentanaPracticante"><input type="button" value="Cancelar" class="submit cabin"></a>
		            		</div>
		            	</c:when>
		            </c:choose>	
	        </fieldset>
	    </div>
	</form>
	</div>
</body>
</html>