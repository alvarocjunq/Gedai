package br.com.gedai.data;

import java.io.Serializable;
import java.util.Date;

public class DemandaListaAtividadeTempo  implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idAtividade;
	private Date horaInicio;
	private Date horaFim;
	private Integer idUsuario;

	public DemandaListaAtividadeTempo(Integer idAtividade, Usuario usuario) {
		this.idAtividade = idAtividade;
		this.idUsuario = usuario.getId();
	}
	public Integer getId() {
		return id;
	}
	public Integer getIdAtividade() {
		return idAtividade;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public Date getHoraFim() {
		return horaFim;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setIdAtividade(Integer idAtividade) {
		this.idAtividade = idAtividade;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
