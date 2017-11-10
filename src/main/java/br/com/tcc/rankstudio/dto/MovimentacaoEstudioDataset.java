package br.com.tcc.rankstudio.dto;

import java.util.List;

public class MovimentacaoEstudioDataset {

    public static final String BORDER_WIDTH = "1";

    private String label;
    private List<Long> totais;
    private List<String> backgroundColor;
    private List<String> borderColor;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Long> getTotais() {
        return totais;
    }

    public void setTotais(List<Long> totais) {
        this.totais = totais;
    }

    public List<String> getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(List<String> backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public List<String> getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(List<String> borderColor) {
        this.borderColor = borderColor;
    }
}
