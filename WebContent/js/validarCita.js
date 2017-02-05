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
	var nomForm = document.getElementById('FormDatos');
	document.getElementById('operacion').value = "detalleCitas";
	
    if (validarSeleccionTratamiento()) {
    	nomForm.submit();

    }
}

function validarSeleccionTratamiento(){
	var pacientes = document.getElementsByName("grupoTratamiento");
	
	for(var i = 0; i < pacientes.length; i++) {
		if(pacientes[i].checked == true) {
			return true;
		}
	}
	
	alert('Seleccione un tratamiento');
	return false;
}

function enviarCita(){
	var nomForm = document.getElementById('FormDatos');
	document.getElementById('operacion').value = "detalleSesion";
	
    if (validarSeleccionCita()) {
    	nomForm.submit();

    }
}

function validarSeleccionCita(){
	var pacientes = document.getElementsByName("grupoCita");
	
	for(var i = 0; i < pacientes.length; i++) {
		if(pacientes[i].checked == true) {
			return true;
		}
	}
	
	alert('Seleccione una cita');
	return false;
}
