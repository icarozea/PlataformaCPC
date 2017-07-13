/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function abrirVentana(){ 
    v = open("ServletEPS?opcion=listEPS","","menubar=no, srcrollbars=yes, width=1000, height=500"); 
    v.moveTo(screen.width/2-500,screen.height/2-250); 
} 

function salir(){
    dialogArguments('FormularioEPS.jsp');
}

function EliminarEPS(codigo){
    var criterio = document.getElementById('criterio');
    criterio.value = codigo;
    document.accionesEPS.action = "./ServletEPS?opcion=borrarEPS";
    document.accionesEPS.submit();
}

function modificarEPS(codigo){
    var criterio = document.getElementById('criterio');
    criterio.value = codigo;
    document.accionesEPS.action = "./ServletEPS?opcion=getEPSById";
    document.accionesEPS.submit();
}

function checkPass()
{
    //Store the password field objects into variables ...
    var pass1 = document.getElementById('password');
    var pass2 = document.getElementById('password2');
    //Store the Confimation Message Object ...
    var message = document.getElementById('confirmMessage');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    //Compare the values in the password field 
    //and the confirmation field
    if (pass1.value == pass2.value) {
        //The passwords match. 
        //Set the color to the good color and inform
        //the user that they have entered the correct password 
        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "¡Las contraseñas son iguales!"
    } else {
        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "¡Las contraseñas no son iguales!"

    }
}


function checkMail() {
    var email = document.getElementById('correo');
    valueForm = email.value;
    var message = document.getElementById('confirmMessage');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";

    expr = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;
    if (valueForm.search(expr) === 0) {
        email.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "La dirección de correo es correcta."
        return;
    } else {
        email.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Error: La dirección de correo es incorrecta."
        return;
    }
}

function validarPass()
{
    var pass1 = document.getElementById('password');
    var pass2 = document.getElementById('password2');
    if(!pass1 || pass1.value === "" || pass1.length == 0){
    	alert('Digite una clave');
        return false;
    }
    else if (pass1.value == pass2.value) {
        return true;
    } else {
        alert('Las claves no coinciden');
        return false;
    }
}

function validarMail(){
    var email = document.getElementById('correo');
    valueForm = email.value;
    expr = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;
    if (valueForm.search(expr) === 0) {
        return true;
    } else {
        alert('El correo digitado no es valido');
        return false;
    }
}

function validarTexto(texto){
    expr = /^[a-zA-Z ñÑáéíóúÁÉÍÓÚÄËÏÖÜäëïöüàèìòùÀÈÌÔÙ, ]{2,}$/;
    valueForm = texto;
    if(valueForm.search(expr) === 0){
        return true;
    } else {
        return false;
    }
            
}

function validarNumero(){
    var numero = document.getElementById('telefono').value;
    if (!/^([0-9])*$/.test(numero)){
        alert("El telefono digitado no es valido");
        return false;
    }
    
    numero =  document.getElementById('numeroDocumento').value
    if (!/^([0-9])*$/.test(numero)){
        alert("El documento digitado no es valido");
        return false;
    }
    
    numero = document.getElementById('codigo').value
    if (numero != null && numero != "" && !/^([0-9])*$/.test(numero)){
        alert("El codigo digitado no es valido");
        return false;
    }
    return true;
  }

function validarNombreCompleto(nom1,nom2,ape1,ape2) {
    if(validarTexto(nom1)){
    }else{
        alert('El primer nombre no es un nombre valido');
        return false;
    }
    if(validarTexto(ape1)){
    }else{
        alert('El primer apellido no es un apellido valido');
        return false;
    }
    if(validarTexto(ape2)){
    }else{
        alert('El segundo apellido no es un apellido valido');
        return false;
    }
    return true;
}

function crearEPS(){
	var nomForm = document.getElementById('register-form');
	document.getElementById('operacion').value = "irEPS";
	nomForm.submit();
}

function checkTodo(id){
	
	var nomForm = document.getElementById('register-form');
	
	if(id != null)
		document.getElementById('operacion').value = "actualizarDatos";
	else
		document.getElementById('operacion').value = "guardarPersona";
	
    var nom1 = document.getElementById('nombre1').value;
    var nom2 = document.getElementById('nombre2').value;
    var ape1 = document.getElementById('apellido1').value;
    var ape2 = document.getElementById('apellido2').value;
    
    if (validarPass()){
        
        if (validarMail()){
            
            if (validarNombreCompleto(nom1,nom2,ape1,ape2)){
                
                if(validarNumero()){     	
                    nomForm.submit();
                }
            }
        }
    }
}

function guardarDetalle(){	
	var nomForm = document.getElementById('register-form');
	document.getElementById('operacion').value = "guardarPersona";
    nomForm.submit();
}

function modificarDetalle(){	
	var nomForm = document.getElementById('register-form');
	document.getElementById('operacion').value = "modificarPersona";
    nomForm.submit();
}

function generarReporte(operacion){
	fechaReporte = document.getElementById('fechaReporte').value;
    document.reporteForm.action = "./ServletReporte?operacion="+operacion+"&fechaReporte="+fechaReporte;
    document.reporteForm.submit();
}

function generarReporteAdmin(operacion){
	alert('generarReporteAdmin');
	idPerfil = 4;
    document.reporteForm.action = "./ServletReporte?operacion="+operacion+"&idPerfil="+idPerfil;
    document.reporteForm.submit();
}
