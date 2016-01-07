package br.com.gedai.utils;

import java.util.Set;

public class StringUtils {
	
	public static String emptyToNull(String value){
		return (value == null || "".equals(value) ? null : value.trim());
	}
	
	public static String nullToEmpty(String value){
		return (value == null  || "null".equals(value)? "" : value.trim());
	}

	public static boolean isEmpty(String value){
		return (null == value || "" == value.trim() || "".equals(value.trim()));
	}
	
	/**
	 * Coloca os simbolos de % antes e depois da String
	 * @param value
	 * @return
	 */
	public static String setLike(String value){
		return (value == null ? null : String.format("%s%s%s", "%", value, "%"));
	}
	
	/**
	 * Concatenador de Strings
	 * @param args
	 * @return
	 */
	public static String concat(Object ... args){
		StringBuilder s = new StringBuilder();
		for(int i=0, len=args.length; i < len ; i++){
			s.append(args[i]);
		}
		return s.toString();
	}
	
	/**
	 * Muda as virgulas(,) para barra (/) e retira o primeiro e o ultimo caracter ([])
	 * @param value
	 * @return
	 */
	public static String hashToString(Set<String> value){
		 String ret = value.toString().replace(", ", " / ");
		 return ret.substring(1, ret.length() - 1).trim();
	}
	
	/**
	 * Retorna um valor em String para um valor formatado com o % 
	 * @param value Número a ser convertido
	 * @param qtdCasas Quantidade de casas decimais a serem retornadas
	 * @return
	 */
	public static String toPercent(String value, int qtdCasas){
		if(isEmpty(value)) return null;
		return String.format("%."+qtdCasas+"f%s",(Double.parseDouble(value.replace(",", ".")) * 100), "%");
	}
	
	/**
	 * Converte uma valor String com a quantidade de casas enviada
	 * @param value
	 * @param qtdCasas
	 * @return
	 */
	public static String toNumber(String value, int qtdCasas){
		if(isEmpty(value)) return null;
		return String.format("%."+qtdCasas+"f",Double.parseDouble(value.replace(",", ".")));
	}
	
}
