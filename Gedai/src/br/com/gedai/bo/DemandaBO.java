package br.com.gedai.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.Demanda;
import br.com.gedai.data.DemandaLista;
import br.com.gedai.data.TipoLista;
import br.com.gedai.enums.TipoListaEnum;
import br.com.gedai.mapper.DemandaMapper;

@Service
public class DemandaBO {

	@Autowired
	private DemandaMapper demandaMapper;
	
	@Autowired
	private DemandaListaBO demandaListaBO;
	
	public Demanda insert(Demanda demanda) {
		demandaMapper.insert(demanda);
		Demanda demandaReturn = obterPorUUID(demanda.getUuid());
		for(TipoListaEnum tp: TipoListaEnum.values()){
			this.demandaListaBO.insert(getDemandaLista(demandaReturn, tp.getNome()));
		}
		return demandaReturn;
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
	
	public Demanda obterPorUUID(String uuid){
		return demandaMapper.obterPorUUID(uuid);
	}
	
	private void setProgresso(List<Demanda> lista) {
		for(Demanda d: lista){
			d.setProgresso(demandaListaBO.obterProgresso(d.getId()));
			d.setLstProgressoRacional(demandaListaBO.obterProgressoRacional(d.getId()));
		}
	}
	
	private DemandaLista getDemandaLista(Demanda demanda, String tipo){
		DemandaLista demandaLista = new DemandaLista();
		TipoLista tipoLista = new TipoLista();
		demandaLista.setIdDemanda(demanda.getId());
		tipoLista.setId(1);
		demandaLista.setTipoLista(tipoLista);
		demandaLista.setNome(tipo);
		return demandaLista;
	}

}
