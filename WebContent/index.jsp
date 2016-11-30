<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html">

    <head>
        <link rel="stylesheet" href="estiloLogin.css"></link>
        <title>CPC login</title>
    </head>

    <body>
        <div id="contenedor">
            <img src="resources/logo2cpc.png">
                <form id="FormUsuario" name="FormUsuario" action="./ServletUsuario" method="POST">
                    <input type="hidden" name="operacion" />
                    <input type="text" name="user" id="user" placeholder="usuario" class="text"/><br/>                                  
                    <input type="password" name="password" id="password" placeholder="contraseña" class="text"/><br/>
                    <input type="submit" name="btnIngresar" id="btnIngresar" value="Entrar" class="botones" onclick="{document.FormUsuario.operacion.value=this.id;document.FormUsuario.submit();}"/> 
                </form>
                <%if (request.getAttribute("mensaje") != null) {
                        if (request.getAttribute("mensaje").equals("1")) {
                %>            
                <label class="alerta">.::El usuario no existe en la base de datos de la Uninversidad Konrad Lorenz::.</label>
                <%}
                    if (request.getAttribute("mensaje").equals("2")) {%>
                <label class="alerta">.::El usuario no existe en la base de datos del CPC::.</label>
                <%}
                    } //azd 28-05-2016 Fin%>
        </div>        

        <footer>

            <small>Fundación Universitaria Konrad Lorenz</small>
            <address>www.konradlorenz.edu.co</address>
        </footer> 
    </body>

</html>