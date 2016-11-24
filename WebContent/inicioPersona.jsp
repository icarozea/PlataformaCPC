<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script type="text/javascript" src="js/util.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Persona</title>
		<title>Insert title here</title>
	</head>
	<body onload="cargueInicial('formCreatePersona')">
		<form align="center" id="formCreatePersona" name="formCreatePersona" value="formCreatePersona" action="./ServletPersona" method="POST">
                <input type="hidden" name="operacion"  id="operacion"/>
        </form>
	</body>
</html>