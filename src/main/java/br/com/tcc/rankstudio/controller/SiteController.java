package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.exception.CredencialUsuarioInvalidaException;
import br.com.tcc.rankstudio.exception.CredencialUsuarioNaoEncontradaException;
import br.com.tcc.rankstudio.exception.UsuarioExistenteException;
import br.com.tcc.rankstudio.model.*;
import br.com.tcc.rankstudio.service.IEstudioService;
import br.com.tcc.rankstudio.service.IUsuarioService;
import br.com.tcc.rankstudio.util.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class SiteController {

	private final IUsuarioService usuarioService;
	private final IEstudioService estudioService;
	private final Environment environment;

	@Autowired
	public SiteController(IUsuarioService usuarioService, IEstudioService estudioService, Environment environment) {
		this.usuarioService = usuarioService;
		this.estudioService = estudioService;
		this.environment = environment;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("melhoresEstudios", estudioService.buscaTop5());
		modelAndView.addObject("estudios", estudioService.listaTodos());

		return modelAndView;

	}

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView loginPage(Usuario usuario) {
		return new ModelAndView("login");
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

				if(usuarioBanco.getPerfil().getId()==1) { //cliente e redirecionado para o site
					modelAndView.setViewName("redirect:/");
				}

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
				modelAndView.addObject("estudios", estudioService.listaTodos());
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

		if(authUser == null) {
			modelAndView.setViewName("redirect:/");
		} else {
			if(authUser.getPerfil() != null) {
				if (authUser.getPerfil().getId() == 2) {//1 cliente, 2 proprietario
					modelAndView.addObject("request", request);
					modelAndView.setViewName("redirect:/estudio/info");
				}
			}
		}

		return modelAndView;
	}

	@RequestMapping(value = "site/estudio/{id}/detalhes", method = RequestMethod.GET)
	public ModelAndView detalhes(@PathVariable Long id) {

		Estudio estudio = estudioService.buscaPorId(id);
		List<Equipamento> equipamentos = estudio.getEquipamentos();
		List<Agenda> agendas = estudio.getAgendas();
		List<CondicaoComercial> condicoesComerciais = estudio.getCondicoesComerciais();

		ModelAndView modelAndView = new ModelAndView("detalhes-estudio");
		modelAndView.addObject("estudio", estudio);
		modelAndView.addObject("melhoresEstudios", estudioService.buscaTop5());
		modelAndView.addObject("equipamentos", equipamentos);
		modelAndView.addObject("agendas", agendas.stream().filter(Agenda::getDisponivel).filter(distinctByKey(Agenda::getSala)).collect(Collectors.toList()));
		modelAndView.addObject("condicoesComerciais", condicoesComerciais);

		return modelAndView;
	}

	@RequestMapping(value = "site/estudios/query", method = RequestMethod.GET)
	public ModelAndView busca(@RequestParam("busca") String textoPesquisa) {

		ModelAndView modelAndView = new ModelAndView("resultado-busca");

		try {
			List<Estudio> estudioList = estudioService.buscaEstudios(textoPesquisa);
			modelAndView.addObject("estudios", estudioList);
			modelAndView.addObject("melhoresEstudios", estudioService.buscaTop5());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}


	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}
}
