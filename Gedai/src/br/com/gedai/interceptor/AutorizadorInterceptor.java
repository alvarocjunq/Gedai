package br.com.gedai.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.gedai.utils.Utils;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse response, Object controller) throws Exception {

		String uri = req.getRequestURI();
		boolean isLogado = (Utils.getUsuarioLogado(req.getSession()) != null);
		
		if (uri.endsWith("login") || uri.endsWith("efetuarLogin") || uri.contains("resources")) {
			return true;
		}
		
		if (isLogado) {
			return true;
		}
		response.sendRedirect("login");

		return true;
	}
}