package br.com.tcc.rankstudio.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

public class ClientesEstudioDTO implements Serializable {

    private String nome;

    private String bairro;

    private Date dataAgendamento;

    private String horarioAgendamento;

    private String tipoAgendamento;

    private String nomeEstudio;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getHorarioAgendamento() {
        return horarioAgendamento;
    }

    public void setHorarioAgendamento(String horarioAgendamento) {
        this.horarioAgendamento = horarioAgendamento;
    }

    public String getTipoAgendamento() {
        return tipoAgendamento;
    }

    public void setTipoAgendamento(String tipoAgendamento) {
        this.tipoAgendamento = tipoAgendamento;
    }

    public String getNomeEstudio() {
        return nomeEstudio;
    }

    public void setNomeEstudio(String nomeEstudio) {
        this.nomeEstudio = nomeEstudio;
    }
}
