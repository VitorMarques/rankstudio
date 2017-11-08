/**
 *
 */
package br.com.tcc.rankstudio.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name="tb_agendamento")
public class Agendamento implements Serializable {

	private static final long serialVersionUID = -8073046649171797245L;

	@Id @GeneratedValue
	private Long id;

	@Column(name = "tipo_agendamento")
	private String tipoAgendamento;

	@Column(name="data_agendamento")
	@Temporal(TemporalType.DATE)
	private Calendar dataAgendamento;

	@Column(name="horario_agendamento")
	private String horarioAgendamento;

	@Column(name="periodo_agendamento")
	private String periodoAgendamento;

	@Column(name="sala_agendamento")
	private String salaAgendamento;

	@Column(name="valor_agendamento")
	private Double valorAgendamento;

	@Column(name="usuario_id")
	private Long usuarioId;

	@Column(name="estudio_id")
	private Long estudioId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoAgendamento() {
		return tipoAgendamento;
	}

	public void setTipoAgendamento(String tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}

	public Calendar getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Calendar dataAgendamento) {
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
