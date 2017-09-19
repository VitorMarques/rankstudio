package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Perfil;
import br.com.tcc.rankstudio.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tcc.rankstudio.dao.UsuarioDao;

/**
 *
 * @author Vitor Marques
 *
 */
@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public boolean login(Usuario usuario) {
		return true;
	}

	@Override
	public void registrar(Usuario usuario) {
		usuarioDao.persist(usuario);
	}

	@Override
	public Usuario buscaPorId(Long id) {
		return (Usuario) usuarioDao.findById(id);
	}

	@Override
	public Usuario buscaPorEmail(String email) {
		return (Usuario) usuarioDao.findByEmail(email);
	}

	public Perfil buscaPerfil(Usuario usuario) {
		return usuarioDao.getPerfil(usuario);
	}
		
}
