package br.com.gotorcidaws.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.PositionDAO;
import br.com.gotorcidaws.dao.TeamDAO;
import br.com.gotorcidaws.model.Position;
import br.com.gotorcidaws.model.Sport;
import br.com.gotorcidaws.utils.JSONConverter;
import br.com.gotorcidaws.utils.ServiceLogger;
import br.com.gotorcidaws.utils.json.JSONArray;

@Path("/position")
public class PositionService extends GoTorcidaService {

	@GET
	@Path("listBySport/{teamId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listPositionBySport(@PathParam("teamId") String sportId) {
		ServiceLogger.received(sportId);
		
		try {
			PositionDAO positionDAO = DAOManager.getPositionDAO();
			TeamDAO teamDAO = DAOManager.getTeamDAO();
			
			Sport sport = teamDAO.findById(Integer.parseInt(sportId)).getSport();
			List<Position> positions = positionDAO.listBySport(sport); 

			JSONArray positionsArray = new JSONArray();
			for (int i = 0; i < positions.size(); i++) {
				positionsArray.put(JSONConverter.toJSON(Position.class, positions.get(i)));
			}
			
			message.setResponse(200,  "Ok.");
			message.addData("positions", positionsArray);
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}
		
		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}
}
