package br.com.gedai.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.DemandaListaAtividade;
import br.com.gedai.data.DemandaListaAtividadeUsuario;
import br.com.gedai.data.Usuario;
import br.com.gedai.dto.AtividadePDFDTO;
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
		String nomeLista = atividades.get(0).getNomeDemandaLista();
		
		boolean isFazendo = (nomeLista.equalsIgnoreCase(TipoListaEnum.FAZENDO.getNome()));
		boolean isFeito = (nomeLista.equalsIgnoreCase(TipoListaEnum.FEITO.getNome()));
		
		for(DemandaListaAtividade dla: atividades){
			dla.setUsuarioLogado(usuarioLogado);
			dla.setDataInclusao(dataAtual);
			
			if(isFazendo)
				dla.setDataInicio(dataAtual);
			
			if(isFeito){
				dla.setDataInicio(dataAtual);
				dla.setDataFinalizacao(dataAtual);
			}
			
			demandaListaAtividadeMapper.insert(dla);
			lstUuid.add(dla.getUuid());
		}
		return demandaListaAtividadeMapper.obterPorUUID(lstUuid);
	}

	public void update(DemandaListaAtividade demandaListaAtividade, HttpSession session) {
		StringUtils.emptyToNull(demandaListaAtividade);
		demandaListaAtividade.setDataInicio(demandaListaAtividade.getDataInicio());
		demandaListaAtividade.setDataFinalizacao(demandaListaAtividade.getDataFinalizacao());
		TipoListaEnum TIPO = TipoListaEnum.getEnum(demandaListaAtividade.getNomeDemandaLista());
		
		if(TIPO != null)
			switch (TIPO) {
			case FAZENDO:
				setUsuarioLogadoNaAtividade(demandaListaAtividade, session);
				demandaListaAtividade.setDataInicio(demandaListaAtividade.getDataInicio()!= null ? demandaListaAtividade.getDataInicio():new Date());
				break;
			case FEITO:
				setUsuarioLogadoNaAtividade(demandaListaAtividade, session);
				demandaListaAtividade.setDataFinalizacao(demandaListaAtividade.getDataFinalizacao()!= null? demandaListaAtividade.getDataFinalizacao(): new Date());
				break;
			case FAZER:
				break;
			default:
				break;
			}
		
		demandaListaAtividadeUsuarioBO.delete(demandaListaAtividade.getId());
		
		for(DemandaListaAtividadeUsuario ativUsuario: demandaListaAtividade.getLstAtividadeUsuario())
			demandaListaAtividadeUsuarioBO.insert(ativUsuario);

		demandaListaAtividadeMapper.update(demandaListaAtividade);
	}

	private void setUsuarioLogadoNaAtividade(DemandaListaAtividade demandaListaAtividade, HttpSession session) {
		Usuario usuLogado = usuarioBO.getUserSession(session);
		boolean exist = false;
		for(DemandaListaAtividadeUsuario ativUsuario: demandaListaAtividade.getLstAtividadeUsuario()){
			if(usuLogado.getId() == ativUsuario.getUsuario().getId()){
				exist = true;
				break;
			}
		}
		if(!exist)
			demandaListaAtividade.getLstAtividadeUsuario().add(
					new DemandaListaAtividadeUsuario(usuLogado,
							demandaListaAtividade.getId()));
	}

	public void delete(Integer idDemandaListaAtividade) {
		demandaListaAtividadeMapper.delete(idDemandaListaAtividade);
	}

	public List<DemandaListaAtividade> obterPorLista(Integer idDemandaLista) {
		return demandaListaAtividadeMapper.obterPorLista(idDemandaLista);
	}
	
	public List<AtividadePDFDTO> obterAtividadePorDemandaPDF(Integer idDemanda){
		return this.demandaListaAtividadeMapper.obterAtividadePorDemandaPDF(idDemanda);
	}
	
	
	
}
