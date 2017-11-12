package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.service.IAgendaService;
import br.com.tcc.rankstudio.service.IEstudioService;
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
@RequestMapping(value = "/estudio/{estudioId}/agenda")
public class AgendaController {

	private final IEstudioService estudioService;
	private final IAgendaService agendaService;
	private final Environment environment;

	@Autowired
	public AgendaController(IEstudioService estudioService, IAgendaService agendaService, Environment environment) {
		this.estudioService = estudioService;
		this.agendaService = agendaService;
		this.environment = environment;
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView detalhes(@PathVariable Long estudioId, HttpServletRequest request) {

		Estudio estudio = estudioService.buscaPorId(estudioId);
		List<Agenda> agendas = estudio.getAgendas();

		ModelAndView modelAndView = new ModelAndView("agenda/lista");
		modelAndView.addObject("estudio", estudio);

		if(!agendas.isEmpty()) {
			modelAndView.addObject("agendas", agendas);
		}

		if(request.getSession().getAttribute("mensagem")!=null) {
			modelAndView.addObject("mensagem", request.getSession().getAttribute("mensagem"));
			request.getSession().removeAttribute("mensagem");
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(@PathVariable Long estudioId, Agenda agenda) {

		boolean edita = false;

		if(null!=agenda.getId()) {
			edita = true;
		}

		ModelAndView modelAndView = new ModelAndView("agenda/formulario");
		modelAndView.addObject("estudio", estudioService.buscaPorId(estudioId));
		modelAndView.addObject("agenda", agenda);
		modelAndView.addObject("edita", edita);

		return modelAndView;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView adiciona(@PathVariable Long estudioId, Agenda agenda, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		try {
			agendaService.save(agenda);
			request.getSession().setAttribute("mensagem", environment.getProperty("cadastro.realizado.sucesso"));
		} catch (Exception ex) {

			modelAndView.addObject("mensagem", ex.getMessage());
			modelAndView.addObject("agenda", agenda);
			modelAndView.setViewName("agenda/formulario");

		}

		return detalhes(estudioId, request);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable Long estudioId, @PathVariable Long id) {
		return novo(estudioId, agendaService.buscaPorId(id));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView atualiza(@PathVariable Long estudioId, Agenda agenda, HttpServletRequest request) {
		return adiciona(estudioId, agenda, request);
	}

}
