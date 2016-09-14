package br.com.gotorcidaws.dao;

import br.com.gotorcidaws.model.Sport;

public class SportDAO extends GenericDAO<Sport> {

	public SportDAO() {
		super();
	}

	public void save(Sport sport) {
		save(sport);
	}

	public void update(Sport sport) {
		update(sport);
	}

	public void delete(int id) {
		Sport s = findById(id);
		delete(s);
	}

	public Sport findByName(String name) {
		return super.findByUsername(name);
	}

	public Sport findByID(int id) {
		return super.findById(id);
	}
	
}
