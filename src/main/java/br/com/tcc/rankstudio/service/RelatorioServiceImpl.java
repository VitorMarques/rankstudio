package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.dao.RelatorioDao;
import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.model.Equipamento;
import br.com.tcc.rankstudio.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("relatorioService")
@Transactional
public class RelatorioServiceImpl implements IRelatorioService {

	@Autowired
	private RelatorioDao relatorioDao;

	public RelatorioServiceImpl() {}

	public List<MovimentacaoEstudioDTO> geraRelatorioMovimentacaoEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) throws Exception {
		return relatorioDao.geraRelatorioMovimentacaoEstudio(estudioId, dataIni, dataFim);
	}

	public List<ClientesEstudioDTO> geraRelatorioClientesEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) {
		return relatorioDao.geraRelatorioClientesEstudio(estudioId, dataIni, dataFim);
	}

	public List<HistoricoNotasEstudioDTO> geraRelatorioHistoricoNotasEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) {
		return relatorioDao.geraRelatorioHistoricoNotasEstudio(estudioId, dataIni, dataFim);
	}

	public List<RankEstudioDTO> geraRelatorioRankEstudio(Calendar dataIni, Calendar dataFim) {
		return relatorioDao.geraRelatorioRankEstudio(dataIni, dataFim);
	}

	public List<Estudio> geraRelatorioEstudiosCadastrados(Calndar dataIni, Calndar dataFim) {
		return relatorioDao.geraRelatorioEstudiosCadastrados(dataIni, dataFim);
	}
}
