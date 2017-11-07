package br.com.tcc.rankstudio.dao;

import br.com.tcc.rankstudio.model.Avaliacao;
import br.com.tcc.rankstudio.model.Perfil;
import br.com.tcc.rankstudio.model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import sun.security.x509.AVA;

import java.util.List;

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

	public List<Avaliacao> buscaAvaliacoesPorUsuario(String nome) {

		Criteria criteria = super.getSession().createCriteria(Avaliacao.class);
		List<Avaliacao> avaliacaoList = criteria.add(Restrictions.ilike("nomeUsuario", nome, MatchMode.ANYWHERE)).list();

		return avaliacaoList;
	}
}
