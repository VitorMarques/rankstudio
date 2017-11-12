package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.model.CondicaoComercial;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.service.ICondicaoComercialService;
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
@RequestMapping(value = "/estudio/{estudioId}/condicao-comercial")
public class CondicaoComercialController {

	private final IEstudioService estudioService;
	private final ICondicaoComercialService condicaoComercialService;
	private final Environment environment;

	@Autowired
	public CondicaoComercialController(IEstudioService estudioService, ICondicaoComercialService condicaoComercialService, Environment environment) {
		this.estudioService = estudioService;
		this.condicaoComercialService = condicaoComercialService;
		this.environment = environment;
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView detalhes(@PathVariable Long estudioId, HttpServletRequest request) {

		Estudio estudio = estudioService.buscaPorId(estudioId);
		List<CondicaoComercial> condicoesComerciais = estudio.getCondicoesComerciais();

		ModelAndView modelAndView = new ModelAndView("condicaocomercial/detalhes");
		modelAndView.addObject("estudio", estudio);
		modelAndView.addObject("condicoesComerciais", condicoesComerciais);

		if(request.getSession().getAttribute("mensagem")!=null) {
			modelAndView.addObject("mensagem", request.getSession().getAttribute("mensagem"));
			request.getSession().removeAttribute("mensagem");
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(@PathVariable Long estudioId, CondicaoComercial condicaoComercial) {

		boolean edita = false;

		if(null!=condicaoComercial.getId()) {
			edita = true;
		}

		ModelAndView modelAndView = new ModelAndView("condicaocomercial/formulario");
		modelAndView.addObject("estudio", estudioService.buscaPorId(estudioId));
		modelAndView.addObject("condicaoComercial", condicaoComercial);
		modelAndView.addObject("edita", edita);

		return modelAndView;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView adiciona(@PathVariable Long estudioId, CondicaoComercial condicaoComercial, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		try {
			Estudio estudio = estudioService.buscaPorId(estudioId);
			condicaoComercial.setEstudio(estudio);
			condicaoComercialService.save(condicaoComercial);
/*			estudio.setCondicaoComercial(condicaoComercial);
			estudioService.save(estudio);*/
			request.getSession().setAttribute("mensagem", environment.getProperty("cadastro.realizado.sucesso"));
		} catch (Exception ex) {

			modelAndView.addObject("mensagem", ex.getMessage());
			modelAndView.addObject("condicaoComercial", condicaoComercial);
			modelAndView.setViewName("condicaocomercial/formulario");

		}

		return detalhes(estudioId, request);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable Long estudioId, @PathVariable Long id) {
		return novo(estudioId, condicaoComercialService.buscaPorId(id));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView atualiza(@PathVariable Long estudioId, CondicaoComercial condicaoComercial, HttpServletRequest request) {
		return adiciona(estudioId, condicaoComercial, request);
	}

}
