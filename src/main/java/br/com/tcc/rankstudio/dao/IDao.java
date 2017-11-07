package br.com.tcc.rankstudio.dao;

/**
 * Interface que define os contratos para os DAOs da aplicacao
 * 
 * @author Vitor Marques
 *
 */
public interface IDao {

	void persist(Object entity);
	void delete(Object entity);
}
