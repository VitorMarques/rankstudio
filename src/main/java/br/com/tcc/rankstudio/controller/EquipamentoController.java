package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.model.Empresa;
import br.com.tcc.rankstudio.model.Equipamento;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.model.Usuario;
import br.com.tcc.rankstudio.service.IEmpresaService;
import br.com.tcc.rankstudio.service.IEquipamentoService;
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
@RequestMapping(value = "/estudio/{estudioId}/equipamento")
public class EquipamentoController {

	@Autowired
	private IEstudioService estudioService;
	@Autowired
	private IEquipamentoService equipamentoService;

	@Autowired
	private Environment environment;

	public EquipamentoController() {}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView detalhes(@PathVariable Long estudioId, HttpServletRequest request) {

		Estudio estudio = estudioService.buscaPorId(estudioId);
		List<Equipamento> equipamentos = estudio.getEquipamentos();

		ModelAndView modelAndView = new ModelAndView("equipamento/lista");
		modelAndView.addObject("estudio", estudio);

		if(!equipamentos.isEmpty() && equipamentos.size() > 0) {
			modelAndView.addObject("equipamentos", equipamentos);
		}

		if(request.getSession().getAttribute("mensagem")!=null) {
			modelAndView.addObject("mensagem", request.getSession().getAttribute("mensagem"));
			request.getSession().removeAttribute("mensagem");
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(@PathVariable Long estudioId, Equipamento equipamento) {

		boolean edita = false;

		if(null!=equipamento.getId()) {
			edita = true;
		}

		ModelAndView modelAndView = new ModelAndView("equipamento/formulario");
		modelAndView.addObject("estudio", estudioService.buscaPorId(estudioId));
		modelAndView.addObject("equipamento", equipamento);
		modelAndView.addObject("edita", edita);

		return modelAndView;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView adiciona(@PathVariable Long estudioId, Equipamento equipamento, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		try {
			equipamentoService.save(equipamento);
			request.getSession().setAttribute("mensagem", environment.getProperty("cadastro.realizado.sucesso"));
		} catch (Exception ex) {

			modelAndView.addObject("mensagem", ex.getMessage());
			modelAndView.addObject("equipamento", equipamento);
			modelAndView.setViewName("equipamento/formulario");

		}

		return detalhes(estudioId, request);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable Long estudioId, @PathVariable Long id) {
		return novo(estudioId, equipamentoService.buscaPorId(id));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView atualiza(@PathVariable Long estudioId, Equipamento equipamento, HttpServletRequest request) {
		return adiciona(estudioId, equipamento, request);
	}

}
