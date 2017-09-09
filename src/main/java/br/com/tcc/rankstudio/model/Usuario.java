/**
 * 
 */
package br.com.tcc.rankstudio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vitor
 *
 */
@Entity
@Table(name="tb_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -8073046649171797385L;
	
	@Id @GeneratedValue
	private Long id;	
	
	@Column(nullable=false, length=40)
	private String nome;
	
	@Column(nullable=false, unique=true, length=20)
	private String email;
	
	@Column(nullable=false)
	private String senha;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
