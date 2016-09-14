package br.com.gotorcidaws.services;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import br.com.gotorcida.fw.Message;
import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.LeagueDAO;
import br.com.gotorcidaws.dao.SportDAO;
import br.com.gotorcidaws.model.League;
import br.com.gotorcidaws.model.Sport;

@Path("/league")
public class LeagueService {
	
	@GET
	@Path("{selectedSports}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listLeague(@PathParam("selectedSports") String selectedSports) throws Exception {
		
		/*JsonArray sports = new Gson().fromJson(selectedSports, JsonArray.class);
		for (int i = 0; i < sports.size(); i++) {
		}
		 */
		
		Message message = new Message();
		SportDAO sportDAO = DAOManager.getSportDAO();
		Sport sport = sportDAO.findById(Integer.parseInt(selectedSports));
		
		if (sport != null) {
			LeagueDAO leagueDAO = DAOManager.getLeagueDAO();
			List<League> leagues = leagueDAO.findBySport(sport);
					
			JsonArray arrayLeagues = new JsonArray();

			for (int i = 0; i < leagues.size(); i++) {
				arrayLeagues.add(new Gson().fromJson(leagues.get(i).toJSON(), JsonElement.class));
			}
			
			message.addSystem("code", "200");
			message.addSystem("message", "OK.");
			message.addData("leagues", arrayLeagues.toString());
			
		} else {
			message.addSystem("code", "500");
			message.addSystem("message", "Esporte inválido.");
		}
		
		return message.toJSON();	
	}
}
