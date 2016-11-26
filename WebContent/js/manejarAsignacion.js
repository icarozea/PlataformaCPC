function manejarAsignacion(operacion,idAsignado, actualId, pNom, sNom, pApe, sApe, rol){
	document.data.operacion.value = operacion;
	document.data.asignado.value = idAsignado;
	document.data.id.value = actualId;
	document.data.pNom.value = pNom;
	document.data.sNom.value = sNom;
	document.data.pApe.value = pApe;
	document.data.sApe.value = sApe;
	document.data.rol.value = rol;
	document.data.submit();
}