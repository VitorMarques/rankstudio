package br.com.tcc.rankstudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.tcc.rankstudio.service.IRelatorioService;
import br.com.tcc.rankstudio.param.RelatorioParamBean;

@RestController
@RequestMapping(value = "/relatorios")
public class RelatorioController {

    @Autowired
	private IRelatorioService relatorioService;

  public RelatorioController() {}

  @RequestMapping(value = "/movimentacoes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  //@ResponseBody
  public List<MovimentacaoEstudioDTO> relatorioMovimentacaoEstudio(RelatorioParamBean relatorioParamBean) {

      List<MovimentacaoEstudioDTO> movimentacaoList = null;

      try {
           movimentacaoList = relatorioService.geraRelatorioMovimentacaoEstudio(relatorioParamBean.getEstudioId(), relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
      } catch(Exception ex) {
          throw new InternalServerException(ex);
      }

      return movimentacaoList;
  }

  @RequestMapping(value = "/clientes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  //@ResponseBody
  public List<ClientesEstudioDTO> relatorioClientesEstudio(RelatorioParamBean relatorioParamBean) {

      List<ClientesEstudioDTO> clienteList = null;

      try {
           clienteList = relatorioService.geraRelatorioClientesEstudio(relatorioParamBean.getEstudioId(), relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
      } catch(Exception ex) {
          throw new InternalServerException(ex);
      }

      return clienteList;

  }

  @RequestMapping(value = "/historiconotas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  //@ResponseBody
  public List<HistoricoNotasEstudioDTO> relatorioHistoricoNotasEstudio(RelatorioParamBean relatorioParamBean) {

      List<HistoricoNotasEstudioDTO> historicoNotasList = null;

      try {
           historicoNotasList = relatorioService.geraRelatorioHistoricoNotasEstudio(relatorioParamBean.getEstudioId(), relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
      } catch(Exception ex) {
          throw new InternalServerException(ex);
      }

      return historicoNotasList;

  }

  @RequestMapping(value = "/ranks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  //@ResponseBody
  public List<RankEstudioDTO> relatorioRankEstudio(RelatorioParamBean relatorioParamBean) {

      List<RankEstudioDTO> rankStudioList = null;

      try {
           rankStudioList = relatorioService.geraRelatorioRankEstudio(relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
      } catch(Exception ex) {
          throw new InternalServerException(ex);
      }

      return rankStudioList;

  }

  @RequestMapping(value = "/estudios", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  //@ResponseBody
  public List<Estudio> relatorioEstudios(RelatorioParamBean relatorioParamBean) {

      List<Estudio> estudioList = null;

      try {
           estudioList = relatorioService.geraRelatorioEstudiosCadastrados(relatorioParamBean.getDataIni(), relatorioParamBean.getDataFim());
      } catch(Exception ex) {
          throw new InternalServerException(ex);
      }

      return estudioList;

  }

}
