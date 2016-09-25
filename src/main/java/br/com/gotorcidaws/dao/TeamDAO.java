package br.com.gotorcidaws.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.gotorcidaws.model.Event;
import br.com.gotorcidaws.model.League;
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
	public List<Team> findByLeague(League league) {
		Criteria crit = getSession().createCriteria(Team.class);
		crit.createAlias("leagues", "leaguesAlias");
		crit.add(Restrictions.eq("leaguesAlias.id", league.getId()));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> findByEvent(Event event) {
		Criteria crit = getSession().createCriteria(Team.class);
		crit.createAlias("events", "eventsAlias");
		crit.add(Restrictions.eq("eventsAlias.id", event.getId()));
		return crit.list();
	}
}
