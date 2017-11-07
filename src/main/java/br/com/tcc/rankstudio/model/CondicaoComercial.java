/**
 * 
 */
package br.com.tcc.rankstudio.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_condicao_comercial")
public class CondicaoComercial implements Serializable {

	private static final long serialVersionUID = -8073046649173497385L;
	
	@Id @GeneratedValue
	private Long id;	
	
	@Column(nullable=false)
	private double preco;

	@Column(nullable = false)
	private String tipoPagamento;

	@Column(nullable = false)
	private String tipoCondicao;

	@ManyToOne
	@JoinColumn(name = "estudio_id")
	private Estudio estudio;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getTipoCondicao() {
		return tipoCondicao;
	}

	public void setTipoCondicao(String tipoCondicao) {
		this.tipoCondicao = tipoCondicao;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}
}
