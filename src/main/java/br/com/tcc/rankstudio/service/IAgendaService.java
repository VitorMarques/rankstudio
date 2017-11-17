package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Agenda;

public interface IAgendaService {

	void save(Agenda agenda);
	Agenda buscaPorId(Long id);
    void delete(Agenda agenda);
}
