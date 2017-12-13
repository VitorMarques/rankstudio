/**
 * 
 */
package br.com.tcc.rankstudio.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_agenda")
public class Agenda implements Serializable {

	private static final long serialVersionUID = -8073046649171797385L;
	
	@Id @GeneratedValue
	private Long id;	
	
	@Column(nullable=false)
	private String data;

	@Column(nullable=false)
	private String horario;

	@Column(nullable=false)
	private String sala;

	@Column(nullable = false)
	private Boolean disponivel = Boolean.TRUE;

	@ManyToOne
	@JoinColumn(name = "estudio_id")
	private Estudio estudio;

	@Column(name = "estudio_id", insertable = false, updatable = false)
	private Long estudioId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}


	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Long getEstudioId() {
		return estudioId;
	}

	public void setEstudioId(Long estudioId) {
		this.estudioId = estudioId;
	}
}
