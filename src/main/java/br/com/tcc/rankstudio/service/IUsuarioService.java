package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Avaliacao;
import br.com.tcc.rankstudio.model.Perfil;
import br.com.tcc.rankstudio.model.Usuario;

import java.util.List;

/**
 * Interface que define os contratos que devem ser implementados pelos servicos dos usuarios
 * 
 * @author Vitor Marques
 * 
 */
public interface IUsuarioService {

	//definir metodos de login e registro
	boolean login(Usuario usuario);
	void registrar(Usuario usuario);
	Usuario buscaPorId(Long id);
	Usuario buscaPorEmail(String email);
	Perfil buscaPerfil(Usuario usuario);
    List<Avaliacao> buscaAvaliacoesPorUsuario(String nome);
}
