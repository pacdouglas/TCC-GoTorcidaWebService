package br.com.gotorcidaws.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import br.com.gotorcida.fw.Message;
import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.SportDAO;
import br.com.gotorcidaws.model.Sport;

@Path("/sport")
public class SportService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listSports() {
		
		SportDAO sportDAO = DAOManager.getSportDAO();
		
		Message message = new Message();
		
		List<Sport> sports;
		
		try {
			sports = sportDAO.findAll();
			
			JsonArray arraySports = new JsonArray();
			
			for (int i = 0; i < sports.size(); i++) {
				arraySports.add(new Gson().fromJson(sports.get(i).toJSON(), JsonElement.class));
			}
			
			message.addSystem("code", "200");
			message.addSystem("message", "OK.");
			message.addData("sports", arraySports.toString());	
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			message.addSystem("code", "500");
			message.addSystem("message", "Erro interno da aplicação.");
		}
		
		return message.toJSON();
	}
	
}