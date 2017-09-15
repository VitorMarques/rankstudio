package br.com.tcc.rankstudio.dao;

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
	
}
