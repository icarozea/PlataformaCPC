var lMargin = 30;
var tMargin = 35;
var rMargin = 50;
var pdfInPt = 595;
var lineMargin = 10;

var docTitleSize = 24;
var titleSize = 16;
var textSize = 12;

function toPDF(type){
	var doc = new jsPDF("p","pt","a4");        

	if(type == 1)
		sesionToPDF(doc);
	else if (type == 2)
		valoracionToPDF(doc);
	else
		return;
}

function escribirParrafo(document, lines, start_point){
	var spacing = start_point;

	for(i=0;i<lines.length;i++){
		if(spacing >= (document.internal.pageSize.height) - tMargin){
			document.addPage();
			spacing = tMargin;
		}
		
		document.text(lines[i], lMargin*2, spacing);
		spacing+= lineMargin;
	}

	return spacing;
}

function sesionToPDF(doc){
	// Encabezado con datos de la sesión
	doc.setFontSize(docTitleSize);
	doc.text('REPORTE DE SESIÓN', lMargin, tMargin);
	doc.setFontSize(titleSize);
	doc.text('FECHA: ' + document.getElementById("h_Fecha").innerHTML, lMargin, tMargin*2);
	doc.text('No. CITA: ' + document.getElementById("h_Numero").innerHTML, lMargin*10, tMargin*2);
	doc.text('PACIENTE: ' + document.getElementById("h_NomPaciente").innerHTML, lMargin, tMargin*2.5);
	doc.text('PROFESIONAL A CARGO: ' + document.getElementById("h_Profesional").innerHTML, lMargin, tMargin*3);

	// Sección de tamaño variable con la información del documento	
	doc.text('OBJETIVO DE LA SESIÓN:', lMargin, tMargin*4);
	doc.setFontSize(textSize);
	var secText = document.getElementById("objetivoSesion").innerHTML;
	var lines = doc.splitTextToSize(secText, (pdfInPt-lMargin-rMargin));
	var spacing = escribirParrafo(doc, lines, tMargin*4.5);

	doc.setFontSize(titleSize);
	spacing+= lineMargin*2;
	doc.text('DESCRIPCIÓN DE LA SESIÓN:', lMargin, spacing);
	doc.setFontSize(textSize);
	spacing+=lineMargin*1.25;
	secText = document.getElementById("descripcionSesion").innerHTML;
	lines = doc.splitTextToSize(secText, (pdfInPt-lMargin-rMargin));
	spacing = escribirParrafo(doc, lines, spacing);

	doc.setFontSize(titleSize);
	spacing += lineMargin*2;
	doc.text('TAREAS ASIGNADAS:', lMargin, spacing);
	doc.setFontSize(textSize);
	spacing+=lineMargin*1.25;
	secText = document.getElementById("tareasSesion").innerHTML;
	lines = doc.splitTextToSize(secText, (pdfInPt-lMargin-rMargin));
	spacing = escribirParrafo(doc, lines, spacing);

	doc.setFontSize(titleSize);
	spacing += lineMargin*2;
	doc.text('ACTIVIDADES PARA LA PROXIMA SESIÓN:', lMargin, spacing);
	doc.setFontSize(textSize);
	spacing+=lineMargin*1.25;
	secText = document.getElementById("actividadesProxSesion").innerHTML;
	lines = doc.splitTextToSize(secText, (pdfInPt-lMargin-rMargin));
	escribirParrafo(doc, lines, spacing);

	doc.output("save",document.getElementById("h_NomPaciente").innerHTML + "--" + document.getElementById("h_Fecha").innerHTML + ".pdf");
}

function valoracionToPDF(doc){
	// Encabezado con datos de la valoracion
	doc.setFontSize(docTitleSize);
	doc.text('REPORTE DE VALORACION', lMargin, tMargin);
	doc.setFontSize(titleSize);
	doc.text('FECHA: ' + document.getElementById("h_Fecha").innerHTML, lMargin, tMargin*2);
	doc.text('No. CITA: 0', lMargin*10, tMargin*2);
	doc.text('PACIENTE: ' + document.getElementById("h_NomPaciente").innerHTML, lMargin, tMargin*2.5);
	doc.text('PROFESIONAL A CARGO: ' + document.getElementById("entrevistador").value, lMargin, tMargin*3);
	
	// Sección de tamaño variable con la información del documento	
	doc.text('MOTIVO:', lMargin, tMargin*4);
	doc.setFontSize(textSize);
	var secText = document.getElementById("motivo_consulta").innerHTML;
	var lines = doc.splitTextToSize(secText, (pdfInPt-lMargin-rMargin));
	var spacing = escribirParrafo(doc, lines, tMargin*4.5);
	
	doc.setFontSize(titleSize);
	spacing+= lineMargin*2;
	doc.text('ASPECTOS SOBRESALIENTES:', lMargin, spacing);
	doc.setFontSize(textSize);
	spacing+=lineMargin*1.25;
	secText = document.getElementById("aspectos").innerHTML;
	lines = doc.splitTextToSize(secText, (pdfInPt-lMargin-rMargin));
	spacing = escribirParrafo(doc, lines, spacing);
	
	doc.setFontSize(titleSize);
	spacing += lineMargin*2;
	doc.text('HIPOTESIS PRELIMINARES:', lMargin, spacing);
	doc.setFontSize(textSize);
	spacing+=lineMargin*1.25;
	secText = document.getElementById("hipotesis").innerHTML;
	lines = doc.splitTextToSize(secText, (pdfInPt-lMargin-rMargin));
	spacing = escribirParrafo(doc, lines, spacing);
	
	doc.setFontSize(titleSize);
	spacing += lineMargin*2;
	doc.text('SERVICIO REMITIDO:', lMargin, spacing);
	doc.setFontSize(textSize);
	spacing+=lineMargin*1.25;
	secText = document.getElementById("remitido").innerHTML;
	lines = doc.splitTextToSize(secText, (pdfInPt-lMargin-rMargin));
	escribirParrafo(doc, lines, spacing);
	
	doc.output("save",document.getElementById("h_NomPaciente").innerHTML + "--" + document.getElementById("h_Fecha").innerHTML + ".pdf");
}