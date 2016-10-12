package br.com.gotorcidaws.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.SportDAO;
import br.com.gotorcidaws.dao.TeamDAO;
import br.com.gotorcidaws.dao.UserDAO;
import br.com.gotorcidaws.model.Sport;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.model.User;
import br.com.gotorcidaws.utils.ServiceLogger;
import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("/dashboardConfig")
public class DashboardService extends GoTorcidaService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String saveConfiguration(String content) throws Exception {
		ServiceLogger.received(content);
		
		JSONObject postParameters = new JSONObject(content);
		
		String userId = postParameters.getString("userId");
		String sports = postParameters.getString("sports");
		String teams = postParameters.getString("teams");
		
		UserDAO userDAO = DAOManager.getUserDAO();
		User user = userDAO.findById(Integer.parseInt(userId));
		
			if (user != null){
				try {
					if (sports != null && !sports.equals("")){
						SportDAO sportDAO = DAOManager.getSportDAO();
						JSONArray sportsArray = new JSONArray(sports);
						List<Sport> sportsList = new ArrayList<>();
						for (int i = 0; i < sportsArray.length(); i++) {
							sportsList.add(sportDAO.findByID(sportsArray.getInt(i)));
						}
						
						user.setSports(sportsList);
					}

					if (teams != null && !teams.equals("")){
						TeamDAO teamDAO = DAOManager.getTeamDAO();
						JSONArray teamsArray = new JSONArray(teams);
						List<Team> teamsList = new ArrayList<>();
						for (int i = 0; i < teamsArray.length(); i++) {
							teamsList.add(teamDAO.findByID(teamsArray.getInt(i)));
						}
						
						user.setTeams(teamsList);
					}
					
					user.setFirstAccess("N");
					userDAO.update(user);
					message.setResponse(200, "Ok.");
				} catch (Exception ex) {
					message.setResponse(500, "Erro interno da aplicação");
					ex.printStackTrace();
				}
			} else {
				message.setResponse(500, "Erro interno da aplicação");
				message.addData("error", "Objeto usuário está nulo, verifique o userId informado via parâmetro.");
			}
		
		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}
}
