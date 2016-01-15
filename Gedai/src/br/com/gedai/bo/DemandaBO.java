package br.com.gedai.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.Demanda;
import br.com.gedai.mapper.DemandaMapper;

@Service
public class DemandaBO {

	@Autowired
	private DemandaMapper demandaMapper;
	
	@Autowired
	private DemandaListaBO demandaListaBO;
	
	public void insert(Demanda demanda) {
		demandaMapper.insert(demanda);
	}

	public void update(Demanda demanda) {
		demandaMapper.update(demanda);
	}

	public List<Demanda> obterTodos() {
		List<Demanda> lista = demandaMapper.obterTodos();
		for(Demanda d: lista){
			d.setProgresso(demandaListaBO.obterProgresso(d.getId()));
			d.setLstProgressoRacional(demandaListaBO.obterProgressoRacional(d.getId()));
		}
		return lista;
	}

}
