package br.com.tcc.rankstudio.dao;

import br.com.tcc.rankstudio.model.Estudio;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Classe responsavel por realizar as operacoes na base de dados para o objeto de modelo SMS
 * Herda as operacoes basicas de {@link AbstractDao} 
 * 
 * @author Vitor Marques
 *
 */
@SuppressWarnings("unchecked")
@Repository("estudioDao")
public class EstudioDao extends AbstractDao implements IDao {

    public EstudioDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Estudio findById(Long id) {
        String query = "SELECT * FROM tb_estudio WHERE id = :id";
        return (Estudio) super.getSession()
                .createSQLQuery(query)
                .addEntity(Estudio.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    public void deleteAllFotosFromEstudio(Long idEstudio) {
        String query = "DELETE FROM tb_foto_estudio WHERE estudio_id = :estudioId";
        super.getSession().createSQLQuery(query).setParameter("estudioId", idEstudio).executeUpdate();
    }

    @Override
    public List<Estudio> listaTodos(Class classz) {

        String query = "SELECT distinct tb_estudio.* FROM tb_estudio;";
        return super.getSession().createSQLQuery(query).addEntity(Estudio.class).list();

    }

    public List<Estudio> listaEstudiosPorProprietario(Long idProprietario) {

        String query = "SELECT tb_estudio.* FROM tb_usuario " +
                "LEFT JOIN tb_empresa ON tb_usuario.id = tb_empresa.representante_id " +
                "JOIN tb_estudio ON tb_empresa.id = tb_estudio.empresa_id " +
                "AND tb_usuario.id = :representanteId";

        return (List<Estudio>) super.getSession().createSQLQuery(query).addEntity(Estudio.class).setParameter("representanteId", idProprietario).list();
    }

    public List<Estudio> pesquisaEstudios(String textoPesquisa) {

        Criteria criteria = super.getSession().createCriteria(Estudio.class);

        criteria.add(
                Restrictions.disjunction(Restrictions.ilike("nome", textoPesquisa, MatchMode.ANYWHERE),
                        Restrictions.ilike("bairro", textoPesquisa, MatchMode.ANYWHERE),
                        Restrictions.ilike("cidade", textoPesquisa, MatchMode.ANYWHERE)
                )
        );

        return (List<Estudio>) criteria.list();

    }

    public List<Estudio> buscaTop5() {

        Criteria criteria = super.getSession().createCriteria(Estudio.class);
        criteria.addOrder(Order.desc("rank"));
        criteria.setCacheable(Boolean.TRUE);
        criteria.setCacheMode(CacheMode.NORMAL);
        criteria.setFetchSize(5);

        return criteria.list();
    }
}
