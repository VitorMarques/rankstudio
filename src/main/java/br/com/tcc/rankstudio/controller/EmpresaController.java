package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.model.Empresa;
import br.com.tcc.rankstudio.model.Usuario;
import br.com.tcc.rankstudio.service.IEmpresaService;
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

@Controller
@RequestMapping(value = "empresa")
public class EmpresaController {

	@Autowired
	private IEmpresaService empresaService;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private Environment environment;

	public EmpresaController() {}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView detalhes(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("empresa/detalhes");

		//recuperar o usuario logado
		Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("authUser");
		//recuperar empresa associada a ele
		modelAndView.addObject("empresa", usuarioService.buscaPorId(usuarioLogado.getId()).getEmpresa());
		//se houver alguma, devolve a mesma na resposta e exibe seus detalhes na tela
		//se nao houver, exibe a pagina sem informacoes e com um botao para cadastrar uma nova empresa
		if(request.getSession().getAttribute("mensagem")!=null) {
			modelAndView.addObject("mensagem", request.getSession().getAttribute("mensagem"));
			request.getSession().removeAttribute("mensagem");
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/nova", method = RequestMethod.GET)
	public ModelAndView nova(Empresa empresa) {

		boolean edita = false;

		if(null!=empresa.getId()) {
			edita = true;
		}

		ModelAndView modelAndView = new ModelAndView("empresa/formulario");
		modelAndView.addObject("empresa", empresa);
		modelAndView.addObject("edita", edita);

		return modelAndView;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView adiciona(Empresa empresa, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();

		try {
			empresaService.save(empresa);
			request.getSession().setAttribute("mensagem", environment.getProperty("cadastro.realizado.sucesso"));
		} catch (Exception ex) {

			modelAndView.addObject("mensagem", ex.getMessage());
			modelAndView.addObject("empresa", empresa);
			modelAndView.setViewName("empresa/formulario");

		}

		return detalhes(request);
	}

	@RequestMapping(value = "/{id}/edita", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable Long id, HttpServletRequest request) {

		Usuario representanteEmpresa = (Usuario) request.getSession().getAttribute("authUser");

		return nova(empresaService.buscaPorId(id, representanteEmpresa.getId()));
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ModelAndView atualiza(Empresa empresa, HttpServletRequest request) {
		return adiciona(empresa, request);
	}

}
