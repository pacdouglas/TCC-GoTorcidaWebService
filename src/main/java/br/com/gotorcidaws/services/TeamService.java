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
import br.com.gotorcidaws.dao.SportDAO;
import br.com.gotorcidaws.dao.TeamDAO;
import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.Athlete;
import br.com.gotorcidaws.model.Sport;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.model.User;
import br.com.gotorcidaws.utils.JSONConverter;
import br.com.gotorcidaws.utils.ServiceLogger;
import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("/team")
public class TeamService extends GoTorcidaService {

	@GET
	@Path("{userId}/{selectedSports}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listTeams(@PathParam("userId") String userId, @PathParam("selectedSports") String selectedSports) throws Exception {
		TeamDAO teamDAO = DAOManager.getTeamDAO();
		JSONArray teamsArray = new JSONArray();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			if (selectedSports.equals("user")){
				UserDAO userDAO = DAOManager.getUserDAO();
				User user = userDAO.findById(Integer.parseInt(userId));
				
				List<Team> teams = teamDAO.findByUser(user);
				
				for (int i = 0; i < teams.size(); i++) {
					Team team = teams.get(i);
					team.setFormatedRegistrationDate(dateFormat.format(team.getRegistrationDate().getTime())); 
					teamsArray.put(JSONConverter.toJSON(Team.class, team));
				}
			} else {
				SportDAO sportDAO = DAOManager.getSportDAO();
				
					JSONArray sportsArray = new JSONArray(selectedSports);
					
					for (int i = 0; i < sportsArray.length(); i++) {
						Sport sport = sportDAO.findById(sportsArray.getInt(i));
						
						if (sport != null) {
							JSONArray teamsFromSport = new JSONArray();
							List<Team> teamsList = teamDAO.findBySport(sport);
							
							for (int j = 0; j < teamsList.size(); j++) {
								Team team = teamsList.get(j);
								team.setFormatedRegistrationDate(dateFormat.format(team.getRegistrationDate().getTime())); 
								teamsFromSport.put(JSONConverter.toJSON(Team.class, team));
							}
							
							teamsArray.put(teamsFromSport);
						}
					}
			}
					
			message.setResponse(200, "Ok.");
			message.addData("teams", teamsArray);
			
			} catch (Exception ex) {
				message.setResponse(500, "Erro interno da aplicação");
				ex.printStackTrace();
			}
		
		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}
	
	@GET
	@Path("{teamID}")
	@Produces(MediaType.APPLICATION_JSON)	
	public String findTeam(@PathParam("teamID") String teamID) throws Exception {
		TeamDAO teamDAO = DAOManager.getTeamDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Team team = teamDAO.findById(Integer.parseInt(teamID));
			team.setFormatedRegistrationDate(dateFormat.format(team.getRegistrationDate().getTime()));
			message.setResponse(200, "Ok.");
			message.addData("team", JSONConverter.toJSON(Team.class, team));
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}
	
	@POST
	@Path("update/{teamID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("teamID") String teamID, String content) {
		TeamDAO teamDAO = DAOManager.getTeamDAO();
		ServiceLogger.received(content);

		Team team = teamDAO.findByID(Integer.parseInt(teamID));
		JSONObject teamJSON = new JSONObject(content);
		
		team.setName(teamJSON.getString("name"));
		team.setCity(teamJSON.getString("city"));
		team.setEmailAddress(teamJSON.getString("emailAddress"));
		team.setFacebook(teamJSON.getString("facebook"));
		team.setTwitter(teamJSON.getString("twitter"));
		team.setInstagram(teamJSON.getString("instagram"));
			
		try {
			teamDAO.update(team);
			message.setResponse(200, "Dados alterados com sucesso!");
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação.");
			ex.printStackTrace();
		}

		ServiceLogger.sent(message.toJSON());
		return Response.ok(message.toJSON(), MediaType.APPLICATION_JSON).build();
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
				athlete.setFormatedRegistrationDate(dateFormat.format(athlete.getRegistrationDate().getTime()));
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
}
