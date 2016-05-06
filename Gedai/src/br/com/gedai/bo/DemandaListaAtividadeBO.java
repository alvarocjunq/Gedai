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
import br.com.gedai.utils.BooleanUtils;
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
	
	public DemandaListaAtividade duplicate(DemandaListaAtividade atividade, HttpSession session) {
		atividade.setId(demandaListaAtividadeMapper.nextSequence());
		atividade.setUsuarioLogado(usuarioBO.getUserSession(session));
		atividade.setDataInclusao(new Date());
		atividade.setAtividadeContinuaValue(1);
			
		demandaListaAtividadeMapper.insert(atividade);
		
		return atividade;
	}
	
	public List<DemandaListaAtividade> insert(List<DemandaListaAtividade> atividades, HttpSession session) {
		List<String> lstUuid = new ArrayList<String>();
		Usuario usuarioLogado = usuarioBO.getUserSession(session);
		final Date dataAtual = new Date();
		
		for(DemandaListaAtividade dla: atividades){
			dla.setUsuarioLogado(usuarioLogado);
			dla.setDataInclusao(dataAtual);
			dla.setAtividadeContinuaValue(0);
			int id = demandaListaAtividadeMapper.nextSequence();
			
			dla.setId(id);
			
			demandaListaAtividadeMapper.insert(dla);
			demandaListaAtividadeUsuarioBO.insert(new DemandaListaAtividadeUsuario(usuarioLogado, id));
			lstUuid.add(dla.getUuid());
		}
		
		return demandaListaAtividadeMapper.obterPorUUID(lstUuid);
	}

	public void update(DemandaListaAtividade dla, HttpSession session) {
		StringUtils.emptyToNull(dla);
		dla.setDataInicio(dla.getDataInicio());
		dla.setDataFinalizacao(dla.getDataFinalizacao());
		dla.setAtividadeContinuaValue(BooleanUtils.getValue(dla.getAtividadeContinua()));
		TipoListaEnum TIPO = TipoListaEnum.getEnum(dla.getNomeDemandaLista());
		
		if(TIPO != null)
			switch (TIPO) {
			case FAZENDO:
				setUsuarioLogadoNaAtividade(dla, session);
				dla.setDataInicio(dla.getDataInicio()!= null ? dla.getDataInicio():new Date());
				break;
			case FEITO:
				setUsuarioLogadoNaAtividade(dla, session);
				dla.setDataFinalizacao(dla.getDataFinalizacao()!= null? dla.getDataFinalizacao(): new Date());
				break;
			case FAZER:
				break;
			default:
				break;
			}
		
		demandaListaAtividadeUsuarioBO.delete(dla.getId());
		
		for(DemandaListaAtividadeUsuario ativUsuario: dla.getLstAtividadeUsuario())
			demandaListaAtividadeUsuarioBO.insert(ativUsuario);

		demandaListaAtividadeMapper.update(dla);
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
		demandaListaAtividadeUsuarioBO.delete(idDemandaListaAtividade);
		demandaListaAtividadeMapper.delete(idDemandaListaAtividade);
	}

	public List<DemandaListaAtividade> obterPorLista(Integer idDemandaLista) {
		return demandaListaAtividadeMapper.obterPorLista(idDemandaLista);
	}
	
	public List<AtividadePDFDTO> obterAtividadePorDemandaPDF(Integer idDemanda){
		return this.demandaListaAtividadeMapper.obterAtividadePorDemandaPDF(idDemanda);
	}
	
	
	
}
