package br.com.gotorcidaws.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.LeagueDAO;
import br.com.gotorcidaws.dao.SportDAO;
import br.com.gotorcidaws.dao.TeamDAO;
import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.League;
import br.com.gotorcidaws.model.Sport;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.model.User;
import br.com.gotorcidaws.utils.json.JSONArray;

@Path("/dashboardConfig")
public class DashboardService extends GoTorcidaService {

	@GET
	@Path("{userId}/{sports}/{leagues}/{teams}")
	@Produces(MediaType.APPLICATION_JSON)
	public String saveConfiguration(@PathParam("userId") String userId, @PathParam("sports") String sports,
			@PathParam("leagues") String leagues, @PathParam("teams") String teams) throws Exception {

		UserDAO userDAO = DAOManager.getUserDAO();
		User user = userDAO.findById(Integer.parseInt(userId));
		
			if (user != null){
				try {
					if (sports != null && !sports.equals("")){
						SportDAO sportDAO = DAOManager.getSportDAO();
						JSONArray sportsArray = new JSONArray(sports);
						List<Sport> sportsList = new ArrayList<>();
						for (int i = 0; i < sportsArray.length(); i++) {
							sportsList.add(sportDAO.findByID(sportsArray.getInt(i)));
						}
						
						user.setSports(sportsList);
					}

					if (leagues != null && !leagues.equals("")){
						LeagueDAO leagueDAO = DAOManager.getLeagueDAO();
						JSONArray leaguesArray = new JSONArray(leagues);
						List<League> leaguesList = new ArrayList<>();
						for (int i = 0; i < leaguesArray.length(); i++) {
							leaguesList.add(leagueDAO.findByID(leaguesArray.getInt(i)));
						}
						
						user.setLeagues(leaguesList);
					}
					
					if (teams != null && !teams.equals("")){
						TeamDAO teamDAO = DAOManager.getTeamDAO();
						JSONArray teamsArray = new JSONArray(teams);
						List<Team> teamsList = new ArrayList<>();
						for (int i = 0; i < teamsArray.length(); i++) {
							teamsList.add(teamDAO.findByID(teamsArray.getInt(i)));
						}
						
						user.setTeams(teamsList);
					}
					
					userDAO.update(user);
					
					message.setResponse(200, "Ok.");
				} catch (Exception ex) {
					message.setResponse(500, "Erro interno da aplicação");
					ex.printStackTrace();
				}
			} else {
				message.setResponse(500, "Erro interno da aplicação");
				message.addData("error", "Objeto usuário está nulo, verifique o userId informado via parâmetro.");
			}
		
		return message.toJSON();
	}
}
