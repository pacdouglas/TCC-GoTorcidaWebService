package br.com.gotorcidaws.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.gotorcidaws.model.Athlete;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.model.TeamAthlete;

public class AthleteDAO extends GenericDAO<Athlete> {

	public AthleteDAO() {
		super();
	}

	public void save(Athlete Athlete) {
		super.save(Athlete);
	}

	public void update(Athlete Athlete) {
		super.update(Athlete);
	}

	public void delete(int id) {
		Athlete a = findById(id);
		super.delete(a);
	}

	public Athlete findByID(int id) {
		return super.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<Athlete> findByTeam(Team team) {
		Criteria criteria = getSession().createCriteria(Athlete.class);
		criteria.createAlias("teamAthletes", "teamsAlias");
		criteria.add(Restrictions.eq("teamsAlias.team.id", team.getId()));
		criteria.addOrder(Order.asc("name"));
		List<Athlete> athletes = criteria.list();
		getSession().disconnect();
		return athletes;
	}

	@SuppressWarnings("unchecked")
	public List<Athlete> findAvailableForTeam(Team team) {
		Criteria criteria = getSession().createCriteria(Athlete.class);
		criteria.add(Restrictions.eq("sport.id", team.getSport().getId()));
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TeamAthlete.class);
		detachedCriteria.add(Restrictions.eq("team.id", team.getId()));
		
		criteria.add(Subqueries.propertyNotIn("id", detachedCriteria.setProjection(Property.forName("athlete.id"))));
		criteria.addOrder(Order.asc("name"));
		
		List<Athlete> athletes = criteria.list();
		getSession().disconnect();
		return athletes;
	}
}
