function toPDF(){
	var doc = new jsPDF("p","pt","a4");        
	
	var lMargin = 25;
	var tMargin = 35;
    var rMargin = 50;
    var pdfInPt = 595;
    var error = 0.05;
    
    var docTitleSize = 24;
    var titleSize = 16;
    var textSize = 12;
    
	// Encabezado con datos de la sesión
	doc.setFontSize(docTitleSize);
	doc.text('REPORTE DE SESIÓN', lMargin, tMargin);
	doc.setFontSize(titleSize);
	doc.text('FECHA: ' + document.getElementById("h_Fecha").innerHTML, lMargin, tMargin*2);
	doc.text('No. CITA: ' + document.getElementById("h_Numero").innerHTML, lMargin*30, tMargin*2);
	doc.text('PACIENTE: ' + document.getElementById("h_NomPaciente").innerHTML, lMargin, tMargin*2.5);
	doc.text('PROFESIONAL A CARGO: ' + document.getElementById("h_Profesional").innerHTML, lMargin, tMargin*3);
	
	// Sección de tamaño variable con la información del documento	
	doc.text('OBJETIVO DE LA SESIÓN:', lMargin, tMargin*4);
	doc.setFontSize(textSize);
	var secText = document.getElementById("objetivoSesion").innerHTML;
	var lines = doc.splitTextToSize(secText, (pdfInPt-lMargin-rMargin));
	doc.text(lines, lMargin*2, tMargin*4.5);
	
	doc.setFontSize(titleSize);
	var spacing = 4.5 + 0.5*lines.length;
	doc.text('DESCRIPCIÓN DE LA SESIÓN:', lMargin, tMargin*spacing);
	doc.setFontSize(textSize);
	spacing+=0.5;
	secText = document.getElementById("descripcionSesion").innerHTML;
	lines = doc.splitTextToSize(secText, (pdfInPt-lMargin-rMargin));
	doc.text(lines, lMargin*2, tMargin*spacing);
	
	doc.setFontSize(titleSize);
	spacing += 0.5*lines.length;
	doc.text('TAREAS ASIGNADAS:', lMargin, tMargin*spacing);
	doc.setFontSize(textSize);
	spacing+=0.5;
	secText = document.getElementById("tareasSesion").innerHTML;
	lines = doc.splitTextToSize(secText, (pdfInPt-lMargin-rMargin));
	doc.text(lines, lMargin*2, tMargin*spacing);
	
	doc.setFontSize(titleSize);
	spacing += 0.5*lines.length;
	doc.text('ACTIVIDADES PARA LA PROXIMA SESIÓN:', lMargin, tMargin*spacing);
	doc.setFontSize(textSize);
	spacing+=0.5;
	secText = document.getElementById("actividadesProxSesion").innerHTML;
	lines = doc.splitTextToSize(secText, (pdfInPt-lMargin-rMargin));
	doc.text(lines, lMargin*2, tMargin*spacing);
	
	doc.output("save","file.pdf");
}