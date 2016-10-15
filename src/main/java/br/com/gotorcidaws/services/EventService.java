package br.com.gotorcidaws.services;

import java.text.SimpleDateFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.EventDAO;
import br.com.gotorcidaws.model.Event;
import br.com.gotorcidaws.utils.JSONConverter;

@Path("event")
public class EventService extends GoTorcidaService {

	@GET
	@Path("{userId}/{eventId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findEvent(@PathParam("userId") String userId, @PathParam("eventId") String eventId) {
		
		EventDAO eventDAO = DAOManager.getEventDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Event event = eventDAO.findById(Integer.parseInt(eventId));
			event.setFormatedRegistrationDate(dateFormat.format(event.getDate().getTime()));
			message.setResponse(200, "Ok.");
			message.addData("event", JSONConverter.toJSON(event));
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}
		
		return message.toJSON();
	}
	
	@GET
	@Path("{teamID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findEventsByTeam(@PathParam("teamID") String teamID) {
		return message.toJSON();
	}
	
	@GET
	@Path("{latitude}/{longitude}/{huebr}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findNearbyEvents(@PathParam("latitude") String latitude, @PathParam("longitude") String longitude, @PathParam("huebr") String huebr){
		
		/*EventDAO eventDAO = DAOManager.getEventDAO();
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
		}*/
		
		return message.toJSON();
	}
	
}
