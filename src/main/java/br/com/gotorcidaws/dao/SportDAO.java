package br.com.gotorcidaws.dao;

import br.com.gotorcidaws.model.Sport;

public class SportDAO extends GenericDAO<Sport> {

	public SportDAO() {
		super();
	}

	public void save(Sport sport) {
		super.save(sport);
	}

	public void update(Sport sport) {
		super.update(sport);
	}

	public void delete(int id) {
		Sport s = findById(id);
		super.delete(s);
	}

	public Sport findByID(int id) {
		return super.findById(id);
	}
	
}
