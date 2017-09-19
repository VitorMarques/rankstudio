package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Equipamento;

/**
 * Interface que define os contratos que devem ser implementados pelos servicos dos usuarios
 * 
 * @author Vitor Marques
 * 
 */
public interface IEquipamentoService {

	void save(Equipamento equipamento);
	Equipamento buscaPorId(Long id);
	
}
