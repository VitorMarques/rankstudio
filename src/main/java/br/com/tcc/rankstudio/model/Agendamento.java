/**
 *
 */
package br.com.tcc.rankstudio.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_agendamento")
public class Agendamento implements Serializable {

	private static final long serialVersionUID = -8073046649171797385L;

	@Id @GeneratedValue
	private Long id;

	@Column
	private String dataAgendamento;

	@Column
	private String horarioAgendamento;

	@Column
	private String periodoAgendamento;

	@Column
	private String salaAgendamento;

	@Column
	private Double valorAgendamento;

	@Column
	private Long usuarioId;

	@Column
	private Long estudioId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getHorarioAgendamento() {
		return horarioAgendamento;
	}

	public void setHorarioAgendamento(String horarioAgendamento) {
		this.horarioAgendamento = horarioAgendamento;
	}

	public String getPeriodoAgendamento() {
		return periodoAgendamento;
	}

	public void setPeriodoAgendamento(String periodoAgendamento) {
		this.periodoAgendamento = periodoAgendamento;
	}

	public String getSalaAgendamento() {
		return salaAgendamento;
	}

	public void setSalaAgendamento(String salaAgendamento) {
		this.salaAgendamento = salaAgendamento;
	}

	public Double getValorAgendamento() {
		return valorAgendamento;
	}

	public void setValorAgendamento(Double valorAgendamento) {
		this.valorAgendamento = valorAgendamento;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getEstudioId() {
		return estudioId;
	}

	public void setEstudioId(Long estudioId) {
		this.estudioId = estudioId;
	}

}
