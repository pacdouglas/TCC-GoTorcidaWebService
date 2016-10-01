package br.com.gotorcidaws.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.gotorcidaws.model.Sport;
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
	
	@SuppressWarnings("unchecked")
	public List<Team> findBySport(Sport sport) {
		return getSession().createCriteria(Team.class).add(Restrictions.eq("sport.id", sport.getId())).list();
	}
}
