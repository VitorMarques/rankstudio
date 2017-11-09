package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.dto.*;
import br.com.tcc.rankstudio.model.Estudio;

import java.util.Calendar;
import java.util.List;

public interface IRelatorioService {

	//relatorio de movimentacao do estudio (ensaios gravacoes realizados / mensal ou anual)
    List<MovimentacaoEstudioDTO> geraRelatorioMovimentacaoEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) throws Exception;

	//relatorio de clientes que visitaram o estudio
    List<ClientesEstudioDTO> geraRelatorioClientesEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) throws Exception;

	//relatorio de historico de notas (mensal ou anual)
    List<HistoricoNotasEstudioDTO> geraRelatorioHistoricoNotasEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) throws Exception;

	//relatorio de rank dos estudios (os 10 melhores colocados / mensal ou anual) - admin
    List<RankEstudioDTO> geraRelatorioRankEstudio(Calendar dataIni, Calendar dataFim) throws Exception;

	//relatorio de estudios cadastrados - admin
    List<Estudio> geraRelatorioEstudiosCadastrados(Calendar dataIni, Calendar dataFim) throws Exception;

}
