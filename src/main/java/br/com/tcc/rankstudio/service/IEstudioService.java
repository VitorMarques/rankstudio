package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.model.Avaliacao;
import br.com.tcc.rankstudio.model.Empresa;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.model.FotoEstudio;

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
    FotoEstudio saveFotoEstudio(String fileName, Estudio estudio);
	void deleteAllFotos(Long idEstudio);
	List<Estudio> listaEstudiosPorProprietario(Long idProprietario);
	void saveAvaliacao(Avaliacao avaliacao);
	void saveAgendamento(Agendamento agendamento);
    List<Estudio> buscaEstudios(String textoPesquisa);
}
