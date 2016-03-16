package br.com.gedai.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.gedai.annotation.PositionExcel;
import br.com.gedai.utils.DateUtils;

public class TarefaPDFDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomeDemanda;
	private String nomeTarefa;
	private Date dataAtualizacao;
	private String nomeResp;
	private Integer contadorTarefa;
	
	public TarefaPDFDTO(){
		
	}
	
	@PositionExcel(posicao={0})
	public String getNomeDemanda() {
		return nomeDemanda;
	}
	
	@PositionExcel(posicao={1})
	public String getNomeTarefa() {
		return nomeTarefa;
	}
	
	@PositionExcel(posicao={2})
	public String getNomeResp() {
		return nomeResp;
	}
	
	@PositionExcel(posicao={3})
	public String getDataAtualizacao() {
		return DateUtils.getDataString(dataAtualizacao,"dd/MM/yyyy");
	}
	
	@PositionExcel(posicao={4})
	public Integer getContadorTarefa() {
		return contadorTarefa;
	}
	
	
	public void setNomeDemanda(String nomeDemanda) {
		this.nomeDemanda = nomeDemanda;
	}
	public void setNomeTarefa(String nomeTarefa) {
		this.nomeTarefa = nomeTarefa;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	public void setNomeResp(String nomeResp) {
		this.nomeResp = nomeResp;
	}
	public void setContadorTarefa(Integer contadorTarefa) {
		this.contadorTarefa = contadorTarefa;
	}
	
}
