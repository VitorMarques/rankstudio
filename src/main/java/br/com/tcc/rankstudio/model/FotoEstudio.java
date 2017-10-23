/**
 * 
 */
package br.com.tcc.rankstudio.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_foto_estudio")
public class FotoEstudio implements Serializable {

	private static final long serialVersionUID = -8073046649173497385L;
	
	@Id @GeneratedValue
	private Long id;	
	
	@Column(nullable=false, length=255)
	private String nomeArquivo;

	@ManyToOne
	@JoinColumn(name = "estudio_id")
	private Estudio estudio;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

    public Estudio getEstudio() {
        return estudio;
    }

    public void setEstudio(Estudio estudio) {
        this.estudio = estudio;
    }
}
