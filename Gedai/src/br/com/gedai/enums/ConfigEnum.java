package br.com.gedai.enums;

public enum ConfigEnum {
	USUARIO_LOGADO("usuario"),
	CLASS_DEMANDA_ZEBRADA("demanda-zebrada");
	
	private String valor;

	private ConfigEnum(String valor){
		this.valor = valor;
	}
	
	public String getValor() { return valor; }
	public int getValorInt(){ return Integer.parseInt(valor); }
}
