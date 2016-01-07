package br.com.gedai.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.DemandaListaAtividade;
import br.com.gedai.mapper.DemandaListaAtividadeMapper;

@Service
public class DemandaListaAtividadeBO {

	@Autowired
	private DemandaListaAtividadeMapper demandaListaAtividadeMapper;
	
	public void insert(DemandaListaAtividade demandaListaAtividade) {
		demandaListaAtividadeMapper.insert(demandaListaAtividade);
	}

	public void update(DemandaListaAtividade demandaListaAtividade) {
		demandaListaAtividadeMapper.update(demandaListaAtividade);
	}

	public void delete(Integer idDemandaListaAtividade) {
		demandaListaAtividadeMapper.delete(idDemandaListaAtividade);
	}

	public List<DemandaListaAtividade> obterPorLista(Integer idDemandaLista) {
		return demandaListaAtividadeMapper.obterPorLista(idDemandaLista);
	}

}
