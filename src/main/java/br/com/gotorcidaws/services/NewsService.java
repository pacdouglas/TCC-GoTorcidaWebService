package br.com.gotorcidaws.services;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gotorcidaws.dao.DAOManager;
import br.com.gotorcidaws.dao.NewsDAO;
import br.com.gotorcidaws.model.News;
import br.com.gotorcidaws.model.Team;
import br.com.gotorcidaws.utils.ServiceLogger;
import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

@Path("news")
public class NewsService extends GoTorcidaService {

	@GET
	@Path("{userId}/{type}/{targetId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String listNews(@PathParam("userId") String userId, @PathParam("type") String type, @PathParam("targetId") String targetId) {
		ServiceLogger.received(userId + " / " + type + " / " + targetId);
		
		try {
			NewsDAO newsDAO = DAOManager.getNewsDAO();
			List<News> newsList = null;
			
			switch (type) {
				case "all":
					newsList = newsDAO.findLastNews();
				break;
				
				case "team":
					Team team = DAOManager.getTeamDAO().findByID(Integer.parseInt(targetId));
					newsList = newsDAO.findNewsByTeam(team.getId());
				break;
				
				case "event":
					newsList = newsDAO.findNewsByEvent(Integer.parseInt(targetId));
				break;
			}
			
			newsList = updateNewsDate(newsList);
			
			JSONArray newsArray = new JSONArray();
			for (int i = 0; i < newsList.size(); i++) {
				newsArray.put(new JSONObject(newsList.get(i)));
			}
		
			message.setResponse(200, "Ok.");
			message.addData("newsList", newsArray);
		} catch (Exception ex) {
			message.setResponse(500, "Erro interno da aplicação");
			ex.printStackTrace();
		}
		
		ServiceLogger.sent(message.toJSON());
		return message.toJSON();
	}
	
	private List<News> updateNewsDate(List<News> news) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		for (int i = 0; i < news.size(); i++) {
			News newss = news.get(i);
			newss.setFormatedRegistrationDate(dateFormat.format(newss.getDate().getTime()));
		}
		
		return news;
	}
}
