package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.exception.UsuarioExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.tcc.rankstudio.exception.CredencialUsuarioInvalidaException;
import br.com.tcc.rankstudio.exception.CredencialUsuarioNaoEncontradaException;
import br.com.tcc.rankstudio.model.Usuario;
import br.com.tcc.rankstudio.service.IUsuarioService;
import br.com.tcc.rankstudio.util.Criptografia;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private Environment environment;

	public UsuarioController() {}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView formLogin(Usuario usuario) {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(Usuario usuario, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("redirect:/app");
		
		//criar logica de login
		try {
			
			Usuario usuarioBanco = usuarioService.buscaPorEmail(usuario.getEmail());
			String senhaCriptografada = Criptografia.criptografarSenha(usuario.getSenha());
			
			if(usuarioBanco == null) {
				throw new CredencialUsuarioNaoEncontradaException(environment.getProperty("credencial.usuario.nao.encontrada"));
			}
			
			if(usuarioBanco.getEmail().equalsIgnoreCase(usuario.getEmail()) && usuarioBanco.getSenha().equalsIgnoreCase(senhaCriptografada)) {

				request.getSession().setAttribute("authUser", usuarioBanco);

			} else {
				throw new CredencialUsuarioInvalidaException(environment.getProperty("credencial.usuario.invalida"));
			}
			
		} catch (Exception ex) {

			modelAndView.addObject("usuario", usuario);
			modelAndView.addObject("erro", ex.getMessage());
			modelAndView.setViewName("index");
			
		}
		
		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/registrar", method = RequestMethod.GET)
	public ModelAndView formRegistrar(Usuario usuario) {
		return new ModelAndView("usuario/form-registrar");
	}
	
	@RequestMapping(value = "/realizar-registro", method = RequestMethod.POST)
	public ModelAndView registrar(Usuario usuario, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("painel/painel");

		//validar dados do formulario - email ja existe?
		try {

			Usuario usuarioBanco = usuarioService.buscaPorEmail(usuario.getEmail());

			if(usuarioBanco != null) {
				throw new UsuarioExistenteException(environment.getProperty("usuario.ja.cadastrado"));
			} else {
				usuario.setSenha(Criptografia.criptografarSenha(usuario.getSenha()));
				usuarioService.registrar(usuario);

				request.getSession().setAttribute("authUser", usuarioService.buscaPorEmail(usuario.getEmail()));
				modelAndView.addObject("sucesso", environment.getProperty("cadastro.realizado.sucesso"));
			}

		} catch (Exception ex) {

			modelAndView.addObject("usuario", usuario);
			modelAndView.addObject("erro", ex.getMessage());
			modelAndView.setViewName("usuario/form-registrar");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/app", method = RequestMethod.GET)
	public ModelAndView painelDeControle(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("painel/painel");

		Usuario authUser = (Usuario) request.getSession().getAttribute("authUser");

		if(authUser == null) {
			modelAndView.setViewName("redirect:/");
		}

		return modelAndView;
	}
}
