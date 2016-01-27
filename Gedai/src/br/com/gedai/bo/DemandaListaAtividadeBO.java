package br.com.gedai.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.DemandaListaAtividade;
import br.com.gedai.mapper.DemandaListaAtividadeMapper;
import br.com.gedai.utils.StringUtils;

@Service
public class DemandaListaAtividadeBO {

	@Autowired
	private DemandaListaAtividadeMapper demandaListaAtividadeMapper;
	
	@Autowired
	private DemandaListaAtividadeUsuarioBO demandaListaAtividadeUsuarioBO;
	
	public DemandaListaAtividade obterPorId(Integer idDemandaListaAtividade){
		DemandaListaAtividade atividade = demandaListaAtividadeMapper.obterPorId(idDemandaListaAtividade);
		atividade.setDescricao(StringUtils.quebraDeLinha(atividade.getDescricao()));
		atividade.setLstAtividadeUsuario(demandaListaAtividadeUsuarioBO.obterPorAtividade(atividade.getId()));
		return atividade;
	}
	
	public List<DemandaListaAtividade> insert(List<DemandaListaAtividade> atividades) {
		List<String> lstUuid = new ArrayList<String>();
		for(DemandaListaAtividade dla: atividades){
			demandaListaAtividadeMapper.insert(dla);
			lstUuid.add(dla.getUuid());
		}
		return demandaListaAtividadeMapper.obterPorUUID(lstUuid);
	}

	public void update(DemandaListaAtividade demandaListaAtividade) {
		StringUtils.emptyToNull(demandaListaAtividade);
		demandaListaAtividadeMapper.update(demandaListaAtividade);
	}

	public void delete(Integer idDemandaListaAtividade) {
		demandaListaAtividadeMapper.delete(idDemandaListaAtividade);
	}

	public List<DemandaListaAtividade> obterPorLista(Integer idDemandaLista) {
		return demandaListaAtividadeMapper.obterPorLista(idDemandaLista);
	}

}
