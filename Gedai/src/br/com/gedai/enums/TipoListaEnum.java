package br.com.gedai.enums;

public enum TipoListaEnum {
	FAZER(1),FAZENDO(2),FEITO(3);
	
	private Integer id;

	private TipoListaEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	public static TipoListaEnum getEnum(Integer id){
		for(TipoListaEnum tipo: TipoListaEnum.values()){
			if(id == tipo.getId()){
				return tipo;
			}
		}
		return null;
	}
	
}
