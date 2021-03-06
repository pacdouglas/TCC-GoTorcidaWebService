package br.com.gotorcidaws.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.EventDAO;
import br.com.gotorcidaws.dao.EventResultDAO;
import br.com.gotorcidaws.dao.TeamDAO;
import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.Event;
import br.com.gotorcidaws.model.EventResult;
import br.com.gotorcidaws.utils.CollectionUtils;
import br.com.gotorcidaws.utils.JSONConverter;
import br.com.gotorcidaws.utils.ServiceLogger;
import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("event")
public class EventService extends GoTorcidaService {

	@GET
	@Path("find/{eventId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findEvent(@PathParam("eventId") String eventId) {
		ServiceLogger.received("Event" + eventId);

		EventDAO eventDAO = DAOManager.getEventDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		EventResultDAO eventResultDAO = DAOManager.getEventResultDAO();

		try {
			Event event = eventDAO.findById(Integer.parseInt(eventId));
			EventResult eventResult = eventResultDAO.findByEventID(event);
			event.setFormatedEventDate(dateFormat.format(event.getDate().getTime()));
			message.setResponse(200, "Ok.");
			message.addData("event", JSONConverter.toJSON(Event.class, event));
			message.addData("eventResult", JSONConverter.toJSON(EventResult.class, eventResult));
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplica��o");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}

	@GET
	@Path("listBySport/{userID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listEventsBySport(@PathParam("userID") String userID) {
		ServiceLogger.received("User ID: " + userID);
		EventDAO eventDAO = DAOManager.getEventDAO();

		try {
			List<Event> events = eventDAO.listEventsBySport(userID);
			events = updateEventsDate(events);
			events = setEventsResult(events);

			JSONArray eventsArray = new JSONArray();
			for (int i = 0; i < events.size(); i++) {
				eventsArray.put(new JSONObject(JSONConverter.toJSON(Event.class, events.get(i))));
			}

			message.setResponse(200, "Ok.");
			message.addData("eventsList", eventsArray);
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplica��o");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}
	
	@GET
	@Path("listByUser/{userID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listEventsByUser(@PathParam("userID") String userID) {
		ServiceLogger.received("User ID: " + userID);
		EventDAO eventDAO = DAOManager.getEventDAO();

		try {
			List<Event> events = eventDAO.listEventsByUserTeams(userID);
			events = updateEventsDate(events);
			events = setEventsResult(events);

			JSONArray eventsArray = new JSONArray();
			for (int i = 0; i < events.size(); i++) {
				eventsArray.put(new JSONObject(JSONConverter.toJSON(Event.class, events.get(i))));
			}

			message.setResponse(200, "Ok.");
			message.addData("eventsList", eventsArray);
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplica��o");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}

	@GET
	@Path("listByTeam/{teamID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listEventsByTeam(@PathParam("teamID") String teamID) {
		ServiceLogger.received("Team ID: " + teamID);
		EventDAO eventDAO = DAOManager.getEventDAO();

		try {
			List<Event> events = eventDAO.listEventsByTeam(teamID);
			events = updateEventsDate(events);
			events = setEventsResult(events);

			JSONArray eventsArray = new JSONArray();
			for (int i = 0; i < events.size(); i++) {
				eventsArray.put(new JSONObject(JSONConverter.toJSON(Event.class, events.get(i))));
			}

			message.setResponse(200, "Ok.");
			message.addData("eventsList", eventsArray);
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplica��o");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}

	@GET
	@Path("{latitude}/{longitude}/{huebr}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findNearbyEvents(@PathParam("latitude") String latitude, @PathParam("longitude") String longitude,
			@PathParam("huebr") String huebr) {
		try {
			EventDAO eventDAO = DAOManager.getEventDAO();
			List<Event> eventsList = eventDAO.listByLocation(Double.parseDouble(latitude),
					Double.parseDouble(longitude));
			JSONArray eventsArray = CollectionUtils.fromListToJSONArray(eventsList);
			message.addData("events", eventsArray);
			message.setResponse(200, "Ok.");
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplica��o");
			System.out.println(ex.getMessage());
		}

		return message.toJSON();
	}

	private List<Event> setEventsResult(List<Event> events) {
		EventResultDAO dao = DAOManager.getEventResultDAO();

		for (int i = 0; i < events.size(); i++) {
			EventResult result = dao.findByEventID(events.get(i));

			if (result != null) {
				events.get(i).setResult(result);
			}
		}

		return events;
	}

	private List<Event> updateEventsDate(List<Event> events) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);
			event.setFormatedEventDate(dateFormat.format(event.getDate().getTime()));
		}

		return events;
	}

	@POST
	@Path("save/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(String content) {
		EventDAO eventDAO = DAOManager.getEventDAO();
		EventResultDAO eventResultDAO = DAOManager.getEventResultDAO();
		TeamDAO teamDAO = DAOManager.getTeamDAO();
		UserDAO userDAO = DAOManager.getUserDAO();
		
		ServiceLogger.received(content);

		JSONObject eventJSON = new JSONObject(content);
		Event event = new Event();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(dateFormat.parse(eventJSON.getString("date")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		event.setDate(calendar);
		event.setTime(eventJSON.getString("time"));
		event.setDescription(eventJSON.getString("description"));
		event.setEventOwner(userDAO.findById(Integer.parseInt(eventJSON.getString("eventOwner"))));
		event.setFirstTeam(teamDAO.findByID(Integer.parseInt(eventJSON.getString("firstTeam"))));
		event.setSecondTeam(teamDAO.findByID(Integer.parseInt(eventJSON.getString("secondTeam"))));
		event.setLatitude(Double.parseDouble(eventJSON.getString("latitude")));
		event.setLongitude(Double.parseDouble(eventJSON.getString("longitude")));
		event.setLocation(eventJSON.getString("location"));
		event.setName(eventJSON.getString("name"));
		event.setSport(event.getFirstTeam().getSport());
		event.setCosts(eventJSON.getString("costs"));
		
		EventResult eventResult = new EventResult();
		eventResult.setEvent(event);
		eventResult.setFirstTeamScore(-1.0);
		eventResult.setSecondTeamScore(-1.0);
		eventResult.setWinner(null);
		
		try {
			eventDAO.save(event);
			eventResultDAO.save(eventResult);
			message.setResponse(200, "Evento criado com sucesso!");
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplica��o.");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("postResult/{eventId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response postResult(@PathParam("eventId") String eventId, String content) {
		ServiceLogger.received(content);
		
		EventDAO eventDAO = DAOManager.getEventDAO();
		EventResultDAO eventResultDAO = DAOManager.getEventResultDAO();

		JSONObject eventResultJSON = new JSONObject(content);
		Event event = eventDAO.findByID(Integer.parseInt(eventId));
		
		EventResult eventResult = eventResultDAO.findByEventID(event);
		eventResult.setFirstTeamScore(Double.parseDouble(eventResultJSON.getString("resultFirstTeam")));
		eventResult.setSecondTeamScore(Double.parseDouble(eventResultJSON.getString("resultSecondTeam")));
		eventResult.setWinner(null);
		
		if (eventResult.getFirstTeamScore() != eventResult.getSecondTeamScore()) {
			eventResult.setWinner(eventResult.getFirstTeamScore() > eventResult.getSecondTeamScore() ? event.getFirstTeam() : event.getSecondTeam());
		} 
		
		try {
			eventResultDAO.update(eventResult);
			message.setResponse(200, "Resultado do evento atribu�do com sucesso!");
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplica��o.");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("update/{eventId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("eventId") String eventId, String content) {
		EventDAO eventDAO = DAOManager.getEventDAO();
		ServiceLogger.received(content);

		Event temporaryEvent = JSONConverter.toInstanceOf(Event.class, content);
		Event event = eventDAO.findByID(Integer.parseInt(eventId));
		event.setCosts(temporaryEvent.getCosts());
		event.setDate(temporaryEvent.getDate());
		event.setDescription(temporaryEvent.getDescription());
		event.setFirstTeam(temporaryEvent.getFirstTeam());
		event.setLatitude(temporaryEvent.getLatitude());
		event.setLocation(temporaryEvent.getLocation());
		event.setLongitude(temporaryEvent.getLongitude());
		event.setName(temporaryEvent.getName());
		event.setSecondTeam(temporaryEvent.getSecondTeam());
		event.setSport(temporaryEvent.getSport());
		event.setTime(temporaryEvent.getTime());
		
		try {
			eventDAO.save(event);
			message.setResponse(200, "Evento alterado com sucesso!");
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplica��o.");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("delete/{eventId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("eventId") String eventId, String content) {
		EventDAO eventDAO = DAOManager.getEventDAO();
		EventResultDAO eventResultDAO = DAOManager.getEventResultDAO();
		
		ServiceLogger.received(content);

		Event event = eventDAO.findByID(Integer.parseInt(eventId));
		EventResult eventResult = eventResultDAO.findByEventID(event);
		try {
			eventResultDAO.delete(eventResult.getId());
			eventDAO.delete(Integer.parseInt(eventId));
			message.setResponse(200, "Evento exclu�do com sucesso!");
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplica��o.");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
	}
	
	

}
