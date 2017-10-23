package br.com.tcc.rankstudio.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
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

	public Object save(Object entity) {
		return getSession().save(entity);
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}

	public List<?> listaTodos(Class classz) {
		return getSession().createCriteria(classz).list();
	}
	
}
