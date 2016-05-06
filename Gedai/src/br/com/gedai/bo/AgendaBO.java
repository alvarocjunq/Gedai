package br.com.gedai.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.DemandaListaAtividade;
import br.com.gedai.data.DemandaListaAtividadeTempo;
import br.com.gedai.data.DemandaListaAtividadeUsuario;
import br.com.gedai.data.Usuario;
import br.com.gedai.enums.ConfigEnum;
import br.com.gedai.mapper.DemandaListaAtividadeMapper;
import br.com.gedai.mapper.DemandaListaAtividadeTempoMapper;

@Service
public class AgendaBO {

	@Autowired
	private DemandaListaAtividadeMapper demandaListaAtividadeMapper;
	
	@Autowired
	private DemandaListaAtividadeTempoMapper demandaListaAtividadeTempoMapper;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	@Autowired
	private DemandaListaAtividadeBO demandaListaAtividadeBO;
	
	@Autowired
	private DemandaListaAtividadeUsuarioBO demandaListaAtividadeUsuarioBO;
	
	public List<DemandaListaAtividade> obterAtividadesContinuasPorUsuario(HttpSession session, 
																		  List<DemandaListaAtividade> atividades, 
																		  Map<String, Boolean> mapColor) {
		List<DemandaListaAtividade> lista = new ArrayList<DemandaListaAtividade>();
		for(DemandaListaAtividade ativ: atividades){
			
			if(mapColor.get(ativ.getDemanda()) != null && mapColor.get(ativ.getDemanda()))
				ativ.setDemandaZebrada(ConfigEnum.CLASS_DEMANDA_ZEBRADA.getValor());
			
			if(ativ.getAtividadeContinua())
				lista.add(ativ);
		}
		return lista;
	}
	
	public List<DemandaListaAtividade> obterAtividadesPorUsuario(HttpSession session, 
																 List<DemandaListaAtividade> atividades, 
																 Map<String, Boolean> mapColor) {
		List<DemandaListaAtividade> lista = new ArrayList<DemandaListaAtividade>();
		for(DemandaListaAtividade ativ: atividades){
			
			if(mapColor.get(ativ.getDemanda())!= null && mapColor.get(ativ.getDemanda()))
				ativ.setDemandaZebrada(ConfigEnum.CLASS_DEMANDA_ZEBRADA.getValor());
			
			if(!ativ.getAtividadeContinua())
				lista.add(ativ);
		}
		return lista;
	}
	
	public Map<String, Boolean> getMapColor(List<DemandaListaAtividade> atividades){
		Map<String, Boolean> mapColor = new HashMap<String, Boolean>();
		TreeSet<String> treeColor = new TreeSet<String>();
		
		for(DemandaListaAtividade ativ: atividades){
			if(!ativ.getAtividadeContinua())
				treeColor.add(ativ.getDemanda());
		}
		
		armazenarMap(mapColor, treeColor);
		
		return mapColor;
	}
	
	public Map<String, Boolean> getMapColorContinua(List<DemandaListaAtividade> atividades){
		Map<String, Boolean> mapColor = new HashMap<String, Boolean>();
		TreeSet<String> treeColor = new TreeSet<String>();
		
		for(DemandaListaAtividade ativ: atividades){
			if(ativ.getAtividadeContinua())
				treeColor.add(ativ.getDemanda());
		}
		
		armazenarMap(mapColor, treeColor);
		
		return mapColor;
	}

	private void armazenarMap(Map<String, Boolean> mapColor, TreeSet<String> treeColor) {
		Boolean controle = false;
		for(String i: treeColor){
			mapColor.put(i, controle);
			controle = (!controle);
		}
	}
	
	public List<DemandaListaAtividade> obterPorUsuario(HttpSession session) {
		return demandaListaAtividadeMapper.obterParaAgenda(usuarioBO.getUserSession(session).getId());
	}
	
	public Integer countParaAgenda(HttpSession session) {
		return demandaListaAtividadeMapper.countParaAgenda(usuarioBO.getUserSession(session).getId());
	}
	
	

	public DemandaListaAtividade obterPorId(HttpSession session){
		return demandaListaAtividadeMapper.obterPorId(usuarioBO.getUserSession(session).getIdAtividadeFazendo());
	}
	
	public void iniciarAtividade(Integer idAtividade, HttpSession session){
		Usuario usuario = usuarioBO.getUserSession(session);
		DemandaListaAtividadeTempo ativ = new DemandaListaAtividadeTempo(idAtividade, usuario);
		Integer ativFazendo = usuario.getIdAtividadeFazendo();
		
		if(ativFazendo != idAtividade){
			demandaListaAtividadeTempoMapper.update(new DemandaListaAtividadeTempo(ativFazendo, usuario));
		}
		
		demandaListaAtividadeTempoMapper.insert(ativ);
		usuarioBO.atualizarAtividadeUsuario(idAtividade, session);
	}
	
	public void pausarAtividade(Integer idAtividade, HttpSession session){
		Usuario usuario = usuarioBO.getUserSession(session);
		demandaListaAtividadeTempoMapper.update(new DemandaListaAtividadeTempo(idAtividade, usuario));
		usuarioBO.atualizarAtividadeUsuario(null, session);
	}
	
	public void finalizarAtividade(Integer idAtividade, HttpSession session){
		Usuario usuario = usuarioBO.getUserSession(session);
		
		demandaListaAtividadeTempoMapper.update(new DemandaListaAtividadeTempo(idAtividade, usuario));
		demandaListaAtividadeMapper.finalizarAtividade(idAtividade);
		usuarioBO.atualizarAtividadeUsuario(null, session);
	}

	public Integer pararAtividade(DemandaListaAtividade atividade, HttpSession session){
		Integer id = atividade.getId();
		this.finalizarAtividade(id, session);
		
		DemandaListaAtividade atividadeDuplicada = demandaListaAtividadeBO.duplicate(atividade, session);
		
		Integer newId = atividadeDuplicada.getId(); 
		List<DemandaListaAtividadeUsuario> lstUsuario = demandaListaAtividadeUsuarioBO.obterPorAtividade(id);
		
		for(DemandaListaAtividadeUsuario obj: lstUsuario){
			obj.setIdDemandaListaAtividade(newId);
			demandaListaAtividadeUsuarioBO.insert(obj);
		}
		
		return newId;
	}
	
}
