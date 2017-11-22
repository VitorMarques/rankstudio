package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.dao.AgendaDao;
import br.com.tcc.rankstudio.model.Agenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Vitor Marques
 *
 */
@Service("agendaService")
@Transactional
public class AgendaServiceImpl implements IAgendaService {

	private final AgendaDao agendaDao;

	@Autowired
	public AgendaServiceImpl(AgendaDao agendaDao) {
		this.agendaDao = agendaDao;
	}

	@Override
	public void save(Agenda agenda) {
		agendaDao.persist(agenda);
	}

	@Override
	public Agenda buscaPorId(Long id) {
		return (Agenda) agendaDao.findById(id);
	}

	@Override
	public void delete(Agenda agenda) {
		agendaDao.delete(agenda);
	}

    @Override
    public List<Agenda> findByEstudioId(Long estudioId) {
        return agendaDao.findAll(estudioId);
    }

	@Override
	public List<Agenda> findByNomeSala(String nomeSala) {
		return agendaDao.findByNomeSala(nomeSala);
	}

}
