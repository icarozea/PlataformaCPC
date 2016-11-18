package com.plataforma.cpc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author HARLIN
 */
public class ConexionOracle {

    private final String USUARIO = "CPCDB";
    private final String PASSWORD = "1234";
    private final String SID = "xe";
    private final String HOST = "52.45.218.235";
    private final String PUERTO = "1521";
    private Connection connection;

    public Connection getConexionOracle() {
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

    public void cerrar() throws SQLException {
        if (connection != null && connection.isClosed() == false) {
            connection.close();
        }
    }
    
    public static void main(String args[]) {
        ConexionOracle conexionOracle = new ConexionOracle();
        try {
            conexionOracle.conectar();
            Connection conn = conexionOracle.getConexionOracle();
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
