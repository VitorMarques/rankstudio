package br.com.tcc.rankstudio.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * DAO abstrato que implementa as operacoes basicas de persistencia
 * 
 * @author Vitor Marques
 *
 */
public abstract class AbstractDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void persist(Object entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}
	
	public Object findById(Object entity, Long id) {
		return getSession().load(entity.getClass(), id);
	}

/*	public List<?> listaTodos(Object entity) {
		return getSession().createCriteria(entity.getClass()).list();
	}*/

	public List<?> listaTodos(Object entity) {
		return getSession().createCriteria(entity.getClass()).list();
	}
	
}
