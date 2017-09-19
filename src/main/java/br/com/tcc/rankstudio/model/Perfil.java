/**
 * 
 */
package br.com.tcc.rankstudio.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_perfil")
public class Perfil implements Serializable {

	private static final long serialVersionUID = -8073046649173497385L;
	
	@Id @GeneratedValue
	private Long id;	
	
	@Column(nullable=false, length=40, unique=true)
	private String descricao;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
}
