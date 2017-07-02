package com.plataforma.cpc.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.plataforma.cpc.interfaces.Conexion;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class Reporte {
		
	/**
	 * Variable donde se encuentran el directorio de los reportes
	 */
	private String directorio;

    /**
     * Default constructor. 
     */
	public Reporte(String directorio) {
		//this.directorio = directorio;
		this.directorio = "C:/reportes";
    } 
				
	/**
	 * Metodo que permite tomar la conexion a utilizar en jasper
	 * @return
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	private Connection tomarConexion() throws Exception{
		Conexion conexion = new ConexionOracle();
		conexion.conectar();
		return conexion.getConexion();
	}

	
	/**
	 * Metodo que devuelve un reporte exportado a PDF
	 * @param nombreReporte
	 * @param parametros
	 * @return
	 * @throws Exception
	 */
	public byte[] reporte(String nombreReporte, Map<String, Object> parametros) throws Exception{
		return doJasper2ByteAll(nombreReporte, parametros, tomarConexion());
	}
	

	private byte[] doJasper2ByteAll(String jasperFileName, Map<String, Object> parametros, Object conexion) throws Exception{
		// se inicializa con nulo el jasper print
		JasperPrint jprint = null;
		
		// concatenamos el nombre del archivo con el directorio y la extensión
		String nombreArchivo = directorio + File.separator + jasperFileName + ".jasper";
		
		jprint = JasperFillManager.fillReport(nombreArchivo, parametros, (Connection)conexion);
		
		byte report[] = export(jprint,"XLSX");
		return report;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public byte[] export(final JasperPrint print, String formato) throws JRException {
	    final Exporter exporter;
	    final ByteArrayOutputStream out = new ByteArrayOutputStream();
	    boolean html = false;

	    switch (formato) {
	        case "HTML":
	            exporter = new HtmlExporter();
	            exporter.setExporterOutput(new SimpleHtmlExporterOutput(out));
	            html = true;
	            break;

	        case "CSV":
	            exporter = new JRCsvExporter();
	            break;

	        case "XML":
	            exporter = new JRXmlExporter();
	            break;

	        case "XLSX":
	            exporter = new JRXlsxExporter();
	            break;

	        case "PDF":
	            exporter = new JRPdfExporter();
	            break;

	        default:
	            throw new JRException("Unknown report format: " + formato);
	    }

	    if (!html) {
	        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
	    }

	    exporter.setExporterInput(new SimpleExporterInput(print));
	    exporter.exportReport();

	    return out.toByteArray();
	}
}
