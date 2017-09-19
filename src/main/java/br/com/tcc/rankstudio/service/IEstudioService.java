package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Empresa;
import br.com.tcc.rankstudio.model.Estudio;

import java.util.List;

/**
 * Interface que define os contratos que devem ser implementados pelos servicos dos usuarios
 * 
 * @author Vitor Marques
 * 
 */
public interface IEstudioService {

	void save(Estudio estudio);
	Estudio buscaPorId(Long id);
	List<Estudio> listaTodos();
	
}
