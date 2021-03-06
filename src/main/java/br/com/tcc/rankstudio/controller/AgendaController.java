package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.service.IAgendaService;
import br.com.tcc.rankstudio.service.IEstudioService;
import br.com.tcc.rankstudio.util.DataUtils;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.Date;
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

		    agendas.sort(Comparator.comparing(Agenda::getSala).thenComparing(Agenda::getData).thenComparing(Agenda::getHorario));

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
            Date dataAgenda = DataUtils.converteStringParaDate(agenda.getData());
            if(dataAgenda.before(new Date())) {
                throw new Exception("Data de agendamento nao pode ser anterior a data atual.");
            }

            try {

                Agenda agendaExistente = agendaService.findByNomeSalaEHorario(agenda.getSala(), agenda.getHorario(), estudioId);

                if (agendaExistente!=null)
                    throw new Exception("Ja existe uma agenda cadastrada para a mesma sala e horario.");

            } catch (HibernateException he) {
                throw new Exception(he.getMessage());
            }

			agendaService.save(agenda);
			request.getSession().setAttribute("mensagem", environment.getProperty("cadastro.realizado.sucesso"));
		} catch (Exception ex) {

			modelAndView.addObject("mensagem", ex.getMessage());
			modelAndView.addObject("agenda", agenda);
			modelAndView.addObject("estudio", estudioService.buscaPorId(estudioId));
			modelAndView.setViewName("agenda/formulario");
            return modelAndView;
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

	@RequestMapping(value = "/{id}/excluir", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Long estudioId, @PathVariable Long id, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("agenda/lista");

        Agenda agenda = agendaService.buscaPorId(id);

        if(agenda!=null) {
            try {
                agendaService.delete(agenda);
                Estudio estudio = estudioService.buscaPorId(estudioId);
                modelAndView.addObject("mensagem", "Agenda removida com sucesso.");
                modelAndView.addObject("estudio", estudioService.buscaPorId(estudioId));
                modelAndView.addObject("agendas", agendaService.findByEstudioId(estudioId));
            } catch (Exception ex) {
                modelAndView.addObject("mensagem", ex.getMessage());
            }
        } else {
            modelAndView.addObject("mensagem", "Nao foi possivel encontrar uma agenda com o ID informado. ID=" + id);
        }

        return modelAndView;
	}

	@RequestMapping(value = "/{nomeSala}/lista", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Agenda> listAgendasByName(@PathVariable(value = "estudioId") Long estudioId, @PathVariable(value = "nomeSala") String nomeSala) {
        return agendaService.findByNomeSala(nomeSala, estudioId);
    }

}
