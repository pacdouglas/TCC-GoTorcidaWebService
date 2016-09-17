package br.com.gotorcidaws.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.SportDAO;
import br.com.gotorcidaws.model.Sport;
import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("/sport")
public class SportService extends GoTorcidaService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listSports() {
		
		SportDAO sportDAO = DAOManager.getSportDAO();
		
		List<Sport> sportsList;
		
		JSONArray sportsArray = new JSONArray();
		try {
			sportsList = sportDAO.findAll();
			
			for (int i = 0; i < sportsList.size(); i++) {
				sportsArray.put(new JSONObject(sportsList.get(i)));
			}
			
			message.add("sports", sportsArray);	
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			System.out.println(ex.getMessage());
		}
		
		return message.toJSON();
	}
	
}