package br.com.gotorcidaws.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.LeagueDAO;
import br.com.gotorcidaws.dao.TeamDAO;
import br.com.gotorcidaws.model.League;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.utils.JSONConverter;
import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("/team")
public class TeamService extends GoTorcidaService {

	@GET
	@Path("{userID}/{selectedLeagues}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listTeams(@PathParam("userId") String userId, @PathParam("selectedLeagues") String selectedLeagues) throws Exception {
		LeagueDAO leagueDAO = DAOManager.getLeagueDAO();
		TeamDAO teamDAO = DAOManager.getTeamDAO();
		JSONArray teamsArray = new JSONArray();
		
		try {
			JSONArray leaguesArray = new JSONArray(selectedLeagues);
			
			for (int i = 0; i < leaguesArray.length(); i++) {
				System.out.println(leaguesArray.getInt(i));
				
				League league = leagueDAO.findById(leaguesArray.getInt(i));
				
				if (league != null) {
					JSONArray teamsFromLeague = new JSONArray();
					List<Team> teamsList = teamDAO.findByLeague(league);
					
					for (int j = 0; j < teamsList.size(); j++) {
						System.out.println(teamsList.get(j).toString());
						teamsFromLeague.put(new JSONObject(teamsList.get(j)));
					}
					
					teamsArray.put(teamsFromLeague);
				}
			}
			
			message.setResponse(200, "Ok.");
			message.addData("teams", teamsArray);
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}
		
		return message.toJSON();
	}
	
	@GET
	@Path("{teamID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findTeam(@PathParam("teamID") String teamID) throws Exception {
		TeamDAO teamDAO = DAOManager.getTeamDAO();
		
		try {
			Team team = teamDAO.findById(Integer.parseInt(teamID));
			message.setResponse(200, "Ok.");
			message.addData("team", JSONConverter.toJSON(team));
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}

		return message.toJSON();
	}
}
