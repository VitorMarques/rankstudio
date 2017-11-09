package br.com.tcc.rankstudio.dto;

import java.io.Serializable;

public class HistoricoNotasEstudioDTO implements Serializable {

    private Double notaMedia;

    private String mes;

    public Double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
}
