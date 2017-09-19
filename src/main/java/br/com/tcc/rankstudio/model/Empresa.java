/**
 * 
 */
package br.com.tcc.rankstudio.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tb_empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = -8073046649171797385L;
	
	@Id @GeneratedValue
	private Long id;	
	
	@Column(nullable=false, length=40)
	private String nome;

	@Column(nullable=false, length=25)
	private String cnpj;
	
	@Column(nullable = false)
	private String endereco;

	@Column(nullable = false)
	private String bairro;

	@Column(nullable = false)
	private String cidade;

	@OneToOne
	private Usuario representante;

	@OneToMany(mappedBy = "empresa")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Estudio> estudios;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) { this.nome = nome; }
	public String getEndereco() { return endereco; }
	public void setEndereco(String endereco) { this.endereco = endereco; }
	public String getBairro() { return bairro; }
	public void setBairro(String bairro) { this.bairro = bairro; }
	public String getCidade() { return cidade; }
	public void setCidade(String cidade) { this.cidade = cidade; }
	public Usuario getRepresentante() { return representante; }
	public void setRepresentante(Usuario representante) { this.representante = representante; }
	public String getCnpj() { return cnpj; }
	public void setCnpj(String cnpj) { this.cnpj = cnpj; }
	public List<Estudio> getEstudios() { return estudios; }
	public void setEstudios(List<Estudio> estudios) { this.estudios = estudios; }
}
