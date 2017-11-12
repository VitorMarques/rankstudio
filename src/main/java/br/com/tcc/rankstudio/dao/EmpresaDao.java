package br.com.tcc.rankstudio.dao;

import br.com.tcc.rankstudio.model.Empresa;
import br.com.tcc.rankstudio.model.Usuario;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Classe responsavel por realizar as operacoes na base de dados para o objeto de modelo SMS
 * Herda as operacoes basicas de {@link AbstractDao} 
 * 
 * @author Vitor Marques
 *
 */
@Repository("empresaDao")
public class EmpresaDao extends AbstractDao implements IDao {

    public EmpresaDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Empresa findById(Long id, Long representanteId) {
        String query = "SELECT * FROM tb_empresa WHERE id = :id AND representante_id = :representanteId";
        return (Empresa) super.getSession()
                .createSQLQuery(query)
                .addEntity(Empresa.class)
                .setParameter("id", id)
                .setParameter("representanteId", representanteId)
                .uniqueResult();
    }
}
