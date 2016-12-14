function validarCita(){
	var nomForm = document.getElementById('FormDatos');
	document.getElementById('operacion').value = "guardarCita";
	
    var paciente = document.getElementById('grupoPaciente').value;
    console.log(paciente);
    if (validarPaciente()) {
    	  console.log("hay paciente");
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