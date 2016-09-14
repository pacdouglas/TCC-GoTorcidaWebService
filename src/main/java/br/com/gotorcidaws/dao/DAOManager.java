package br.com.gotorcidaws.dao;

public class DAOManager {

	public static LeagueDAO getLeagueDAO(){
		return new LeagueDAO();
	}
	
	public static SportDAO getSportDAO(){
		return new SportDAO();
	}
}
