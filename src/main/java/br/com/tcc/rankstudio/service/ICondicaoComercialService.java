package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.CondicaoComercial;

public interface ICondicaoComercialService {

	void save(CondicaoComercial condicaoComercial);
	CondicaoComercial buscaPorId(Long id);
	
}
