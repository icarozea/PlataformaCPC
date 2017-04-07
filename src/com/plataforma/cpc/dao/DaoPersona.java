package com.plataforma.cpc.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.modelo.UtilBean;
import com.plataforma.cpc.to.EpsTo;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.PersonaDetalleTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.TipoDocumentoTo;
import com.plataforma.cpc.to.UsuarioTo;
import com.plataforma.cpc.utils.ConexionOracle;

public class DaoPersona {
	
	public Conexion conexionActual;
    
    public ArrayList<PersonaTo> consultarPersonasFiltro(PersonaTo persona){
    	
    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	ArrayList<PersonaTo> personas = new ArrayList<PersonaTo>();
    	int numeroParametros = 0;
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,USUARIO_ID_USUARIO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL, OTRO_TEL, CODIGO, JORNADA FROM PERSONA WHERE 1 = 1 ";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			
			if(persona.getTipoDocumento().getIdTipoDocumento()!= null){
	 			sql+= "AND TIPO_DOCUMENTO_ID_DOCUMENTO = ? AND NUMERO_DOCUMENTO = ? ";
	 			numeroParametros++;
	 			conexionActual.agregarAtributo(numeroParametros, persona.getTipoDocumento().getIdTipoDocumento());
	 			numeroParametros++;
	 			conexionActual.agregarAtributo(numeroParametros, persona.getNumeroDocumento()); 			
	 		}
	 		if (persona.getPrimerNombre()!= null){
	 			sql+= "AND PRIMER_NOMBRE = ? ";
	 			numeroParametros++;
	 			conexionActual.agregarAtributo(numeroParametros, persona.getPrimerNombre()); 
	 		}
	 		
	 		if (persona.getPrimerApellido()!= null){
	 			sql+= "AND PRIMER_APELLIDO = ? ";
	 			numeroParametros++;
	 			conexionActual.agregarAtributo(numeroParametros, persona.getPrimerApellido()); 
	 		}
					
	 		conexionActual.agregarAtributo(1, persona.getPerfil().getIdPerfil()); 
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				PersonaTo personaTo = new PersonaTo();	
				UsuarioTo usuarioTo = new UsuarioTo();
				TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
				EpsTo epsTo = new EpsTo();
				PerfilTo perfilTo = new PerfilTo();
				
				personaTo.setIdPersona(rs.getInt("ID_PERSONA"));
				personaTo.setPrimerNombre(rs.getString("PRIMER_NOMBRE")); 
				personaTo.setSegundoNombre(rs.getString("SEGUNDO_NOMBRE"));
				personaTo.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
				personaTo.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
				personaTo.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
				personaTo.setDireccion(rs.getString("DIRECCION"));
				personaTo.setTelefono(rs.getLong("TELEFONO"));
				personaTo.setCorreo(rs.getString("CORREO"));
				
				usuarioTo.setIdUsuario(rs.getInt("USUARIO_ID_USUARIO"));
				personaTo.setUsuario(usuarioTo);
				tipoDocumentoTo.setIdTipoDocumento(rs.getInt("TIPO_DOCUMENTO_ID_DOCUMENTO"));
				personaTo.setTipoDocumento(tipoDocumentoTo);
				epsTo.setIdEPS(rs.getInt("EPS_ID_EPS"));
				personaTo.setEps(epsTo);
				perfilTo.setIdPerfil(rs.getInt("PERFIL_ID_PERFIL"));
				personaTo.setPerfil(perfilTo);
				personaTo.setOtroTelefono(rs.getLong("OTRO_TEL"));
				personaTo.setCodigoEstudiante(rs.getInt("CODIGO"));
				personaTo.setJornada(rs.getString("JORNADA"));
				personas.add(personaTo);

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
    	return personas;
    }

    public ArrayList<PersonaTo> consultarPersonasPerfil(PersonaTo persona){
    	
    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	ArrayList<PersonaTo> personas = new ArrayList<PersonaTo>();
    	
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL, PERSONA_ID_SUPERIOR, OTRO_TEL, CODIGO, JORNADA FROM PERSONA WHERE PERFIL_ID_PERFIL = ? ";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getPerfil().getIdPerfil()); 
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				PersonaTo personaTo = new PersonaTo();	
				//UsuarioTo usuarioTo = new UsuarioTo();
				TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
				EpsTo epsTo = new EpsTo();
				PerfilTo perfilTo = new PerfilTo();
				
				personaTo.setIdPersona(rs.getInt("ID_PERSONA"));
				personaTo.setPrimerNombre(rs.getString("PRIMER_NOMBRE")); 
				personaTo.setSegundoNombre(rs.getString("SEGUNDO_NOMBRE"));
				personaTo.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
				personaTo.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
				personaTo.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
				personaTo.setDireccion(rs.getString("DIRECCION"));
				personaTo.setTelefono(rs.getLong("TELEFONO"));
				personaTo.setCorreo(rs.getString("CORREO"));
				
				tipoDocumentoTo.setIdTipoDocumento(rs.getInt("TIPO_DOCUMENTO_ID_DOCUMENTO"));
				personaTo.setTipoDocumento(tipoDocumentoTo);
				epsTo.setIdEPS(rs.getInt("EPS_ID_EPS"));
				personaTo.setEps(epsTo);
				perfilTo.setIdPerfil(rs.getInt("PERFIL_ID_PERFIL"));
				personaTo.setPerfil(perfilTo);
				personaTo.setSuperior(rs.getInt("PERSONA_ID_SUPERIOR"));
				personaTo.setOtroTelefono(rs.getLong("OTRO_TEL"));
				personaTo.setCodigoEstudiante(rs.getInt("CODIGO"));
				personaTo.setJornada(rs.getString("JORNADA"));
				personas.add(personaTo);

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
    	return personas;
    }
    
    public ArrayList<PersonaTo> consultarAsignados(Integer idSuperior){
    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	ArrayList<PersonaTo> personas = new ArrayList<PersonaTo>();
    	
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL, PERSONA_ID_SUPERIOR, OTRO_TEL, CODIGO, JORNADA FROM PERSONA WHERE PERSONA_ID_SUPERIOR = ? ";
    	
    	try {
    		UtilBean util = new UtilBean();
        	ArrayList<TipoDocumentoTo> documentos = util.consultarTiposDocumento();
        	
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idSuperior); 
			rs = conexionActual.ejecutarSentencia();
			

			while (rs.next()){
				PersonaTo personaTo = new PersonaTo();	
				TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
				EpsTo epsTo = new EpsTo();
				PerfilTo perfilTo = new PerfilTo();
				
				personaTo.setIdPersona(rs.getInt("ID_PERSONA"));
				personaTo.setPrimerNombre(rs.getString("PRIMER_NOMBRE")); 
				personaTo.setSegundoNombre(rs.getString("SEGUNDO_NOMBRE"));
				personaTo.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
				personaTo.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
				personaTo.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
				personaTo.setDireccion(rs.getString("DIRECCION"));
				personaTo.setTelefono(rs.getLong("TELEFONO"));
				personaTo.setCorreo(rs.getString("CORREO"));
				
				for(int i=0; i < documentos.size(); i++){
					if(documentos.get(i).getIdTipoDocumento() == rs.getInt("TIPO_DOCUMENTO_ID_DOCUMENTO"))
						personaTo.setTipoDocumento(documentos.get(i));
				}
				
				epsTo.setIdEPS(rs.getInt("EPS_ID_EPS"));
				personaTo.setEps(epsTo);
				perfilTo.setIdPerfil(rs.getInt("PERFIL_ID_PERFIL"));
				personaTo.setPerfil(perfilTo);
				personaTo.setSuperior(rs.getInt("PERSONA_ID_SUPERIOR"));
				personaTo.setOtroTelefono(rs.getLong("OTRO_TEL"));
				personaTo.setCodigoEstudiante(rs.getInt("CODIGO"));
				personaTo.setJornada(rs.getString("JORNADA"));
				personas.add(personaTo);
			}
    	}
    	catch (Exception e) {
    		e.printStackTrace();
		}
    	finally{
    		try {
    			conexionActual.cerrar();
    			rs.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	return personas;
    }
    
    public PersonaTo consultarPersona (PersonaTo persona){

    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	PersonaTo personaTo = new PersonaTo();
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL, PASS, PERSONA_ID_SUPERIOR, OTRO_TEL, CODIGO, JORNADA FROM PERSONA WHERE ID_PERSONA = ?";
    	 	
		try {
			DaoUtilidades utils = new DaoUtilidades();
			
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getIdPersona()); 		
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
				EpsTo epsTo = new EpsTo();
				PerfilTo perfilTo = new PerfilTo();
				
				personaTo.setIdPersona(rs.getInt("ID_PERSONA"));
				personaTo.setPrimerNombre(rs.getString("PRIMER_NOMBRE")); 
				personaTo.setSegundoNombre(rs.getString("SEGUNDO_NOMBRE"));
				personaTo.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
				personaTo.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
				personaTo.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
				personaTo.setDireccion(rs.getString("DIRECCION"));
				personaTo.setTelefono(rs.getLong("TELEFONO"));
				personaTo.setCorreo(rs.getString("CORREO"));
				tipoDocumentoTo = utils.buscarTipoDocumento(rs.getInt("TIPO_DOCUMENTO_ID_DOCUMENTO"));
				personaTo.setTipoDocumento(tipoDocumentoTo);
				epsTo = utils.buscarEps((rs.getInt("EPS_ID_EPS")));
				personaTo.setEps(epsTo);
				perfilTo = utils.buscarPerfil((rs.getInt("PERFIL_ID_PERFIL")));
				personaTo.setPerfil(perfilTo);
				personaTo.setPassword(rs.getString("PASS"));
				personaTo.setSuperior(rs.getInt("PERSONA_ID_SUPERIOR"));
				personaTo.setOtroTelefono(rs.getLong("OTRO_TEL"));
				personaTo.setCodigoEstudiante(rs.getInt("CODIGO"));
				personaTo.setJornada(rs.getString("JORNADA"));
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
    	return personaTo;
    }
    
    public PersonaTo consultarPersonaUsuario (String numeroDocumento){

    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	PersonaTo personaTo = new PersonaTo();
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL, PERSONA_ID_SUPERIOR, OTRO_TEL, CODIGO, JORNADA, PASS FROM PERSONA WHERE NUMERO_DOCUMENTO = ?";
    	 	
		try {
			DaoUtilidades utils = new DaoUtilidades();
			
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, numeroDocumento);
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				TipoDocumentoTo tipoDocumentoTo = new TipoDocumentoTo();
				EpsTo epsTo = new EpsTo();
				PerfilTo perfilTo = new PerfilTo();
				
				personaTo.setIdPersona(rs.getInt("ID_PERSONA"));
				personaTo.setPrimerNombre(rs.getString("PRIMER_NOMBRE")); 
				personaTo.setSegundoNombre(rs.getString("SEGUNDO_NOMBRE"));
				personaTo.setPrimerApellido(rs.getString("PRIMER_APELLIDO"));
				personaTo.setSegundoApellido(rs.getString("SEGUNDO_APELLIDO"));
				personaTo.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
				personaTo.setDireccion(rs.getString("DIRECCION"));
				personaTo.setTelefono(rs.getLong("TELEFONO"));
				personaTo.setCorreo(rs.getString("CORREO"));
				tipoDocumentoTo = utils.buscarTipoDocumento(rs.getInt("TIPO_DOCUMENTO_ID_DOCUMENTO"));
				personaTo.setTipoDocumento(tipoDocumentoTo);
				epsTo = utils.buscarEps((rs.getInt("EPS_ID_EPS")));
				personaTo.setEps(epsTo);
				perfilTo = utils.buscarPerfil((rs.getInt("PERFIL_ID_PERFIL")));
				personaTo.setPerfil(perfilTo);
				personaTo.setSuperior(rs.getInt("PERSONA_ID_SUPERIOR"));
				personaTo.setOtroTelefono(rs.getLong("OTRO_TEL"));
				personaTo.setCodigoEstudiante(rs.getInt("CODIGO"));
				personaTo.setJornada(rs.getString("JORNADA"));
				personaTo.setPassword(rs.getString("PASS"));
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
    	return personaTo;
    }
    
    public int crearPersona(PersonaTo persona){
    	
    	int retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "INSERT INTO PERSONA (ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL,PERSONA_ID_SUPERIOR,PASS,OTRO_TEL,CODIGO,JORNADA)"
    				+ "VALUES (PERSONA_SEQ.NEXTVAL, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getPrimerNombre()); 	
			conexionActual.agregarAtributo(2, persona.getSegundoNombre()); 
			conexionActual.agregarAtributo(3, persona.getPrimerApellido()); 
			conexionActual.agregarAtributo(4, persona.getSegundoApellido()); 
			conexionActual.agregarAtributo(5, persona.getNumeroDocumento());
			conexionActual.agregarAtributo(6,persona.getDireccion());
			conexionActual.agregarAtributo(7, persona.getTelefono());
			conexionActual.agregarAtributo(8, persona.getCorreo());
			conexionActual.agregarAtributo(9, persona.getTipoDocumento().getIdTipoDocumento());
			conexionActual.agregarAtributo(10, persona.getEps().getIdEPS());
			conexionActual.agregarAtributo(11, persona.getPerfil().getIdPerfil());
			conexionActual.agregarAtributo(12, 0);
			conexionActual.agregarAtributo(13, persona.getPassword());
			conexionActual.agregarAtributo(14, persona.getOtroTelefono());
			conexionActual.agregarAtributo(15, persona.getCodigoEstudiante());
			conexionActual.agregarAtributo(16, persona.getJornada());
			
			conexionActual.ejecutarActualizacion();
			retorno = 1;
		} catch (Exception e) {
			e.printStackTrace();
			retorno = -1;
		}finally{
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
    	return retorno;
    }
    
    public int crearPersona2(PersonaTo persona){
    	
    	String generatedColumns[] = { "ID_PERSONA" };
    	int retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "INSERT INTO PERSONA (ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL,PERSONA_ID_SUPERIOR,PASS,OTRO_TEL,CODIGO,JORNADA)"
    				+ "VALUES (PERSONA_SEQ.NEXTVAL, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql,generatedColumns);
			conexionActual.agregarAtributo(1, persona.getPrimerNombre()); 	
			conexionActual.agregarAtributo(2, persona.getSegundoNombre()); 
			conexionActual.agregarAtributo(3, persona.getPrimerApellido()); 
			conexionActual.agregarAtributo(4, persona.getSegundoApellido()); 
			conexionActual.agregarAtributo(5, persona.getNumeroDocumento());
			conexionActual.agregarAtributo(6,persona.getDireccion());
			conexionActual.agregarAtributo(7, persona.getTelefono());
			conexionActual.agregarAtributo(8, persona.getCorreo());
			conexionActual.agregarAtributo(9, persona.getTipoDocumento().getIdTipoDocumento());
			conexionActual.agregarAtributo(10, persona.getEps().getIdEPS());
			conexionActual.agregarAtributo(11, persona.getPerfil().getIdPerfil());
			conexionActual.agregarAtributo(12, 0);
			conexionActual.agregarAtributo(13, persona.getPassword());
			conexionActual.agregarAtributo(14, persona.getOtroTelefono());
			conexionActual.agregarAtributo(15, persona.getCodigoEstudiante());
			conexionActual.agregarAtributo(16, persona.getJornada());
			
			conexionActual.ejecutarActualizacion();
			
			retorno = conexionActual.recuperarLlavePrimaria();
		} catch (Exception e) {
			e.printStackTrace();
			retorno = -1;
		}finally{
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
    	return retorno;
    }
    
    public boolean actualizarPersona(PersonaTo persona){
    	
    	boolean retorno =  Boolean.FALSE;
    	conexionActual = new ConexionOracle();
    	String sql = "UPDATE PERSONA SET PRIMER_NOMBRE = ?, SEGUNDO_NOMBRE = ?, PRIMER_APELLIDO = ?, SEGUNDO_APELLIDO = ?,NUMERO_DOCUMENTO = ?,DIRECCION = ?,TELEFONO = ?,CORREO = ?,TIPO_DOCUMENTO_ID_DOCUMENTO = ?, EPS_ID_EPS = ?, PERFIL_ID_PERFIL = ?, PERSONA_ID_SUPERIOR = ?, PASS = ?, OTRO_TEL = ?, CODIGO = ?, JORNADA = ? WHERE ID_PERSONA = ?";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getPrimerNombre());
			conexionActual.agregarAtributo(2, persona.getSegundoNombre());
			conexionActual.agregarAtributo(3, persona.getPrimerApellido());
			conexionActual.agregarAtributo(4, persona.getSegundoApellido());
			conexionActual.agregarAtributo(5, persona.getNumeroDocumento());
			conexionActual.agregarAtributo(6,persona.getDireccion());
			conexionActual.agregarAtributo(7, persona.getTelefono());
			conexionActual.agregarAtributo(8, persona.getCorreo());
			conexionActual.agregarAtributo(9, persona.getTipoDocumento().getIdTipoDocumento());
			conexionActual.agregarAtributo(10, persona.getEps().getIdEPS());
			conexionActual.agregarAtributo(11, persona.getPerfil().getIdPerfil());
			conexionActual.agregarAtributo(12, persona.getSuperior());		
			conexionActual.agregarAtributo(13, persona.getPassword());
			conexionActual.agregarAtributo(14, persona.getOtroTelefono());
			conexionActual.agregarAtributo(15, persona.getCodigoEstudiante());
			conexionActual.agregarAtributo(16, persona.getJornada());
			conexionActual.agregarAtributo(17, persona.getIdPersona());
			
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
				retorno = Boolean.FALSE;
				return retorno;
			}
		}	
    	return retorno;
    }
    
    public boolean eliminarPersona(PersonaTo persona){
    	
    	boolean retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "DELETE FROM PERSONA WHERE ID_PERSONA = ?";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getIdPersona());
			
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
    
    public boolean crearPersonaDetalle(PersonaDetalleTo persona){
    	
    	boolean retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "Insert into DETALLE_PERSONA (ID_PERSONA,SEXO,EDAD,ACUDIENTE,PROCESO,PERTENECE_U,FACULTAD,SEMESTRE,PROBLEMATICA,OBSERVACIONES,PERSONA_MODIFICA_DATOS)"
    				+ "values (?,?,?,?,?,?,?,?,?,?,?)";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getPersonaId());
			conexionActual.agregarAtributo(2, persona.getSexo()); 	
			conexionActual.agregarAtributo(3, persona.getEdad()); 
			conexionActual.agregarAtributo(4, persona.getAcudiente()); 
			conexionActual.agregarAtributo(5, persona.getProceso()); 
			conexionActual.agregarAtributo(6, persona.getPerteneceU());
			conexionActual.agregarAtributo(7,persona.getFacultad());
			conexionActual.agregarAtributo(8, persona.getSemestre());
			conexionActual.agregarAtributo(9, persona.getProblematica());
			conexionActual.agregarAtributo(10, persona.getObservación());
			conexionActual.agregarAtributo(11, persona.getNombreModifica());
			
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
    
    public boolean actualizarPersonaDetalle(PersonaDetalleTo persona){
    	
    	boolean retorno =  Boolean.FALSE;
    	conexionActual = new ConexionOracle();
    	String sql = "UPDATE DETALLE_PERSONA SET SEXO = ?, EDAD = ?, ACUDIENTE = ?, PROCESO = ?, PERTENECE_U = ?, FACULTAD = ?, SEMESTRE = ?, PROBLEMATICA = ?, OBSERVACIONES = ?, PERSONA_MODIFICA_DATOS = ? WHERE ID_PERSONA = ?";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getSexo()); 	
			conexionActual.agregarAtributo(2, persona.getEdad()); 
			conexionActual.agregarAtributo(3, persona.getAcudiente()); 
			conexionActual.agregarAtributo(4, persona.getProceso()); 
			conexionActual.agregarAtributo(5, persona.getPerteneceU());
			conexionActual.agregarAtributo(6, persona.getFacultad());
			conexionActual.agregarAtributo(7, persona.getSemestre());
			conexionActual.agregarAtributo(8, persona.getProblematica());
			conexionActual.agregarAtributo(9, persona.getObservación());
			conexionActual.agregarAtributo(10, persona.getNombreModifica());
			conexionActual.agregarAtributo(11, persona.getPersonaId());
			
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
				retorno = Boolean.FALSE;
				return retorno;
			}
		}	
    	return retorno;
    }
    
    public boolean eliminarPersonaDetalle(PersonaDetalleTo persona){
    	
    	boolean retorno;
    	conexionActual = new ConexionOracle();
    	String sql = "DELETE FROM DETALLE_PERSONA WHERE ID_PERSONA = ?";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getPersonaId());
			
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
    
    public PersonaDetalleTo consultarPersonaDetalle (PersonaDetalleTo persona){

    	ResultSet rs =null;
    	conexionActual = new ConexionOracle();
    	PersonaDetalleTo personaTo = new PersonaDetalleTo();
    	String sql = "SELECT ID_PERSONA,SEXO,EDAD,ACUDIENTE,PROCESO,PERTENECE_U,FACULTAD,SEMESTRE,PROBLEMATICA,OBSERVACIONES,PERSONA_MODIFICA_DATOS FROM DETALLE_PERSONA WHERE ID_PERSONA = ?";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getPersonaId()); 		
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){

				personaTo.setPersonaId(rs.getInt("ID_PERSONA"));
				personaTo.setSexo(rs.getString("SEXO")); 
				personaTo.setEdad(rs.getString("EDAD"));
				personaTo.setAcudiente(rs.getString("ACUDIENTE"));
				personaTo.setProceso(rs.getString("PROCESO"));
				personaTo.setPerteneceU(rs.getString("PERTENECE_U"));
				personaTo.setFacultad(rs.getString("FACULTAD"));
				personaTo.setSemestre(rs.getString("SEMESTRE"));
				personaTo.setProblematica(rs.getString("PROBLEMATICA"));
				personaTo.setObservación(rs.getString("OBSERVACIONES"));
				personaTo.setNombreModifica(rs.getString("PERSONA_MODIFICA_DATOS"));
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
    	return personaTo;
    }
}