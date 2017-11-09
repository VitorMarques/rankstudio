package br.com.tcc.rankstudio.dto;

import java.io.Serializable;

public class RankEstudioDTO implements Serializable {

    private String nome;

    private Double nota;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
