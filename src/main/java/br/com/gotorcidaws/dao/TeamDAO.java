package br.com.gotorcidaws.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import br.com.gotorcidaws.model.Sport;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.model.User;

public class TeamDAO extends GenericDAO<Team> {

	public TeamDAO() {
		super();
	}

	public void save(Team team) {
		save(team);
	}

	public void update(Team team) {
		super.update(team);
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
	
	@SuppressWarnings("unchecked")
	public List<Team> findByUser(User user){
		Criteria criteria = getSession().createCriteria(Team.class);
		criteria.createAlias("users", "usersAlias");
		criteria.add(Restrictions.eq("usersAlias.id", user.getId()));
		return criteria.list();
	}
}
