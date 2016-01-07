package br.com.gedai.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.DemandaLista;
import br.com.gedai.mapper.DemandaListaMapper;

@Service
public class DemandaListaBO {
	
	@Autowired
	private DemandaListaMapper demandaListaMapper;

	public void insert(DemandaLista demandaLista) {
		demandaListaMapper.insert(demandaLista);
	}

	public void update(DemandaLista demandaLista) {
		demandaListaMapper.update(demandaLista);
	}

	public void delete(Integer idDemandaLista) {
		demandaListaMapper.delete(idDemandaLista);
	}

	public List<DemandaLista> obterPorDemanda(Integer idDemanda) {
		return demandaListaMapper.obterPorDemanda(idDemanda);
	}

}
