package br.com.gedai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		model.addAttribute("idArea", idArea);
		return "demanda";
	}
	@RequestMapping(value = "inserirDemanda", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Demanda inserirDemanda(@RequestBody Demanda demanda){
		return this.demandaBO.insert(demanda);
	}
	
	@RequestMapping(value="obterDemandaPorArea", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Demanda> obterDemandaPorArea(Integer idArea) {
		return demandaBO.obterPorArea(idArea);
	}
	
}
