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
	private String dataInicio;

	@Column(nullable=false)
	private String dataFim;

	@Column(nullable=false)
	private String sala;

	@ManyToOne
	@JoinColumn(name = "estudio_id")
	private Estudio estudio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
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


}
