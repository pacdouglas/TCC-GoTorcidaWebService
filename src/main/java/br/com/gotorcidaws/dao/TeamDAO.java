package br.com.gotorcidaws.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.gotorcidaws.model.Athlete;
import br.com.gotorcidaws.model.Sport;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.model.TeamAthlete;
import br.com.gotorcidaws.model.User;

public class TeamDAO extends GenericDAO<Team> {

	public TeamDAO() {
		super();
	}

	public void save(Team team) {
		super.save(team);
	}

	public void update(Team team) {
		super.update(team);
	}

	public void delete(int id) {
		Team t = findById(id);
		super.delete(t);
	}

	public Team findByID(int id) {
		return super.findById(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> findBySport(Sport sport) {
		List<Team> teams = getSession().createCriteria(Team.class).add(Restrictions.eq("sport.id", sport.getId())).list();
		getSession().disconnect();
		return teams;
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> findByUser(User user){
		Criteria criteria = getSession().createCriteria(Team.class);
		criteria.createAlias("users", "usersAlias");
		criteria.add(Restrictions.eq("usersAlias.id", user.getId()));
		criteria.addOrder(Order.asc("name"));
		
		List<Team> teams = criteria.list();
		getSession().disconnect();
		return teams;
	}

	@SuppressWarnings("unchecked")
	public List<Athlete> getTeamAthletes(Team team) {
		Criteria criteria = getSession().createCriteria(TeamAthlete.class);
		criteria.createAlias("team", "teamAlias");
		criteria.add(Restrictions.like("teamAlias.id", team.getId()));
		criteria.addOrder(Order.asc("name"));
		List<TeamAthlete> teamAthletes = criteria.list();
		
		List<Athlete> athletes = new ArrayList<Athlete>();
		
		for (int i = 0; i < teamAthletes.size(); i++) {
			TeamAthlete teamAthlete = teamAthletes.get(i);
			Athlete athlete = teamAthlete.getAthlete();
			athlete.setPosition(teamAthlete.getPosition());
			athlete.setNumber(teamAthlete.getNumber());
			athletes.add(athlete);
		}
		
		getSession().disconnect();
		return athletes;
	}
}
