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
		setProgresso(lista);
		return lista;
	}
	
	public List<Demanda> obterPorArea(Integer idArea) {
		List<Demanda> lista = demandaMapper.obterPorArea(idArea);
		setProgresso(lista);
		return lista;
	}

	public Demanda obterPorId(Integer id){
		return demandaMapper.obterPorId(id);
	}
	
	private void setProgresso(List<Demanda> lista) {
		for(Demanda d: lista){
			d.setProgresso(demandaListaBO.obterProgresso(d.getId()));
			d.setLstProgressoRacional(demandaListaBO.obterProgressoRacional(d.getId()));
		}
	}

}
