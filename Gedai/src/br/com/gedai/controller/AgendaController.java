package br.com.gedai.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.gedai.bo.AgendaBO;
import br.com.gedai.bo.DemandaListaAtividadeBO;
import br.com.gedai.data.DemandaListaAtividade;

@Controller
public class AgendaController {

	@Autowired
	private AgendaBO agendaBO;
	
	@Autowired
	private DemandaListaAtividadeBO demandaListaAtividadeBO;

	@RequestMapping("agenda")
	public String demanda(Model model, HttpSession session) {
		List<DemandaListaAtividade> lista = agendaBO.obterPorUsuario(session);
		Map<String, Boolean> mapColor = agendaBO.getMapColor(lista);
		Map<String, Boolean> mapColorContinua = agendaBO.getMapColorContinua(lista);
		
		model.addAttribute("atividades", agendaBO.obterAtividadesPorUsuario(session, lista, mapColor));
		model.addAttribute("atividadesContinuas", agendaBO.obterAtividadesContinuasPorUsuario(session, lista, mapColorContinua));
		model.addAttribute("fazendo", agendaBO.obterPorId(session));
		return "agenda";
	}

	@RequestMapping(value = "iniciarAtividade", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DemandaListaAtividade iniciarAtividade(Integer idAtividade, HttpSession session) {
		agendaBO.iniciarAtividade(idAtividade, session);
		return demandaListaAtividadeBO.obterPorId(idAtividade);
	}
	
	@RequestMapping(value = "countAtividade", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer countAtividade(HttpSession session) {
		return agendaBO.countParaAgenda(session);
	}
	
	@RequestMapping(value = "pausarAtividade", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean pausarAtividade(Integer idAtividade, HttpSession session) {
		agendaBO.pausarAtividade(idAtividade, session);
		return true;
	}
	
	@RequestMapping(value = "finalizarAtividade", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody boolean finalizarAtividade(Integer idAtividade, HttpSession session) {
		agendaBO.finalizarAtividade(idAtividade, session);
		return true;
	}
	
	@RequestMapping(value = "pararAtividade", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer pararAtividade(@RequestBody DemandaListaAtividade atividade, HttpSession session) {
		return agendaBO.pararAtividade(atividade, session);
	}
}
