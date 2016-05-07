package br.com.gotorcidaws.dao;

import br.com.gotorcidaws.model.User;

public class UserDAO extends GenericDAO<User> {

	public UserDAO() {
		super();
	}

	public void save(User user) {
		save(user);
	}

	public void update(User user) {
		update(user);
	}

	public void delete(long id) {
		User u = findById(id);
		delete(u);
	}

	public User findByUsername(String username) {
		return super.findByUsername(username);
	}

}
