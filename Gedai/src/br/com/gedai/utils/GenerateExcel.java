package br.com.gedai.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.gedai.annotation.PositionExcel;

public class GenerateExcel {
	
	static Logger log = Logger.getLogger(GenerateExcel.class);
	
	public static void gerarExcel(Workbook workbook, HttpServletResponse res, String nomeXLSSaida, Date dataPlanilha){
		log.info(StringUtils.concat("Gerando Excel: ", nomeXLSSaida));
		
		try {
			 
			if(dataPlanilha == null)
				nomeXLSSaida = StringUtils.concat(DateUtils.getDataHoraAtualString("yyyyMMdd"), " - ", nomeXLSSaida);
			else
				nomeXLSSaida = StringUtils.concat(DateUtils.getDataString(dataPlanilha, "yyyyMMdd"), " - ", nomeXLSSaida);
			
			res.setContentType("application/vnd.ms-excel");  
			res.setHeader("Content-disposition", String.format("attachment;filename=%s.xlsx", nomeXLSSaida));
			res.setHeader("Set-Cookie", "fileDownload=true; path=/");
			
			OutputStream ouputStream = res.getOutputStream();
			workbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
			workbook.close();
			
		} catch (IOException e) {
			log.info("----------Erro ao gerar Excel----------");
			e.printStackTrace();
		}

	}
	
	public static void gerarExcela(XSSFWorkbook workbook, String nomeXLSSaida, Date dataPlanilha) {
		try {
			
			if(dataPlanilha == null)
				nomeXLSSaida = StringUtils.concat(DateUtils.getDataHoraAtualString("yyyyMMdd"), " - ", nomeXLSSaida, ".xlsx");
			else
				nomeXLSSaida = StringUtils.concat(DateUtils.getDataString(dataPlanilha, "yyyyMMdd"), " - ", nomeXLSSaida, ".xlsx");
			
			FileOutputStream out = new FileOutputStream(nomeXLSSaida);
			workbook.write(out);
			out.flush();
			out.close();
			workbook.close();
		} catch (IOException e) {
			log.info("----------Erro ao gerar Excel----------");
			e.printStackTrace();
		}
	}
	
	/**
	 * Gera uma sheet com a lista de objetos enviada, todas em String
	 * @param workbook XSSFWorkbook
	 * @param name Nome da sheet a ser criada no Excel
	 * @param lista Lista de registros a ser colocada na sheet (Objetos mapeados com a @PositionExcel 
	 * @param titles Titulos que vão aparecer no Header
	 * 
	 * @see {@link com.altec.bsbr.app.mab.annotation.PositionExcel}
	 */
	public static void addSheet(Workbook workbook, String name, List<?> lista, String...titles){
		Sheet sheet = workbook.createSheet(name);
		int qtdCol = setHeader(sheet, titles);
		generateLines(sheet, lista, qtdCol);
	}
	
	/**
	 * Gera as linhas no excel
	 * @param workbook Excel
	 * @param sheet Sheet aonde será escrita as linhas
	 * @param lista Lista de objetos anotados
	 * @param qtdCol Deve ser maior que a ultima posição colocada no objeto na @PositionExcel
	 * @param lineBegin Linha que deve começar a gerar as rows
	 */
	public static void generateLines(Sheet sheet, List<?> lista, int qtdCol, int lineBegin){
		for(int i=0,len=lista.size(); i<len;i++){
			setLine(sheet, lineBegin++, getCamposExcel(lista.get(i), qtdCol));
		}
	}
	
	private static void generateLines(Sheet sheet, List<?> lista, int qtdCol){
		for(int i=0,rownum=1,len=lista.size(); i<len;i++){
			setLine(sheet, rownum++, getCamposExcel(lista.get(i), qtdCol));
		}
	}
	
	private static void setLine(Sheet sheet, int rownum, String[] values){
		Row row;
		row = sheet.createRow(rownum);
		for(int i=0, len=values.length; i<len;i++){
			row.createCell(i).setCellValue(values[i]);
		}
	}
	
	/**
	 * Gera o Header da sheet
	 * @param workbook
	 * @param sheet
	 * @param titles Titulos a serem colocados no Header
	 * @return Retorna a quantidade de colunas geradas
	 */
	private static int setHeader(Sheet sheet, String[] titles){
		Row headerRow = sheet.createRow(0);
		int len=titles.length;
        for (int i=0; i < len; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(titles[i]);
        }
		return len;
	}
	
	
	private static String[] getCamposExcel(Object object, int qtdCol){
		String [] arrString = new String [qtdCol];
		try {
			//Itero sobre os metodos do objeto enviado
			for(Method f: object.getClass().getMethods()){
				//Verifica se tem a annotation PositionExcel
				if(f.isAnnotationPresent(PositionExcel.class)){
					
					//Pega as posicoes que a informacao deve ficar
					PositionExcel ann = f.getAnnotation(PositionExcel.class);
					int[] posicoes = ann.posicao();
					
					//Armazena no array de String, os valores dos campos
					//Se for necessário alterar o tipo do dado a ser colocado na célula, alterar nesse ponto
					for(int i=0,len=posicoes.length; i<len; i++){
						
						if(posicoes[i] > qtdCol)
							throw new RuntimeException("Número da coluna acessada, difere do números de colunas no HEADER\nQtd Header:"+qtdCol+" - Posição acessada:"+posicoes[i]);
						String value = String.valueOf(f.invoke(object, new Object[0]));
						arrString[posicoes[i]] = ("null".equals(value) ? "" : value);
					}
				}
			}
			return arrString;
		} catch ( SecurityException |  IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
			return new String[0];
		}
		
	}
	
}
