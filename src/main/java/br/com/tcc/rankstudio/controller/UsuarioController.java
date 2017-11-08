package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.exception.UsuarioExistenteException;
import br.com.tcc.rankstudio.model.Agendamento;
import br.com.tcc.rankstudio.model.Avaliacao;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.service.IEmpresaService;
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
import java.util.ArrayList;
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

	@RequestMapping(value = "/usuario/{idUsuario}/avaliacoes", method = RequestMethod.GET)
	public ModelAndView avaliacoesUsuario(@PathVariable Long idUsuario) {

		ModelAndView modelAndView = new ModelAndView();

		Usuario usuario = usuarioService.buscaPorId(idUsuario);

		List<Avaliacao> avaliacaoList = usuarioService.buscaAvaliacoesPorUsuario(usuario.getNome());

		modelAndView.addObject("avaliacoes", avaliacaoList);
		modelAndView.setViewName("usuario/avaliacoes");

		return modelAndView;
	}

	@RequestMapping(value = "/usuario/{idUsuario}/agendamentos", method = RequestMethod.GET)
	public ModelAndView agendamentosUsuario(@PathVariable Long idUsuario) {

		ModelAndView modelAndView = new ModelAndView();
		List<Agendamento> agendamentoList = usuarioService.buscaAgendamentosUsuario(idUsuario);
		List<Estudio> estudioList = new ArrayList<Estudio>();

		for (Agendamento agendamento : agendamentoList) {
			estudioList.add(estudioService.buscaPorId(agendamento.getEstudioId()));
		}

		modelAndView.addObject("agendamentos", agendamentoList);
		modelAndView.addObject("estudios", estudioList);
		modelAndView.setViewName("usuario/agendamentos");

		return modelAndView;
	}

}
