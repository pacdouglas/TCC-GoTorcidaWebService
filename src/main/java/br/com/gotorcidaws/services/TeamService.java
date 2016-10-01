package br.com.gotorcidaws.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.SportDAO;
import br.com.gotorcidaws.dao.TeamDAO;
import br.com.gotorcidaws.model.Sport;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.utils.JSONConverter;
import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("/team")
public class TeamService extends GoTorcidaService {

	@GET
	@Path("{userID}/{selectedSports}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listTeams(@PathParam("userId") String userId, @PathParam("selectedSports") String selectedSports) throws Exception {
		TeamDAO teamDAO = DAOManager.getTeamDAO();
		JSONArray teamsArray = new JSONArray();
		
			SportDAO sportDAO = DAOManager.getSportDAO();
			
			try {
				JSONArray sportsArray = new JSONArray(selectedSports);
				
				for (int i = 0; i < sportsArray.length(); i++) {
					Sport sport = sportDAO.findById(sportsArray.getInt(i));
					
					if (sport != null) {
						JSONArray teamsFromSport = new JSONArray();
						List<Team> teamsList = teamDAO.findBySport(sport);
						
						for (int j = 0; j < teamsList.size(); j++) {
							System.out.println(teamsList.get(j).toString());
							teamsFromSport.put(new JSONObject(teamsList.get(j)));
						}
						
						teamsArray.put(teamsFromSport);
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
