package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Empresa;
import br.com.tcc.rankstudio.model.Usuario;

/**
 * Interface que define os contratos que devem ser implementados pelos servicos dos usuarios
 * 
 * @author Vitor Marques
 * 
 */
public interface IEmpresaService {

	void save(Empresa empresa);
	Empresa buscaPorId(Long id, Long representanteId);
	
}
