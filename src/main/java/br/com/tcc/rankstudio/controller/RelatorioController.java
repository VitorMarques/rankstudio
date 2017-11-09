package br.com.tcc.rankstudio.controller;

import br.com.tcc.rankstudio.dto.ClientesEstudioDTO;
import br.com.tcc.rankstudio.dto.HistoricoNotasEstudioDTO;
import br.com.tcc.rankstudio.dto.MovimentacaoEstudioDTO;
import br.com.tcc.rankstudio.dto.RankEstudioDTO;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.param.RelatorioParamBean;
import br.com.tcc.rankstudio.service.IRelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/relatorios")
public class RelatorioController {

    @Autowired
    private IRelatorioService relatorioService;

    public RelatorioController() { }

    @RequestMapping(value = "/movimentacoes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MovimentacaoEstudioDTO> relatorioMovimentacaoEstudio(RelatorioParamBean relatorioParamBean) {

        List<MovimentacaoEstudioDTO> movimentacaoList = null;

        try {
            movimentacaoList = relatorioService.geraRelatorioMovimentacaoEstudio(relatorioParamBean.getEstudioId(), relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return movimentacaoList;
    }

    @RequestMapping(value = "/clientes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientesEstudioDTO> relatorioClientesEstudio(RelatorioParamBean relatorioParamBean) {

        List<ClientesEstudioDTO> clienteList = null;

        try {
            clienteList = relatorioService.geraRelatorioClientesEstudio(relatorioParamBean.getEstudioId(), relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return clienteList;

    }

    @RequestMapping(value = "/historiconotas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HistoricoNotasEstudioDTO> relatorioHistoricoNotasEstudio(RelatorioParamBean relatorioParamBean) {

        List<HistoricoNotasEstudioDTO> historicoNotasList = null;

        try {
            historicoNotasList = relatorioService.geraRelatorioHistoricoNotasEstudio(relatorioParamBean.getEstudioId(), relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return historicoNotasList;

    }

    @RequestMapping(value = "/ranks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RankEstudioDTO> relatorioRankEstudio(RelatorioParamBean relatorioParamBean) {

        List<RankEstudioDTO> rankStudioList = null;

        try {
            rankStudioList = relatorioService.geraRelatorioRankEstudio(relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return rankStudioList;

    }

    @RequestMapping(value = "/estudios", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Estudio> relatorioEstudios(RelatorioParamBean relatorioParamBean) {

        List<Estudio> estudioList = null;

        try {
            estudioList = relatorioService.geraRelatorioEstudiosCadastrados(relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return estudioList;

    }

}
