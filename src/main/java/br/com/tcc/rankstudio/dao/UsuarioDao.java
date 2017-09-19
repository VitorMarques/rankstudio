package br.com.tcc.rankstudio.dao;

import br.com.tcc.rankstudio.model.Perfil;
import br.com.tcc.rankstudio.model.Usuario;
import org.springframework.stereotype.Repository;

/**
 * Classe responsavel por realizar as operacoes na base de dados para o objeto de modelo SMS
 * Herda as operacoes basicas de {@link AbstractDao} 
 * 
 * @author Vitor Marques
 *
 */
@Repository("usuarioDao")
public class UsuarioDao extends AbstractDao implements IDao {

	public Usuario findByEmail(String email) {
		return (Usuario) super.getSession().createSQLQuery(" SELECT * FROM tb_usuario WHERE EMAIL = :email").addEntity(Usuario.class).setParameter("email", email).uniqueResult();
	}

	public Usuario findById(Long id) {
		return (Usuario) super.getSession().createSQLQuery(" SELECT * FROM tb_usuario WHERE ID = :id").addEntity(Usuario.class).setParameter("id", id).uniqueResult();
	}

	public Perfil getPerfil(Usuario usuario) {

		String query = "SELECT tp.* FROM tb_perfil tp, tb_usuario tu WHERE tu.id = :id and tu.perfil_id = tp.id";

		return (Perfil) super.getSession().createSQLQuery(query).addEntity(Perfil.class).setParameter("id", usuario.getId()).uniqueResult();
	}
}
