package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.model.Agendamento;

import java.util.List;

public interface IAgendaService {

	void save(Agenda agenda);
	Agenda buscaPorId(Long id);
    void delete(Agenda agenda);
	List<Agenda> findByEstudioId(Long estudioId);
    List<Agenda> findByNomeSala(String nomeSala, Long estudioId);
    Agenda findByAgendamento(Agendamento agendamento);
    Agenda findByNomeSalaEHorario(String sala, String horario, Long estudioId);
}
