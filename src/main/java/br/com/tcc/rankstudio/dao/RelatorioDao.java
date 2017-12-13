package br.com.tcc.rankstudio.dao;

import br.com.tcc.rankstudio.dto.ClientesEstudioDTO;
import br.com.tcc.rankstudio.dto.HistoricoNotasEstudioDTO;
import br.com.tcc.rankstudio.dto.MovimentacaoEstudioDTO;
import br.com.tcc.rankstudio.dto.RankEstudioDTO;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.transformer.ClientesEstudioTransformer;
import br.com.tcc.rankstudio.transformer.HistoricoNotasEstudioTransformer;
import br.com.tcc.rankstudio.transformer.MovimentacaoEstudioTransformer;
import br.com.tcc.rankstudio.transformer.RankEstudioTransformer;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository("relatorioDao")
public class RelatorioDao extends AbstractDao implements IDao {

    public RelatorioDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<MovimentacaoEstudioDTO> geraRelatorioMovimentacaoEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) {

        String query = "SELECT count(tipo_agendamento) as total, " +
                "sum(valor_agendamento) as lucro, tipo_agendamento as tipoAgendamento, " +
                "year(data_agendamento) AS ano, month(data_agendamento) as mes, " +
                "monthname(data_agendamento) as nomeMes" +
                " FROM tb_agendamento" +
                " WHERE estudio_id = :estudioId" +
                " AND data_agendamento BETWEEN :dataIni AND :dataFim" +
                " GROUP BY tipo_agendamento, ano, nomeMes, mes ORDER BY ano, mes desc";

        return (List<MovimentacaoEstudioDTO>) getSession().createSQLQuery(query)
                .setParameter("estudioId", estudioId)
                .setParameter("dataIni", dataIni)
                .setParameter("dataFim", dataFim)
                .setResultTransformer(new MovimentacaoEstudioTransformer())
                .list();
    }

    public List<ClientesEstudioDTO> geraRelatorioClientesEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) {

        String query = "SELECT tu.nome, tu.bairro, ta.data_agendamento, ta.horario_agendamento, ta.tipo_agendamento, te.nome as nomeEstudio" +
                " FROM tb_agendamento ta" +
                " JOIN tb_usuario tu ON ta.usuario_id = tu.id" +
                " JOIN tb_estudio te ON ta.estudio_id = te.id" +
                " WHERE data_agendamento BETWEEN :dataIni AND :dataFim";

        return (List<ClientesEstudioDTO>) getSession().createSQLQuery(query)
/*                .setParameter("estudioId", estudioId)*/
                .setParameter("dataIni", dataIni)
                .setParameter("dataFim", dataFim)
                .setResultTransformer(new ClientesEstudioTransformer())
                .list();

    }

    public List<HistoricoNotasEstudioDTO> geraRelatorioHistoricoNotasEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) {

        String query = "SELECT avg(tav.nota) as notas, monthname(tav.data_avaliacao) as mes" +
                " FROM tb_avaliacao tav" +
                " WHERE tav.estudio_id = :estudioId" +
                " AND tav.data_avaliacao BETWEEN :dataIni AND :dataFim" +
                " GROUP BY mes, month(tav.data_avaliacao)" +
                " ORDER BY month(tav.data_avaliacao) asc";

        return (List<HistoricoNotasEstudioDTO>) getSession().createSQLQuery(query)
                .setParameter("estudioId", estudioId)
                .setParameter("dataIni", dataIni)
                .setParameter("dataFim", dataFim)
                .setResultTransformer(new HistoricoNotasEstudioTransformer())
                .list();

    }

    public List<RankEstudioDTO> geraRelatorioRankEstudio(Calendar dataIni, Calendar dataFim) {

        String query = "SELECT te.nome, round(avg(tav.nota), 2) as nota" +
                " FROM tb_avaliacao tav" +
                " JOIN tb_estudio te ON tav.estudio_id = te.id" +
                " WHERE tav.data_avaliacao BETWEEN :dataIni AND :dataFim" +
                " GROUP BY te.nome" +
                " ORDER BY nota DESC LIMIT 10";

        return (List<RankEstudioDTO>) getSession().createSQLQuery(query)
                .setParameter("dataIni", dataIni)
                .setParameter("dataFim", dataFim)
                .setResultTransformer(new RankEstudioTransformer())
                .list();
    }

    public List<Estudio> geraRelatorioEstudiosCadastrados(Calendar dataIni, Calendar dataFim) {

        return (List<Estudio>) getSession().createCriteria(Estudio.class).list();
    }
}
