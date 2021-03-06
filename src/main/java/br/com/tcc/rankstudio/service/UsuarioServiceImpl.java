package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Agendamento;
import br.com.tcc.rankstudio.model.Avaliacao;
import br.com.tcc.rankstudio.model.Perfil;
import br.com.tcc.rankstudio.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tcc.rankstudio.dao.UsuarioDao;

import java.util.List;

/**
 *
 * @author Vitor Marques
 *
 */
@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {

	private final UsuarioDao usuarioDao;

	@Autowired
	public UsuarioServiceImpl(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

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
		return usuarioDao.findById(id);
	}

	@Override
	public Usuario buscaPorEmail(String email) {
		return usuarioDao.findByEmail(email);
	}

	public Perfil buscaPerfil(Usuario usuario) {
		return usuarioDao.getPerfil(usuario);
	}

	@Override
	public List<Avaliacao> buscaAvaliacoesPorUsuario(String nome) {
		return usuarioDao.buscaAvaliacoesPorUsuario(nome);
	}

	@Override
	public List<Agendamento> buscaAgendamentosUsuario(Long idUsuario) {
		return usuarioDao.buscaAgendamentosPorUsuario(idUsuario);
	}

}
