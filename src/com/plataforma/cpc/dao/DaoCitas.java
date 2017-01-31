package com.plataforma.cpc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.TratamientoTo;
import com.plataforma.cpc.utils.ConexionOracle;

public class DaoCitas extends ConexionOracle{

	public Conexion conexionActual;

	/*
	 * Filtra: todos si CitaTo viene nulo
	 * Filtra: por practicante si trae poblado información del practicante
	 * Filtra: por paciente si trae poblado información del paciente
	 * Filtra: por ambos si trae información poblada tanto del paciente como del practicante.
	 */
	public ArrayList<CitaTo> consultarCitasFiltro(CitaTo cita){

		ResultSet rs =null;
		conexionActual = new ConexionOracle();
		ArrayList<CitaTo> citas = new ArrayList<CitaTo>();

		int numeroParametros = 0;
		String sql = "SELECT ID_CITA,SALON,FECHA_SOLICITUD,FECHA_CITA,ID_PRACTICANTE,ID_PACIENTE FROM CITA WHERE 1=1 ";
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);

			if(cita.getPracticante()!= null){
				sql+= "AND ID_PRACTICANTE = ? ";
				numeroParametros++;
				conexionActual.agregarAtributo(numeroParametros, cita.getPracticante().getIdPersona()); 
			}
			if (cita.getPaciente() != null){
				sql+= "AND ID_PACIENTE = ? ";
				numeroParametros++;
				conexionActual.agregarAtributo(numeroParametros, cita.getPaciente().getIdPersona()); 
			}
			sql+= "ORDER BY FECHA_CITA ";
			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){
				CitaTo citaTo = new CitaTo();
				citaTo.setIdCita(rs.getInt("ID_CITA"));
				citaTo.setSalon(rs.getString("SALON"));
				citaTo.setFechaSolicitud(rs.getTimestamp("FECHA_SOLICITUD").toLocalDateTime());
				citaTo.setFechaCita(rs.getTimestamp("FECHA_CITA").toLocalDateTime());

				PersonaTo practicante = new PersonaTo();
				practicante.setIdPersona(rs.getInt("ID_PRACTICANTE"));
				citaTo.setPracticante(practicante);

				PersonaTo paciente = new PersonaTo();
				paciente.setIdPersona(rs.getInt("ID_PACIENTE"));
				citaTo.setPaciente(paciente);

				citas.add(citaTo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return citas;
	}

	public ArrayList<CitaTo> consultarCitasPracticante(Integer idPracticante){
		ResultSet rs =null;
		conexionActual = new ConexionOracle();
		ArrayList<CitaTo> citas = new ArrayList<CitaTo>();
		String sql = "SELECT ID_CITA,SALON,FECHA_SOLICITUD,FECHA_CITA,ID_PRACTICANTE,ID_PACIENTE FROM CITA WHERE  ID_PRACTICANTE=?";
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idPracticante);

			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){
				CitaTo citaTo = new CitaTo();
				citaTo.setIdCita(rs.getInt("ID_CITA"));
				citaTo.setSalon(rs.getString("SALON"));
				citaTo.setFechaSolicitud(rs.getTimestamp("FECHA_SOLICITUD").toLocalDateTime());
				citaTo.setFechaCita(rs.getTimestamp("FECHA_CITA").toLocalDateTime());

				PersonaTo practicante = new PersonaTo();
				practicante.setIdPersona(rs.getInt("ID_PRACTICANTE"));
				citaTo.setPracticante(practicante);

				PersonaTo paciente = new PersonaTo();
				paciente.setIdPersona(rs.getInt("ID_PACIENTE"));
				citaTo.setPaciente(paciente);

				citas.add(citaTo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return citas;
	}

	public CitaTo consultarCita(CitaTo cita){

		ResultSet rs =null;
		conexionActual = new ConexionOracle();
		CitaTo citaTo = new CitaTo();
		String sql = "SELECT ID_CITA,SALON,FECHA_SOLICITUD,FECHA_CITA,ID_PRACTICANTE,ID_PACIENTE FROM CITA WHERE ID_CITA = ? ";

		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);	
			conexionActual.agregarAtributo(1, cita.getIdCita()); 
			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){
				citaTo.setIdCita(rs.getInt("ID_CITA"));
				citaTo.setSalon(rs.getString("SALON"));
				citaTo.setFechaSolicitud(rs.getTimestamp("FECHA_SOLICITUD").toLocalDateTime());
				citaTo.setFechaCita(rs.getTimestamp("FECHA_CITA").toLocalDateTime());

				PersonaTo practicante = new PersonaTo();
				practicante.setIdPersona(rs.getInt("ID_PRACTICANTE"));
				citaTo.setPracticante(practicante);

				PersonaTo paciente = new PersonaTo();
				paciente.setIdPersona(rs.getInt("ID_PACIENTE"));
				citaTo.setPaciente(paciente);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return citaTo;
	}

	public boolean crearCita(CitaTo cita){

		boolean retorno;
		conexionActual = new ConexionOracle();
		String sql = "INSERT INTO CITA (ID_CITA,SALON,FECHA_SOLICITUD,FECHA_CITA,ID_PRACTICANTE,ID_PACIENTE,ID_TRATAMIENTO,ES_VALORACION) ";
				sql+= "VALUES (CITA_SEQ.NEXTVAL,?,TO_TIMESTAMP(SYSDATE,'DD/MM/RR HH24:MI:SS'), ?,?,?,?,?)";

		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);	
			conexionActual.agregarAtributo(1, cita.getSalon()); 
			conexionActual.agregarAtributo(2, cita.getFechaCita()); 
			conexionActual.agregarAtributo(3, cita.getPracticante().getIdPersona()); 
			conexionActual.agregarAtributo(4, cita.getPaciente().getIdPersona()); 
			conexionActual.agregarAtributo(5, cita.getTratamiento().getIdTratamiento());
			int valoracion = cita.isValoracion()? 1 : 0;
			conexionActual.agregarAtributo(6, valoracion);

			conexionActual.ejecutarActualizacion();
			retorno = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
		}finally{
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return retorno;
	}

	public int crearTratamiento(TratamientoTo tratamiento){ 	
		int id = 0;
		conexionActual = new ConexionOracle();
		String sql = "INSERT INTO TRATAMIENTO (ID_TRATAMIENTO,ID_PACIENTE,FECHA_INICIO,TIPO) ";
				sql+= "VALUES (TRATAMIENTO_SEQ.NEXTVAL,?,?,?)";

		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);	
			conexionActual.agregarAtributo(1, tratamiento.getPaciente().getIdPersona()); 
			conexionActual.agregarAtributo(2, tratamiento.getFechaInicio());
			conexionActual.agregarAtributo(3, tratamiento.getTipo()); 

			conexionActual.ejecutarActualizacion();
			
			sql = "SELECT TRATAMIENTO_SEQ.CURRVAL FROM DUAL ";
			conexionActual.prepararSentencia(sql);	
			ResultSet rs = conexionActual.ejecutarSentencia();
			
			if ( rs!=null && rs.next() ) {
				id = rs.getInt(1);
				System.out.println(id);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
		return id;
	}

	public boolean actualizarCita(CitaTo cita){

		boolean retorno;
		conexionActual = new ConexionOracle();
		String sql = "UPDATE CITA SET SALON = ?, FECHA_SOLICITUD = TO_TIMESTAMP(SYSDATE,'DD/MM/RR HH24:MI:SS'),";
		sql+="FECHA_CITA = TO_TIMESTAMP(?,'DD/MM/RR HH24:MI:SS'),ID_PRACTICANTE = ?, ID_PACIENTE = ? WHERE ID_CITA = ? ";

		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, cita.getSalon()); 
			conexionActual.agregarAtributo(2, cita.getFechaCita()); 
			conexionActual.agregarAtributo(3, cita.getPracticante().getIdPersona()); 
			conexionActual.agregarAtributo(4, cita.getPaciente().getIdPersona()); 
			conexionActual.agregarAtributo(5, cita.getIdCita()); 

			conexionActual.ejecutarActualizacion();
			retorno = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
		}finally{
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return retorno;
	}

	public boolean eliminarCita(CitaTo cita){

		boolean retorno;
		conexionActual = new ConexionOracle();
		String sql = "DELETE FROM CITA WHERE ID_CITA = ?";

		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, cita.getIdCita()); 

			conexionActual.ejecutarActualizacion();
			retorno = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
		}finally{
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return retorno;
	}
	
	public ArrayList<TratamientoTo> concultarTratamientosPaciente (Integer idPaciente){
		ResultSet rs =null;
		conexionActual = new ConexionOracle();
		ArrayList<TratamientoTo> tratamientos = new ArrayList<TratamientoTo>();
		String sql = "SELECT ID_TRATAMIENTO,ESTADO,FECHA_INICIO,FECHA_CIERRE,TIPO FROM TRATAMIENTO WHERE ID_PACIENTE=?";
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idPaciente);

			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){
				TratamientoTo tratamientoTo = new TratamientoTo();
				tratamientoTo.setIdTratamiento(rs.getInt("ID_TRATAMIENTO"));
				tratamientoTo.setEstado(rs.getString("ESTADO"));
				tratamientoTo.setFechaInicio(rs.getTimestamp("FECHA_INICIO").toLocalDateTime());
				
				if(rs.getTimestamp("FECHA_CIERRE") != null)
					tratamientoTo.setFechaCierre(rs.getTimestamp("FECHA_CIERRE").toLocalDateTime());
				
				tratamientoTo.setTipo(rs.getString("TIPO"));

				tratamientos.add(tratamientoTo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return tratamientos;
	}
	
	public ArrayList<CitaTo> consultarCitasPacienteTratamiento(Integer idPaciente, Integer idTratamiento){
		ResultSet rs =null;
		conexionActual = new ConexionOracle();
		ArrayList<CitaTo> citas = new ArrayList<CitaTo>();
		String sql = "SELECT CITA.ID_CITA, CITA.SALON, CITA.FECHA_SOLICITUD, CITA.FECHA_CITA,CITA.ID_PRACTICANTE, CITA.ID_PACIENTE, ";
				sql+="CITA.ESTADO, CITA.ID_TRATAMIENTO, CITA.ES_VALORACION ";
				sql+="FROM PERSONA PER, TRATAMIENTO TRA, CITA CITA ";
				sql+="WHERE PER.PERFIL_ID_PERFIL  =  4 ";
				sql+="AND PER.ID_PERSONA = TRA.ID_PACIENTE ";
				sql+="AND CITA.ID_TRATAMIENTO = TRA.ID_TRATAMIENTO ";
				sql+="AND PER.ID_PERSONA =NVL(?,PER.ID_PERSONA) ";
				sql+="AND TRA.ID_TRATAMIENTO = NVL(?,TRA.ID_TRATAMIENTO) ";
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idPaciente);
			conexionActual.agregarAtributo(2, idTratamiento);

			rs = conexionActual.ejecutarSentencia();

			while (rs.next()){
				CitaTo citaTo = new CitaTo();
				citaTo.setIdCita(rs.getInt("CITA.ID_CITA"));
				citaTo.setSalon(rs.getString("CITA.SALON"));
				citaTo.setFechaSolicitud(rs.getTimestamp("CITA.FECHA_SOLICITUD").toLocalDateTime());
				citaTo.setFechaCita(rs.getTimestamp("CITA.FECHA_CITA").toLocalDateTime());

				PersonaTo practicante = new PersonaTo();
				practicante.setIdPersona(rs.getInt("CITA.ID_PRACTICANTE"));
				citaTo.setPracticante(practicante);

				PersonaTo paciente = new PersonaTo();
				paciente.setIdPersona(rs.getInt("CITA.ID_PACIENTE"));
				citaTo.setPaciente(paciente);
				
				citaTo.setEstado(rs.getString("CITA.ESTADO"));
				
				TratamientoTo tratamiento = new TratamientoTo();
				tratamiento.setIdTratamiento(rs.getInt("CITA.ID_TRATAMIENTO"));
				citaTo.setTratamiento(tratamiento);
				
				int valoracion = rs.getInt("CITA.ES_VALORACION");
				boolean b = (valoracion != 0);
				citaTo.setValoracion(b);

				citas.add(citaTo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conexionActual.cerrar();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return citas;
	}
}