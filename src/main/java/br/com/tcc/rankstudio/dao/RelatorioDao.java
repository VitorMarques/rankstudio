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
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository("relatorioDao")
public class RelatorioDao extends AbstractDao implements IDao {

    public List<MovimentacaoEstudioDTO> geraRelatorioMovimentacaoEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) {

        String query = "SELECT count(tipo_agendamento) as total, tipo_agendamento as tipoAgendamento, monthname(data_agendamento) as mes" +
                " FROM tb_agendamento" +
                " WHERE estudio_id = :estudioId" +
                " AND data_agendamento BETWEEN :dataIni AND :dataFim" +
                " GROUP BY tipo_agendamento, mes";

        List<MovimentacaoEstudioDTO> movimentacaoList = getSession().createSQLQuery(query)
                .setParameter("estudioId", estudioId)
                .setParameter("dataIni", dataIni)
                .setParameter("dataFim", dataFim)
                .setResultTransformer(new MovimentacaoEstudioTransformer())
                .list();

        return movimentacaoList;
    }

    public List<ClientesEstudioDTO> geraRelatorioClientesEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) {

        String query = "SELECT tu.nome, tu.bairro, ta.data_agendamento, ta.horario_agendamento" +
                " FROM tb_agendamento ta" +
                " JOIN tb_usuario tu ON ta.usuario_id = tu.id" +
                " WHERE estudio_id = :estudioId" +
                " AND data_agendamento BETWEEN :dataIni AND :dataFim";

        List<ClientesEstudioDTO> clienteList = getSession().createSQLQuery(query)
                .setParameter("estudioId", estudioId)
                .setParameter("dataIni", dataIni)
                .setParameter("dataFim", dataFim)
                .setResultTransformer(new ClientesEstudioTransformer())
                .list();

        return clienteList;

    }

    public List<HistoricoNotasEstudioDTO> geraRelatorioHistoricoNotasEstudio(Long estudioId, Calendar dataIni, Calendar dataFim) {

        String query = "SELECT avg(tav.nota) as nota_media, monthname(tav.data_avaliacao) as mes" +
                " FROM tb_avaliacao tav" +
                " WHERE estudio_id = :estudioId" +
                " AND data_avaliacao BETWEEN :dataIni AND :dataFim" +
                " GROUP BY mes";

        List<HistoricoNotasEstudioDTO> historicoNotasList = getSession().createSQLQuery(query)
                .setParameter("estudioId", estudioId)
                .setParameter("dataIni", dataIni)
                .setParameter("dataFim", dataFim)
                .setResultTransformer(new HistoricoNotasEstudioTransformer())
                .list();

        return historicoNotasList;

    }

    public List<RankEstudioDTO> geraRelatorioRankEstudio(Calendar dataIni, Calendar dataFim) {

        String query = "SELECT te.nome, avg(tav.nota) as nota" +
                " FROM tb_avaliacao tav" +
                " JOIN tb_estudio te ON tav.estudio_id = te.id" +
                " WHERE tav.data_avaliacao BETWEEN :dataIni AND :dataFim" +
                " GROUP BY te.nome" +
                " ORDER BY te.nome DESC ";

        List<RankEstudioDTO> rankEstudioList = getSession().createSQLQuery(query)
                .setParameter("dataIni", dataIni)
                .setParameter("dataFim", dataFim)
                .setResultTransformer(new RankEstudioTransformer())
                .list();

        return rankEstudioList;
    }

    public List<Estudio> geraRelatorioEstudiosCadastrados(Calendar dataIni, Calendar dataFim) {

        List<Estudio> estudioList = getSession().createCriteria(Estudio.class).list();

        return estudioList;
    }
}
