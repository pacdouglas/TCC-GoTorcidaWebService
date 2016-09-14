package br.com.gotorcidaws.services;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcida.fw.Message;

@Path("/league")
public class LeagueService {
	
	@Path("{selectedSports}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listLeague(@PathParam("selectedSports") String selectedSports) throws Exception {
		
		
		/*JsonArray sports = new Gson().fromJson(selectedSports, JsonArray.class);
		
		for (int i = 0; i < sports.size(); i++) {
			
		}
		


		SportDAO sportDAO = DAOManager.getSportDAO();
		List<Sport> sports = sportDAO.findById(Integer.parseInt(selectedSports));

		JsonArray arraySports = new JsonArray();

		for (int i = 0; i < sports.size(); i++) {
			arraySports.add(new Gson().fromJson(sports.get(i).toJSON(), JsonElement.class));
		}*/

		Message message = new Message();
		message.addSystem("code", "200");
		message.addSystem("message", "OK.");
		message.addData("sports", "MEUCU");
		return message.toJSON();
	}
}
