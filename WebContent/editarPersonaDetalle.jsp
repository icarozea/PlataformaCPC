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
							<option value="soltero">Soltero(a)</option>
							<option value="casado">Casado(a)</option>
							<option value="divorciado">Divorciado(a)</option>
							<option value="viudo">Viudo(a)</option>
							<option value="union">Union Libre</option>
						</select>
		            </div>	
		            <div class="fieldgroup">
		                <label class="cabin">Edad</label>
		                <input type="text" id="edad" name="edad" value="${requestScope.personaDetalle.edad}" placeholder="Edad">            
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Fecha de Nacimiento</label>
						<input id="fecha_nacimiento" name="fecha_nacimiento" type="text"  value="" placeholder="Fecha de Nacimiento">
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Lugar de Nacimiento</label>
		            	<input id="lugar_nacimiento" name="lugar_nacimiento" type="text"  value="" placeholder="Lugar de Nacimiento">
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Nivel de Escolaridad</label>
						<select id="escolaridad" name="escolaridad">
							<option value="ninguna">Ninguna</option>
							<option value="primaria">Primaria</option>
							<option value="bachillerato">Bachillerato</option>
							<option value="tecnica">Técnica/Tecnológica</option>
							<option value="profesional">Profesional</option>
							<option value="especializacion">Especialización</option>
							<option value="maestria">Maestría</option>
							<option value="doctorado">Doctorado</option>
							<option value="postdoctorado">Post-Doctorado</option>
						</select>
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Ocupación</label>
		            	<input id="ocupacion" name="ocupacion" type="text"  value="" placeholder="Ocupación">
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Localidad</label>
		            	<input id="localidad" name="localidad" type="text" value="" placeholder="Localidad">
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Barrio</label>
		            	<input id="barrio" name="barrio" type="text" value="" placeholder="Barrio">
		            </div>
		            <div class="fieldgroup">
		            	<label class="cabin">Estrato</label>
						<select id="estrato" name="estrato">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
						</select>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">En Caso de Emergencia Llamar A</label>
		                <input id="emergencia" name="emergencia" type="text" value="" placeholder="Emergencia">
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">A los Teléfonos</label>
		                <input id="telefonos_emergencia" name="telefonos_emergencia" type="text" value="" placeholder="Telefonos Emergencia">
		            </div>
		             <div class="fieldgroup">
		                <label class="cabin">Parentesco</label>
		                <input id="parentesco" name="parentesco" type="text" value="" placeholder="Parentesco">
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Formato de Solicitud del Servicio</label>
						<select id="solicitud" name="solicitud" >
							<option value="voluntaria">Voluntaria</option>
							<option value="remision">Remisión</option>
						</select>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Institución que Remite</strong></label>
		                <input id="institucion" name="institucion" type="text" value="" placeholder="Institución">
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Acudiente</label>
						<input type="text" id="acudiente" name="acudiente" value="${requestScope.personaDetalle.acudiente}" placeholder="Acudiente"  />
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Parentesco del Acudiente</label>
						<input id="parentesco_acudiente" name="parentesco_acudiente" type="text" value="" placeholder="Parentesco Acudiente">
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Telefonos del Acudiente</label>
						<input id="telefonos_acudiente" name="telefonos_acudiente" type="text" value="" placeholder="Telefonos Acudiente">
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Personas con las que vive actualmente</label>
						<textarea id="parientes" name="parientes" rows="10" value=""  cols="46" style="resize: none;">
						</textarea>
		            </div>
		            <div class="fieldgroup">
		                <label class="cabin">Problematica</label>
						<textarea id="problematica" name="problematica" rows="10" value="${requestScope.personaDetalle.problematica}"  cols="46" style=" resize: none;">
						</textarea>
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
						<textarea id=observaciones" name="observaciones" rows="10" value="${requestScope.personaDetalle.observación}" cols="46" style=" resize: none;">
						</textarea>
		            </div>
		            <div class="fieldgroup">
		            </div>
		            <div class="fieldgroup">
		                <input type="button" value="Registrar" onclick="modificarDetalle()" class="submit cabin"/>
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