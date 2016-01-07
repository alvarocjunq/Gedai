package br.com.gedai.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.DemandaListaAtividadeUsuario;
import br.com.gedai.mapper.DemandaListaAtividadeUsuarioMapper;

@Service
public class DemandaListaAtividadeUsuarioBO{

	@Autowired
	private DemandaListaAtividadeUsuarioMapper demandaListaAtividadeUsuarioMapper;
	
	public void insert(DemandaListaAtividadeUsuario demandaListaAtividadeUsuario) {
		demandaListaAtividadeUsuarioMapper.insert(demandaListaAtividadeUsuario);
	}

	public void delete(Integer idDemandaListaAtividadeUsuario) {
		demandaListaAtividadeUsuarioMapper.delete(idDemandaListaAtividadeUsuario);
	}

	public List<DemandaListaAtividadeUsuario> obterPorAtividade(Integer idDemandaListaAtividade) {
		return demandaListaAtividadeUsuarioMapper.obterPorAtividade(idDemandaListaAtividade);
	}

}
