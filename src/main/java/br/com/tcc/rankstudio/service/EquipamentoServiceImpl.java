package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.dao.EquipamentoDao;
import br.com.tcc.rankstudio.dao.EstudioDao;
import br.com.tcc.rankstudio.model.Equipamento;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.model.FotoEquipamento;
import br.com.tcc.rankstudio.model.TipoEquipamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	public List<TipoEquipamento> listaTiposEquipamento() {
		return equipamentoDao.listaTiposEquipamento();
	}

	@Override
	public void delete(Equipamento equipamento) {
		equipamentoDao.delete(equipamento);
	}

	@Override
	public FotoEquipamento saveFotoEquipamento(String fileName) {

		FotoEquipamento fotoEquipamento = new FotoEquipamento();
		fotoEquipamento.setNomeArquivo(fileName);
		fotoEquipamento.setId(equipamentoDao.saveFotoEquipamento(fotoEquipamento));

		return fotoEquipamento;
	}

}
