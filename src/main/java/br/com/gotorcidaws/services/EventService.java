package br.com.gotorcidaws.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.EventDAO;
import br.com.gotorcidaws.dao.TeamDAO;
import br.com.gotorcidaws.model.Event;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("event")
public class EventService extends GoTorcidaService {

	@GET
	@Path("{teamID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findEventsByTeam(@PathParam("teamID") String teamID) {
		return message.toJSON();
	}
	
	@GET
	@Path("{latitude}/{longitude}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findNearbyEvents(@PathParam("latitude") String latitude, @PathParam("longitude") String longitude){
		
		EventDAO eventDAO = DAOManager.getEventDAO();
		TeamDAO teamDAO = DAOManager.getTeamDAO();
		
		List<Event> eventsList;
		
		JSONArray eventsArray = new JSONArray();
		try {
			eventsList = eventDAO.findAll();
			
			for (int i = 0; i < eventsList.size(); i++) {
				Event event = eventsList.get(i);
				List<Team> participants = teamDAO.findByEvent(event);
				event.setParticipants(participants);
				eventsArray.put(new JSONObject(eventsList.get(i)));
			}
			
			message.addData("events", eventsArray);
			message.setResponse(200, "Ok.");	
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			System.out.println(ex.getMessage());
		}
		
		return message.toJSON();
	}
	
}
