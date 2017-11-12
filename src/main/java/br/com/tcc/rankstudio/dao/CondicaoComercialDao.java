package br.com.tcc.rankstudio.dao;

import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.model.CondicaoComercial;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("condicaoComercialDao")
public class CondicaoComercialDao extends AbstractDao implements IDao {

    public CondicaoComercialDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public CondicaoComercial findById(Long id) {
        String query = "SELECT * FROM tb_condicao_comercial WHERE id = :id";
        return (CondicaoComercial) super.getSession()
                .createSQLQuery(query)
                .addEntity(CondicaoComercial.class)
                .setParameter("id", id)
                .uniqueResult();
    }
}
