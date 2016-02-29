package br.com.gedai.data;

import java.io.Serializable;
import java.util.Date;

public class DemandaTarefaDiaria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer idTarefa;
	private Usuario usuario;
	private Date dataAtualizacao;
	private Integer contador;
	private String tipoOperacao;
	
	public DemandaTarefaDiaria(){
	}

	public Long getId() {
		return id;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public Integer getContador() {
		return contador;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(Integer idTarefa) {
		this.idTarefa = idTarefa;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	
}
