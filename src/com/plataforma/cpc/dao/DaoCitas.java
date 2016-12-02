package com.plataforma.cpc.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
    	String sql = "SELECT ID_CITA,SALON,FECHA_SOLICITUD,FECHA_CITA,ID_PER_PRACTICANTE,ID_PER_PACIENTE FROM CITA WHERE 1=1 ";
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
	 		
			if(cita.getPracticante()!= null){
	 			sql+= "AND ID_PER_PRACTICANTE = ? ";
	 			numeroParametros++;
	 			conexionActual.agregarAtributo(numeroParametros, cita.getPracticante().getIdPersona()); 
	 		}
	 		if (cita.getPaciente() != null){
	 			sql+= "AND ID_PER_PACIENTE = ? ";
	 			numeroParametros++;
	 			conexionActual.agregarAtributo(numeroParametros, cita.getPaciente().getIdPersona()); 
	 		}
	 		sql+= "ORDER BY FECHA_CITA ";
	 		rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				CitaTo citaTo = new CitaTo();
				citaTo.setIdCita(rs.getInt("ID_CITA"));
				citaTo.setSalon(rs.getString("SALON"));
				citaTo.setFechaSolicitud(rs.getDate("FECHA_SOLICITUD"));
				citaTo.setFechaCita(rs.getDate("FECHA_CITA"));
				
				PersonaTo practicante = new PersonaTo();
				practicante.setIdPersona(rs.getInt("ID_PER_PRACTICANTE"));
				citaTo.setPracticante(practicante);
				
				PersonaTo paciente = new PersonaTo();
				paciente.setIdPersona(rs.getInt("ID_PER_PACIENTE"));
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
    	String sql = "SELECT ID_CITA,SALON,FECHA_SOLICITUD,FECHA_CITA,ID_PER_PRACTICANTE,ID_PER_PACIENTE FROM CITA WHERE ID_CITA = ? ";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);	
			conexionActual.agregarAtributo(1, cita.getIdCita()); 
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				citaTo.setIdCita(rs.getInt("ID_CITA"));
				citaTo.setSalon(rs.getString("SALON"));
				citaTo.setFechaSolicitud(rs.getDate("FECHA_SOLICITUD"));
				citaTo.setFechaCita(rs.getDate("FECHA_CITA"));
				
				PersonaTo practicante = new PersonaTo();
				practicante.setIdPersona(rs.getInt("ID_PER_PRACTICANTE"));
				citaTo.setPracticante(practicante);
				
				PersonaTo paciente = new PersonaTo();
				paciente.setIdPersona(rs.getInt("ID_PER_PACIENTE"));
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
    	String sql = "INSERT INTO CITA (ID_CITA,SALON,FECHA_SOLICITUD,FECHA_CITA,ID_PRACTICANTE,ID_PACIENTE,ESTADO) ";
    			sql+= "VALUES (CITA_SEQ.NEXTVAL,?,TO_DATE(SYSDATE,'DD/MM/RR'),?,?,?,'PENDIENTE')";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);	
			conexionActual.agregarAtributo(1, cita.getSalon());
			Timestamp timestamp = new Timestamp(cita.getFechaCita().getTime());
			conexionActual.agregarAtributo(2, timestamp);
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
    	String sql = "UPDATE CITA SET SALON = ?, FECHA_SOLICITUD = TO_DATE(SYSDATE,'DD/MM/RR'),";
    	sql+="FECHA_CITA = TO_TIMESTAMP(?,'DD/MM/RR HH12:MI:SS AM'),ID_PER_PRACTICANTE = ?, ID_PER_PACIENTE = ? WHERE ID_CITA = ? ";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, cita.getSalon()); 
			conexionActual.agregarAtributo(2, (Date) cita.getFechaCita()); 
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
    
    public static void main(String args[]) {
    	DaoUsuario daoUsuario = new DaoUsuario();
    	
    	//daoUsuario.consultarDaoUsuarios(daoUsuario);
    	//daoUsuario.consultarDaoUsuario(daoUsuario);
    	//daoUsuario.crearDaoUsuario(daoUsuario);
    	//daoUsuario.modificarDaoUsuario(daoUsuario);
    	daoUsuario.eliminarDaoUsuario(daoUsuario);
    }
    public void eliminarDaoUsuario(DaoUsuario daoUsuario){
     	 
    	UsuarioTo usuario = new UsuarioTo();
    	usuario.setIdUsuario(new Integer(3));
   
    	if (daoUsuario.eliminarUsuario(usuario)){
    		System.out.println("Eliminado"); 
    	}else {
    		System.out.println("No eliminado");
    	}
    }
    public void modificarDaoUsuario(DaoUsuario daoUsuario){
      	 
    	UsuarioTo usuario = new UsuarioTo();
    	usuario.setIdUsuario(new Integer(4));
    	usuario.setNombreUsuario("alberto");
    	usuario.setContrasena("alberto123");
    	usuario.setCorreo("alberto@gmail.com");
   
    	if (daoUsuario.actualizarUsuario(usuario)){
    		System.out.println("Actualizado"); 
    	}else {
    		System.out.println("No actualizado");
    	}
    }
    
    public void crearDaoUsuario(DaoUsuario daoUsuario){
   	 
    	UsuarioTo usuario = new UsuarioTo();
    	usuario.setNombreUsuario("vanessa");
    	usuario.setContrasena("vanessa123");
    	usuario.setCorreo("vanessa@gmail.com");
   
    	if (daoUsuario.crearUsuario(usuario)){
    		System.out.println("Creado"); 
    	}else {
    		System.out.println("No creado");
    	}
    }
    
    public void consultarDaoUsuario(DaoUsuario daoUsuario){
    	 
    	UsuarioTo usuario = new UsuarioTo();
    	usuario.setIdUsuario(new Integer(4));
    	
    	usuario = daoUsuario.consultarUsuario(usuario);
    	if (usuario != null){
    		System.out.println(usuario.toString()); 
    	}else {
    		System.out.println("No trae datos");
    	}
    }
    public void consultarDaoUsuarios(DaoUsuario daoUsuario){
 
    	ArrayList<UsuarioTo> usuarios = new ArrayList<UsuarioTo>();
    	UsuarioTo usuario = new UsuarioTo();
    	
    	usuarios = daoUsuario.consultarUsuarios(usuario);
    	if (usuarios.size()>0){
    		for (UsuarioTo usuarioTo: usuarios){
    			System.out.println(usuarioTo.toString()); 
    		}
    	}else {
    		System.out.println("No trae datos");
    	}
    }
}
