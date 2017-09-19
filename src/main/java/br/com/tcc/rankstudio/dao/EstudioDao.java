package br.com.tcc.rankstudio.dao;

import br.com.tcc.rankstudio.model.Empresa;
import br.com.tcc.rankstudio.model.Estudio;
import org.springframework.stereotype.Repository;

/**
 * Classe responsavel por realizar as operacoes na base de dados para o objeto de modelo SMS
 * Herda as operacoes basicas de {@link AbstractDao} 
 * 
 * @author Vitor Marques
 *
 */
@Repository("estudioDao")
public class EstudioDao extends AbstractDao implements IDao {

    public Estudio findById(Long id) {
        String query = "SELECT * FROM tb_estudio WHERE id = :id";
        return (Estudio) super.getSession()
                .createSQLQuery(query)
                .addEntity(Estudio.class)
                .setParameter("id", id)
                .uniqueResult();
    }
}
