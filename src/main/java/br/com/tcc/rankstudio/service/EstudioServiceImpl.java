package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.dao.EmpresaDao;
import br.com.tcc.rankstudio.dao.EstudioDao;
import br.com.tcc.rankstudio.model.Empresa;
import br.com.tcc.rankstudio.model.Estudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author Vitor Marques
 *
 */
@Service("estudioService")
@Transactional
public class EstudioServiceImpl implements IEstudioService {

	@Autowired
	private EstudioDao estudioDao;

	@Override
	public void save(Estudio estudio) {
		estudioDao.persist(estudio);
	}

	@Override
	public Estudio buscaPorId(Long id) {
		return (Estudio) estudioDao.findById(id);
	}

	@Override
	public List<Estudio> listaTodos() {
		return (List<Estudio>) estudioDao.listaTodos(new Estudio());
	}

}
