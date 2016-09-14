package br.com.gotorcidaws.dao;

import br.com.gotorcidaws.model.Athlete;

public class AthleteDAO extends GenericDAO<Athlete> {

	public AthleteDAO() {
		super();
	}

	public void save(Athlete Athlete) {
		save(Athlete);
	}

	public void update(Athlete Athlete) {
		update(Athlete);
	}

	public void delete(int id) {
		Athlete a = findById(id);
		delete(a);
	}

	public Athlete findByName(String name) {
		return super.findByUsername(name);
	}

	public Athlete findByID(int id) {
		return super.findById(id);
	}

}
