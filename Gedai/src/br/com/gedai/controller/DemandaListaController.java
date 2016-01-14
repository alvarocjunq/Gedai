package br.com.gedai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gedai.bo.DemandaListaBO;

@Controller
public class DemandaListaController {
	
	@Autowired
	private DemandaListaBO demandaListaBO;
	
	@RequestMapping("atividade")
	public String demanda(Model model, Integer idDemanda) {
		model.addAttribute("listas", demandaListaBO.obterPorDemanda(idDemanda));
		return "demandaAtividade";
	}
}
