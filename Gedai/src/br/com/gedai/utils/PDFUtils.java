package br.com.gedai.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.export.PdfExporterConfiguration;

public class PDFUtils{
	
	private static final String DIR_REPORT = "/br/com/gedai/report/";
	private static final String DIR_IMAGES_REPORT = "/br/com/gedai/report/images/";
	/**
	 * Gera um PDF no response
	 * 
	 * @param params Parametros do Relatorio
	 * @param lista Collection<?> a ser usada nos registros
	 * @param report Nome do Relatorio
	 * @param nomeReportSaida
	 * @param res HttpServletResponse
	 * 
	 * @see #gerarExcel
	 */
	public static void gerar(Map<String, Object> params, Collection<?> lista, String report, String nomeReportSaida, HttpServletResponse res){
		byte[] bytes = null;
		
//		StringUtils.nullToEmpty(params);
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(lista);

		InputStream arquivo = PDFUtils.class.getResourceAsStream(StringUtils.concat(DIR_REPORT, report ,".jasper"));

		params.put("SUBREPORT_DIR", DIR_REPORT);
		params.put("REPORT_LOCALE", new Locale("pt", "BR"));
		params.put(PdfExporterConfiguration.PROPERTY_PDF_JAVASCRIPT, "this.zoom = 50;");
		
		try {
			bytes = JasperRunManager.runReportToPdf(arquivo, params, dataSource);
			int len = bytes.length;
			
			res.setContentType("application/pdf");
		    res.setContentLength(len);
		    res.setHeader("Content-Disposition", String.format("inline:filename=%s.pdf", nomeReportSaida));
		    
			OutputStream ouputStream = res.getOutputStream();
			ouputStream.write(bytes, 0, len);
			ouputStream.flush();
			ouputStream.close();
			
		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setImageParam(Map<String, Object> params, String nameParam, String image) {
		ImageIcon imageIcon = new ImageIcon(PDFUtils.class.getResource(DIR_IMAGES_REPORT+image));
		params.put(nameParam, imageIcon.getImage());
	}
}
