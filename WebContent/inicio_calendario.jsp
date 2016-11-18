<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="./resources/Calendario_cpc_mejorado/recursos_Calendario/demos/temas.css">
        <script src='./resources/Calendario_cpc_mejorado/recursos_Calendario/lib/moment.min.js'></script>
        <script src='./resources/Calendario_cpc_mejorado/recursos_Calendario/lib/jquery.min.js'></script>
        <script src='./resources/Calendario_cpc_mejorado/recursos_Calendario/lib/jquery-ui.custom.min.js'></script>
        <script src='./resources/Calendario_cpc_mejorado/recursos_Calendario/demos/java.js'></script>
        <link rel="stylesheet" href="./estilo.css"></link>

        <title>Gestion calendario</title>
        <script>
            $(document).ready(function () {
                $("#boton_agendar").click(function () {
                    $("#formularios").toggle("50000");

                });
            });
            $(document).ready(function () {
                $("#cancecelar").click(function () {
                    $("#formularios").hide("50000");

                });
            });
        </script> 
       
    </head>
    <body>
        <!--MEMU SUPERIOR--> 
        <%@include file="./menuNavegacion.jsp" %>
        <br> 


        <div class="caja_inicio">
            <h2>GESTION DE CITAS</h2>
            <table >
                <thead>
                    <tr>
                        <th><img src="./resources/Calendario_cpc_mejorado/iconos_utilizados/calendar.png"/></th>
                        <th>  <a href="./Listar_horarios.jsp"><img src="./resources/Calendario_cpc_mejorado/iconos_utilizados/calendar-2.png"/></a></th>
                        <th>  <a href="./Calendario.jsp"><img src="./resources/Calendario_cpc_mejorado/iconos_utilizados/calendar-8.png"/></a></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="button" class="botonInicio" id="boton_agendar" value="Agendar Nueva Cita"  > </td>

                        <td>
                            <input type="button" class="botonInicio"  value="Actualizar Citas" onclick="location.href = './Listar_horarios.jsp'">
                        </td>


                        <td>
                            <input type="button" class="botonInicio"  value="Ver Horarios Asignados" onclick="location.href = './Calendario.jsp'">
                        </td>

                    </tr>
                </tbody>
            </table>
        </div>
    </Form>


    <div class="container2" id="formularios">
        <form id="ServletGestionCitas" action="ServletGestionCitas.do?Accion=InsertaCita" method="POST" accept-charset="utf-8" id="agen" onsubmit=" return addEvent(this)">
            <table style="width:100%">
                <tr>
                    <td colspan="3">
                        <div class="form_div">
                            <label> ·Agendar practicante:</label>
                            <select name="selectPracticantes" id="selectPracticantes">
                                <option value="0" id="seleccionar_practicante">Elegir practicante </option>
                                <c:forEach var="item" >
                                    <option value="${item.CODIGO}">${item.CODIGO} ${item.NOMBRE1} ${item.NOMBRE2} ${item.APELLIDO1}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form_div">
                            <label > .:Agendar paciente :</label>
                            <select  name="selectPaciente" id="selectPaciente">
                                <option value="0" id="seleccionar_paciente">Elegir paciente </option>
                                <c:forEach var="item" >
                                    <option value="${item.CODIGO}">${item.NOMBRE1} ${item.NOMBRE2} ${item.APELLIDO1}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </td>    
                </tr>
                <tr>
                    <td colspan="3">
                        <div class="form_div">
                            <label>·Ubicacion de cubiculo:</label>
                            <input class="texbox" type="number" id="ubicacion" placeholder="Codigo salon" name="salon">
                        </div>
                        <div  class="form_div2">
                            <label>.:Tipo Tratamiento:</label>
                            <select  id="selectTratamiento" name="selectTratamiento" >
                                <option value="0"   id="seleccionar" >Elegir tratamiento </option>
                                <c:forEach var="item" >
                                    <option value="${item.COD_TRATAMIENTO}">${item.NOM_TRATAMIENTO}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </td>    
                </tr>
                <tr>
                    <td colspan="3">

                        <div class="form_div">
                            <label>·Dia y hora a agendar:</label>
                            <input class="texbox1" name="datetime-local" type="datetime-local"  id="fecha_hora" >
                        </div>
                        <div class="form_div">
                            <label>.:Hora salida :</label>
                            <input class="texbox1" name="hora_salida" type="time" id="hora_salida">
                        </div>
                    </td>    
                </tr>
                <tr>
                    <td colspan="3">
                        <div class="form_div3">
                            <label class="div_label">·Multimedia Necesarios en  la cita:</label>
                            <textarea  name="Multimedia"  id="multimedia" value="No aplica" placeholder="Escriba la multimedia necesaria, De no ser así deje este espacio vacío."></textarea>
                        </div>
                    </td>    
                </tr>
                <tr>
                    <td colspan="3" >
                        <div class="form_div4" style="text-align:right">
                            <p>             
                                <input type="submit" value="Crear Cita"  class="botonR1">
                            </p>
                        </div>
                    </td>    
                </tr>
            </table>
        </form>

    </div>

</body>
</html>