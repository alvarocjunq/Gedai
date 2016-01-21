package br.com.gedai.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gedai.bo.UsuarioBO;
import br.com.gedai.data.Usuario;
import br.com.gedai.enums.ConfigEnum;
import br.com.gedai.utils.Criptografia;
import br.com.gedai.utils.Utils;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioBO usuarioBO;
	
	private static final String PAG_LOGIN = "login";

	@RequestMapping(value={"/", "/login"})
	public String login() {
		return PAG_LOGIN;
	}
	
	@RequestMapping("efetuarLogin")
	public String efetuarLogin(Model model, HttpSession session, Usuario usuario) {
		
		Usuario usuarioBanco = this.usuarioBO.obterPorLogin(usuario);
		
		if (usuarioBanco == null) {
			Utils.setMsgRetorno(model, -1, "Usuário não identificado!");
			return PAG_LOGIN;
		}
		
		if(usuarioBanco.getSenha() == null){
			Utils.setMsgRetorno(model, -1, "A senha deve ser cadastrada");
			return PAG_LOGIN;
		}
		
		if (!usuarioBanco.getSenha().equals(Criptografia.getSenha(usuario.getSenha()))) {
			Utils.setMsgRetorno(model, -1, "Senha incorreta");
			return PAG_LOGIN;
		}

		session.setAttribute(ConfigEnum.USUARIO_LOGADO.getValor(), usuarioBanco);
		
		return "redirect:area";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}
}