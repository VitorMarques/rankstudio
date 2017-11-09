package br.com.tcc.rankstudio.transformer;

import br.com.tcc.rankstudio.dto.ClientesEstudioDTO;
import br.com.tcc.rankstudio.dto.MovimentacaoEstudioDTO;
import org.hibernate.transform.ResultTransformer;

import java.math.BigInteger;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class ClientesEstudioTransformer implements ResultTransformer {

    @Override
    public Object transformTuple(Object[] objects, String[] strings) {
        ClientesEstudioDTO clientesEstudioDTO = new ClientesEstudioDTO();
        clientesEstudioDTO.setNome((String) objects[0]);
        clientesEstudioDTO.setBairro((String) objects[1]);
        clientesEstudioDTO.setDataAgendamento((Date) objects[2]);
        clientesEstudioDTO.setHorarioAgendamento((String) objects[3]);
        return clientesEstudioDTO;
    }

    @Override
    public List transformList(List list) {
        return list;
    }

}
