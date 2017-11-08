package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.dto.*;

public interface IRelatorioService {

	//relatorio de movimentacao do estudio (ensaios gravacoes realizados / mensal ou anual)
    List<MovimentacaoEstudioDTO> geraRelatorioMovimentacaoEstudio(Long estudioId, Calendar dataIni, Calendar dataFim);

	//relatorio de clientes que visitaram o estudio
    List<ClientesEstudioDTO> geraRelatorioClientesEstudio(Long estudioId, Calendar dataIni, Calendar dataFim);

	//relatorio de historico de notas (mensal ou anual)
    List<HistoricoNotasEstudioDTO> geraRelatorioHistoricoNotasEstudio(Long estudioId, Calendar dataIni, Calendar dataFim);

	//relatorio de rank dos estudios (os 10 melhores colocados / mensal ou anual) - admin
    List<RankEstudioDTO> geraRelatorioRankEstudio(Calendar dataIni, Calendar dataFim);

	//relatorio de estudios cadastrados - admin
    List<Estudio> geraRelatorioEstudiosCadastrados(Calndar dataIni, Calndar dataFim);

}
