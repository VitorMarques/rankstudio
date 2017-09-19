package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.dao.EmpresaDao;
import br.com.tcc.rankstudio.dao.UsuarioDao;
import br.com.tcc.rankstudio.model.Empresa;
import br.com.tcc.rankstudio.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vitor Marques
 *
 */
@Service("empresaService")
@Transactional
public class EmpresaServiceImpl implements IEmpresaService {

	@Autowired
	private EmpresaDao empresaDao;

	@Override
	public void save(Empresa empresa) {
		empresaDao.persist(empresa);
	}

	@Override
	public Empresa buscaPorId(Long id, Long representanteId) {
		return (Empresa) empresaDao.findById(id, representanteId);
	}
}
