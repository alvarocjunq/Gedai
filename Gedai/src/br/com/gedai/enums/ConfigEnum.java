package br.com.gedai.enums;

public enum ConfigEnum {
	USUARIO_LOGADO("usuario");
	
	private String valor;

	private ConfigEnum(String valor){
		this.valor = valor;
	}
	
	public String getValor() { return valor; }
	public int getValorInt(){ return Integer.parseInt(valor); }
}
