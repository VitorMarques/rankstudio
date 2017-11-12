package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.model.Equipamento;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.model.FotoEquipamento;
import br.com.tcc.rankstudio.service.IEquipamentoService;
import br.com.tcc.rankstudio.service.IEstudioService;
import br.com.tcc.rankstudio.util.AmazonS3FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/estudio/{estudioId}/equipamento")
public class EquipamentoController {

	private final IEstudioService estudioService;
	private final IEquipamentoService equipamentoService;
	private final Environment environment;

	@Autowired
	public EquipamentoController(IEstudioService estudioService, IEquipamentoService equipamentoService, Environment environment) {
		this.estudioService = estudioService;
		this.equipamentoService = equipamentoService;
		this.environment = environment;
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView detalhes(@PathVariable Long estudioId, HttpServletRequest request) {

		Estudio estudio = estudioService.buscaPorId(estudioId);
		List<Equipamento> equipamentos = estudio.getEquipamentos();

		ModelAndView modelAndView = new ModelAndView("equipamento/lista");
		modelAndView.addObject("estudio", estudio);

		if(!equipamentos.isEmpty()) {
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
		modelAndView.addObject("tiposEquipamento", equipamentoService.listaTiposEquipamento());
		modelAndView.addObject("edita", edita);

		return modelAndView;
	}

	@RequestMapping(value = "/adiciona", method = RequestMethod.POST)
	public ModelAndView adiciona(@PathVariable Long estudioId, @RequestParam("file") MultipartFile file, Equipamento equipamento, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		try {

			if(file!=null) {
				String fileName = AmazonS3FileUpload.uploadFile(file);
				FotoEquipamento fotoEquipamento = equipamentoService.saveFotoEquipamento(fileName);
				equipamento.setFotoEquipamento(fotoEquipamento);
			}

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
		return adiciona(estudioId, null, equipamento, request);
	}

	@RequestMapping(value = "/{id}/excluir", method = RequestMethod.GET)
	public ModelAndView excluir(@PathVariable Long estudioId, @PathVariable Long id, HttpServletRequest request) {

		try {
			equipamentoService.delete(equipamentoService.buscaPorId(id));
			request.getSession().setAttribute("mensagem", environment.getProperty("Equipamento removido com sucesso!"));
		} catch (Exception ex) {
			request.getSession().setAttribute("mensagem", environment.getProperty("Erro ao tentar excluir o equipamento.\n" +ex.getMessage()+"\n  Favor tentar novamente!"));
		}

		return detalhes(estudioId,request);
	}

}
