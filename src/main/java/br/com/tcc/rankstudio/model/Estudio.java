/**
 * 
 */
package br.com.tcc.rankstudio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tb_estudio")
public class Estudio implements Serializable {

	private static final long serialVersionUID = -8073046649171797385L;
	
	@Id @GeneratedValue
	private Long id;	
	
	@Column(nullable=false, length=40)
	private String nome;

	@Column(columnDefinition = "text")
	private String descricao;

	@Column(nullable=false, length=25)
	private String cnpj;
	
	@Column(nullable = false)
	private String endereco;

	@Column(nullable = false)
	private String bairro;

	@Column(nullable = false)
	private String cidade;

	@Column(precision = 2)
	private Double rank;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	@JsonIgnore
	private Empresa empresa;

	@OneToMany(mappedBy = "estudio")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<Equipamento> equipamentos;

	@OneToMany(mappedBy = "estudio")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<Agenda> agendas;

	@OneToMany(mappedBy = "estudio")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
	private List<CondicaoComercial> condicoesComerciais;

	@OneToMany(mappedBy = "estudio", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
    private List<FotoEstudio> fotosEstudio;

	@OneToMany(mappedBy = "estudio")
    @LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnore
    private List<Avaliacao> avaliacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Double getRank() {
		return rank;
	}

	public void setRank(Double rank) {
		this.rank = rank;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public List<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	public List<CondicaoComercial> getCondicoesComerciais() {
		return condicoesComerciais;
	}

	public void setCondicoesComerciais(List<CondicaoComercial> condicoesComerciais) {
		this.condicoesComerciais = condicoesComerciais;
	}

	public List<FotoEstudio> getFotosEstudio() {
        return fotosEstudio;
    }

    public void setFotosEstudio(List<FotoEstudio> fotosEstudio) {
        this.fotosEstudio = fotosEstudio;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
