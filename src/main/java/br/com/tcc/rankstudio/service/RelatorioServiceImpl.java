package br.com.tcc.rankstudio.service;

import br.com.tcc.rankstudio.dao.RelatorioDao;
import br.com.tcc.rankstudio.model.Agenda;
import br.com.tcc.rankstudio.model.Equipamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vitor Marques
 *
 */
@Service("relatorioService")
@Transactional
public class RelatorioServiceImpl implements IRelatorioService {

	@Autowired
	private RelatorioDao relatorioDao;

}
