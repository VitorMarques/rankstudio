package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Agenda;

import java.util.List;

public interface IAgendaService {

	void save(Agenda agenda);
	Agenda buscaPorId(Long id);
    void delete(Agenda agenda);
	List<Agenda> findByEstudioId(Long estudioId);
    List<Agenda> findByNomeSala(String nomeSala);
}
