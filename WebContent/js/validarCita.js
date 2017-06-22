function validarCita(){
	var nomForm = document.getElementById('FormDatos');
	document.getElementById('operacion').value = "guardarCita";
	
    if (validarPaciente()) {
    	if(validarSalon()){
    		if(validarFecha()){
    			nomForm.submit();
    		}
    	}
    }
}

function validarPaciente(){
	var pacientes = document.getElementsByName("grupoPaciente");
	
	for(var i = 0; i < pacientes.length; i++) {
		if(pacientes[i].checked == true) {
			return true;
		}
	}
	
	alert('Seleccione un paciente');
	return false;
}

function validarSalon(){
	var salon = document.getElementById("salon").value;
	
	if(salon != ""){
		if(/^([0-9])*$/.test(salon))
			return true;
	}
	
	alert('Digite un salón válido');
	return false;
}

function validarFecha(){
	var fecha = document.getElementById("fecha").value;
	
	if(fecha != ""){
		if(/^20[0-9]{2}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}$/.test(fecha))
			return true;
	}
	
	alert('Seleccione una fecha válida');
	return false;
}

function validarTratamiento(){
	var form = document.getElementById("FormDatos");
	var tratamientos = document.getElementsByName("grupoTratamiento");
	var seleccion = false;
	for(var i = 0; i < tratamientos.length; i++) {
		if(tratamientos[i].checked == true) {
			seleccion = true;
		}
	}
	if(seleccion)
		form.submit();
	else
		alert('Seleccione un tratamiento');
}

function enviarCita(){
	var form = document.getElementById("FormDatos");
	var citas = document.getElementsByName("grupoCita");
	var seleccion = false;
	for(var i = 0; i < citas.length; i++) {
		if(citas[i].checked == true) {
			seleccion = true;
		}
	}
	if(seleccion)
		form.submit();
	else
		alert('Seleccione una cita');
}