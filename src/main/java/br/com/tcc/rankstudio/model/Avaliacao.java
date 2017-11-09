package br.com.tcc.rankstudio.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "tb_avaliacao")
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = -3878059762846198562L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nomeUsuario;

    @Column(precision = 2)
    private Double nota;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "data_avaliacao")
    @Temporal(TemporalType.DATE)
    private Calendar dataAvaliacao;

    @ManyToOne
    @JoinColumn(name = "estudio_id")
    private Estudio estudio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Calendar getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Calendar dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
