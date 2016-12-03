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

import br.com.gotorcidaws.dao.AthleteDAO;
import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.TeamAthleteDAO;
import br.com.gotorcidaws.dao.TeamDAO;
import br.com.gotorcidaws.model.Athlete;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.model.TeamAthlete;
import br.com.gotorcidaws.utils.DateUtils;
import br.com.gotorcidaws.utils.FileUtilsGoTorcida;
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
				List<TeamAthlete> teamAthletes = athlete.getTeamAthletes();
				
				for (int i = 0; i < teamAthletes.size(); i++) {
					if (teamAthletes.get(i).getTeam().getId() == Integer.parseInt(teamId)) {
						athlete.setPosition(teamAthletes.get(i).getPosition());
						athlete.setNumber(teamAthletes.get(i).getNumber());
						break;
					}
				}
				
				athlete.setFormatedRegistrationDate(dateFormat.format(athlete.getBirthDate().getTime()));
				athletesArray.put(JSONConverter.toJSON(Athlete.class, athlete));
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
	@Path("findAthleteOnTeam/{athleteId}/{teamId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findAthlete(@PathParam("athleteId") String athleteId, @PathParam("teamId") String teamId) throws Exception {
		AthleteDAO athleteDAO = DAOManager.getAthleteDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Athlete athlete = athleteDAO.findById(Integer.parseInt(athleteId));
			
			List<TeamAthlete> teamAthletes = athlete.getTeamAthletes();
			
			for (int i = 0; i < teamAthletes.size(); i++) {
				if (teamAthletes.get(i).getTeam().getId() == Integer.parseInt(teamId)) {
					athlete.setPosition(teamAthletes.get(i).getPosition());
					athlete.setNumber(teamAthletes.get(i).getNumber());
					athlete.setAge(DateUtils.getAgeFromCalendar(athlete.getBirthDate()));
					break;
				}
			}
			
			athlete.setFormatedRegistrationDate(dateFormat.format(athlete.getBirthDate().getTime()));
			message.setResponse(200, "Ok.");
			message.addData("athlete", JSONConverter.toJSON(Athlete.class, athlete));
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}

		return message.toJSON();
	}
	
	@GET
	@Path("findAvailable/{teamId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findAvailableAthletes(@PathParam("teamId") String teamId) throws Exception {
		ServiceLogger.received(teamId);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		AthleteDAO athleteDAO = DAOManager.getAthleteDAO();
		TeamDAO teamDAO = DAOManager.getTeamDAO();
		Team team = teamDAO.findById(Integer.parseInt(teamId));
		
		JSONArray athletesArray = new JSONArray();
		try {
			List<Athlete> athletesList = athleteDAO.findAvailableForTeam(team);
			
			for (int j = 0; j < athletesList.size(); j++) {
				Athlete athlete = athletesList.get(j);
				athlete.setFormatedRegistrationDate(dateFormat.format(athlete.getBirthDate().getTime()));
				athletesArray.put(JSONConverter.toJSON(Athlete.class, athlete));
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
	
	@POST
	@Path("save/{teamId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@PathParam("teamId") String teamId, String content) {
		AthleteDAO athleteDAO = DAOManager.getAthleteDAO();
		TeamDAO teamDAO = DAOManager.getTeamDAO();
		
		JSONObject postParameters = new JSONObject(content);
		JSONObject athleteData = postParameters.getJSONObject("athleteData");
		
		Athlete athlete = JSONConverter.toInstanceOf(Athlete.class, athleteData.toString());
		athlete.setSport(teamDAO.findById(Integer.parseInt(teamId)).getSport());
		
		if (postParameters.has("image")){
			athlete.setUrlImage(FileUtilsGoTorcida.convertBase64ToImage(postParameters.getString("image"), athlete.getName()));
		}
		
		try {
			athleteDAO.save(athlete);
			message.setResponse(200, "Atleta cadastrado com sucesso!");
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação.");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("update/{teamId}/{athleteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("teamId") String teamId, @PathParam("athleteId") String athleteId, String content) {
		AthleteDAO athleteDAO = DAOManager.getAthleteDAO();
		TeamAthleteDAO teamAthleteDAO = DAOManager.getTeamAthleteDAO();
		
		JSONObject postParameters = new JSONObject(content);
		JSONObject athleteData = postParameters.getJSONObject("athleteData");
		JSONObject teamAthleteData = postParameters.getJSONObject("teamAthleteData");
		
		Athlete temporaryAthlete = JSONConverter.toInstanceOf(Athlete.class, athleteData.toString());
		Athlete athlete = athleteDAO.findById(Integer.parseInt(athleteId));
		athlete.setName(temporaryAthlete.getName());
		athlete.setBirthDate(temporaryAthlete.getBirthDate());
		athlete.setCity(temporaryAthlete.getCity());
		athlete.setEmailAddress(temporaryAthlete.getEmailAddress());
		athlete.setWebsite(temporaryAthlete.getWebsite());
		athlete.setFacebook(temporaryAthlete.getFacebook());
		athlete.setInstagram(temporaryAthlete.getInstagram());
		athlete.setTwitter(temporaryAthlete.getTwitter());
		
		if (postParameters.has("image")){
			athlete.setUrlImage(FileUtilsGoTorcida.convertBase64ToImage(postParameters.getString("image"), athlete.getName()));
		}
		
		TeamAthlete teamAthlete = null;
		for (int i = 0; i < athlete.getTeamAthletes().size(); i++) {
			teamAthlete = athlete.getTeamAthletes().get(i); 
			if (teamAthlete.getTeam().getId() == Integer.parseInt(teamId)){
				teamAthlete.setPosition(teamAthleteData.getString("position"));
				teamAthlete.setNumber(teamAthleteData.getString("number"));
				break;
			}
		}
		
		try {
			athleteDAO.update(athlete);
			teamAthleteDAO.update(teamAthlete);
			message.setResponse(200, "Atleta cadastrado com sucesso!");
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação.");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
	}
	
}
