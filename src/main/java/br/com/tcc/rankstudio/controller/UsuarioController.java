package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.exception.UsuarioExistenteException;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.model.Perfil;
import br.com.tcc.rankstudio.service.IEstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.tcc.rankstudio.exception.CredencialUsuarioInvalidaException;
import br.com.tcc.rankstudio.exception.CredencialUsuarioNaoEncontradaException;
import br.com.tcc.rankstudio.model.Usuario;
import br.com.tcc.rankstudio.service.IUsuarioService;
import br.com.tcc.rankstudio.util.Criptografia;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IEstudioService estudioService;

	@Autowired
	private Environment environment;

	public UsuarioController() {}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView formLogin(Usuario usuario) {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/usuario/info", method = RequestMethod.GET)
	public ModelAndView mostra(Usuario usuario, HttpServletRequest request) {

		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("authUser");

		ModelAndView modelAndView = new ModelAndView("usuario/detalhes");
		modelAndView.addObject("usuario", usuarioService.buscaPorId(usuarioLogado.getId()));

		return modelAndView;
	}

	@RequestMapping(value = "/usuario/{id}/edita", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable Long id) {

		Usuario usuario = usuarioService.buscaPorId(id);

		ModelAndView modelAndView = new ModelAndView("usuario/formulario");
		modelAndView.addObject("usuario", usuario);

		return modelAndView;
	}

	@RequestMapping(value = "/usuario/atualiza", method = RequestMethod.POST)
	public ModelAndView atualiza(Usuario usuario) {

		ModelAndView modelAndView = new ModelAndView("usuario/detalhes");

		try {

			usuario.setSenha(Criptografia.criptografarSenha(usuario.getSenha()));
			usuarioService.registrar(usuario);

			modelAndView.addObject("usuario", usuarioService.buscaPorId(usuario.getId()));
			modelAndView.addObject("mensagem", environment.getProperty("atualizacao.realizada.sucesso"));

		} catch (Exception ex){
			modelAndView.addObject("usuario", usuario);
			modelAndView.addObject("mensagem", ex.getMessage());
			modelAndView.setViewName("usuario/formulario");
		}

		return modelAndView;
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
			modelAndView.addObject("mensagem", ex.getMessage());
			modelAndView.setViewName("login");
			
		}
		
		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/registrar", method = RequestMethod.GET)
	public ModelAndView formRegistrar(Usuario usuario) {
		return new ModelAndView("cadastro");
	}
	
	@RequestMapping(value = "/realizar-registro", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
				modelAndView.addObject("mensagem", environment.getProperty("cadastro.realizado.sucesso"));
			}

		} catch (Exception ex) {

			modelAndView.addObject("usuario", usuario);
			modelAndView.addObject("mensagem", ex.getMessage());
			modelAndView.setViewName("cadastro");

		}

		return modelAndView;
	}

	@RequestMapping(value = "/app", method = RequestMethod.GET)
	public ModelAndView painelDeControle(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("painel/painel");

		Usuario authUser = (Usuario) request.getSession().getAttribute("authUser");
		List<Estudio> estudios = estudioService.listaTodos();

		if(authUser == null) {
			modelAndView.setViewName("redirect:/");
		}

		modelAndView.addObject("estudios", estudios);

		return modelAndView;
	}
}
