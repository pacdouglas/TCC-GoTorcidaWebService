package br.com.gotorcidaws.dao;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import br.com.gotorcidaws.model.League;
import br.com.gotorcidaws.model.Sport;

public class LeagueDAO extends GenericDAO<League> {

	public LeagueDAO() {
		super();
	}

	public void save(League league) {
		save(league);
	}

	public void update(League league) {
		update(league);
	}

	public void delete(int id) {
		League s = findById(id);
		delete(s);
	}

	public League findByID(int id) {
		return super.findById(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<League> findBySport(Sport sport) {
		
		
		/*Criteria cr = session.createCriteria(User.class)
                .createCriteria("roles")
                .add(Restrictions.or(Restrictions.eq("roletype", 1), Restrictions.eq("roletype", 2)));*/
		
		return getSession().createCriteria(League.class).add(Restrictions.eq("sport.id", sport.getId())).list();
	}
	
}
