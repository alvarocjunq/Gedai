package br.com.gedai.enums;

public enum TipoListaEnum {
	FAZER("Fazer"),FAZENDO("Fazendo"),FEITO("Feito");
	
	private String nome;

	private TipoListaEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public static TipoListaEnum getEnum(String nome){
		for(TipoListaEnum tipo: TipoListaEnum.values()){
			if(tipo.getNome().equals(nome)){
				return tipo;
			}
		}
		return null;
	}
	
}
