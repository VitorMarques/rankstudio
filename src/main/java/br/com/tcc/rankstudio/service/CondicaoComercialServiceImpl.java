package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.dao.AgendaDao;
import br.com.tcc.rankstudio.dao.CondicaoComercialDao;
import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.model.CondicaoComercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("condicaoComercialService")
@Transactional
public class CondicaoComercialServiceImpl implements ICondicaoComercialService {

	@Autowired
	private CondicaoComercialDao condicaoComercialDao;

	@Override
	public void save(CondicaoComercial condicaoComercial) {
		condicaoComercialDao.persist(condicaoComercial);
	}

	@Override
	public CondicaoComercial buscaPorId(Long id) {
		return (CondicaoComercial) condicaoComercialDao.findById(id);
	}

}
