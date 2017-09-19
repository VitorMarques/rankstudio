package br.com.tcc.rankstudio.dao;

import br.com.tcc.rankstudio.model.Equipamento;
import br.com.tcc.rankstudio.model.Estudio;
import org.springframework.stereotype.Repository;

/**
 * Classe responsavel por realizar as operacoes na base de dados para o objeto de modelo SMS
 * Herda as operacoes basicas de {@link AbstractDao} 
 * 
 * @author Vitor Marques
 *
 */
@Repository("equipamentoDao")
public class EquipamentoDao extends AbstractDao implements IDao {

    public Equipamento findById(Long id) {
        String query = "SELECT * FROM tb_equipamento WHERE id = :id";
        return (Equipamento) super.getSession()
                .createSQLQuery(query)
                .addEntity(Equipamento.class)
                .setParameter("id", id)
                .uniqueResult();
    }
}
