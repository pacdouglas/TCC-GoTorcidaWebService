package br.com.gotorcidaws.dao;

import org.hibernate.criterion.Restrictions;

import br.com.gotorcidaws.model.User;

public class UserDAO extends GenericDAO<User> {

	public UserDAO() {
		super();
	}

	public void save(User user) {
		super.save(user);
	}

	public void update(User user) {
		super.update(user);
	}

	public void delete(int id) {
		User u = findById(id);
		super.delete(u);
	}

	public User findByEmail(String email) {
		return (User) getSession().createCriteria(User.class).add(Restrictions.eq("emailAddress", email).ignoreCase())
				.uniqueResult();
	}

}
