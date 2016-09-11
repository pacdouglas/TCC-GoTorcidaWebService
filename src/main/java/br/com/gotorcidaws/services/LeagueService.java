package br.com.gotorcidaws.services;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import br.com.gotorcida.fw.Message;
import br.com.gotorcidaws.model.Sport;

public class LeagueService {
	
	private final LeagueDAO leagueDAO = new LeagueDAO();

	@Path("{selectedSports}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listLeague(@PathParam("selectedSports") String selectedSports) throws Exception {
		
		
		JsonArray sports = new Gson().fromJson(selectedSports, JsonArray.class);
		
		for (int i = 0; i < sports.size(); i++) {
			
		}
		
		Message message = new Message();
		message.addSystem("code", "200");
		message.addSystem("message", "OK.");

		List<Sport> sports = sportDAO.findAll();

		JsonArray arraySports = new JsonArray();

		for (int i = 0; i < sports.size(); i++) {
			arraySports.add(new Gson().fromJson(sports.get(i).toJSON(), JsonElement.class));
		}

		message.addData("sports", arraySports.toString());
		return message.toJSON();
	}
}
