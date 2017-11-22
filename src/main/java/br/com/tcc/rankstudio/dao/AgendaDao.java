package br.com.tcc.rankstudio.dao;

import br.com.tcc.rankstudio.model.Agenda;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("agendaDao")
public class AgendaDao extends AbstractDao implements IDao {

    public AgendaDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Agenda findById(Long id) {
        String query = "SELECT * FROM tb_agenda WHERE id = :id";
        return (Agenda) super.getSession()
                .createSQLQuery(query)
                .addEntity(Agenda.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    public List findAll(Long estudioId) {
        String query = "SELECT * FROM tb_agenda WHERE estudio_id = :estudioId";
        return super.getSession()
                .createSQLQuery(query)
                .addEntity(Agenda.class)
                .setParameter("estudioId", estudioId)
                .list();
    }

    public List findByNomeSala(String nomeSala) {
        String query = "SELECT * FROM tb_agenda WHERE sala = :sala";
        return super.getSession()
                .createSQLQuery(query)
                .addEntity(Agenda.class)
                .setParameter("sala", nomeSala)
                .list();
    }
}
