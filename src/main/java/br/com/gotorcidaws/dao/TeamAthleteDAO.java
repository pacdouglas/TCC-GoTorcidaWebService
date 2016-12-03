package br.com.gotorcidaws.dao;

import br.com.gotorcidaws.model.TeamAthlete;

public class TeamAthleteDAO extends GenericDAO<TeamAthlete> {

	public TeamAthleteDAO() {
		super();
	}

	public void save(TeamAthlete teamAthlete) {
		super.save(teamAthlete);
	}

	public void update(TeamAthlete teamAthlete) {
		super.update(teamAthlete);
	}

	public void delete(int id) {
		TeamAthlete teamAthlete = findById(id);
		super.delete(teamAthlete);
	}

}
