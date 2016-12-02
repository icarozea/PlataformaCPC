package com.plataforma.cpc.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.plataforma.cpc.interfaces.Conexion;

import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author HARLIN
 */
public class ConexionOracle implements Conexion {

    private final String USUARIO = "CPCDB";
    private final String PASSWORD = "1234";
    private final String SID = "xe";
    private final String HOST = "52.45.218.235";
    private final String PUERTO = "1521";
    private Connection connection;
    private PreparedStatement sentenciaActual;

    public Connection getConexion() {
        return connection;
    }

    public void registrarDriver() throws SQLException {
        OracleDriver oracleDriver = new oracle.jdbc.driver.OracleDriver();
        DriverManager.registerDriver(oracleDriver);
    }

    public void conectar() throws SQLException {
        if (connection == null || connection.isClosed() == true) {
            String cadenaCoenexion = "jdbc:oracle:thin:@"
                    + HOST + ":"
                    + PUERTO + ":"
                    + SID;

            registrarDriver();
            connection = DriverManager.getConnection(cadenaCoenexion, USUARIO, PASSWORD);

        }
    }
    
	public void prepararSentencia(String sentencia) throws Exception {
		sentenciaActual = this.connection.prepareStatement(sentencia);
	}
	
	public void agregarAtributo(int numAtributo, String atributo) throws Exception {
		sentenciaActual.setString(numAtributo, atributo);
	}
	
	public void agregarAtributo(int numAtributo, int atributo) throws Exception {
		sentenciaActual.setInt(numAtributo, atributo);
	}
	
	public void agregarAtributo(int numAtributo, Timestamp atributo) throws Exception{
		sentenciaActual.setTimestamp(numAtributo, atributo);
	}
	
	public void agregarAtributo(int numAtributo, Date atributo) throws Exception{
		sentenciaActual.setDate(numAtributo, atributo);
	}
	
	@Override
	public void agregarAtributo(int numAtributo, Long atributo) throws SQLException {
		sentenciaActual.setLong(numAtributo, atributo);	
	}

	public ResultSet ejecutarSentencia() throws Exception {
		if(sentenciaActual != null){
			return sentenciaActual.executeQuery();
		}
		else
			throw new Exception("No se ha preparado previamente una sentencia SQL");
	}
	
	public void ejecutarActualizacion() throws Exception {
		if(sentenciaActual != null){
			sentenciaActual.executeUpdate();
		}
		else
			throw new Exception("No se ha preparado previamente una sentencia SQL");
	}

    public void cerrar() throws SQLException {
        if (connection != null && connection.isClosed() == false) {
        	sentenciaActual.close();
        	sentenciaActual = null;
        	connection.close();
        }
    }
    
    public static void main(String args[]) {
        ConexionOracle conexionOracle = new ConexionOracle();
        try {
            conexionOracle.conectar();
            Connection conn = conexionOracle.getConexion();
            // driver@machineName:port:SID           ,  userid,  password
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select BANNER from SYS.V_$VERSION");
            while (rset.next()) {
                System.out.println(rset.getString(1));   // Print col 1
            }
            stmt.close();
            conexionOracle.cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}