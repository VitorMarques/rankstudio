package br.com.tcc.rankstudio.dao;

import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.model.Agendamento;
import br.com.tcc.rankstudio.util.DataUtils;
import org.hibernate.Criteria;
import org.hibernate.NonUniqueResultException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

    public List findByNomeSala(String nomeSala, Long estudioId) {
        String query = "SELECT * FROM tb_agenda WHERE sala = :sala AND estudio_id = :estudioId AND disponivel = true";
        return super.getSession()
                .createSQLQuery(query)
                .addEntity(Agenda.class)
                .setParameter("sala", nomeSala)
                .setParameter("estudioId", estudioId)
                .list();
    }

    public Agenda findByAgendamento(Agendamento agendamento) {
        String query = "SELECT * FROM tb_agenda agenda WHERE agenda.sala = :sala AND agenda.data = :data AND agenda.horario = :horario AND agenda.estudio_id = :estudioId";
        return (Agenda) super.getSession()
                .createSQLQuery(query)
                .addEntity(Agenda.class)
                .setParameter("sala", agendamento.getSalaAgendamento())
                .setParameter("data", DataUtils.calendarToStringDate(agendamento.getDataAgendamento()))
                .setParameter("horario", agendamento.getHorarioAgendamento())
                .setParameter("estudioId", agendamento.getEstudioId())
                .uniqueResult();
    }

    public Agenda findByNomeSalaEHorario(String sala, String horario, Long estudioId) throws NonUniqueResultException {

        Criteria criteria = super.getSession().createCriteria(Agenda.class);
        criteria
                .add(Restrictions.eq("sala", sala))
                .add(Restrictions.eq("horario", horario))
                .add((Restrictions.eq("estudioId", estudioId)));

        return (Agenda) criteria.uniqueResult();
    }
}
