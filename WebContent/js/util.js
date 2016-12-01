/**
 * 
 */

function cargueInicial(){
	
}

function mostrarEPS(perfil){
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