package br.com.gedai.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	private static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat FORMAT_DATE_HOUR = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	/**
	 * Retorna a data atual e a hora em String de acordo com o formato enviado
	 * @return Data formatada do tipo String
	 * @see #getDataString(Date)
	 */
	public static String getDataHoraAtualString() {
		return FORMAT_DATE_HOUR.format(new Date());
	}
	
	/**
	 * Retorna new Date atual, de acordo com o formato enviado
	 * @param formato
	 * @return
	 */
	public static String getDataHoraAtualString(String formato) {
		return new SimpleDateFormat(formato).format(new Date());
	}
	
	/**
	 * Converte um Date em String
	 * @param value Date
	 * @return String no formato dd/MM/yyyy
	 * @see #getDataHoraAtualString()
	 */
	public static String getDataString(Date value) {
		if(value == null) return "";
		
		return FORMAT_DATE.format(value);
	}
	
	/**
	 * Converte a data enviada para o formato enviado em String
	 * @param value
	 * @param formato
	 * @return
	 */
	public static String getDataString(Date value, String formato) {
		if(value == null) return "";
		return new SimpleDateFormat(formato).format(value);
	}
	
	/**
	 * Muda a hora da data para a ultima hora disponivel no dia 23:59:59
	 * @param dataDeCorte
	 */
	public static Date setFinalHour(Date dataDeCorte) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataDeCorte);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	
	public static Date getDateNull(Date data) {
		if(data == null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.YEAR, 1900);
			return calendar.getTime();
		}
		return data;
	}


	/**
	 * Seta a data enviada para o dia 1 com o mesmo mes e ano
	 * @param data
	 * @return
	 */
	public static Date getFirstDay(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
}
