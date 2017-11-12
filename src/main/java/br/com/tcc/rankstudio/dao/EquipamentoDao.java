package br.com.tcc.rankstudio.dao;

import br.com.tcc.rankstudio.model.Equipamento;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.model.FotoEquipamento;
import br.com.tcc.rankstudio.model.TipoEquipamento;
import org.hibernate.SessionFactory;
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
@Repository("equipamentoDao")
public class EquipamentoDao extends AbstractDao implements IDao {

    public EquipamentoDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Equipamento findById(Long id) {
        String query = "SELECT * FROM tb_equipamento WHERE id = :id";
        return (Equipamento) super.getSession()
                .createSQLQuery(query)
                .addEntity(Equipamento.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    public List<TipoEquipamento> listaTiposEquipamento() {
        String query = "SELECT * FROM tb_tipo_equipamento";
        return (List<TipoEquipamento>) super.getSession()
                .createSQLQuery(query)
                .addEntity(TipoEquipamento.class)
                .list();
    }

    public Long saveFotoEquipamento(FotoEquipamento fotoEquipamento) {
        return (Long) super.getSession().save(fotoEquipamento);
    }
}
