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
import br.com.gotorcidaws.dao.SportDAO;
import br.com.gotorcidaws.model.Sport;

@Path("/sport")
public class SportService {

	private final SportDAO sportDAO = new SportDAO();
	
	//
	//
	// Classe responsável pelos serviços pertinentes aos Esportes
	// 
	//
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String listSports() {
		Message message = new Message();
		message.addSystem("code", "200");
		message.addSystem("message", "OK.");
		
		List<Sport> sports;
		
		try {
			sports = sportDAO.findAll();
			
			JsonArray arraySports = new JsonArray();
			
			for (int i = 0; i < sports.size(); i++) {
				arraySports.add(new Gson().fromJson(sports.get(i).toJSON(), JsonElement.class));
			}
			
			message.addData("sports", arraySports.toString());	
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			message.addSystem("500", "Erro interno da aplicação");
		}
		
		return message.toJSON();
	}
}
