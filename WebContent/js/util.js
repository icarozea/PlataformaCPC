function mostrarEPS(perfil){
	alert("Perfil: " + perfil);
	
	if (perfil == 4){
		document.getElementById('seccionCodigo').style.display='none';
		document.getElementById('codigo').style.display='none';
		document.getElementById('seccionEPS').style.display='block';
		document.getElementById('eps').style.display='block';
		document.getElementById('seccionJornada').style.display='block';
		document.getElementById('jornada').style.display='block';
	
	}
	else if(perfil == 3){
		document.getElementById('seccionEPS').style.display='none';
		document.getElementById('eps').style.display='none';
		
		document.getElementById('seccionJornada').style.display='block';
		document.getElementById('jornada').style.display='block';
		document.getElementById('seccionCodigo').style.display='block';
		document.getElementById('codigo').style.display='block';
	}
	else{
		document.getElementById('seccionEPS').style.display='none';
		document.getElementById('eps').style.display='none';
		document.getElementById('seccionJornada').style.display='none';
		document.getElementById('jornada').style.display='none';
		document.getElementById('seccionCodigo').style.display='none';
		document.getElementById('codigo').style.display='none';
	}
}

function campoOculto(){
	document.getElementById('campoOculto').style.display='inline-block';
}

function administrarSecciones(perfil){
	if (perfil == 4){
		document.getElementById('campoEPS').style.display='inline-block';
		document.getElementById('campoJornada').style.display='inline-block';
		document.getElementById('campoCodigo').style.display='none';
//		document.getElementById('campoOculto').style.display='none';
	}
	else if(perfil == 3){
		document.getElementById('campoCodigo').style.display='inline-block';
		document.getElementById('campoJornada').style.display='inline-block';
		document.getElementById('campoEPS').style.display='none';
//		document.getElementById('campoOculto').style.display='none';
	}
	else{
		document.getElementById('campoOculto').style.display='inline-block';
		document.getElementById('campoCodigo').style.display='none';
		document.getElementById('campoEPS').style.display='none';
		document.getElementById('campoJornada').style.display='none';
	}
}
