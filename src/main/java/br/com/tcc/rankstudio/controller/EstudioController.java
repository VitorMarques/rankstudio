package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.model.Empresa;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.model.Usuario;
import br.com.tcc.rankstudio.service.IEmpresaService;
import br.com.tcc.rankstudio.service.IEstudioService;
import br.com.tcc.rankstudio.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "estudio")
public class EstudioController {

	@Autowired
	private IEmpresaService empresaService;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IEstudioService estudioService;
	@Autowired
	private Environment environment;

	public EstudioController() {}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView detalhes(HttpServletRequest request) {

		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("authUser");
		Empresa empresa = usuarioService.buscaPorId(usuarioLogado.getId()).getEmpresa();
		List<Estudio> estudios = empresa.getEstudios();

		ModelAndView modelAndView = new ModelAndView("estudio/lista");
		modelAndView.addObject("empresa", empresa);

		if(!estudios.isEmpty() && estudios.size() > 0) {
			modelAndView.addObject("estudios", estudios);
		}

		if(request.getSession().getAttribute("mensagem")!=null) {
			modelAndView.addObject("mensagem", request.getSession().getAttribute("mensagem"));
			request.getSession().removeAttribute("mensagem");
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(Estudio estudio, HttpServletRequest request) {

		boolean edita = false;

		if(null!=estudio.getId()) {
			edita = true;
		}

		Usuario usuario = (Usuario) request.getSession().getAttribute("authUser");
		Usuario usuarioBanco = usuarioService.buscaPorId(usuario.getId());

		ModelAndView modelAndView = new ModelAndView("estudio/formulario");
		modelAndView.addObject("estudio", estudio);
		modelAndView.addObject("empresa", usuarioBanco.getEmpresa());
		modelAndView.addObject("edita", edita);

		return modelAndView;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView adiciona(Estudio estudio, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		try {
			estudioService.save(estudio);
			request.getSession().setAttribute("mensagem", environment.getProperty("cadastro.realizado.sucesso"));
		} catch (Exception ex) {

			modelAndView.addObject("mensagem", ex.getMessage());
			modelAndView.addObject("estudio", estudio);
			modelAndView.setViewName("empresa/formulario");

		}

		return detalhes(request);
	}

	@RequestMapping(value = "/{id}/edita", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable Long id, HttpServletRequest request) {
		return novo(estudioService.buscaPorId(id), request);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView atualiza(Estudio estudio, HttpServletRequest request) {
		return adiciona(estudio, request);
	}

}
