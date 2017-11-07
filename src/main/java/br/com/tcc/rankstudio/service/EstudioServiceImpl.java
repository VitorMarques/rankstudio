package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.dao.EmpresaDao;
import br.com.tcc.rankstudio.dao.EstudioDao;
import br.com.tcc.rankstudio.model.Avaliacao;
import br.com.tcc.rankstudio.model.Empresa;
import br.com.tcc.rankstudio.model.Estudio;
import br.com.tcc.rankstudio.model.FotoEstudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vitor Marques
 *
 */
@Service("estudioService")
@Transactional
public class EstudioServiceImpl implements IEstudioService {

	@Autowired
	private EstudioDao estudioDao;

	@Override
	public void save(Estudio estudio) {
		estudioDao.persist(estudio);
	}

	@Override
	public Estudio buscaPorId(Long id) {
		return (Estudio) estudioDao.findById(id);
	}

	@Override
	public List<Estudio> listaTodos() {
		return (List<Estudio>) estudioDao.listaTodos(Estudio.class);
	}

	@Override
	public FotoEstudio saveFotoEstudio(String fileName, Estudio estudio) {
		FotoEstudio fotoEstudio = new FotoEstudio();
		fotoEstudio.setNomeArquivo(fileName);
		fotoEstudio.setEstudio(estudio);
		fotoEstudio.setId((Long) estudioDao.save(fotoEstudio));
		return fotoEstudio;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteAllFotos(Long idEstudio) {
		estudioDao.deleteAllFotosFromEstudio(idEstudio);
	}

	@Override
	public List<Estudio> listaEstudiosPorProprietario(Long idProprietario) {
		return estudioDao.listaEstudiosPorProprietario(idProprietario);
	}

	@Override
	public void saveAvaliacao(Avaliacao avaliacao) {
		estudioDao.save(avaliacao);
	}

	public void saveAgendamento(Agendamento agendamento) {
		estudioDao.save(agendamento);
	}

	@Override
	public List<Estudio> buscaEstudios(String textoPesquisa) {

		List<Estudio> estudioList = estudioDao.pesquisaEstudios(textoPesquisa);

		return estudioList;
	}

}
