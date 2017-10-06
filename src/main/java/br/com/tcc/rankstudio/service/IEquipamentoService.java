package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Equipamento;
import br.com.tcc.rankstudio.model.FotoEquipamento;
import br.com.tcc.rankstudio.model.TipoEquipamento;

import java.util.List;

/**
 * Interface que define os contratos que devem ser implementados pelos servicos dos usuarios
 * 
 * @author Vitor Marques
 * 
 */
public interface IEquipamentoService {

	void save(Equipamento equipamento);
	Equipamento buscaPorId(Long id);
	List<TipoEquipamento> listaTiposEquipamento();
    void delete(Equipamento equipamento);
    FotoEquipamento saveFotoEquipamento(String fileName);
}
