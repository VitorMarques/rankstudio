package br.com.tcc.rankstudio.transformer;

import br.com.tcc.rankstudio.dto.HistoricoNotasEstudioDTO;
import org.hibernate.transform.ResultTransformer;

import java.util.List;

public class HistoricoNotasEstudioTransformer implements ResultTransformer {

    @Override
    public Object transformTuple(Object[] objects, String[] strings) {
        HistoricoNotasEstudioDTO historicoNotasEstudioDTO = new HistoricoNotasEstudioDTO();
        historicoNotasEstudioDTO.setNotaMedia((Double) objects[0]);
        historicoNotasEstudioDTO.setMes((String) objects[1]);
        return historicoNotasEstudioDTO;
    }

    @Override
    public List transformList(List list) {
        return list;
    }
}
