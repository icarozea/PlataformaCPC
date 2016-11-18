
function  color() {
    var resul;
    var color = Math.round(Math.random() * 11);
    ;
    switch (color) {
        case 1:
            return resul = "#00a65a";
            break;
        case 2:
            return resul = "#f39c12";
            break;
        case 3:
            return resul = "#00c0ef";
            break;
        case 4:
            return resul = "#3c8dbc ";
            break;
        case 5:
            return resul = "#00c0ef";
            break;
        case 6:
            return resul = "#f56954";
            break;
        case 7:
            return resul = "#0073b7";
            break;
        case 8:
            return resul = "#00a65a";
            break;
        case 9:
            return resul = "#01FF70";
            break;
        case 10:
            return resul = "#001F3F";
            break;
        case 11:
            return resul = "rgb(60, 141, 188)";
            break;

    }
    return resul = "0000";
}
/*============================================================================*/
/*============================Verificaciones===================================*/
/*============================Campos Citas  ===================================*/
function Verificar_lista_prac(nombrePracticante) {
    if (nombrePracticante === 0) {
        if (confirm('Debe seleccionar un practicante') === true) {
         document.agendarCita.Lista_practicantes.focus();
        }
                
        return false;
    } else
    {
        return true;
    }
    ;
}
;
function Verificar_lista_pac(nombrepaciente) {
    if (nombrepaciente === 0) {
        if (confirm('Debe seleccionar un paciente') === true) {
         document.agendarCita.Lista_pacientes.focus();
        }
                
        return false;
    } else
    {
        return true;
    }
    ;
}
;
function Verificar_listaTrata(nombretratamiento) {
    if (nombretratamiento === 0) {
        alert("Debe seleccionar el tipo de tratamiento");
        document.agendarCita.Lista_Tratamiento.focus();
        return false;
    } else
    {
        return true;
    }
    ;
}
;

function validar_fecha(valorfecha) {
       
    if (valorfecha === "") {
        alert("Es necesario la fecha y la hora para crear una cita");
        document.getElementById("fecha_hora").focus();
        return false;
    } else {
       // alert(fecha);
        return true;
    }
    ;
}
;
function validar_hora_salida(horasalida,horainicial) {
    var fecha_hora = horainicial;  
    var horaini = fecha_hora.substring(11, 13);
    var minutoini = fecha_hora.substring(14, 17);
    var horafin = horasalida.substring(0, 2);
    var minutofin = horasalida.substring(3, 5);
    
    
    //alert("hora ini : " + horaini + "hora fin" + horafin + "minuto ini : " + minutoini + " minuto fin :" + minutofin);
    
    if (horasalida === "" || horafin < horaini || minutofin < minutoini) {
        alert("la hora de salida no puede estar vacia o debe ser mayor a la de inicio revisar!");
        document.getElementById("hora_salida").focus();
        return false;
    } else {
       // alert(fecha);
        return true;
    }
    ;
}
;
function validarTex() {
    var valor = "";
    if (document.agendarCita.multimedia.value.length === 0) {
        valor = "Ninguno"
        return valor;
    } else {
        valor = document.getElementById("multimedia").value;
        return valor;
    }
    ;
}
;
function validar_ubicacion() {
    var fecha = document.getElementById("fecha_hora").value;
    if (fecha == "") {
        alert("Es necesario la ubicacion");
        document.getElementById("ubicacion").focus();
        return false;
    } else {

        return true;
    }
    ;
};



/*============================Verificaciones===================================*/
/*============================ borrar campos===================================*/
function Borrador_Cita(){ document.getElementById("ServletGestionCitas").reset();};
/*============================Agregando campos=================================*/
/*============================y verificando===================================*/

function  addEvent() {
    var valorPracticante = document.getElementById("selectPracticantes").selectedIndex;
    var nombre_paciente = document.getElementById("selectPaciente").selectedIndex;
    var tipo_tratamiento = document.getElementById("selectTratamiento").selectedIndex;
    var fecha_hora = document.getElementById("fecha_hora").value;
    var hora_salida = document.getElementById("hora_salida").value;
     

    if (Verificar_lista_prac(valorPracticante) === true 
        && Verificar_lista_pac(nombre_paciente) === true 
        && Verificar_listaTrata(tipo_tratamiento) === true
        && validar_fecha(fecha_hora) === true
        && validar_hora_salida(hora_salida,fecha_hora) === true
        ) 
      {
         alert("La Cita se ha creado exitosamente...!!!");
//        Borrador_Cita();
//        var newEvent = new Object();
//        newEvent.title =
//                "Practicante: " + nombre_practicante +
//                ",Tiene cita con: " + nombre_paciente +
//                ",Uibcacion cita" + document.getElementById("ubicacion").value +
//                " Tipo tratamiento:" + document.getElementById("Lista_Tratamiento").value +
//                ",Multimedia necesaria:" + multimedias2;
//
//        newEvent.start = document.getElementById("fecha_hora").value;
//        newEvent.end = '2016-06-09T10:30:00';
//        newEvent.allDay = false;
//        newEvent.backgroundColor = c;
//        newEvent.borderColor = c;
//        document.getElementById("multimedia").value = "";
//        //new Date(y, m, d, 14, 0),
//         $('#calendar').fullCalendar('renderEvent', newEvent);
    }
    else if(Verificar_lista_prac(valorPracticante) === false)
    {
        if (confirm('no se inserto la cita!') === true) {
         document.agendarCita.Lista_practicantes.focus();
        }
    };
}
;