package br.com.gotorcidaws.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class GenericDAO<T extends Serializable> {

	private final Session session;
	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.session = HibernateUtil.getSession();
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public Session getSession() {
		return session;
	}

	protected void save(T entity) {
		try {
			getSession().getTransaction().begin();
			getSession().save(entity);
			getSession().getTransaction().commit();
		} catch (Throwable t) {
			getSession().getTransaction().rollback();
			t.printStackTrace();
		} finally {
			close();
		}
	}

	protected void update(T entity) {
		try {
			getSession().getTransaction().begin();
			getSession().update(entity);
			getSession().getTransaction().commit();
		} catch (Throwable t) {
			getSession().getTransaction().rollback();
			t.printStackTrace();
		} finally {
			close();
		}
	}

	protected void delete(T entity) {
		try {
			getSession().getTransaction().begin();
			getSession().delete(entity);
			getSession().getTransaction().commit();
		} catch (Throwable t) {
			getSession().getTransaction().rollback();
			t.printStackTrace();
		} finally {
			close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() throws Exception {
		return getSession().createCriteria(persistentClass).list();
	}

	@SuppressWarnings("unchecked")
	public T findByUsername(String username) {
		return (T) getSession().createCriteria(persistentClass).add(Restrictions.eq("username", username).ignoreCase())
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public T findById(int id) {
		return (T) getSession().createCriteria(persistentClass).add(Restrictions.eq("id", id)).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public T findByName(String name) {
		return (T) getSession().createCriteria(persistentClass).add(Restrictions.eq("name", name)).uniqueResult();
	}

	private void close() {
		if (getSession() != null && getSession().isOpen()) {
			getSession().close();
		}
	}
}
