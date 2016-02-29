package br.com.gedai.dto;

import java.io.Serializable;
import java.util.Date;

public class TarefaPDFDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Date dataInclusao;
	private Date dataAtualizacao;
	private String nomeUsuario;

	public TarefaPDFDTO(){
		
	}
	
	public String getNome() {
		return nome;
	}
	public Date getDataInclusao() {
		return dataInclusao;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
}
