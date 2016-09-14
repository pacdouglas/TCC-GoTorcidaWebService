package br.com.gotorcidaws.dao;

import br.com.gotorcidaws.model.Team;

public class TeamDAO extends GenericDAO<Team> {

	public TeamDAO() {
		super();
	}

	public void save(Team team) {
		save(team);
	}

	public void update(Team team) {
		update(team);
	}

	public void delete(int id) {
		Team t = findById(id);
		delete(t);
	}

	public Team findByName(String name) {
		return super.findByName(name);
	}

	public Team findByID(int id) {
		return super.findById(id);
	}

}
