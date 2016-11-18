package com.plataforma.cpc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.plataforma.cpc.to.CitaTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.UsuarioTo;
import com.plataforma.cpc.utils.ConexionOracle;

public class DaoCitas extends ConexionOracle{

	public Connection connection;
	
	/*
	 * Filtra: todos si CitaTo viene nulo
	 * Filtra: por practicante si trae poblado información del practicante
	 * Filtra: por paciente si trae poblado información del paciente
	 * Filtra: por ambos si trae información poblada tanto del paciente como del practicante.
	 */
	public ArrayList<CitaTo> consultarCitasFiltro(CitaTo cita){
    	
    	PreparedStatement ps=null;
    	ResultSet rs =null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	ArrayList<CitaTo> citas = new ArrayList<CitaTo>();
    	int numeroParametros = 0;
    	String sql = "SELECT ID_CITA,SALON,FECHA_SOLICITUD,FECHA_CITA,ID_PER_PRACTICANTE,ID_PER_PACIENTE FROM CPC_CITA WHERE 1=1 ";
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
	 		
			if(cita.getPracticante()!= null){
	 			sql+= "AND ID_PER_PRACTICANTE = ? ";
	 			numeroParametros++;
	 			ps.setInt(numeroParametros, cita.getPracticante().getIdPersona());
	 		}
	 		if (cita.getPaciente() != null){
	 			sql+= "AND ID_PER_PACIENTE = ? ";
	 			numeroParametros++;
	 			ps.setInt(numeroParametros, cita.getPaciente().getIdPersona());
	 		}
	 		sql+= "ORDER BY FECHA_CITA ";
			rs = ps.executeQuery();
			
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
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conexionOracle.cerrar();
				this.connection.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
    	return citas;
    }
    
    public CitaTo consultarCita(CitaTo cita){

    	PreparedStatement ps=null;
    	ResultSet rs =null;
    	CitaTo citaTo = new CitaTo();
    	ConexionOracle conexionOracle = new ConexionOracle();
    	String sql = "SELECT ID_CITA,SALON,FECHA_SOLICITUD,FECHA_CITA,ID_PER_PRACTICANTE,ID_PER_PACIENTE FROM CPC_CITA WHERE ID_CITA = ? ";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);		
			ps.setInt(1, cita.getIdCita());
			rs = ps.executeQuery();
			
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
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conexionOracle.cerrar();
				this.connection.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
    	return citaTo;
    }
    
    public boolean crearCita(CitaTo cita){
    	
    	boolean retorno;
    	PreparedStatement ps=null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	String sql = "INSERT INTO CPC_CITA (ID_CITA,SALON,FECHA_SOLICITUD,FECHA_CITA,ID_PER_PRACTICANTE,ID_PER_PACIENTE) ";
    			sql+= "VALUES (CITA_SEQ.NEXTVAL,?,TO_DATE(SYSDATE,'DD/MM/RR'), TO_TIMESTAMP(?,'DD/MM/RR HH12:MI:SS AM'),?,?)";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, cita.getSalon());
			ps.setDate(2, (Date) cita.getFechaCita());
			ps.setInt(3, cita.getPracticante().getIdPersona());
			ps.setInt(4, cita.getPaciente().getIdPersona());
			
			ps.executeUpdate();
			retorno = Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
		}finally{
			try {
				conexionOracle.cerrar();
				this.connection.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
    	return retorno;
    }
    
    public boolean actualizarCita(CitaTo cita){
    	
    	boolean retorno;
    	PreparedStatement ps=null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	String sql = "UPDATE CPC_CITA SET SALON = ?, FECHA_SOLICITUD = TO_DATE(SYSDATE,'DD/MM/RR'),";
    	sql+="FECHA_CITA = TO_TIMESTAMP(?,'DD/MM/RR HH12:MI:SS AM'),ID_PER_PRACTICANTE = ?, ID_PER_PACIENTE = ? WHERE ID_CITA = ? ";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, cita.getSalon());
			ps.setDate(2, (Date) cita.getFechaCita());
			ps.setInt(3, cita.getPracticante().getIdPersona());
			ps.setInt(4, cita.getPaciente().getIdPersona());
			ps.setInt(5, cita.getIdCita());
			
			ps.executeUpdate();
			retorno = Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
		}finally{
			try {
				conexionOracle.cerrar();
				this.connection.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
    	return retorno;
    }
    
    public boolean eliminarCita(CitaTo cita){
    	
    	boolean retorno;
    	PreparedStatement ps=null;
    	ConexionOracle conexionOracle = new ConexionOracle();
    	String sql = "DELETE FROM CPC_CITA WHERE ID_CITA = ?";
    	 	
		try {
			conexionOracle.conectar();
	    	this.connection = conexionOracle.getConexionOracle();
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, cita.getIdCita());
			
			ps.executeUpdate();
			retorno = Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
			retorno = Boolean.FALSE;
		}finally{
			try {
				conexionOracle.cerrar();
				this.connection.close();
				ps.close();
			} catch (SQLException e) {
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
