package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.dao.AgendaDao;
import br.com.tcc.rankstudio.dao.EquipamentoDao;
import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.model.Equipamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vitor Marques
 *
 */
@Service("agendaService")
@Transactional
public class AgendaServiceImpl implements IAgendaService {

	@Autowired
	private AgendaDao agendaDao;

	@Override
	public void save(Agenda agenda) {
		agendaDao.persist(agenda);
	}

	@Override
	public Agenda buscaPorId(Long id) {
		return (Agenda) agendaDao.findById(id);
	}

}
