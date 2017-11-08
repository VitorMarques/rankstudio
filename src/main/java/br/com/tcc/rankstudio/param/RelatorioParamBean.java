package br.com.tcc.rankstudio.param;

import java.util.Calandar;

public class RelatorioParamBean implements Serializable {

    private Long estudioId;

    private Calendar dataIni;

    private Calendar dataFim;

    public void setEstudioId(Long estudioId) {
        this.estudioId = estudioId;
    }

    public Long getEstudioId() {
        return estudioId;
    }

    public void setDataIni(Calendar dataIni) {
        this.dataIni = dataIni;
    }

    public Calendar getDataIni() {
        return dataIni;
    }

    public void setDataFim(Calandar dataFim) {
        this.dataFim = dataFim;
    }

    public Calendar getDataFim() {
        return dataFim;
    }
}
