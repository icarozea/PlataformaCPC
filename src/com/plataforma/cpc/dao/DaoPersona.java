package com.plataforma.cpc.dao;


import java.sql.ResultSet;
import java.util.ArrayList;
import org.jasypt.util.password.StrongPasswordEncryptor;
import com.plataforma.cpc.interfaces.Conexion;
import com.plataforma.cpc.modelo.UtilBean;
import com.plataforma.cpc.to.EpsTo;
import com.plataforma.cpc.to.HistoriaClinicaTo;
import com.plataforma.cpc.to.PerfilTo;
import com.plataforma.cpc.to.PersonaDetalleTo;
import com.plataforma.cpc.to.PersonaTo;
import com.plataforma.cpc.to.TipoDocumentoTo;
import com.plataforma.cpc.to.UsuarioTo;
import com.plataforma.cpc.utils.ConexionOracle;
import com.plataforma.cpc.utils.TextAdmin;

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
				
				String rawText = rs.getString("PRIMER_NOMBRE");
				personaTo.setPrimerNombre(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("SEGUNDO_NOMBRE");
				personaTo.setSegundoNombre(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("PRIMER_APELLIDO");
				personaTo.setPrimerApellido(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("SEGUNDO_APELLIDO");
				personaTo.setSegundoApellido(TextAdmin.parseUTF(rawText));
				
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
				
				String rawText = rs.getString("PRIMER_NOMBRE");
				personaTo.setPrimerNombre(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("SEGUNDO_NOMBRE");
				personaTo.setSegundoNombre(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("PRIMER_APELLIDO");
				personaTo.setPrimerApellido(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("SEGUNDO_APELLIDO");
				personaTo.setSegundoApellido(TextAdmin.parseUTF(rawText));
				
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
				EpsTo epsTo = new EpsTo();
				PerfilTo perfilTo = new PerfilTo();
				
				personaTo.setIdPersona(rs.getInt("ID_PERSONA"));
				
				String rawText = rs.getString("PRIMER_NOMBRE");
				personaTo.setPrimerNombre(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("SEGUNDO_NOMBRE");
				personaTo.setSegundoNombre(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("PRIMER_APELLIDO");
				personaTo.setPrimerApellido(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("SEGUNDO_APELLIDO");
				personaTo.setSegundoApellido(TextAdmin.parseUTF(rawText));
				
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
    	String sql = "SELECT ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL, PERSONA_ID_SUPERIOR, OTRO_TEL, CODIGO, JORNADA FROM PERSONA WHERE ID_PERSONA = ?";
    	 	
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
				
				String rawText = rs.getString("PRIMER_NOMBRE");
				personaTo.setPrimerNombre(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("SEGUNDO_NOMBRE");
				personaTo.setSegundoNombre(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("PRIMER_APELLIDO");
				personaTo.setPrimerApellido(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("SEGUNDO_APELLIDO");
				personaTo.setSegundoApellido(TextAdmin.parseUTF(rawText));

				personaTo.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
				personaTo.setDireccion(rs.getString("DIRECCION"));
				personaTo.setTelefono(rs.getLong("TELEFONO"));
				personaTo.setCorreo(rs.getString("CORREO"));
				perfilTo = utils.buscarPerfil((rs.getInt("PERFIL_ID_PERFIL")));
				personaTo.setPerfil(perfilTo);
				personaTo.setSuperior(rs.getInt("PERSONA_ID_SUPERIOR"));
				personaTo.setOtroTelefono(rs.getLong("OTRO_TEL"));
				personaTo.setCodigoEstudiante(rs.getInt("CODIGO"));
				personaTo.setJornada(rs.getString("JORNADA"));
				tipoDocumentoTo = utils.buscarTipoDocumento(rs.getInt("TIPO_DOCUMENTO_ID_DOCUMENTO"));
				personaTo.setTipoDocumento(tipoDocumentoTo);
				epsTo = utils.buscarEps((rs.getInt("EPS_ID_EPS")));
				personaTo.setEps(epsTo);
				personaTo.setPassword("dummytext");
				
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
				
				String rawText = rs.getString("PRIMER_NOMBRE");
				personaTo.setPrimerNombre(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("SEGUNDO_NOMBRE");
				personaTo.setSegundoNombre(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("PRIMER_APELLIDO");
				personaTo.setPrimerApellido(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("SEGUNDO_APELLIDO");
				personaTo.setSegundoApellido(TextAdmin.parseUTF(rawText));
				
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
    	String sql = "INSERT INTO PERSONA (ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL,PASS,OTRO_TEL,CODIGO,JORNADA)"
    				+ "VALUES (PERSONA_SEQ.NEXTVAL, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	 	
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
			
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			String encryptedPassword = passwordEncryptor.encryptPassword(persona.getPassword());
			conexionActual.agregarAtributo(12, encryptedPassword);
			
			conexionActual.agregarAtributo(13, persona.getOtroTelefono());
			conexionActual.agregarAtributo(14, persona.getCodigoEstudiante());
			conexionActual.agregarAtributo(15, persona.getJornada());
			
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
    	String sql = "INSERT INTO PERSONA (ID_PERSONA,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,NUMERO_DOCUMENTO,DIRECCION,TELEFONO,CORREO,TIPO_DOCUMENTO_ID_DOCUMENTO, EPS_ID_EPS,PERFIL_ID_PERFIL,PASS,OTRO_TEL,CODIGO,JORNADA)"
    				+ "VALUES (PERSONA_SEQ.NEXTVAL, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	 	
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
			
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			String encryptedPassword = passwordEncryptor.encryptPassword(persona.getPassword());
			conexionActual.agregarAtributo(12, encryptedPassword);

			conexionActual.agregarAtributo(13, persona.getOtroTelefono());
			conexionActual.agregarAtributo(14, persona.getCodigoEstudiante());
			conexionActual.agregarAtributo(15, persona.getJornada());
			
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
    	boolean passFlag = false;
    	String sql = "UPDATE PERSONA SET PRIMER_NOMBRE = ?, SEGUNDO_NOMBRE = ?, PRIMER_APELLIDO = ?, SEGUNDO_APELLIDO = ?,NUMERO_DOCUMENTO = ?,DIRECCION = ?,TELEFONO = ?,CORREO = ?,TIPO_DOCUMENTO_ID_DOCUMENTO = ?, EPS_ID_EPS = ?, PERFIL_ID_PERFIL = ?, PERSONA_ID_SUPERIOR = ?, OTRO_TEL = ?, CODIGO = ?, JORNADA = ?";
    	
    	if(!(persona.getPassword() == null || persona.getPassword().equals("") || persona.getPassword().equals("dummytext"))) {
    		sql += ", PASS = ?";
    		passFlag = true;
    	}
    	
    	sql += " WHERE ID_PERSONA = ?";
    	
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
			conexionActual.agregarAtributo(13, persona.getOtroTelefono());
			conexionActual.agregarAtributo(14, persona.getCodigoEstudiante());
			conexionActual.agregarAtributo(15, persona.getJornada());
			
			if(passFlag) {
				StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
				String encryptedPassword = passwordEncryptor.encryptPassword(persona.getPassword());
				conexionActual.agregarAtributo(16, encryptedPassword);
				conexionActual.agregarAtributo(17, persona.getIdPersona());
			}
			else
				conexionActual.agregarAtributo(16, persona.getIdPersona());
			
			
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
    	String sql = "UPDATE DETALLE_PERSONA SET SEXO = ?, EDAD = ?, ACUDIENTE = ?, PROCESO = ?, PERTENECE_U = ?, FACULTAD = ?, SEMESTRE = ?, PROBLEMATICA = ?, OBSERVACIONES = ?, PERSONA_MODIFICA_DATOS = ?, ";
    	sql += "ESTADO_CIVIL = ?, FECHA_NACIMIENTO = ?, LUGAR_NACIMIENTO = ?, ESCOLARIDAD = ?, OCUPACION = ?, LOCALIDAD = ?, BARRIO = ?, ESTRATO = ?, PERSONA_EMERGENCIA = ?, TELEFONO_EMERGENCIA = ?, PARENTESCO_EMERGENCIA = ?, ";
    	sql += "FORMATO_SOLICITUD = ?, INSTITUCION_REMISION = ?, PARENTESCO_ACUDIENTE = ?, TELEFONO_ACUDIENTE = ?, PERSONAS_RESIDE = ? WHERE ID_PERSONA = ?";
    	 	
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
			conexionActual.agregarAtributo(11, persona.getEstadoCivil());
			conexionActual.agregarAtributo(12, persona.getFechaNacimiento());
			conexionActual.agregarAtributo(13, persona.getLugarNacimiento());
			conexionActual.agregarAtributo(14, persona.getEscolaridad());
			conexionActual.agregarAtributo(15, persona.getOcupacion());
			conexionActual.agregarAtributo(16, persona.getLocalidad());
			conexionActual.agregarAtributo(17, persona.getBarrio());
			conexionActual.agregarAtributo(18, persona.getEstrato());
			conexionActual.agregarAtributo(19, persona.getPersonaEmergencia());
			conexionActual.agregarAtributo(20, persona.getTelefonoEmergencia());
			conexionActual.agregarAtributo(21, persona.getParentescoEmergencia());
			conexionActual.agregarAtributo(22, persona.getFormatoSolicitud());
			conexionActual.agregarAtributo(23, persona.getInstitucionRemision());
			conexionActual.agregarAtributo(24, persona.getParentescoAcudiente());
			conexionActual.agregarAtributo(25, persona.getTelefonoAcudiente());
			conexionActual.agregarAtributo(26, persona.getPersonasReside());
			conexionActual.agregarAtributo(27, persona.getPersonaId());
			
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
    	String sql = "SELECT ID_PERSONA,SEXO,EDAD,ACUDIENTE,PROCESO,PERTENECE_U,FACULTAD,SEMESTRE,PROBLEMATICA,OBSERVACIONES,PERSONA_MODIFICA_DATOS, ";
    	sql += "ESTADO_CIVIL, FECHA_NACIMIENTO, LUGAR_NACIMIENTO, ESCOLARIDAD, OCUPACION, LOCALIDAD, BARRIO, ESTRATO, PERSONA_EMERGENCIA, TELEFONO_EMERGENCIA, PARENTESCO_EMERGENCIA, ";
    	sql+= "FORMATO_SOLICITUD, INSTITUCION_REMISION, PARENTESCO_ACUDIENTE, TELEFONO_ACUDIENTE, PERSONAS_RESIDE FROM DETALLE_PERSONA WHERE ID_PERSONA = ?";
    	 	
		try {
			conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, persona.getPersonaId()); 		
			rs = conexionActual.ejecutarSentencia();
			
			while (rs.next()){
				personaTo.setPersonaId(rs.getInt("ID_PERSONA"));
				personaTo.setSexo(rs.getString("SEXO")); 
				personaTo.setEdad(rs.getString("EDAD"));
				String rawText = rs.getString("ACUDIENTE");
				personaTo.setAcudiente(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("PROCESO");
				personaTo.setProceso(TextAdmin.parseUTF(rawText));
				personaTo.setPerteneceU(rs.getString("PERTENECE_U"));
				rawText = rs.getString("FACULTAD");
				personaTo.setFacultad(TextAdmin.parseUTF(rawText));
				personaTo.setSemestre(rs.getString("SEMESTRE"));
				rawText = rs.getString("PROBLEMATICA");
				personaTo.setProblematica(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("OBSERVACIONES");
				personaTo.setObservación(TextAdmin.parseUTF(rawText));
				personaTo.setNombreModifica(rs.getString("PERSONA_MODIFICA_DATOS"));
				personaTo.setEstadoCivil(rs.getString("ESTADO_CIVIL"));
				personaTo.setFechaNacimiento(rs.getString("FECHA_NACIMIENTO"));
				rawText = rs.getString("LUGAR_NACIMIENTO");
				personaTo.setLugarNacimiento(TextAdmin.parseUTF(rawText));
				personaTo.setEscolaridad(rs.getString("ESCOLARIDAD"));
				rawText = rs.getString("OCUPACION");
				personaTo.setOcupacion(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("LOCALIDAD");
				personaTo.setLocalidad(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("BARRIO");
				personaTo.setBarrio(TextAdmin.parseUTF(rawText));
				personaTo.setEstrato(rs.getString("ESTRATO"));
				rawText = rs.getString("PERSONA_EMERGENCIA");
				personaTo.setPersonaEmergencia(TextAdmin.parseUTF(rawText));
				personaTo.setTelefonoEmergencia(rs.getString("TELEFONO_EMERGENCIA"));
				rawText = rs.getString("PARENTESCO_EMERGENCIA");
				personaTo.setParentescoEmergencia(TextAdmin.parseUTF(rawText));
				personaTo.setFormatoSolicitud(rs.getString("FORMATO_SOLICITUD"));
				rawText = rs.getString("INSTITUCION_REMISION");
				personaTo.setInstitucionRemision(TextAdmin.parseUTF(rawText));
				rawText = rs.getString("PARENTESCO_ACUDIENTE");
				personaTo.setParentescoAcudiente(TextAdmin.parseUTF(rawText));
				personaTo.setTelefonoAcudiente(rs.getString("TELEFONO_ACUDIENTE"));
				rawText = rs.getString("PERSONAS_RESIDE");
				personaTo.setPersonasReside(TextAdmin.parseUTF(rawText));
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
    
    public boolean CrearHistoriaClinica(HistoriaClinicaTo nuevaHistoria){
    	boolean retorno;
    	
    	conexionActual = new ConexionOracle();
    	
    	String sql = "INSERT INTO HISTORIA_CLINICA (ID_HISTORIA, ID_PACIENTE, CODIGO) ";
    		sql+= "VALUES (HISTORIA_SEQ.NEXTVAL, ?, ?)";
    	
    	try{
    		conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, nuevaHistoria.getIdPaciente());
			conexionActual.agregarAtributo(2, nuevaHistoria.getCodigo());
			
			conexionActual.ejecutarActualizacion();
			retorno = Boolean.TRUE;
    	}
    	catch (Exception e) {
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
    
    public HistoriaClinicaTo consultarHistoriaClinica(int idPaciente){
    	ResultSet rs =null;
    	HistoriaClinicaTo historiaTo = new HistoriaClinicaTo();
    	conexionActual = new ConexionOracle();
    	String sql = "SELECT ID_HISTORIA, CODIGO FROM HISTORIA_CLINICA WHERE ID_PACIENTE = ?";
    	
    	try{
    		conexionActual.conectar();
			conexionActual.prepararSentencia(sql);
			conexionActual.agregarAtributo(1, idPaciente);
			rs = conexionActual.ejecutarSentencia();
			
			while(rs.next()){
				
				historiaTo.setIdHistoria(rs.getInt("ID_HISTORIA"));
				historiaTo.setCodigo(rs.getString("CODIGO"));
				historiaTo.setIdPaciente(idPaciente);
			}
			
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conexionActual.cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	
    	return historiaTo;
    }
}