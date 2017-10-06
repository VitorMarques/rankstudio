/**
 * 
 */
package br.com.tcc.rankstudio.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_equipamento")
public class Equipamento implements Serializable {

	private static final long serialVersionUID = -8073046649171797385L;
	
	@Id @GeneratedValue
	private Long id;	
	
	@Column(nullable=false)
	private String dataAquisicao;

/*	@Column(nullable=false)
	private Long numeroSerie;*/
	
	@Column(nullable = false)
	private String nome;

	@Column
	private String tipoEquipamento;

/*	@OneToOne
	@JoinColumn(name = "tipo_equipamento_id")
	private TipoEquipamento tipoEquipamento;*/

	@OneToOne
	@JoinColumn(name = "foto_equipamento_id")
	private FotoEquipamento fotoEquipamento;

	@ManyToOne
	@JoinColumn(name = "estudio_id")
	private Estudio estudio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(String dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

/*	public Long getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(Long numeroSerie) {
		this.numeroSerie = numeroSerie;
	}*/

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}

	public String getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(String tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}

	public FotoEquipamento getFotoEquipamento() {
		return fotoEquipamento;
	}

	public void setFotoEquipamento(FotoEquipamento fotoEquipamento) {
		this.fotoEquipamento = fotoEquipamento;
	}
}
