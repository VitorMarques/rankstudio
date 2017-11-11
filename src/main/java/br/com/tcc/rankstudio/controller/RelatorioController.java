package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.dto.*;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.model.Usuario;
import br.com.tcc.rankstudio.param.RelatorioParamBean;
import br.com.tcc.rankstudio.service.IEstudioService;
import br.com.tcc.rankstudio.service.IRelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/relatorios")
public class RelatorioController {

    private static final String TIPO_RELATORIO_GRAFICO = "GRAFICO";
    private static final String TIPO_RELATORIO_LISTA = "LISTA";
    
    private final IRelatorioService relatorioService;
    private final IEstudioService estudioService;

    @Autowired
    public RelatorioController(IRelatorioService relatorioService, IEstudioService estudioService) {
        this.relatorioService = relatorioService;
        this.estudioService = estudioService;
    }

    @RequestMapping(value = "/{nomeRelatorio}")
    public ModelAndView getRelatorioPage(@PathVariable("nomeRelatorio") String nomeRelatorio, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("relatorio/relatorios");

        try {
            Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("authUser");
            modelAndView.addObject("nomeRelatorio", nomeRelatorio);
            modelAndView.addObject("tipoRelatorio", getTipoRelatorio(nomeRelatorio));
            modelAndView.addObject("estudios", estudioService.listaEstudiosPorProprietario(usuarioLogado.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @RequestMapping(value = "/movimentacoes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MovimentacaoEstudioDTO> relatorioMovimentacaoEstudio(RelatorioParamBean relatorioParamBean) {

        List<MovimentacaoEstudioDTO> movimentacaoList;
        //List<MovimentacaoEstudioDataset> datasets = null;

        try {
            movimentacaoList = relatorioService.geraRelatorioMovimentacaoEstudio(relatorioParamBean.getEstudioId(), relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
/*            for (MovimentacaoEstudioDTO movimentacaoEstudioDTO : movimentacaoList) {
                MovimentacaoEstudioDataset dataset = new MovimentacaoEstudioDataset();
                dataset.setLabel(movimentacaoEstudioDTO.getTipoAgendamento());
                dataset.getTotais().add(movimentacaoEstudioDTO.getTotal().longValue());
                datasets.add(dataset);
            }*/

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return movimentacaoList;
    }

    @RequestMapping(value = "/clientes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientesEstudioDTO> relatorioClientesEstudio(RelatorioParamBean relatorioParamBean) {

        List<ClientesEstudioDTO> clienteList;

        try {
            clienteList = relatorioService.geraRelatorioClientesEstudio(relatorioParamBean.getEstudioId(), relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return clienteList;

    }

    @RequestMapping(value = "/historiconotas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HistoricoNotasEstudioDTO> relatorioHistoricoNotasEstudio(RelatorioParamBean relatorioParamBean) {

        List<HistoricoNotasEstudioDTO> historicoNotasList;

        try {
            historicoNotasList = relatorioService.geraRelatorioHistoricoNotasEstudio(relatorioParamBean.getEstudioId(), relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return historicoNotasList;

    }

    @RequestMapping(value = "/ranks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RankEstudioDTO> relatorioRankEstudio(RelatorioParamBean relatorioParamBean) {

        List<RankEstudioDTO> rankStudioList;

        try {
            rankStudioList = relatorioService.geraRelatorioRankEstudio(relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return rankStudioList;

    }

    @RequestMapping(value = "/estudios", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estudio> relatorioEstudios(RelatorioParamBean relatorioParamBean) {

        List<Estudio> estudioList;

        try {
            estudioList = relatorioService.geraRelatorioEstudiosCadastrados(relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return estudioList;

    }

    private String getTipoRelatorio(String nomeRelatorio) throws Exception {

        if(nomeRelatorio.equalsIgnoreCase("movimentacoes"))
            return TIPO_RELATORIO_GRAFICO;
        else if (nomeRelatorio.equalsIgnoreCase("clientes"))
            return TIPO_RELATORIO_LISTA;
        else if(nomeRelatorio.equalsIgnoreCase("historiconotas"))
            return TIPO_RELATORIO_GRAFICO;
        else if(nomeRelatorio.equalsIgnoreCase("ranks"))
            return TIPO_RELATORIO_LISTA;
        else if (nomeRelatorio.equalsIgnoreCase("estudios"))
            return TIPO_RELATORIO_LISTA;
        else
            throw new Exception("Tipo de Relatorio nao encontrado para o nome " + nomeRelatorio);
    }

}
