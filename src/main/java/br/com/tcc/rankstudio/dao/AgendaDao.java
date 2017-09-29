package br.com.tcc.rankstudio.dao;

import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.model.Equipamento;
import org.springframework.stereotype.Repository;

@Repository("agendaDao")
public class AgendaDao extends AbstractDao implements IDao {

    public Agenda findById(Long id) {
        String query = "SELECT * FROM tb_agenda WHERE id = :id";
        return (Agenda) super.getSession()
                .createSQLQuery(query)
                .addEntity(Agenda.class)
                .setParameter("id", id)
                .uniqueResult();
    }
}
