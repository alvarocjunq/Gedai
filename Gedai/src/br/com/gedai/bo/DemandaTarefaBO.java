package br.com.gedai.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.DemandaTarefa;
import br.com.gedai.data.DemandaTarefaDiaria;
import br.com.gedai.data.Usuario;
import br.com.gedai.dto.TarefaPDFDTO;
import br.com.gedai.mapper.DemandaTarefaDiariaMapper;
import br.com.gedai.mapper.DemandaTarefaMapper;

@Service
public class DemandaTarefaBO {

	@Autowired
	private DemandaTarefaMapper demandaTarefaMapper;
	
	@Autowired
	private DemandaTarefaDiariaMapper demandaTarefaDiariaMapper;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	public List<DemandaTarefa> obterPorDemanda(Integer idDemanda){
		List<DemandaTarefa> lstDemandaTarefa = demandaTarefaMapper.obterPorDemanda(idDemanda);
		List<DemandaTarefaDiaria> lstTarefaDiaria = sumTarefaUsuario(idDemanda);
		for(DemandaTarefa tarefa: lstDemandaTarefa){
			tarefa.setContador(0);
			for(DemandaTarefaDiaria tarefaDiaria:lstTarefaDiaria){
				if(tarefa.getId() == tarefaDiaria.getIdTarefa())
					tarefa.setContador(tarefaDiaria.getContador());
			}
		}
		return lstDemandaTarefa;
	}
	
	public Integer updateContador(DemandaTarefaDiaria demandaTarefaDiaria, HttpSession session){
		demandaTarefaDiaria.setUsuario(usuarioBO.getUserSession(session));
		demandaTarefaDiaria.setDataAtualizacao(new Date());
		DemandaTarefaDiaria tarefaDiaria = demandaTarefaDiariaMapper.obterPorUsuario(demandaTarefaDiaria);
		
		if(null != tarefaDiaria){
			switch (demandaTarefaDiaria.getTipoOperacao()) {
			case "add":
				this.demandaTarefaDiariaMapper.addContadorUsuario(demandaTarefaDiaria);
				return demandaTarefaDiaria.getContador()+1;
			
			case "remove":
				if(tarefaDiaria.getContador() == 0){
					return demandaTarefaDiaria.getContador();
				}
				this.demandaTarefaDiariaMapper.removeContadorUsuario(demandaTarefaDiaria);
				return demandaTarefaDiaria.getContador()-1;
			default:
				break;
			}
		}else{
			if(!demandaTarefaDiaria.getTipoOperacao().equals("remove"))
				this.demandaTarefaDiariaMapper.insert(demandaTarefaDiaria);
		}
		return demandaTarefaDiaria.getContador()+1;
	}
	
	public void updateTarefa(DemandaTarefa demandaTarefa){
		demandaTarefaMapper.updateNomeTarefa(demandaTarefa);
	}
	
	public List<DemandaTarefa> insert(List<DemandaTarefa> lista, HttpSession session){
		List<String> lstUuid = new ArrayList<String>();
		Usuario usuarioLogado = usuarioBO.getUserSession(session);
		Date dataAtual = new Date();
		
		for(DemandaTarefa dt: lista){
			dt.setUsuarioInclusao(usuarioLogado);
			dt.setDataInclusao(dataAtual);
			demandaTarefaMapper.insert(dt);
			lstUuid.add(dt.getUuid());
		}
		return demandaTarefaMapper.obterPorUUID(lstUuid); 
	}
	
	public DemandaTarefa obterPorId(Integer id){
		DemandaTarefa demandaTarefa = demandaTarefaMapper.obterPorId(id);
		return demandaTarefa;
	}
	
	private List<DemandaTarefaDiaria> sumTarefaUsuario(Integer idDemanda){
		return demandaTarefaDiariaMapper.sumTarefaDiariaPorData(idDemanda,new Date());
	}
	
	public List<TarefaPDFDTO> obterTarefaPorDemandaPDF(Integer idDemanda){
		return this.obterTarefaPorDemandaPDF(idDemanda);
	}
}