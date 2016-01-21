package br.com.gedai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.gedai.bo.DemandaBO;
import br.com.gedai.bo.DemandaListaBO;
import br.com.gedai.data.DemandaLista;

@Controller
public class DemandaListaController {
	
	@Autowired
	private DemandaListaBO demandaListaBO;
	
	@Autowired
	private DemandaBO demandaBO; 
	
	@RequestMapping("atividade")
	public String demanda(Model model, Integer idDemanda, Integer idArea) {
		model.addAttribute("listas", demandaListaBO.obterPorDemanda(idDemanda));
		model.addAttribute("idDemanda", idDemanda);
		model.addAttribute("demandas", demandaBO.obterTodos());
		model.addAttribute("demanda", demandaBO.obterPorId(idDemanda));
		model.addAttribute("demandasAll", demandaBO.obterPorArea(idArea));
		return "demandaAtividade";
	}
	
	
	@RequestMapping(value="obterListas", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<DemandaLista> obterListas(Integer idDemanda) {
		return demandaListaBO.obterPorDemanda(idDemanda);
	}
}
