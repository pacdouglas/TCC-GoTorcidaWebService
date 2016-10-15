package br.com.gotorcidaws.services;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcidaws.dao.AthleteDAO;
import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.TeamDAO;
import br.com.gotorcidaws.model.Athlete;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.utils.JSONConverter;
import br.com.gotorcidaws.utils.ServiceLogger;
import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("/athlete")
public class AthleteService extends GoTorcidaService {

	@GET
	@Path("{userID}/{teamId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listAthletes(@PathParam("userID") String userID, @PathParam("teamId") String teamId) throws Exception {

		TeamDAO teamDAO = DAOManager.getTeamDAO();
		AthleteDAO athleteDAO = DAOManager.getAthleteDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		JSONArray athletesArray = new JSONArray();

		try {
			Team team = teamDAO.findById(Integer.parseInt(teamId));
			List<Athlete> athletesList = athleteDAO.findByTeam(team);
					
			for (int j = 0; j < athletesList.size(); j++) {
				Athlete athlete = athletesList.get(j);
				athlete.setFormatedRegistrationDate(dateFormat.format(athlete.getRegistrationDate().getTime()));
				athletesArray.put(new JSONObject(athlete));
			}

			message.setResponse(200, "Ok.");
			message.addData("athletes", athletesArray);
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}
	
	@GET
	@Path("{athleteID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findAthlete(@PathParam("athleteID") String athleteID) throws Exception {
		AthleteDAO athleteDAO = DAOManager.getAthleteDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Athlete athlete = athleteDAO.findById(Integer.parseInt(athleteID));
			athlete.setFormatedRegistrationDate(dateFormat.format(athlete.getRegistrationDate().getTime()));
			message.setResponse(200, "Ok.");
			message.addData("athlete", JSONConverter.toJSON(athlete));
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}

		return message.toJSON();
	}
}
