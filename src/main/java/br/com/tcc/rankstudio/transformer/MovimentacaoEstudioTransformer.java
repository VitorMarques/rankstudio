package br.com.tcc.rankstudio.transformer;

import br.com.tcc.rankstudio.dto.MovimentacaoEstudioDTO;
import org.hibernate.transform.ResultTransformer;

import java.math.BigInteger;
import java.util.List;

public class MovimentacaoEstudioTransformer implements ResultTransformer {

    @Override
    public Object transformTuple(Object[] objects, String[] strings) {
        MovimentacaoEstudioDTO movimentacaoEstudioDTO = new MovimentacaoEstudioDTO();
        movimentacaoEstudioDTO.setTotal((BigInteger) objects[0]);
        movimentacaoEstudioDTO.setLucro((Double) objects[1]);
        movimentacaoEstudioDTO.setTipoAgendamento((String) objects[2]);
        movimentacaoEstudioDTO.setAno((Integer) objects[3]);
        movimentacaoEstudioDTO.setMes((Integer) objects[4]);
        return movimentacaoEstudioDTO;
    }

    @Override
    public List transformList(List list) {
        return list;
    }

}
