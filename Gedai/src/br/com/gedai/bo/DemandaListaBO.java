package br.com.gedai.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.DemandaLista;
import br.com.gedai.enums.TipoListaEnum;
import br.com.gedai.mapper.DemandaListaMapper;

@Service
public class DemandaListaBO {
	
	@Autowired
	private DemandaListaMapper demandaListaMapper;
	
	@Autowired
	private DemandaListaAtividadeBO demandaListaAtividadeBO;

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
		List<DemandaLista> lista = demandaListaMapper.obterPorDemanda(idDemanda);
		
		for(DemandaLista dl: lista)
			dl.setLstAtividades(demandaListaAtividadeBO.obterPorLista(dl.getId()));
		
		return lista;
	}
	
	public Integer obterProgresso(Integer idDemanda){
		List<DemandaLista> lista = demandaListaMapper.obterQtdAtividadePorLista(idDemanda);
		double total = 0, feito = 0;
		
		for(DemandaLista dl: lista){
			TipoListaEnum tipo = TipoListaEnum.getEnum(dl.getId());
			switch (tipo) {
			case FAZER:
				total+= dl.getQtd();
				break;
			case FAZENDO:
				total+= dl.getQtd();
				break;
			case FEITO:
				feito += dl.getQtd();
				break;
			default:
				System.out.println("Tipo não cadastrado! DemandaLista: " + dl.getId());
				break;
			}
		}
		double d = ((feito / (total==0 ? 1: total)) * 100);
		return (int) d;
	}

}
