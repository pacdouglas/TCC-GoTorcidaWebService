package br.com.gotorcidaws.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("login")
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
		return message.toJSON();
	}
	
}
