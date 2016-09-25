package br.com.gotorcidaws.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.LeagueDAO;
import br.com.gotorcidaws.dao.SportDAO;
import br.com.gotorcidaws.model.League;
import br.com.gotorcidaws.model.Sport;
import br.com.gotorcidaws.utils.JSONConverter;
import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("/league")
public class LeagueService extends GoTorcidaService {

	@GET
	@Path("{userID}/{selectedSports}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listLeague(@PathParam("userId") String userId, @PathParam("selectedSports") String selectedSports) throws Exception {

		SportDAO sportDAO = DAOManager.getSportDAO();
		LeagueDAO leagueDAO = DAOManager.getLeagueDAO();

		JSONArray leaguesArray = new JSONArray();

		try {
			JSONArray sportsArray = new JSONArray(selectedSports);
			
			for (int i = 0; i < sportsArray.length(); i++) {
				Sport sport = sportDAO.findById(sportsArray.getInt(i));

				if (sport != null) {
					JSONArray leaguesFromSport = new JSONArray();
					List<League> leaguesList = leagueDAO.findBySport(sport);

					for (int j = 0; j < leaguesList.size(); j++) {
						leaguesFromSport.put(new JSONObject(leaguesList.get(j)));
					}

					leaguesArray.put(leaguesFromSport);
				}
			}

			message.setResponse(200, "Ok.");
			message.addData("leagues", leaguesArray);
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}

		return message.toJSON();
	}
	
	@GET
	@Path("{leagueID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findLeague(@PathParam("leagueID") String leagueID) throws Exception {
		LeagueDAO leagueDAO = DAOManager.getLeagueDAO();
		
		try {
			League league = leagueDAO.findById(Integer.parseInt(leagueID));
			message.setResponse(200, "Ok.");
			message.addData("league", JSONConverter.toJSON(league));
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}

		return message.toJSON();
	}
}
