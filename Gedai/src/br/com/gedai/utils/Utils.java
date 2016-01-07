package br.com.gedai.utils;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import br.com.gedai.data.Usuario;
import br.com.gedai.enums.ConfigEnum;

public class Utils {
	
	public static void setMsgRetorno(Model model, final int cod, final String msg) {
		model.addAttribute("codMsgem", cod);
		model.addAttribute("mensagemRetorno", msg);
	}
	
	public static Usuario getUsuarioLogado(HttpSession session){
		return (Usuario) session.getAttribute(ConfigEnum.USUARIO_LOGADO.getValor());
	}
	
}
