package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.model.*;
import br.com.tcc.rankstudio.service.IEmpresaService;
import br.com.tcc.rankstudio.service.IEstudioService;
import br.com.tcc.rankstudio.service.IUsuarioService;
import br.com.tcc.rankstudio.util.AmazonS3FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "estudio")
public class EstudioController {

	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IEstudioService estudioService;
	@Autowired
	private Environment environment;

	public EstudioController() {}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(HttpServletRequest request) {

		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("authUser");
		Empresa empresa = usuarioService.buscaPorId(usuarioLogado.getId()).getEmpresa();
		List<Estudio> estudios = new ArrayList<Estudio>();

		if(empresa!=null) {
			estudios = empresa.getEstudios();
		}

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

	@RequestMapping(value = "/{id}/detalhes", method = RequestMethod.GET)
	public ModelAndView detalhes(@PathVariable Long id,  HttpServletRequest request) {

		Estudio estudio = estudioService.buscaPorId(id);
		List<Equipamento> equipamentos = estudio.getEquipamentos();
		List<Agenda> agendas = estudio.getAgendas();
		CondicaoComercial condicaoComercial = estudio.getCondicaoComercial();

		ModelAndView modelAndView = new ModelAndView("estudio/detalhes");
		modelAndView.addObject("estudio", estudio);
		modelAndView.addObject("equipamentos", equipamentos);
		modelAndView.addObject("agendas", agendas);
		modelAndView.addObject("condicaoComercial", condicaoComercial);

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

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView adiciona(Estudio estudio, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();

		try {

			if(files!=null) {
				if(files.length>4)
					throw new Exception("Erro: Só é permitido o envio de até 4 arquivos.");

				fileList = Arrays.asList(files);

				if(estudio.getId()!=null) //remove todas as fotos para inserir as novas
					estudioService.deleteAllFotos(estudio.getId());

				estudio.setFotosEstudio(new ArrayList<FotoEstudio>());

				for(MultipartFile file : fileList) {
					if(!file.isEmpty()) {
						String fileName = AmazonS3FileUpload.uploadFile(file);
						FotoEstudio fotoEstudio = new FotoEstudio();
						fotoEstudio.setNomeArquivo(fileName);
						fotoEstudio.setEstudio(estudio);
						estudio.getFotosEstudio().add(fotoEstudio);
					}
				}
			}

			estudio.setRank(0.0);
			estudioService.save(estudio);
			request.getSession().setAttribute("mensagem", environment.getProperty("cadastro.realizado.sucesso"));
		} catch (Exception ex) {

			ex.printStackTrace();
			modelAndView.addObject("mensagem", ex.getMessage());
			modelAndView.addObject("estudio", estudio);
			modelAndView.setViewName("empresa/formulario");

		}

		return info(request);
	}

	@RequestMapping(value = "/{id}/edita", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable Long id, HttpServletRequest request) {
		return novo(estudioService.buscaPorId(id), request);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView atualiza(Estudio estudio, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
		return adiciona(estudio, files, request);
	}

	@RequestMapping(value = "/{id}/avaliacao", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String salvaAvaliacao(@PathVariable Long id, Avaliacao avaliacao, HttpServletRequest request, HttpServletResponse response) {

		String retorno = "";

		try {
			Usuario authUser = (Usuario) request.getSession().getAttribute("authUser");
			Estudio estudio = estudioService.buscaPorId(id);

			avaliacao.setEstudio(estudio);
			avaliacao.setNomeUsuario(authUser.getNome());

			estudioService.saveAvaliacao(avaliacao);

			estudio.getAvaliacoes().add(avaliacao);
			estudio.setRank(calculaRank(estudio.getAvaliacoes()));
			estudioService.save(estudio);

			retorno = "{\"msg\":\"Avaliacao enviada com sucesso!\"}";
		} catch (Exception ex) {
			ex.printStackTrace();
			retorno = "{\"error\":\""+ex.getMessage()+"\"}";
		}

		return retorno;
	}

	private Double calculaRank(List<Avaliacao> avaliacoes) {

		Double total = Double.valueOf(avaliacoes.size());
		Double somaNotas = 0.0;
		/*Double rank = 0.0;*/

		BigDecimal rank = new BigDecimal(0.0);
		/*rank.setScale(2);*/

		for (Avaliacao avaliacao : avaliacoes)
			somaNotas += avaliacao.getNota();

		/*DecimalFormat decimalFormat = new DecimalFormat("#.##");*/
		rank = BigDecimal.valueOf(somaNotas/total).setScale(2, BigDecimal.ROUND_CEILING);

		return rank.doubleValue();
	}

}
