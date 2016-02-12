package br.com.gedai.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.DemandaListaAtividade;
import br.com.gedai.data.Usuario;
import br.com.gedai.enums.TipoListaEnum;
import br.com.gedai.mapper.DemandaListaAtividadeMapper;
import br.com.gedai.utils.StringUtils;

@Service
public class DemandaListaAtividadeBO {

	@Autowired
	private DemandaListaAtividadeMapper demandaListaAtividadeMapper;
	
	@Autowired
	private DemandaListaAtividadeUsuarioBO demandaListaAtividadeUsuarioBO;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	public DemandaListaAtividade obterPorId(Integer idDemandaListaAtividade){
		DemandaListaAtividade atividade = demandaListaAtividadeMapper.obterPorId(idDemandaListaAtividade);
		atividade.setDescricao(StringUtils.quebraDeLinha(atividade.getDescricao()));
		atividade.setLstAtividadeUsuario(demandaListaAtividadeUsuarioBO.obterPorAtividade(atividade.getId()));
		return atividade;
	}
	
	public List<DemandaListaAtividade> insert(List<DemandaListaAtividade> atividades, HttpSession session) {
		List<String> lstUuid = new ArrayList<String>();
		Usuario usuarioLogado = usuarioBO.getUserSession(session);
		Date dataAtual = new Date();
		
		for(DemandaListaAtividade dla: atividades){
			dla.setUsuarioLogado(usuarioLogado);
			dla.setDataInclusao(dataAtual);
			demandaListaAtividadeMapper.insert(dla);
			lstUuid.add(dla.getUuid());
		}
		return demandaListaAtividadeMapper.obterPorUUID(lstUuid);
	}

	public void update(DemandaListaAtividade demandaListaAtividade) {
		StringUtils.emptyToNull(demandaListaAtividade);
		
		TipoListaEnum TIPO = TipoListaEnum.getEnum(demandaListaAtividade.getNomeDemandaLista());
		
		if(TIPO != null)
			switch (TIPO) {
			case FAZENDO:
				demandaListaAtividade.setDataInicio(new Date());
				break;
			case FEITO:
				demandaListaAtividade.setDataFinalizacao(new Date());
				break;
			case FAZER:
				break;
			default:
				break;
			}
		
		demandaListaAtividadeMapper.update(demandaListaAtividade);
	}

	public void delete(Integer idDemandaListaAtividade) {
		demandaListaAtividadeMapper.delete(idDemandaListaAtividade);
	}

	public List<DemandaListaAtividade> obterPorLista(Integer idDemandaLista) {
		return demandaListaAtividadeMapper.obterPorLista(idDemandaLista);
	}

}
