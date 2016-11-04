package br.com.gotorcidaws.services;

import java.text.SimpleDateFormat;
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

		try {
			Event event = eventDAO.findById(Integer.parseInt(eventId));
			event.setFormatedRegistrationDate(dateFormat.format(event.getDate().getTime()));
			message.setResponse(200, "Ok.");
			message.addData("event", new JSONObject(event));
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
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
				eventsArray.put(new JSONObject(events.get(i)));
			}

			message.setResponse(200, "Ok.");
			message.addData("eventsList", eventsArray);
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
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
				eventsArray.put(new JSONObject(events.get(i)));
			}

			message.setResponse(200, "Ok.");
			message.addData("eventsList", eventsArray);
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
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
			message.setResponse(500, "Erro interno da aplicação");
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
			event.setFormatedRegistrationDate(dateFormat.format(event.getDate().getTime()));
		}

		return events;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(String content) {
		EventDAO eventDAO = DAOManager.getEventDAO();
		ServiceLogger.received(content);

		Event event = JSONConverter.toInstanceOf(Event.class, content);

		try {
			eventDAO.save(event);
			message.setResponse(200, "Evento criado com sucesso!");
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação.");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
	}

}
