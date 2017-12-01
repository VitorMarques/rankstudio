package br.com.tcc.rankstudio.dto;

import br.com.tcc.rankstudio.model.Avaliacao;

import java.io.Serializable;
import java.util.List;

public class HistoricoNotasEstudioDTO implements Serializable {

    private Double notaMedia;

    private String mes;

    private List<Avaliacao> avaliacoes;

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

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
