package br.com.gotorcidaws.dao;

public class DAOManager {

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
	
	public static EventDAO getMatchDAO() {
		return new EventDAO();
	}
}
