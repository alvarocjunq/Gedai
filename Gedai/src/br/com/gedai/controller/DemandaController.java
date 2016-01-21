package br.com.gedai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gedai.bo.DemandaBO;
import br.com.gedai.data.Demanda;

@Controller
public class DemandaController {
	
	@Autowired
	private DemandaBO demandaBO; 
	
	@RequestMapping("demanda")
	public String demanda(Model model, Integer idArea) {
		List<Demanda> lista = demandaBO.obterPorArea(idArea);
		model.addAttribute("demandas", lista);
		model.addAttribute("demandasAll", lista);
		model.addAttribute("idArea", idArea);
		return "demanda";
	}
}
