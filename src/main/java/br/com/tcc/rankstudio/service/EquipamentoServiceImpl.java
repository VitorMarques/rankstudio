package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.dao.EquipamentoDao;
import br.com.tcc.rankstudio.dao.EstudioDao;
import br.com.tcc.rankstudio.model.Equipamento;
import br.com.tcc.rankstudio.model.Estudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vitor Marques
 *
 */
@Service("equipamentoService")
@Transactional
public class EquipamentoServiceImpl implements IEquipamentoService {

	@Autowired
	private EquipamentoDao equipamentoDao;

	@Override
	public void save(Equipamento equipamento) {
		equipamentoDao.persist(equipamento);
	}

	@Override
	public Equipamento buscaPorId(Long id) {
		return (Equipamento) equipamentoDao.findById(id);
	}

}
