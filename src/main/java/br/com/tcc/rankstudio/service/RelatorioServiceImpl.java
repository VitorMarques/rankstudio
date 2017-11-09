package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.dao.RelatorioDao;
import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.model.Equipamento;
import br.com.tcc.rankstudio.dto.*;
import br.com.tcc.rankstudio.model.Estudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service("relatorioService")
@Transactional
public class RelatorioServiceImpl implements IRelatorioService {

	@Autowired
	private RelatorioDao relatorioDao;

	public RelatorioServiceImpl() {}

	@Override
	public List<MovimentacaoEstudioDTO> geraRelatorioMovimentacaoEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) throws Exception {
		return relatorioDao.geraRelatorioMovimentacaoEstudio(estudioId, dataIni, dataFim);
	}

	@Override
	public List<ClientesEstudioDTO> geraRelatorioClientesEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) throws Exception {
		return relatorioDao.geraRelatorioClientesEstudio(estudioId, dataIni, dataFim);
	}

	@Override
	public List<HistoricoNotasEstudioDTO> geraRelatorioHistoricoNotasEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) throws Exception {
		return relatorioDao.geraRelatorioHistoricoNotasEstudio(estudioId, dataIni, dataFim);
	}

	@Override
	public List<RankEstudioDTO> geraRelatorioRankEstudio(Calendar dataIni, Calendar dataFim) throws Exception {
		return relatorioDao.geraRelatorioRankEstudio(dataIni, dataFim);
	}

	@Override
	public List<Estudio> geraRelatorioEstudiosCadastrados(Calendar dataIni, Calendar dataFim) throws Exception {
		return relatorioDao.geraRelatorioEstudiosCadastrados(dataIni, dataFim);
	}
}
