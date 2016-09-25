package br.com.gotorcidaws.dao;

public class DAOManager {

	public static LeagueDAO getLeagueDAO(){
		return new LeagueDAO();
	}
	
	public static SportDAO getSportDAO(){
		return new SportDAO();
	}

	public static UserDAO getUserDAO() {
		return new UserDAO();
	}

	public static TeamDAO getTeamDAO() {
		return new TeamDAO();
	}

	public static AthleteDAO getAthleteDAO() {
		return new AthleteDAO();
	}
	
	public static EventDAO getEventDAO() {
		return new EventDAO();
	}
}
