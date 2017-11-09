package br.com.tcc.rankstudio.transformer;

import br.com.tcc.rankstudio.dto.RankEstudioDTO;
import org.hibernate.transform.ResultTransformer;

import java.util.List;

public class RankEstudioTransformer implements ResultTransformer {

    @Override
    public Object transformTuple(Object[] objects, String[] strings) {
        RankEstudioDTO rankEstudioDTO = new RankEstudioDTO();
        rankEstudioDTO.setNome((String) objects[0]);
        rankEstudioDTO.setNota((Double) objects[1]);
        return rankEstudioDTO;
    }

    @Override
    public List transformList(List list) {
        return list;
    }
}
