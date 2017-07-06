function campoOculto(){
	document.getElementById('campoOculto').style.display='inline-block';
}

function administrarSecciones(perfil){
	console.log("Perfil: " + perfil);
	if (perfil == 4){
		document.getElementById('campoEPS').style.display='inline-block';
		document.getElementById('nuevaEPS').style.display='inline-block';
		document.getElementById('campoJornada').style.display='inline-block';
		document.getElementById('campoCodigo').style.display='none';
		document.getElementById('campoPass').style.display='none';
		document.getElementById('campoPass2').style.display='none';
		document.getElementById('password').value = "nonPass";
		document.getElementById('password2').value = "nonPass";
//		document.getElementById('campoOculto').style.display='none';
	}
	else if(perfil == 3){
		document.getElementById('campoCodigo').style.display='inline-block';
		document.getElementById('campoJornada').style.display='inline-block';
		document.getElementById('campoPass').style.display='inline-block';
		document.getElementById('campoPass2').style.display='inline-block';
		document.getElementById('campoEPS').style.display='none';
		document.getElementById('nuevaEPS').style.display='none';
//		document.getElementById('campoOculto').style.display='none';
	}
	else{
		document.getElementById('campoOculto').style.display='inline-block';
		document.getElementById('campoPass').style.display='inline-block';
		document.getElementById('campoPass2').style.display='inline-block';
		document.getElementById('campoCodigo').style.display='none';
		document.getElementById('campoEPS').style.display='none';
		document.getElementById('nuevaEPS').style.display='none';
		document.getElementById('campoJornada').style.display='none';
	}
}
