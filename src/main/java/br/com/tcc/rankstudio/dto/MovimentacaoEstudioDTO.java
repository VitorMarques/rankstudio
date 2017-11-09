package br.com.tcc.rankstudio.dto;

import java.io.Serializable;
import java.math.BigInteger;

public class MovimentacaoEstudioDTO implements Serializable {

    private BigInteger total;

    private String tipoAgendamento;

    private String mes;

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }

    public String getTipoAgendamento() {
        return tipoAgendamento;
    }

    public void setTipoAgendamento(String tipoAgendamento) {
        this.tipoAgendamento = tipoAgendamento;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
