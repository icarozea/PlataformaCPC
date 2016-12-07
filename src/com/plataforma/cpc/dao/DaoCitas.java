package com.plataforma.cpc.dao;

import java.util.Date;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.UsuarioTo;
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
    	String sql = "INSERT INTO CITA (ID_CITA,SALON,FECHA_SOLICITUD,FECHA_CITA,ID_PRACTICANTE,ID_PACIENTE) ";
    			sql+= "VALUES (CITA_SEQ.NEXTVAL,?,TO_TIMESTAMP(SYSDATE,'DD/MM/RR HH24:MI:SS'), ?,?,?)";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);	
			conexionActual.agregarAtributo(1, cita.getSalon()); 
			conexionActual.agregarAtributo(2, cita.getFechaCita()); 
			conexionActual.agregarAtributo(3, cita.getPracticante().getIdPersona()); 
			conexionActual.agregarAtributo(4, cita.getPaciente().getIdPersona()); 
			
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
}
