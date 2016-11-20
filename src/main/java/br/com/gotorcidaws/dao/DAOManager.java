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
	
	public static EventDAO getEventDAO() {
		return new EventDAO();
	}
	
	public static NewsDAO getNewsDAO() {
		return new NewsDAO();
	}
	
	public static EventResultDAO getEventResultDAO() {
		return new EventResultDAO();
	}

	public static PositionDAO getPositionDAO() {
		return new PositionDAO();
	}
	
	public static TeamAthleteDAO getTeamAthleteDAO() {
		return new TeamAthleteDAO();
	}
}
