package br.com.gedai.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gedai.data.DemandaTarefa;
import br.com.gedai.data.Usuario;
import br.com.gedai.mapper.DemandaTarefaMapper;

@Service
public class DemandaTarefaBO {

	@Autowired
	private DemandaTarefaMapper demandaTarefaMapper;
	
	@Autowired
	private UsuarioBO usuarioBO;
	
	public List<DemandaTarefa> obterPorDemanda(Integer idDemanda){
		return demandaTarefaMapper.obterPorDemanda(idDemanda);
	}
	
	public void updateContador(DemandaTarefa demandaTarefa){
		demandaTarefaMapper.updateContador(demandaTarefa);
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
}