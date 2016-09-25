package br.com.gotorcidaws.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.gotorcidaws.model.Athlete;
import br.com.gotorcidaws.model.Team;

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

	public Athlete findByID(int id) {
		return super.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<Athlete> findByTeam(Team team) {
		Criteria criteria = getSession().createCriteria(Athlete.class);
		criteria.createAlias("teams", "teamsAlias");
		criteria.add(Restrictions.eq("teamsAlias.id", team.getId()));
		return criteria.list();
	}

}
