package br.com.gedai.data;

import java.io.Serializable;

public class DemandaTarefaUsuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Usuario usuario;
	private Integer idTarefa;
	
	public DemandaTarefaUsuario(){}
	
	public DemandaTarefaUsuario(DemandaTarefa tarefa){
		this.usuario = tarefa.getUsuarioInclusao();
		this.idTarefa = tarefa.getId();
	}
	public Long getId() {
		return id;
	}
	public Integer getIdTarefa() {
		return idTarefa;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setIdTarefa(Integer idTarefa) {
		this.idTarefa = idTarefa;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
